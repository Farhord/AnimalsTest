package ru.aliev.animalstest.features.main.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.aliev.animalstest.features.main.repository.CatsRepository
import ru.aliev.animalstest.features_utils.entities.cat.Cat
import javax.inject.Inject
import ru.aliev.animalstest.features_utils.entities.result.Result

private const val PAGE_SIZE = 20
private const val PRELOAD_CATS_NUM = 4

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CatsRepository
) : ViewModel() {

    private var page = -1
    private var scrollPosition = 0
    private val isConnected = mutableStateOf(true)

    val isLoading: State<Boolean>
        get() = _isLoading
    private val _isLoading = mutableStateOf(false)

    val catsList: List<Cat>
        get() = _catsList.toList()
    private val _catsList = mutableStateListOf<Cat>()

    val favoriteIds: List<String>
        get() = _favoriteIds
    private val _favoriteIds = mutableStateListOf<String>()

    val errorMessage: State<String>
        get() = _errorMessage
    private val _errorMessage = mutableStateOf("")


    fun getCatsList() {
        if (scrollPosition + PRELOAD_CATS_NUM >= page * PAGE_SIZE && isConnected.value && !isLoading.value) {
            _isLoading.value = true
            page++
            viewModelScope.launch {
                val result = repository.getCats(page = page)
                if (result is Result.Success) {
                    _catsList.addAll(result.data ?: emptyList())
                } else {
                    _errorMessage.value = result.message ?: ""
                }
                _isLoading.value = false
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

    fun onChangeScrollPosition(position: Int) {
        scrollPosition = position
    }
}