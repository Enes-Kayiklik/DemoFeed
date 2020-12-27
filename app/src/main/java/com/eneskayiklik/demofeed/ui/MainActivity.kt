package com.eneskayiklik.demofeed.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.eneskayiklik.demofeed.R
import com.eneskayiklik.demofeed.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.rootLayout)
        binding!!.bottomNavigation.setupWithNavController(findNavController(R.id.fragmentNavHost))
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}