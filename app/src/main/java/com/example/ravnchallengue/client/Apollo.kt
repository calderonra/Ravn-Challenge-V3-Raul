package com.example.ravnchallengue.client

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient


//private var instance: ApolloClient? = null

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
    .build()
/*
fun apolloClient(context: Context): ApolloClient {
    if (instance != null) {
        return instance!!
    }

    val okHttpClient = OkHttpClient.Builder() //.addInterceptor(AuthorizationInterceptor(context))
        .build()

    instance = ApolloClient.Builder()
        .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
        .okHttpClient(okHttpClient)
        .build()

    return instance!!
}*/