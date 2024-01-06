package com.example.bookshelf.fake

import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Books

class FakeNetworkBookshelfRepository: BooksRepository {
    override suspend fun getBooks(): Books {
        return FakeDataSource.books
    }
}