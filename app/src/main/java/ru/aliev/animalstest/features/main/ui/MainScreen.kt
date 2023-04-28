package ru.aliev.animalstest.features.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.aliev.animalstest.features_utils.view.CatCard

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navToDetail: (id: String) -> Unit
) {
    LaunchedEffect(viewModel.favoriteIds) {
        viewModel.getFavoriteIds()
        viewModel.getCatsList()
    }

    when {
        (viewModel.catsList.isEmpty()) -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (viewModel.isLoading.value) {
                    CircularProgressIndicator()
                } else {
                    Text(text = "NO INTERNET :(")
                }
            }
        }
        (viewModel.catsList.isNotEmpty()) -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(8.dp)
            ) {
                itemsIndexed(items = viewModel.catsList) { index, cat ->
                    viewModel.onChangeScrollPosition(index)
                    viewModel.getCatsList()
                    cat.breeds.firstOrNull()?.let {
                        CatCard(
                            cat = cat,
                            navToDetail = navToDetail,
                            onFavoriteClick = {
                                if (cat.id in viewModel.favoriteIds) {
                                    viewModel.removeFavorite(cat.id)
                                } else {
                                    viewModel.addFavorite(cat.id)
                                }
                            },
                            isFavorite = cat.id in viewModel.favoriteIds
                        )
                    }
                }
            }
        }
    }
}