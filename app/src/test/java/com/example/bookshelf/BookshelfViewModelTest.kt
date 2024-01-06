package com.example.bookshelf

import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.fake.FakeNetworkBookshelfRepository
import com.example.bookshelf.rules.TestDispatcherRule
import com.example.bookshelf.ui.screens.BookshelfUiState
import com.example.bookshelf.ui.screens.BookshelfViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class BookshelfViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()
    @Test
    fun bookshelfViewModel_getBooks_verifyBookshelfUiStateSuccess() =
        runTest {
            val bookshelfViewModel = BookshelfViewModel(
                booksRepository = FakeNetworkBookshelfRepository()
            )
            assertEquals(
                BookshelfUiState.Success(FakeDataSource.books),
                bookshelfViewModel.bookshelfUiState
            )
        }
}