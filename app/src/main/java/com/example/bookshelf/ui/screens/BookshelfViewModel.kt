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
import com.example.bookshelf.model.Book
import com.example.bookshelf.ui.screens.BookshelfUiState.Error
import com.example.bookshelf.ui.screens.BookshelfUiState.Loading
import com.example.bookshelf.ui.screens.BookshelfUiState.Success
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


/**
 * Holds and initializes the ui state
 *
 * @property bookshelfUiState The UI state
 */
class BookshelfViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    /**
     * Represents the current state of the Bookshelf UI.
     *
     * Changes to this state trigger re-execution of affected composable functions.
     */
   var bookshelfUiState: BookshelfUiState by mutableStateOf(Loading)

    /**
     * Initializes the ui state
     */
    init {
        getBooks()
    }

    /**
     * Launches a new coroutine in a [viewModelScope] without blocking the calling thread and
     * updates [bookshelfUiState] inside of the coroutine
     */
    fun getBooks() {
        viewModelScope.launch {
            bookshelfUiState = Loading
            bookshelfUiState =
                try { Success(booksRepository.getBooks()) }
                catch (e: IOException) { Error }
                catch (e: HttpException) { Error }
        }
    }

    companion object {
        /**
         * Retrieves the [booksRepository] and passes it on creation of [BookshelfViewModel]
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val booksRepository = application.container.booksRepository
                BookshelfViewModel(booksRepository)
            }
        }
    }

}

/**
 * Declarations for the ui state
 *
 * @property Error The error ui state
 * @property Loading The loading ui state
 * @property Success The success ui state
 */
sealed interface BookshelfUiState {

    /**
     * UI state value, if there are any network or connection errors
     */
    data object Error : BookshelfUiState

    /**
     * UI state value, if the app is waiting for data
     */
    data object Loading : BookshelfUiState

    /**
     * UI state value, if the data was successfully retrieved from the web service
     */
    data class Success(val books: List<Book>) : BookshelfUiState
}
