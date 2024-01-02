package com.example.bookshelf.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.bookshelf.model.Item
import com.example.bookshelf.ui.theme.BookshelfTheme

@Composable
fun HomeScreen(
    bookshelfUiState: BookshelfUiState, retryAction: () -> Unit, modifier: Modifier = Modifier
) {
    when (bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is BookshelfUiState.Success -> BooksList(bookshelfUiState.books, modifier.fillMaxWidth())

        is BookshelfUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

/**
 * Displays a loading image
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
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(R.drawable.ic_connection_error)," ")
        Text(stringResource(R.string.loading_failed), modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun BooksList(books: Books, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = books.items) { item ->
            BookCard(
                item,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}

@Composable
fun BookCard(item: Item, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row() {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(item.volumeInfo.imageLinks.thumbnail
                        .replace("http", "https"))
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .size(90.dp),
                contentDescription = (item.volumeInfo.title),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(1.dp)) {
                Text(item.volumeInfo.title, style = MaterialTheme.typography.headlineSmall)
                Text(item.volumeInfo.authors.toString())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    BookshelfTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    BookshelfTheme {
        ErrorScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    BookshelfTheme {
       /** val mockData =
        BooksList(mockData)
        **/
    }
}
