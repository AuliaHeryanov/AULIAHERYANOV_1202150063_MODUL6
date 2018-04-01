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

public class TabProfile extends Fragment {
    private ArrayList<ProfileModel> mUpload;
    private RecyclerView mRecycler;
    private ProfileAdapter mAdapter;

    private DatabaseReference mDatabaseRef;

    public TabProfile(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layoutView = inflater.inflate(R.layout.activity_tab_profile, container, false);

        mUpload = new ArrayList<>();


        int gridColumn = getResources().getInteger(R.integer.grid_column_count);

        mRecycler = (RecyclerView) layoutView.findViewById(R.id.profileRecycler);
        mRecycler.setHasFixedSize(true);

        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), gridColumn));

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUpload.clear();
                for (DataSnapshot post : dataSnapshot.getChildren()){
                    ProfileModel timelineData = post.getValue(ProfileModel.class);

                    String userMail = MainMenu.email;
                    String dataMail = String.valueOf(timelineData.getmEmail());
                    if (dataMail.equals(userMail)){
                        mUpload.add(timelineData);
                    }
                }
                mAdapter = new ProfileAdapter(getContext(), mUpload);
                mRecycler.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return layoutView;


    }
}
