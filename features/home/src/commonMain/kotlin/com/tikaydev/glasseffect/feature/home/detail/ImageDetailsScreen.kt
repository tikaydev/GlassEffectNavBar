package com.tikaydev.glasseffect.feature.home.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tikaydev.glasseffect.core.designsystem.component.AppBackButton
import com.tikaydev.glasseffect.core.designsystem.navigation.SharedElementKeys
import com.tikaydev.glasseffect.core.designsystem.utils.ImageLoader
import com.tikaydev.glasseffect.feature.home.imageList

@Composable
fun ImageDetailsScreen(
    imageIndex: Int,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {

        ImageDetailsContent(
            imageIndex = imageIndex,
            onBack = onBack,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}



@Composable
private fun ImageDetailsContent(
    imageIndex: Int,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Hero Section with Backdrop and Poster
            ImageHeroSection(
                modifier = Modifier.fillMaxSize(),
                imageUrl = imageList[imageIndex],
                imageIndex = imageIndex,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }

        AppBackButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 48.dp)
        )
    }
}

@Composable
private fun ImageHeroSection(
    modifier: Modifier = Modifier,
    imageUrl: String,
    imageIndex: Int,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(800.dp)
    ) {
        // Backdrop Image
        ImageLoader(
            imageUrl = imageUrl,
            modifier = Modifier.fillMaxSize()
        )

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.5f),
                            Color.Black.copy(alpha = 0.7f),
                        )
                    )
                )
        )

        // Content Row with Poster and Info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .padding(bottom = 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            // Poster Card - Bottom Left
            val sharedElementKey = "${SharedElementKeys.MOVIE_POSTER}${imageIndex}-${imageUrl}"


            val cornerShape = RoundedCornerShape(12.dp)
            val cardModifier = if (sharedTransitionScope != null && animatedVisibilityScope != null) {
                with(sharedTransitionScope) {
                    Modifier
                        .width(180.dp)
                        .height(225.dp)
                        .clip(cornerShape)
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = sharedElementKey),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                }
            } else {
                Modifier
                    .width(180.dp)
                    .height(225.dp)
            }

            Card(
                modifier = cardModifier,
                shape = cornerShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                ImageLoader(
                    imageUrl = imageUrl,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(cornerShape)
                )
            }
        }
    }
}
