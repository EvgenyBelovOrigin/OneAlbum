package ru.netology.onealbum.dto

data class Album(
    val id: Int,
    val title: String,
    val subTitle: String,
    val artist: String,
    val published: String,
    val tracks: List<Tracks>,
)

data class Tracks(
    val id: Int,
    val file: String
)