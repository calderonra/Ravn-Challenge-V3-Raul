package com.example.ravnchallengue.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ravnChallengue.AllTodosQuery
import com.example.ravnchallengue.databinding.ActivityMainBinding
import com.example.ravnchallengue.databinding.PersonItemBinding

class PersonListAdapter(private val launches: List<AllTodosQuery.Person>,val context : Context):RecyclerView.Adapter<PersonListAdapter.ViewHolder> (){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding=PersonItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // val launch =
        //holder.binding.personName.text=launch.name ?:""
        holder.bind(launches[position])


    }

    override fun getItemCount(): Int {
        return launches.size
    }

    inner class ViewHolder(val binding: PersonItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(person : AllTodosQuery.Person){
            binding.root.setOnClickListener {

                Toast.makeText(context, "${person.name}", Toast.LENGTH_LONG).show()
            }


            binding.personName.text = person.name
            binding.species.text=if (person.species?.name.toString() == "null") "Human "+" from "+person.homeworld?.name.toString() else person.species?.name.toString()+" from "+person.homeworld?.name.toString()

        }
    }

}