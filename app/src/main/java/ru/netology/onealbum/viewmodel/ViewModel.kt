package ru.netology.onealbum.viewmodel

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.netology.onealbum.R
import ru.netology.onealbum.dto.Album
import ru.netology.onealbum.dto.Track
import ru.netology.onealbum.repository.Repository
import ru.netology.onealbum.utils.SingleLiveEvent
import kotlin.concurrent.thread

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository = Repository()

    private val _album = MutableLiveData(Album())
    val album: LiveData<Album>
        get() = _album

    private val _tracks = MutableLiveData(emptyList<Track>())
    val tracks: LiveData<List<Track>>
        get() = _tracks

    private val _refresh = SingleLiveEvent<Unit>()
    val refresh: SingleLiveEvent<Unit>
        get() = _refresh

    private val _playTrack = MutableLiveData(emptyList<String>())
    val playTrack: LiveData<List<String>>
        get() = _playTrack

    val _stop = SingleLiveEvent<Unit>()
    val stop: SingleLiveEvent<Unit>
        get() = _stop


    init {
        loadAlbum()
    }

    private fun loadAlbum() {
        thread {
            try {
                val data = repository.getAll()
                _album.postValue(data)
                _tracks.postValue(data.tracks)

            } catch (e: Exception) {
                throw (Exception())
            }
        }
    }

    fun play(track: Track) {
        if (!track.isPlaying) {
            _tracks.value = _tracks.value?.map {
                if (it.id != track.id) {
                    it.copy(isPlaying = false)
                } else it.copy(isPlaying = true)
            }
            _stop.value = Unit
            _playTrack.value = listOf(track.file)
            _refresh.value = Unit

        } else {
            _tracks.value = _tracks.value?.map {
                it.copy(isPlaying = false)
            }
            _refresh.value = Unit
            _stop.value = Unit
        }
    }
}