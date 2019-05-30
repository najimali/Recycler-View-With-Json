package com.example.nazi.recyclerviewjsonex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.nazi.recyclerviewjsonex.MainActivity.EXTRA_CREATOR;
import static com.example.nazi.recyclerviewjsonex.MainActivity.EXTRA_LIKES;
import static com.example.nazi.recyclerviewjsonex.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        String imageURL = intent.getStringExtra(EXTRA_URL);
        String creatorfinal = intent.getStringExtra(EXTRA_CREATOR);
        int Likes = intent.getIntExtra(EXTRA_LIKES,0);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView nametext = findViewById(R.id.text_view_creator_detail);
        TextView likestext = findViewById(R.id.text_view_like_detail);

        Picasso.with(this).load(imageURL).fit().centerInside().into(imageView);
        nametext.setText(creatorfinal);
        likestext.setText("Likes :"+Likes);

    }

}
