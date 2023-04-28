package ru.aliev.animalstest.features.main.repository

import ru.aliev.animalstest.features_utils.entities.cat.Cat
import ru.aliev.animalstest.features_utils.entities.result.Result

interface CatsRepository {

    suspend fun getCats(page: Int): Result<List<Cat>>

    suspend fun updateDatabase(cats: List<Cat>)

    suspend fun addFavoriteCat(id: String)

    suspend fun removeFavoriteCat(id: String)

    suspend fun getFavoriteCats(): Result<List<Cat>>

    suspend fun getFavoriteIds(): List<String>
}