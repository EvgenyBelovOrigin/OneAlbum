package ru.netology.onealbum.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.onealbum.dto.Album
import ru.netology.onealbum.repository.Repository
import kotlin.concurrent.thread

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository = Repository()
    val _album = MutableLiveData(Album())
    val album: LiveData<Album>
        get() = _album

    init {
        loadAlbum()
    }

    fun loadAlbum() {
        thread {
            try {
                val data = repository.getAll()
                _album.postValue(data)

            } catch (e: Exception) {
                throw (Exception())
            }
        }
    }
}