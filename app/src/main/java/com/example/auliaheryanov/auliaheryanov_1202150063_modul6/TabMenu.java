package com.example.auliaheryanov.auliaheryanov_1202150063_modul6;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TabMenu extends Fragment {
    private ArrayList<UploadModel> mUpload;
    private RecyclerView mRecycler;
    private MenuAdapter mAdapter;

    private DatabaseReference mDatabaseRef;


    public TabMenu(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layoutView = inflater.inflate(R.layout.activity_tab_menu, container, false);
        mUpload = new ArrayList<>();


        int gridColumn = getResources().getInteger(R.integer.grid_column_count);

        mRecycler = (RecyclerView) layoutView.findViewById(R.id.timelineRecycler);
        mRecycler.setHasFixedSize(true);

        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), gridColumn));

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot post : dataSnapshot.getChildren()){
                    UploadModel model = post.getValue(UploadModel.class);
                    mUpload.add(model);
                }
                mAdapter = new MenuAdapter(getContext(), mUpload);
                mRecycler.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return layoutView;
    }


}

