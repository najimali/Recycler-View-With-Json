package com.example.nazi.recyclerviewjsonex;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.onItemClickListener {
    public static final String EXTRA_URL ="imageURL";
    public static final String EXTRA_CREATOR="creatorName";
    public static final String EXTRA_LIKES="likeCount";
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ElementItem> mExampleList;
    private RequestQueue mReuestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyler_view);
        mRecyclerView.setHasFixedSize(true);
       // mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        if(getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                   mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else{
                   mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        mExampleList =  new ArrayList<>();
        mReuestQueue = Volley.newRequestQueue(this);
        parseJson();
    }
    private void parseJson(){

        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject hit=jsonArray.getJSONObject(i);
                        String createName=hit.getString("user");
                        String imageUrl= hit.getString("webformatURL");
                        int LikesCount = hit.getInt("likes");
                        mExampleList.add(new ElementItem(imageUrl,createName,LikesCount));

                    }
                    mExampleAdapter = new ExampleAdapter(MainActivity.this,mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                    //attatching adapter to onItemclikclisnter
                    mExampleAdapter.setOnItemClickListener(MainActivity.this);

                }catch (JSONException e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mReuestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,DetailActivity.class);
        ElementItem clickItem = mExampleList.get(position);
        intent.putExtra(EXTRA_URL,clickItem.getmUrl());
        intent.putExtra(EXTRA_CREATOR,clickItem.getmCreatorText());
        intent.putExtra(EXTRA_LIKES,clickItem.getmLikes());
        startActivity(intent);
    }
}
