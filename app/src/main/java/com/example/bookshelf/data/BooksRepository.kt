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

import android.app.Application
import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BookshelfApiService

/**
 * Returns a list of books
 */
interface BooksRepository {
    suspend fun getBooks(): List<Book>
}

class NetworkBooksRepository(
    private val bookshelfApiService: BookshelfApiService) : BooksRepository {
    override suspend fun getBooks(): List<Book> = bookshelfApiService.getBooks()
}

class BookshelfApplication: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}