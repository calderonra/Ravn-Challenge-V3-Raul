package com.example.ravnchallengue.Adapter



import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ravnChallengue.AllTodosQuery
import com.example.ravnchallengue.PersonDetail


import com.example.ravnchallengue.databinding.PersonItemBinding


class PersonListAdapter(private val launches: List<AllTodosQuery.Person>,val context : Context):RecyclerView.Adapter<PersonListAdapter.ViewHolder> (){

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding=PersonItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        sharedPreferences= context.getSharedPreferences("MySP", Activity.MODE_PRIVATE)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(launches[position])
    }

    override fun getItemCount(): Int {
        return launches.size
    }


    inner class ViewHolder(val binding: PersonItemBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(person : AllTodosQuery.Person){
            binding.root.setOnClickListener {
                val editor=sharedPreferences.edit()
                editor.putString("ID", person.id)
                editor.apply()
                val intent=Intent(context, PersonDetail::class.java)
                context.startActivity(intent)

            }
            binding.personName.text = person.name
            binding.species.text=if (person.species?.name.toString() == "null") "Human "+" from "+person.homeworld?.name.toString() else person.species?.name.toString()+" from "+person.homeworld?.name.toString()

        }
    }

}