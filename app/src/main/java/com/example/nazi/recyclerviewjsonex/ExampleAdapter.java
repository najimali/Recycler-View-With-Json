package com.example.nazi.recyclerviewjsonex;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nazi on 5/20/2019.
 */

public class ExampleAdapter extends RecyclerView.Adapter</*Adapter class*/ExampleAdapter.ExampleViewHolder/*ViewHolder*/> {

    private Context context;
    private onItemClickListener mListener;
    private ArrayList<ElementItem> elementList;
    //
    public interface onItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;

    }
    //Constructor
    public ExampleAdapter(Context context, ArrayList<ElementItem> elementList) {
        this.context = context;
        this.elementList = elementList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return layout to view holder
        View v = LayoutInflater.from(context).inflate(R.layout.element_item,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        //binding the image ,text view object
        ElementItem currentElement =elementList.get(position);

        String imageUrl = currentElement.getmUrl();
        String creatorName =currentElement.getmCreatorText();
        int likeCount =currentElement.getmLikes();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextLikes.setText("Likes : "+likeCount);
        Picasso.with(context).load(imageUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }


    //create a custiom View Holder class
    public class ExampleViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextLikes;
        //super matching constructor
        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.content_image);
            mTextViewCreator=itemView.findViewById(R.id.content_name);
            mTextLikes=itemView.findViewById(R.id.content_likes);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
