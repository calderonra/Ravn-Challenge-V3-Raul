package com.example.ravnchallengue.client


import com.apollographql.apollo3.ApolloClient

class Apollo {
    companion object{
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
            .build()

    }
}

