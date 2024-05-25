package com.example.bookshelf.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Displays a Top Bar with the app name and a navigation icon
 *
 * @param modifier The modifier for the layout
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Bookshelf", style = MaterialTheme.typography.titleLarge) },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}
