package ru.aliev.animalstest.activities.navigation

enum class Screen(
    val route: String,
    val screenName: String
) {
    MAIN ("main", "Главная"),
    DETAIL ("detail", "О коте"),
    FAVORITE ("favorite", "Избранное");

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/${arg}")
            }
        }
    }

    fun withKeys(vararg keys: String): String {
        return buildString {
            append(route)
            keys.forEach { key ->
                append("/{$key}")
            }
        }
    }
}