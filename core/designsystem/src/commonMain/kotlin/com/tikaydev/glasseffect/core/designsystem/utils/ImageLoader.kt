package com.tikaydev.glasseffect.core.designsystem.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil3.PlatformContext
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.tikaydev.glasseffect.core.designsystem.resources.Res
import com.tikaydev.glasseffect.core.designsystem.resources.error_loading_image
import com.tikaydev.glasseffect.core.designsystem.resources.image
import com.tikaydev.glasseffect.core.designsystem.resources.loading
import org.jetbrains.compose.resources.stringResource

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    platformContext: PlatformContext = LocalPlatformContext.current
) {
    SubcomposeAsyncImage(
//        model = imageUrl,
        model = ImageRequest.Builder(platformContext)
            .data(imageUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCacheKey(imageUrl)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCacheKey(imageUrl)
            .build(),
        contentDescription = contentDescription ?: stringResource(Res.string.image),
        contentScale = contentScale,
        modifier = modifier,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(Res.string.loading),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        error = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.errorContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(Res.string.error_loading_image),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}
