package ru.aliev.animalstest.app_environment.base_di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.aliev.animalstest.features.main.repository.CatsRepository
import ru.aliev.animalstest.features.main.repository.CatsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindCatsRepository(impl: CatsRepositoryImpl): CatsRepository
}