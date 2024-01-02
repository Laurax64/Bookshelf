/*
* Copyright (C) 2023 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

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
 * Default app container
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
        NetworkBooksRepository(retrofitService)
    }
}
