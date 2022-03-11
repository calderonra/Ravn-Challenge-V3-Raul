package com.example.ravnchallengue.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.ravnChallengue.AllTodosQuery
import com.example.ravnchallengue.Adapter.PersonListAdapter


import com.example.ravnchallengue.databinding.PersonListFragmentBinding

class PersonListFragment:Fragment() {

    private var _binding: PersonListFragmentBinding?= null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //var personListAdapter: PersonListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = PersonListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        lifecycleScope.launchWhenResumed {
            val response = try {
                apolloClient.query(AllTodosQuery()).execute()
            } catch (e: ApolloException) {
                Log.d("LaunchList", "Failure", e)
                null
            }

            val launches = response?.data?.allPeople?.people?.filterNotNull()
            if (launches != null && !response.hasErrors()) {
                var personListAdapter = PersonListAdapter(launches, requireContext())
                // Log.d("personList", "Success ${response.data}")
                _binding!!.launches.layoutManager = LinearLayoutManager(requireContext())
                _binding!!.launches.adapter = personListAdapter
            }
        }

        return view
    }    companion object{
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
            .build()

    }

}
