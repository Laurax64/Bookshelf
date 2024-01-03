package com.example.bookshelf.fake

import com.example.bookshelf.model.Books
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.Item
import com.example.bookshelf.model.VolumeInfo

object FakeDataSource {
    private val items = listOf(
        Item(
            VolumeInfo(
                listOf("Author1", "Author2"),
                ImageLinks("imageLink1"),
                "Booktitle 1")
        ),)
    private  val books = Books(items)
}