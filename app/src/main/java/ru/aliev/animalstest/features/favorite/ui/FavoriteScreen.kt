package ru.aliev.animalstest.features.favorite.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ru.aliev.animalstest.features_utils.view.CatCard

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    navToDetail: (String) -> Unit
) {
    LaunchedEffect(viewModel.favoriteIds) {
        viewModel.getFavoriteIds()
        viewModel.getFavoriteCats()
    }

    val context = LocalContext.current
    when {
        (viewModel.errorMessage.value.isNotEmpty()) -> {
            Toast.makeText(context, viewModel.errorMessage.value, Toast.LENGTH_LONG).show()
        }
        (viewModel.catsList.isEmpty()) -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        (viewModel.catsList.isNotEmpty()) -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(8.dp)
            ) {
                items(items = viewModel.catsList) { cat ->
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