package ru.aliev.animalstest.app_environment.base_di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.aliev.animalstest.app_environment.for_repository.db.AppDatabase
import ru.aliev.animalstest.features.main.repository.db.CatsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCatsDao(appDatabase: AppDatabase): CatsDao {
        return appDatabase.catsDao()
    }

    companion object {
        private const val DB_NAME = "animals.db"
    }
}