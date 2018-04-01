package com.example.auliaheryanov.auliaheryanov_1202150063_modul6;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView mImageView;
    private Button mButtonSelect;
    private EditText mEditTextTitle;
    private EditText mEditTextCaption;
    //    private FloatingActionButton mButtonUpload;
    private ProgressBar mProgress;
    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;

    private String uid;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        email = user.getEmail();

        mImageView = (ImageView) findViewById(R.id.imageview);
        mButtonSelect = (Button) findViewById(R.id.btnSelect);
        mEditTextTitle = (EditText) findViewById(R.id.postTitle);
        mEditTextCaption = (EditText) findViewById(R.id.postCaption);
//        mButtonUpload = (FloatingActionButton) findViewById(R.id.fab);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });
    }

    public void chooseFile(View view) {
        openFileChooser();
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(mImageView);
        }
    }

    private void uploadFile(){
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgress.setProgress(0);
                                }
                            }, 500);
                            Toast.makeText(UploadActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
                            UploadModel upload = new UploadModel(
                                    mEditTextTitle.getText().toString().trim(),
                                    mEditTextCaption.getText().toString(),
                                    uid,
                                    taskSnapshot.getDownloadUrl().toString(),
                                    email);
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgress.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}
