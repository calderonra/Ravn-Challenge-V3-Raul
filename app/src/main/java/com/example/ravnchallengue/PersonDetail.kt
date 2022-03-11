package com.example.ravnchallengue


import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.ravnChallengue.PersonDetailQuery
import com.example.ravnchallengue.databinding.ActivityPersonDetailBinding
//import com.example.ravnchallengue.client.Apollo


import com.example.ravnchallengue.databinding.PersonDetailsBinding


class PersonDetail : AppCompatActivity() {

    private lateinit var binding: ActivityPersonDetailBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences= getSharedPreferences("MySP", Activity.MODE_PRIVATE)
        val personid = sharedPreferences.getString("ID", "")

        lifecycleScope.launchWhenResumed {
            val response = try {
                apolloClient.query((PersonDetailQuery(personid!!))).execute()
            } catch (e: ApolloException) {
                Log.d("Getting person failed", "Failure", e)
                null
            }
            val launches = response?.data?.person
            if (launches != null && !response.hasErrors()) {
                binding.eyeColor.text =launches.eyeColor
                binding.birthYear.text = launches.birthYear
                binding.eyeColor.text = launches.eyeColor
                binding.skinColor.text = launches.skinColor
                binding.hairColor.text = launches.hairColor
                if (launches.vehicleConnection?.vehicles?.isEmpty() == true){
                    binding.vehicle.text= "N/A"
                    binding.vehicle2.text= "N/A"
                }else{

                    if (launches.vehicleConnection?.vehicles?.size==1){
                        binding.vehicle.text= launches.vehicleConnection?.vehicles?.get(0)?.name.toString()
                        binding.vehicle2.text= "N/A"
                    }else{
                        binding.vehicle.text= launches.vehicleConnection?.vehicles?.get(0)?.name.toString()
                        binding.vehicle2.text=launches.vehicleConnection?.vehicles?.get(1)?.name.toString()
                    }
                }


            }
        }

    }
    companion object{
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
            .build()

    }
}