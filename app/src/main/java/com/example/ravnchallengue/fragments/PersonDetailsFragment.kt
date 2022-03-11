package com.example.ravnchallengue.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo3.exception.ApolloException
import com.example.ravnChallengue.AllTodosQuery
import com.example.ravnChallengue.PersonDetailQuery
import com.example.ravnchallengue.Adapter.PersonListAdapter
import com.example.ravnchallengue.client.Apollo

import com.example.ravnchallengue.databinding.PersonDetailsBinding
import com.example.ravnchallengue.databinding.PersonListFragmentBinding

class PersonDetailsFragment :Fragment() {

    private var _binding:PersonDetailsBinding?=null
    private val binding get() = _binding!!


    lateinit var sharedPreferences: SharedPreferences

    val personid = sharedPreferences.getString("ID", "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding=PersonDetailsBinding.inflate(inflater,container,false)
        val view = binding.root

        lifecycleScope.launchWhenResumed {
            val response = try {
                Apollo.apolloClient.query((PersonDetailQuery(personid!!))).execute()
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

}