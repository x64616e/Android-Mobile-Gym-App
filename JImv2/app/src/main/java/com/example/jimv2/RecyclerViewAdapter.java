package com.example.jimv2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<SongTypes> mData;


    public RecyclerViewAdapter(Context mContext, List<SongTypes> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_playlist, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
          holder.songTypeView.setText(mData.get(position).getTitle());
          holder.songTypeImg.setImageResource(mData.get(position).getThumbnail());
          holder.cardView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  //passing data to the playlist activity from the main activity
                  Intent intent = new Intent(mContext,PlaylistActivity.class);
                  intent.putExtra("Title",mData.get(position).getTitle());
                  intent.putExtra("Description",mData.get(position).getDescription());
                  intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                  intent.putExtra("playlist",position);
                  //start the activity
                  mContext.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView songTypeView;
        ImageView songTypeImg;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            songTypeView = itemView.findViewById(R.id.songTypeView);
            songTypeImg = itemView.findViewById(R.id.songTypeImg);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
