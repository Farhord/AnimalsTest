package ru.aliev.animalstest.features_utils.entities.cat

import com.google.gson.annotations.SerializedName

data class Breeds(
    @SerializedName("weight")
    val weight: Weight,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("life_span")
    val lifeSpan: String
)