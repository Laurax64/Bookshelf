package com.example.bookshelf.fake

import com.example.bookshelf.model.Books
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.Item
import com.example.bookshelf.model.VolumeInfo

object FakeDataSource {
    val books =
        Books(listOf(Item(VolumeInfo(listOf("Author1"), ImageLinks("ImageLink1"),
                "Title1"), "id1"),
                Item(VolumeInfo(listOf("Author2, Author3"), ImageLinks("ImageLink2"),
            "Title2"), "id2"))
        )
}