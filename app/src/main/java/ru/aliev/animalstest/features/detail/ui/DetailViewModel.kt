package ru.aliev.animalstest.features.detail.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.aliev.animalstest.features.main.repository.api.CatsApiRepository
import ru.aliev.animalstest.features_utils.entities.cat.Cat
import javax.inject.Inject
import ru.aliev.animalstest.features_utils.entities.result.Result

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val apiRepository: CatsApiRepository
): ViewModel() {

    val cat: State<Result<Cat>?>
        get() = _cat
    private val _cat = mutableStateOf<Result<Cat>?>(null)

    fun getCat(id: String) {
        viewModelScope.launch {
            try {
                val response = apiRepository.getCatById(id)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _cat.value = Result.Success(it)
                    }
                } else {
                    _cat.value = Result.Error("some error message")
                }
            } catch (e: Exception) {
                _cat.value = Result.Error("some error message")
            }
        }
    }
}