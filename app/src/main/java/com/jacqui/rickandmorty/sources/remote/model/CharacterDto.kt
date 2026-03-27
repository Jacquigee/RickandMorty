package com.jacqui.rickandmorty.sources.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val info: InfoDto,
    val results: List<CharacterResult>
)

@Serializable
data class InfoDto(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)

@Serializable
data class CharacterResult(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

@Serializable
data class Location(
    val name: String,
    val url: String
)
@Serializable
data class Origin(
    val name: String,
    val url: String
)
