package ru.aliev.animalstest.features.main.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.aliev.animalstest.features_utils.entities.cat.Cat

@Dao
interface CatsDao {

    @Query("SELECT * FROM cats_list")
    suspend fun getCats(): List<Cat>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateDatabase(cats: List<Cat>)

    @Query("UPDATE cats_list SET favorite = $TRUE WHERE id == :id")
    suspend fun addFavoriteCat(id: String)

    @Query("UPDATE cats_list SET favorite = $FALSE WHERE id == :id")
    suspend fun removeFavoriteCat(id: String)

    @Query("SELECT * FROM cats_list WHERE favorite == $TRUE")
    suspend fun getFavoriteCats(): List<Cat>

    companion object {
        private const val FALSE = 0
        private const val TRUE = 1
    }
}