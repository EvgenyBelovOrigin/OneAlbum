package ru.netology.onealbum.ui

import android.os.Bundle
import android.widget.Adapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.onealbum.adapter.OnInteractionListener
import ru.netology.onealbum.adapter.TrackAdapter
import ru.netology.onealbum.databinding.ActivityMainBinding
import ru.netology.onealbum.dto.Track
import ru.netology.onealbum.viewmodel.ViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val viewModel: ViewModel by viewModels()

        val adapter = TrackAdapter(object : OnInteractionListener {
            override fun onPlay(track: Track) {
                viewModel.play(track)
            }
        })
        viewModel.album.observe(this) { album ->
            with(binding) {
                albumName.text = album.title
                artistName.text = album.artist
                published.text = album.published
                genre.text = album.genre
            }
        }
        binding.list.adapter = adapter

        viewModel._tracks.observe(this) { tracks ->
            adapter.submitList(tracks)
        }
        viewModel.refresh.observe(this){
            adapter.notifyDataSetChanged()
        }


    }
}