package com.example.bookshelf.fake

import com.example.bookshelf.model.Books
import com.example.bookshelf.network.BookshelfApiService

class FakeBookshelfApiService : BookshelfApiService {
    override suspend fun getBooks(): Books {
        return FakeDataSource.books
    }
}
