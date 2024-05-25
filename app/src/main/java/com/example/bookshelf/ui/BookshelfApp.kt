package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.components.BookshelfTopAppBar
import com.example.bookshelf.ui.screens.BooksScreen
import com.example.bookshelf.ui.screens.BooksViewModel
import com.example.bookshelf.ui.screens.BooksViewModel.Companion.Factory

/**
 * Displays a [BookshelfTopAppBar], creates the [BooksViewModel] and calls the [BooksScreen] composable
 *
 * @param modifier The modifier for the layout
 */
@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    Scaffold(topBar = { BookshelfTopAppBar(modifier) }){
        Surface(modifier.fillMaxSize().padding(it)) {
            val bookshelfViewModel: BooksViewModel = viewModel(factory = Factory)
            BooksScreen(bookshelfViewModel.booksUiState, bookshelfViewModel::getBooks, modifier)
        }
    }
}
