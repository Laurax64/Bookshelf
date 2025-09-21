package com.example.bookshelf.network

import com.example.bookshelf.model.Books
import retrofit2.http.GET

/**
 * Interface to define the API endpoints, defines how Retrofit talks to the web server
 * using HTTP requests
 */
interface BookshelfApiService {
    @GET("?q=dog+pets")
    suspend fun getBooks(): Books
}

