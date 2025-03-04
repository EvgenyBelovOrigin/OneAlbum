package ru.netology.onealbum.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.netology.onealbum.dto.Album
import ru.netology.onealbum.dto.Track
import ru.netology.onealbum.repository.Repository
import ru.netology.onealbum.utils.MediaLifecycleObserver
import ru.netology.onealbum.utils.SingleLiveEvent
import kotlin.concurrent.thread

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository = Repository()
    val BASE_URL =
        "https://github.com/netology-code/andad-homeworks/raw/master/09_multimedia/data/"
    //todo to remove in build

    private val _album = MutableLiveData(Album())
    val album: LiveData<Album>
        get() = _album

    private val _refreshAdapter = SingleLiveEvent<Unit>()
    val refreshAdapter: SingleLiveEvent<Unit>
        get() = _refreshAdapter


    init {
        loadAlbum()
    }

    private fun loadAlbum() {
        thread {
            try {
                val data = repository.getAll()
                val tracks = data.tracks
                tracks?.map {
                    it.copy(filePath = "$BASE_URL${it.file}")
                }
                _album.postValue(data)

            } catch (e: Exception) {
                throw (Exception())
            }
        }
    }

    fun play(track: Track) {
        if (!track.isPlaying) {
            _album.value?.tracks = _album.value?.tracks?.map {
                if (it.id != track.id) {
                    it.copy(isPlaying = false)
                } else it.copy(isPlaying = true)
            }
            MediaLifecycleObserver.mediaStop()
            track.filePath?.let { MediaLifecycleObserver.mediaPlay(it) }
            _refreshAdapter.value = Unit

        } else {
            _album.value?.tracks = _album.value?.tracks?.map {
                it.copy(isPlaying = false)
            }
            _refreshAdapter.value = Unit
            MediaLifecycleObserver.mediaStop()
        }
    }
}