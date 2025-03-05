package ru.netology.onealbum.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.netology.onealbum.BuildConfig
import ru.netology.onealbum.dto.Album
import java.util.concurrent.TimeUnit

class Repository {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
    private val gson = Gson()
    private val typeToken = object : TypeToken<Album>() {}
    private val albumName = "album.json"

    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL
    }

    fun getAll(): Album {
        val request: Request = Request.Builder()
            .url("$BASE_URL$albumName")
            .build()

        return client.newCall(request)
            .execute()
            .let { it.body?.string() ?: throw RuntimeException("body is null") }
            .let {
                gson.fromJson(it, typeToken.type)
            }
    }
}
