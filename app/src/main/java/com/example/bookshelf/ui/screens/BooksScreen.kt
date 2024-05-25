package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.Books
import com.example.bookshelf.ui.theme.BookshelfTheme

/**
 * Displays the books screen
 *
 * @param bookshelfUiState The ui state of the bookshelf
 * @param retryAction The function to retry the action
 * @param modifier The modifier for the layout
 */
@Composable
fun BooksScreen(bookshelfUiState: BookshelfUiState, retryAction: () -> Unit, modifier: Modifier = Modifier) {
    when (bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is BookshelfUiState.Success -> BooksGrid(bookshelfUiState.books, modifier.fillMaxWidth())
        is BookshelfUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

/**
 * Displays a loading image
 *
 *  @param modifier The modifier for the layout
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * Displays an error message and a reattempt button
 *
 * @param retryAction The function to retry the action
 * @param modifier The modifier for the layout
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(R.drawable.ic_connection_error), " ")
        Text(stringResource(R.string.loading_failed), Modifier.padding(16.dp))
        Button(onClick = retryAction)
        {
            Text(stringResource(R.string.retry))
        }
    }
}

/**
 * Displays a grid with book images and titles
 *
 * @param books The list of books to display
 * @param modifier The modifier for the layout
 */
@Composable
fun BooksGrid(books: Books, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        content = {
            items(books.items, key = {item -> item.id}) { item ->
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(item.volumeInfo.imageLinks.thumbnail
                            .replace("http", "https"))
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    error = painterResource(id = R.drawable.ic_broken_image),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    contentScale = ContentScale.Crop
                )
            }
        }
    )
}

/**
 * Displays a Preview for the [LoadingScreen]
 */
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    BookshelfTheme {
        LoadingScreen()
    }
}

/**
 * Displays a Preview for the [ErrorScreen]
 */
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    BookshelfTheme {
        ErrorScreen(retryAction = {})
    }
}
