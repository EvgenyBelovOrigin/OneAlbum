package ru.netology.onealbum.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.onealbum.R
import ru.netology.onealbum.viewmodel.ViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: ViewModel by viewModels()
        findViewById<TextView>(R.id.hello).text = viewModel.album.toString()

    }
}