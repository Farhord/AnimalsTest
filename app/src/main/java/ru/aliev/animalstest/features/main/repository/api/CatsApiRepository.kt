package ru.aliev.animalstest.features.main.repository.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import ru.aliev.animalstest.features_utils.entities.cat.Cat

interface CatsApiRepository {

    @GET("search")
    suspend fun getCats(
        @Query("page")
        page: Int,
        @Query("limit")
        limit: Int = 20,
        @Query("order")
        order: String = "DESC",
        @Query("has_breeds")
        hasBreeds: Int = 1,
        @Query("api_key")
        apiKey: String = "live_sM4lXiBR5DiUfDg3LiRhd43osEWpugBSBB8yfrUBAyIfLM4dAixlmbOzGm7iIMGP"
    ): Response<List<Cat>>

    @GET("{id}")
    suspend fun getCatById(
        @Path(value = "id")
        id: String
    ): Response<Cat>
}