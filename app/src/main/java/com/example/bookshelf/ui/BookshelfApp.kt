package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.BooksScreen
import com.example.bookshelf.ui.screens.BooksViewModel
import com.example.bookshelf.ui.screens.BooksViewModel.Companion.Factory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    val bookshelfViewModel: BooksViewModel = viewModel(factory = Factory)
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
            )
        }
    )
    {
        BooksScreen(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            bookshelfUiState = bookshelfViewModel.booksUiState,
            retryAction = bookshelfViewModel::getBooks
        )
    }
}
