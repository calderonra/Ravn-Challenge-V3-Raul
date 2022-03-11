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
//import com.example.ravnchallengue.client.Apollo


import com.example.ravnchallengue.databinding.PersonDetailsBinding


class PersonDetail : AppCompatActivity() {

    private var _binding: PersonDetailsBinding?=null
    private val binding get() = _binding!!
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var eyes: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)
        eyes=findViewById(R.id.eyeColor)

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
                eyes.text=launches.eyeColor
            }
        }








    }
    companion object{
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
            .build()

    }
/*
    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        super.onCreateView(parent, name, context, attrs)
        //onCreateView(inflater, container, savedInstanceState)
        _binding=PersonDetailsBinding.inflate(parent,false)
        val view = binding.root

        lifecycleScope.launchWhenResumed {
            val response = try {
                apolloClient.query((PersonDetailQuery(personid!!))).execute()
            } catch (e: ApolloException) {
                Log.d("Getting person failed", "Failure", e)
                null
            }
            val launches = response?.data?.person
            if (launches != null && !response.hasErrors()) {
                _binding!!.birthYear.text=launches.birthYear
                _binding!!.eyeColor.text=launches.eyeColor
                _binding!!.skinColor.text=launches.skinColor
                _binding!!.hairColor.text=launches.hairColor
            }
        }

        return view
    }
*/
}