package com.example.ravnchallengue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ravnchallengue.databinding.ActivityMainBinding
import com.example.ravnchallengue.fragments.PersonListFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutContainer, PersonListFragment::class.java.newInstance(), "Fragment")
            .commit()
    }


}



