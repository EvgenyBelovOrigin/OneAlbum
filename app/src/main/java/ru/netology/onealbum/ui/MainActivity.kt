package ru.netology.onealbum.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.onealbum.R
import ru.netology.onealbum.databinding.ActivityMainBinding
import ru.netology.onealbum.viewmodel.ViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val viewModel: ViewModel by viewModels()
        viewModel._album.observe(this) { album ->

                binding.hello.text = album.artist
        }
    }
}