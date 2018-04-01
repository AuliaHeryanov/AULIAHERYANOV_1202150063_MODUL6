package com.example.auliaheryanov.auliaheryanov_1202150063_modul6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Aulia Heryanov on 01/04/2018.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ImageViewHolder> {
    private ArrayList<UploadModel> mTimeline;
    private Context mContext;
    private UploadModel mCurrentUpload;

    MenuAdapter(Context context, ArrayList<UploadModel> timeline){
        mTimeline = timeline;
        mContext = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_menu, parent, false);
        return new MenuAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ImageViewHolder holder, int position) {
        mCurrentUpload = mTimeline.get(position);

        holder.mTitle.setText(mCurrentUpload.getmTitle());
        holder.mCaption.setText(mCurrentUpload.getmCaption());
        holder.mEmail.setText(mCurrentUpload.getmEmail());
        Picasso.get()
                .load(mCurrentUpload.getmUrl())
                .fit()

                .into(holder.mImage);

    }

    @Override
    public int getItemCount() {
        return mTimeline.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView mEmail;
        private TextView mTitle;
        private TextView mCaption;
        private ImageView mImage;

        public ImageViewHolder(View itemView) {
            super(itemView);

            mEmail = itemView.findViewById(R.id.email);
            mTitle = itemView.findViewById(R.id.titlePost);
            mCaption = itemView.findViewById(R.id.captionPost);
            mImage = itemView.findViewById(R.id.imageViewUploaded);
        }
    }
}
