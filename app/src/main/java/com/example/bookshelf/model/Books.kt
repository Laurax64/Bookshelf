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

package com.example.bookshelf.model

import kotlinx.serialization.Serializable

/**
 * Represents a list of books
 */
@Serializable
data class Books(
    val items: MutableList<Item>
)

/**
 * Represent a book
 */
@Serializable
data class Item(
    val volumeInfo: VolumeInfo,
    val id: String
)

/**
 * Represents a books information
 */
@Serializable
data class VolumeInfo(
    val authors: List<String>,
    val imageLinks: ImageLinks,
    val title: String
)

/**
 * Represents a books image link
 */
@Serializable
data class ImageLinks(
    val thumbnail: String
)
