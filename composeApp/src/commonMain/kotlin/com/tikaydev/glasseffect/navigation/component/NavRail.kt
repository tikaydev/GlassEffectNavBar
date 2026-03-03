package com.tikaydev.glasseffect.navigation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tikaydev.glasseffect.AppState
import com.tikaydev.glasseffect.navigation.TopLevelDestination
import com.tikaydev.glasseffect.rememberAppState
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect

@Composable
fun NavRail(
    appState: AppState,
    selectedTabIndex: Int,
    hazeState: HazeState,
    modifier: Modifier = Modifier,
    onDestinationSelected: (TopLevelDestination) -> Unit,
) {
    AnimatedVisibility(visible = appState.shouldShowBottomBar()) {
        val itemHeight = 64.dp
        val spacing = 8.dp
        val verticalPadding = 32.dp

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(80.dp)
                    .wrapContentHeight()
                    .clip(CircleShape)
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            backgroundColor = MaterialTheme.colorScheme.background,
                            blurRadius = 30.dp,
                            tint = HazeTint(color = Color.Black.copy(alpha = .2f))
                        )
                    )
                    .border(
                        width = Dp.Hairline,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = .8f),
                                Color.White.copy(alpha = .2f),
                            ),
                        ),
                        shape = CircleShape
                    ),
            ) {
                Column(
                    modifier = Modifier
                        .width(80.dp)
                        .padding(vertical = verticalPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(spacing)
                ) {
                    CompositionLocalProvider(
                        LocalTextStyle provides LocalTextStyle.current.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                        ),
                        LocalContentColor provides Color.White
                    ) {
                        appState.topLevelDestinations.forEachIndexed { index, destination ->
                            val selected = appState.currentTopLevelDestination == destination
                            val alpha by animateFloatAsState(
                                targetValue = if (selected) 1f else .35f,
                                label = "alpha"
                            )
                            val scale by animateFloatAsState(
                                targetValue = if (selected) 1f else .98f,
                                visibilityThreshold = .000001f,
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessLow,
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                ),
                                label = "scale"
                            )


                            Column(
                                modifier = Modifier
                                    .scale(scale)
                                    .alpha(alpha)
                                    .fillMaxWidth()
                                    .height(itemHeight)
                                    .pointerInput(Unit) {
                                        detectTapGestures {
//                                            onTabSelected(tab)
                                            onDestinationSelected(destination)
                                        }
                                    },
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Icon(
                                    imageVector = destination.icon,
                                    contentDescription = "tab ${destination.title}"
                                )
                                Text(text = destination.title)
                            }
                        }
                    }
                }

                val animatedSelectedTabIndex by animateFloatAsState(
                    targetValue = selectedTabIndex.toFloat(),
                    label = "animatedSelectedTabIndex",
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioLowBouncy,
                    )
                )

                val animatedColor by animateColorAsState(
                    targetValue = appState.topLevelDestinations[selectedTabIndex].color,
                    label = "animatedColor",
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                    )
                )

                Canvas(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                        .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                ) {
                    drawCircle(
                        color = animatedColor.copy(alpha = .6f),
                        radius = size.width / 2,
                        center = Offset(
                            size.width / 2,
                            verticalPadding.toPx() + (itemHeight.toPx() * animatedSelectedTabIndex) + (spacing.toPx() * animatedSelectedTabIndex) + itemHeight.toPx() / 2
                        )
                    )
                }

                Canvas(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                ) {
                    val path = Path().apply {
                        addRoundRect(RoundRect(size.toRect(), CornerRadius(size.width / 2f)))
                    }
                    val length = PathMeasure().apply { setPath(path, false) }.length

                    val activeTabStartY =
                        verticalPadding.toPx() + (itemHeight.toPx() * animatedSelectedTabIndex) + (spacing.toPx() * animatedSelectedTabIndex)
                    val activeTabEndY = activeTabStartY + itemHeight.toPx()

                    drawPath(
                        path,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                animatedColor.copy(alpha = 0f),
                                animatedColor.copy(alpha = 1f),
                                animatedColor.copy(alpha = 1f),
                                animatedColor.copy(alpha = 0f),
                            ),
                            startY = activeTabStartY,
                            endY = activeTabEndY,
                        ),
                        style = Stroke(
                            width = 6f,
                            pathEffect = PathEffect.dashPathEffect(
                                intervals = floatArrayOf(length / 2, length)
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NavRailPreview() {
    val appState = rememberAppState()
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavRail(
            appState = appState,
            selectedTabIndex = 0,
            hazeState = HazeState(true),
            onDestinationSelected = {},
        )
        NavRail(
            appState = appState,
            selectedTabIndex = 1,
            hazeState = HazeState(true),
            onDestinationSelected = {},
        )
        NavRail(
            appState = appState,
            selectedTabIndex = 2,
            hazeState = HazeState(true),
            onDestinationSelected = {},
        )
    }
}
