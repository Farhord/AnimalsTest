package ru.aliev.animalstest.features.favorite.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.aliev.animalstest.features.main.repository.CatsRepository
import ru.aliev.animalstest.features_utils.entities.cat.Cat
import ru.aliev.animalstest.features_utils.entities.result.Result
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: CatsRepository
) : ViewModel() {

    val catsList: List<Cat>
        get() = _catsList.toList()
    private val _catsList = mutableStateListOf<Cat>()

    val favoriteIds: List<String>
        get() = _favoriteIds
    private val _favoriteIds = mutableStateListOf<String>()

    val errorMessage: State<String>
        get() = _errorMessage
    private val _errorMessage = mutableStateOf("")


    fun getFavoriteCats() {
        viewModelScope.launch {
            val result = repository.getFavoriteCats()
            if (result is Result.Success) {
                _catsList.addAll(result.data ?: emptyList())
            } else {
                _errorMessage.value = result.message ?: ""
            }
        }
    }

    fun getFavoriteIds() {
        viewModelScope.launch {
            _favoriteIds.addAll(repository.getFavoriteIds())
        }
    }

    fun addFavorite(id: String) {
        viewModelScope.launch {
            repository.addFavoriteCat(id)
            _favoriteIds.add(id)
        }
    }

    fun removeFavorite(id: String) {
        viewModelScope.launch {
            repository.removeFavoriteCat(id)
            _favoriteIds.remove(id)
        }
    }
}