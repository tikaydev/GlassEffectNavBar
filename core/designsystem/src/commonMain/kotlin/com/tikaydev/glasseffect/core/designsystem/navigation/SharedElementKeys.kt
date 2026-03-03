package com.tikaydev.glasseffect.core.designsystem.navigation

/**
 * Shared element transition keys used across the app.
 * These keys are used to match elements between screens for shared element transitions.
 */
object SharedElementKeys {
    /**
     * Key prefix for movie poster shared element transitions.
     * Append the movie ID and category to create a unique key: `"${MOVIE_POSTER}${movieId}-${category}"`
     */
    const val MOVIE_POSTER = "movie-poster-"
}
