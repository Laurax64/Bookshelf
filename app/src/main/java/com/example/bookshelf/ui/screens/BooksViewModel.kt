package com.example.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Books
import com.example.bookshelf.ui.screens.BookshelfUiState.Error
import com.example.bookshelf.ui.screens.BookshelfUiState.Loading
import com.example.bookshelf.ui.screens.BookshelfUiState.Success
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


/**
 * A view model for the books screen
 *
 * @param booksRepository The repository to access book data
 */
class BooksViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    var booksUiState: BookshelfUiState by mutableStateOf(Loading)

    /**
     * Initializes the ui state
     */
    init {
        getBooks()
    }

    /**
     * Launches a new coroutine in a [viewModelScope] without blocking the calling thread and
     * updates the [booksUiState] inside of the coroutine
     */
    fun getBooks() {
        viewModelScope.launch {
            booksUiState = Loading
            booksUiState =
                try {
                    val books = booksRepository.getBooks()
                    Success(books)
                    } catch (_: IOException) {
                    Error
                } catch (_: HttpException) {
                    Error
                }
        }
    }

    companion object {
        /**
         * Retrieves the [booksRepository] and passes it on creation of [BooksViewModel]
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val booksRepository = application.container.booksRepository
                BooksViewModel(booksRepository)
            }
        }
    }
}

/**
 * The declarations for the ui state
 *
 * @property Error The error ui state
 * @property Loading The loading ui state
 * @property Success The success ui state
 */
sealed interface BookshelfUiState {

    /**
     * UI state value, if there are any network or connection errors
     */
    object Error : BookshelfUiState

    /**
     * UI state value, if the app is waiting for data
     */
    object Loading : BookshelfUiState

    /**
     * UI state value, if the data was successfully retrieved from the web service
     */
    data class Success(val books: Books) : BookshelfUiState
}
