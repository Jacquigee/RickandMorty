package com.jacqui.rickandmorty.data.mappers

import com.jacqui.rickandmorty.data.domain.CharacterDomain
import com.jacqui.rickandmorty.data.domain.CharacterResultDomain
import com.jacqui.rickandmorty.data.domain.InfoDomain
import com.jacqui.rickandmorty.data.domain.LocationDomain
import com.jacqui.rickandmorty.data.domain.OriginDomain
import com.jacqui.rickandmorty.sources.remote.model.CharacterDto
import com.jacqui.rickandmorty.sources.remote.model.CharacterResult
import com.jacqui.rickandmorty.sources.remote.model.InfoDto
import com.jacqui.rickandmorty.sources.remote.model.Location
import com.jacqui.rickandmorty.sources.remote.model.Origin

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 2:45 PM
 */

fun CharacterDto.toDomain() =
    CharacterDomain(info = info.toDomain(), results = results.map { it.toDomain() })

fun InfoDto.toDomain() = InfoDomain(count = count, next = next, pages = pages, prev = prev)

fun CharacterResult.toDomain() = CharacterResultDomain(
    created = created,
    episode = episode,
    gender = gender,
    id = id,
    image = image,
    location = location.toDomain(),
    name = name,
    origin = origin.toDomain(),
    species = species,
    status = status,
    type = type,
    url = url
)

fun Location.toDomain() = LocationDomain(name = name, url = url)

fun Origin.toDomain() = OriginDomain(name = name, url = url)