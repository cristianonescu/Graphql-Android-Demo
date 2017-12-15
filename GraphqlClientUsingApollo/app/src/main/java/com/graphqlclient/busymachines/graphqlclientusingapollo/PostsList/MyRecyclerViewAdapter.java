package com.graphqlclient.busymachines.graphqlclientusingapollo.PostsList;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.graphqlclient.busymachines.graphqlclientusingapollo.AllPostsQuery;
import com.graphqlclient.busymachines.graphqlclientusingapollo.DeletePostMutation;
import com.graphqlclient.busymachines.graphqlclientusingapollo.MainActivity;
import com.graphqlclient.busymachines.graphqlclientusingapollo.MyApolloClient;
import com.graphqlclient.busymachines.graphqlclientusingapollo.NewPostMutation;
import com.graphqlclient.busymachines.graphqlclientusingapollo.R;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Created by cristianonescu on 14/12/2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.PostViewHolder> {
    private List<AllPostsQuery.AllPost> postsList;
    private MainActivity mContext;

    public MyRecyclerViewAdapter(MainActivity context, List<AllPostsQuery.AllPost> postsList) {
        this.postsList = postsList;
        this.mContext = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        PostViewHolder viewHolder = new PostViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PostViewHolder postViewHolder, int i) {
        final AllPostsQuery.AllPost post = postsList.get(i);

        //Setting text view title
        postViewHolder.title.setText(post.title());
        postViewHolder.description.setText(post.description());

        postViewHolder.post.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                mContext.removePost(post.id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != postsList ? postsList.size() : 0);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        protected CardView post;
        protected TextView title;
        protected TextView description;

        public PostViewHolder(View view) {
            super(view);
            this.post = (CardView) view.findViewById(R.id.post);
            this.title = (TextView) view.findViewById(R.id.title);
            this.description = (TextView) view.findViewById(R.id.description);
        }
    }
}
