package com.example.bookshelf

import com.example.bookshelf.data.NetworkBookshelfRepository
import com.example.bookshelf.fake.FakeBookshelfApiService
import com.example.bookshelf.fake.FakeDataSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkBookshelfRepositoryTest {
    @Test
    fun networkBookshelfBooksRepository_getBooks_verifyBooks() =
        runTest() {
            val repository = NetworkBookshelfRepository(
                bookshelfApiService = FakeBookshelfApiService()
            )
            assertEquals(FakeDataSource.books, repository.getBooks())
        }
}