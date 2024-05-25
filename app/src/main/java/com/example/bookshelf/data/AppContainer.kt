package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Contains the dependencies that the Bookshelf app requires.
 */
interface AppContainer {
    val booksRepository: BooksRepository
}

/**
 * The default app container
 *
 * @property baseUrl The base URL
 * @property retrofit The retrofit object
 * @property retrofitService The retrofit service
 */
class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://www.googleapis.com/books/v1/volumes/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    /**
     * Implementation of the API endpoints defined by the [BookshelfApiService]
     */
    private val retrofitService: BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }

    override val booksRepository: BooksRepository by lazy {
        NetworkBookshelfRepository(retrofitService)
    }
}
