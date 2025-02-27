package ru.netology.onealbum.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.onealbum.dto.Album
import ru.netology.onealbum.repository.Repository
import java.io.IOException
import kotlin.concurrent.thread

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository = Repository()
    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album>
        get() = _album


    init {
        loadAlbum()
    }

    private fun loadAlbum() {
        thread {
            val data: Album = try {
                repository.getAll()

            } catch (e: Exception) {
                throw (Exception())
            }
            _album.postValue(data)
        }
    }
}