package ru.aliev.animalstest.features_utils.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import ru.aliev.animalstest.R
import ru.aliev.animalstest.activities.ui.Black
import ru.aliev.animalstest.activities.ui.Grey
import ru.aliev.animalstest.activities.ui.Red
import ru.aliev.animalstest.features_utils.entities.cat.Cat

@Composable
fun CatCard(
    cat: Cat,
    navToDetail: (id: String) -> Unit,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(6.dp)
            .clickable {
                navToDetail(cat.id)
            }
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (imageRef, favIcon, textRef) = createRefs()

            Box(
                modifier = Modifier
                    .width(130.dp)
                    .height(135.dp)
                    .constrainAs(imageRef) {
                        start.linkTo(parent.start, 14.dp)
                        end.linkTo(parent.end, 14.dp)
                        top.linkTo(parent.top, 12.dp)
                    }
            ) {
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
            }
            Box(
                modifier = Modifier
                    .constrainAs(favIcon) {
                        top.linkTo(parent.top, 8.dp)
                        end.linkTo(parent.end, 8.dp)
                    }
            ) {
                Heart(isFavorite = isFavorite, onFavoriteClick = onFavoriteClick)
            }
            Text(
                text = cat.breeds[0].name,
                fontSize = 14.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.24.sp,
                color = Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .constrainAs(textRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(imageRef.bottom, 8.dp)
                    }
            )
        }
    }
}

@Composable
private fun Heart(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    val color = if (isFavorite) Red else Grey
    Image(
        imageVector = ImageVector.vectorResource(R.drawable.ic18_rate_fav_fill),
        contentDescription = "",
        colorFilter = ColorFilter.tint(color),
        modifier = Modifier
            .size(18.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false),
                onClick = {
                    onFavoriteClick()
                }
            ),
    )
}
