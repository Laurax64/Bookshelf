package com.example.bookshelf.data

import com.example.bookshelf.model.Books
import com.example.bookshelf.network.BookshelfApiService

/**
 * Returns a list of books
 */
interface BooksRepository {
    suspend fun getBooks(): Books
}

/**
 * Sets [BooksRepository.getBooks] to [BookshelfApiService.getBooks]
 */
class NetworkBookshelfRepository(
    private val bookshelfApiService: BookshelfApiService) : BooksRepository {
    override suspend fun getBooks(): Books = bookshelfApiService.getBooks()
    }
