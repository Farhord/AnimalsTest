package ru.aliev.animalstest.features_utils.entities.cat

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cats_list")
data class Cat(
    @SerializedName("breeds")
    val breeds: List<Breeds>,
    @PrimaryKey()
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @Expose(serialize = false, deserialize = false)
    val favorite: Boolean = false
)
