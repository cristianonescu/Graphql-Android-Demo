package com.graphqlclient.busymachines.graphqlclientusingapollo;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by cristianonescu on 14/12/2017.
 */

public class MyApolloClient {
    private static final String BASE_URL = "https://api.graph.cool/simple/v1/cjb6czlbu1wsq01331fxnw7s5";

    private static ApolloClient myApolloClient;


    public static ApolloClient getMyApolloClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        myApolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();

        return myApolloClient;
    }
}
