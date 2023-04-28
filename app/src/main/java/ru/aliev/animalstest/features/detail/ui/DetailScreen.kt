package ru.aliev.animalstest.features.detail.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import ru.aliev.animalstest.features_utils.entities.cat.Cat
import ru.aliev.animalstest.features_utils.entities.result.Result

@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel
) {

    LaunchedEffect(key1 = viewModel.cat) {
        viewModel.getCat(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when (val result = viewModel.cat.value) {
            is Result.Success -> {
                result.data?.let { cat ->
                    CatInfo(cat = cat)
                }
            }
            is Result.Error -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(text = "NO INTERNET :(")
                }
            }
            null -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun CatInfo(cat: Cat) {
    cat.breeds.firstOrNull()?.let { breed ->
        Text(text = "Name: ${breed.name}")
        Spacer(modifier = Modifier.height(8.dp))
        CoilImage(
            imageModel = cat.url,
            shimmerParams = ShimmerParams(
                baseColor = Color.LightGray,
                highlightColor = Color.White,
                durationMillis = 500,
                dropOff = 0.3f,
                tilt = 20f
            ),
            failure = {
                Text(text = "NO IMAGE ;(")
            },
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Description: ${breed.description}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Weight: ${breed.weight.metric} Kg")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Life span: ${breed.lifeSpan} years")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Origin: ${breed.origin}")
    }
}