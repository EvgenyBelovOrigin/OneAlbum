package ru.netology.onealbum.dto

data class Album(
    val id: Int = 0,
    val title: String = "",
    val subTitle: String = "",
    val artist: String = "",
    val published: String = "",
    val genre: String = "",
    val tracks: List<Tracks> = emptyList(),
)

data class Tracks(
    val id: Int = 0,
    val file: String = ""
)