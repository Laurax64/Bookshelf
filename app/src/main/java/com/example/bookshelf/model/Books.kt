package com.example.bookshelf.model

import kotlinx.serialization.Serializable

/**
 * Represents a list of books
 */
@Serializable
data class Books(
    val items: List<Item>
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
