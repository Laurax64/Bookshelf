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
import com.example.bookshelf.data.BookshelfApplication
import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookshelfUiState {
    data class Success(val photos: List<Book>) : BookshelfUiState
    data object Error : BookshelfUiState
    data object Loading : BookshelfUiState
}

class BookshelfViewModel(private val booksRepository: BooksRepository) : ViewModel() {
   var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)

    init {
    getBooks()
    }
fun getBooks() {
   viewModelScope.launch {
         bookshelfUiState = BookshelfUiState.Loading
         bookshelfUiState = try {
            BookshelfUiState.Success(booksRepository.getBooks())
        } catch (e: IOException) {
           BookshelfUiState.Error
        } catch (e: HttpException) {
            BookshelfUiState.Error
        }
    }
}

companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val application = (this[APPLICATION_KEY] as BookshelfApplication)
            val booksRepository = application.container.booksRepository
            BookshelfViewModel(booksRepository)
        }
    }
}
}
