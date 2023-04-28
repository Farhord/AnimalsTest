package ru.aliev.animalstest.app_environment.for_repository.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.aliev.animalstest.features_utils.entities.cat.Breeds

class TypeConverter() {

    @TypeConverter
    fun periodsToJson(periods: List<Breeds>?): String? {
        return if (periods == null || periods.isEmpty()) {
            null
        } else {
            Gson().toJson(periods)
        }
    }

    /**
     * Функция для конвертации строки с датами подписок в список подписных периодов. Используется
     * при получении подписки из базы данных.
     * @param json Строка периодов подписки, которую нужно конвертировать в список подписных периодов.
     * @return Подписные периоды в виде списка подписных периодов.
     */
    @TypeConverter
    fun jsonToPeriods(json: String?): List<Breeds> {
        return if (json.isNullOrEmpty()) {
            listOf()
        } else {
            val type = object : TypeToken<List<Breeds>>() {}.type
            Gson().fromJson(json, type)
        }
    }
}