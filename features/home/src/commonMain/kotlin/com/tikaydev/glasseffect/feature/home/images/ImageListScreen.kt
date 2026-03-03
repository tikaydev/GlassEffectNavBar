package com.tikaydev.glasseffect.feature.home.images

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.LocalPlatformContext
import com.tikaydev.glasseffect.core.designsystem.navigation.SharedElementKeys
import com.tikaydev.glasseffect.core.designsystem.provider.LocalHazeStateProvider
import com.tikaydev.glasseffect.core.designsystem.utils.ImageLoader
import com.tikaydev.glasseffect.feature.home.imageList
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageListRoute(
    shouldUseNavRail: Boolean,
    onClick: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ImageListScreen(
            shouldUseNavRail = shouldUseNavRail,
            onClick = onClick,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}


@Composable
fun ImageListScreen(
    shouldUseNavRail: Boolean,
    onClick: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null
) {
    val listState = rememberLazyGridState()
    val imageUrls = retain { imageList }
    val hazeState = LocalHazeStateProvider.current
    val platformContext = LocalPlatformContext.current

    LazyVerticalGrid(
        state = listState,
        columns = if (shouldUseNavRail) GridCells.Adaptive(minSize = 280.dp) else GridCells.Fixed(2),
        modifier = Modifier
            .hazeSource(hazeState)
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(
            items = imageUrls,
            key = { index, imageUrl -> "$index$imageUrl" }
        ) { index, imageUrl ->
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                onClick = { onClick(index) }
            ) {
                ImageItem(
                    imageUrl = imageUrl,
                    imageIndex = index,
                    platformContext = platformContext,
                    onClick = onClick,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
        }
    }
}

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    imageIndex: Int,
    platformContext: PlatformContext,
    onClick: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null
) {

    val cornerShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick(imageIndex) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            val imageModifier = Modifier
                .clip(cornerShape)
            val finalModifier =
                if (sharedTransitionScope != null && animatedVisibilityScope != null) {
                    with(sharedTransitionScope) {
                        imageModifier.sharedElement(
                            sharedContentState = rememberSharedContentState(
                                key = "${SharedElementKeys.MOVIE_POSTER}${imageIndex}-${imageUrl}"
                            ),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                            .clip(cornerShape)
                    }
                } else {
                    imageModifier
                }

            ImageLoader(
                imageUrl = imageUrl,
                platformContext = platformContext,
                modifier = finalModifier
                    .fillMaxWidth()
                    .aspectRatio(1.3f),
            )
        }
    }
}

