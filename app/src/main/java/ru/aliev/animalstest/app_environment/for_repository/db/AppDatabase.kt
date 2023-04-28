package ru.aliev.animalstest.app_environment.for_repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.aliev.animalstest.features.main.repository.db.CatsDao
import ru.aliev.animalstest.features_utils.entities.cat.Cat

@Database(
    entities = [Cat::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catsDao(): CatsDao
}