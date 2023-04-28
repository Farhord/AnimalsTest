package ru.aliev.animalstest.features.main.repository

import ru.aliev.animalstest.features.main.repository.api.CatsApiRepository
import ru.aliev.animalstest.features.main.repository.db.CatsDao
import ru.aliev.animalstest.features_utils.entities.cat.Cat
import ru.aliev.animalstest.features_utils.entities.result.Result
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val apiRepository: CatsApiRepository,
    private val catsDao: CatsDao
) : CatsRepository {

    override suspend fun getCats(page: Int): Result<List<Cat>> {
        return try {
            val response = apiRepository.getCats(page)
            val cats = if (!response.isSuccessful) {
                catsDao.getCats()
            } else {
                val catsFromDb = response.body() ?: emptyList()
                updateDatabase(catsFromDb)
                catsFromDb
            }
            if (cats.isNotEmpty()) Result.Success(cats) else Result.Error("empty list")
        } catch (e: Exception) {
            val cats = catsDao.getCats()
            if (cats.isNotEmpty()) Result.Success(cats) else Result.Error("empty list")
        }
    }

    override suspend fun updateDatabase(cats: List<Cat>) {
        catsDao.updateDatabase(cats)
    }

    override suspend fun addFavoriteCat(id: String) {
        catsDao.addFavoriteCat(id)
    }

    override suspend fun removeFavoriteCat(id: String) {
        catsDao.removeFavoriteCat(id)
    }

    override suspend fun getFavoriteCats(): Result<List<Cat>> {
        return Result.Success(catsDao.getFavoriteCats())
    }

    override suspend fun getFavoriteIds(): List<String> {
        val favIds: Set<String> = catsDao.getFavoriteCats().mapTo(HashSet(), Cat::id)
        return favIds.toList()
    }
}