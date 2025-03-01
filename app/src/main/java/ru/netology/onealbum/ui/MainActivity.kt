package ru.netology.onealbum.ui

import android.os.Bundle
import android.widget.Adapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.onealbum.adapter.OnInteractionListener
import ru.netology.onealbum.adapter.TrackAdapter
import ru.netology.onealbum.databinding.ActivityMainBinding
import ru.netology.onealbum.viewmodel.ViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val viewModel: ViewModel by viewModels()

        val adapter = TrackAdapter(object : OnInteractionListener {
        })
        binding.list.adapter = adapter
        viewModel.album.observe(this) { album ->
            with(binding) {
                albumName.text = album.title
                artistName.text = album.artist
                published.text = album.published
                genre.text = album.genre
            }
        }
        viewModel.tracks.observe(this) { tracks ->
            adapter.submitList(tracks)
        }

    }
}