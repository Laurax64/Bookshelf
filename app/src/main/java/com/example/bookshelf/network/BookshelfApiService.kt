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

package com.example.bookshelf.network

import com.example.bookshelf.model.Books
import retrofit2.http.GET

/**
 * Interface to define the API endpoints, defines how Retrofit talks to the web server
 * using HTTP requests
 */
interface BookshelfApiService {
    @GET("?q=jazz+history")
    suspend fun getBooks(): Books
}

