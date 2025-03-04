package ru.netology.onealbum.utils

import android.media.MediaPlayer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MediaLifecycleObserver : LifecycleEventObserver {
    var mediaPlayer: MediaPlayer? = MediaPlayer()


    fun play(trackPath: String) {
        mediaPlayer?.setDataSource(trackPath)
        mediaPlayer?.setOnPreparedListener {
            it.start()
        }
        mediaPlayer?.prepareAsync()
    }

    fun stop() {
        mediaPlayer?.reset()

    }


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {

            Lifecycle.Event.ON_PAUSE -> mediaPlayer?.pause()
            Lifecycle.Event.ON_STOP -> {
                mediaPlayer?.release()
            }

            Lifecycle.Event.ON_DESTROY -> source.lifecycle.removeObserver(this)
            else -> Unit
        }
    }

    companion object MediaPlayerManager {
        private val mediaLifecycleObserver: MediaLifecycleObserver = MediaLifecycleObserver()
        fun mediaStop() {
            mediaLifecycleObserver.stop()

        }

        fun mediaPlay(trackPath: String) {
            mediaLifecycleObserver.play(trackPath)

        }
    }
}