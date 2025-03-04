package ru.netology.onealbum.dto

data class Album(
    val id: Int = 0,
    val title: String = "",
    val subTitle: String = "",
    val artist: String = "",
    val published: String = "",
    val genre: String = "",
    var tracks: List<Track>? = emptyList(),
)

data class Track(
    val id: Int = 0,
    val file: String = "",
    val isPlaying: Boolean = false,
    var filePath: String? = ""
)