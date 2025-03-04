package ru.netology.onealbum.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.onealbum.adapter.OnInteractionListener
import ru.netology.onealbum.adapter.TrackAdapter
import ru.netology.onealbum.databinding.ActivityMainBinding
import ru.netology.onealbum.dto.Track
import ru.netology.onealbum.utils.MediaLifecycleObserver
import ru.netology.onealbum.viewmodel.ViewModel


class MainActivity : AppCompatActivity() {
    private val observer: MediaLifecycleObserver = MediaLifecycleObserver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(observer)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val viewModel: ViewModel by viewModels()

        val adapter = TrackAdapter(object : OnInteractionListener {
            override fun onPlay(track: Track) {
                viewModel.play(track)
            }
        })
        binding.list.adapter = adapter
        viewModel.album.observe(this) { album ->
            adapter.submitList(album.tracks)
            with(binding) {
                albumName.text = album.title
                artistName.text = album.artist
                published.text = album.published
                genre.text = album.genre
            }
        }

        viewModel.refreshAdapter.observe(this) {
            adapter.notifyDataSetChanged()
        }
    }
}