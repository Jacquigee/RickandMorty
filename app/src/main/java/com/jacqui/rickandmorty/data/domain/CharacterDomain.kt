package com.jacqui.rickandmorty.data.domain

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 2:44 PM
 */

data class CharacterDomain(
    val info: InfoDomain,
    val results: List<CharacterResultDomain>
)

data class InfoDomain(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)

data class CharacterResultDomain(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationDomain,
    val name: String,
    val origin: OriginDomain,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

data class LocationDomain(
    val name: String,
    val url: String
)

data class OriginDomain(
    val name: String,
    val url: String
)
