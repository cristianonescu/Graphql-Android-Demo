package com.graphqlclient.busymachines.graphqlclientusingapollo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.graphqlclient.busymachines.graphqlclientusingapollo.PostsList.MyRecyclerViewAdapter;

import javax.annotation.Nonnull;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        getPosts();

    }

    /* list all posts */
    public void getPosts(){

        MyApolloClient.getMyApolloClient().query(
                AllPostsQuery.builder().build()
        ).enqueue(new ApolloCall.Callback<AllPostsQuery.Data>() {
            @Override
            public void onResponse(@Nonnull final Response<AllPostsQuery.Data> response) {
                Log.e(TAG, "onResponse: " + response.data().allPosts().get(0).title());
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new MyRecyclerViewAdapter(MainActivity.this, response.data().allPosts());
                        mRecyclerView.setAdapter(adapter);
                    }
                });
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {

            }
        });
    }

    /* add post */
    public void submitPost(View v){
        EditText newPostTitle = (EditText) findViewById(R.id.new_post_title);
        EditText newPostDescription = (EditText) findViewById(R.id.new_post_description);

        MyApolloClient.getMyApolloClient().mutate(
                NewPostMutation.builder()
                .title(newPostTitle.getText().toString())
                .description(newPostDescription.getText().toString()).build())
        .enqueue(new ApolloCall.Callback<NewPostMutation.Data>(){

            @Override
            public void onResponse(@Nonnull Response<NewPostMutation.Data> response) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Added successfully..", Toast.LENGTH_SHORT).show();
                        getPosts();
                    }
                });
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {

            }
        });

    }

    /* remove post on click */
    public void removePost(String id){
        MyApolloClient.getMyApolloClient().mutate(
                DeletePostMutation.builder()
                        .id(id).build())
                .enqueue(new ApolloCall.Callback<DeletePostMutation.Data>(){

                    @Override
                    public void onResponse(@Nonnull Response<DeletePostMutation.Data> response) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Removed successfully..", Toast.LENGTH_SHORT).show();
                                getPosts();
                            }
                        });
                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {

                    }
                });
    }
}
