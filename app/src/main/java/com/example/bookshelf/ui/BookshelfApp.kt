/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.BookshelfViewModel
import com.example.bookshelf.ui.screens.BookshelfViewModel.Companion.Factory
import com.example.bookshelf.ui.screens.HomeScreen

/**
 * Displays [BookshelfTopAppBar], creates the [BookshelfViewModel] with the [Factory] companion
 * object and displays [HomeScreen]
 */
@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    Scaffold(topBar = { BookshelfTopAppBar(modifier) }){
        Surface(modifier.fillMaxSize().padding(it)) {
            val bookshelfViewModel: BookshelfViewModel = viewModel(factory = Factory)
            HomeScreen(bookshelfViewModel.bookshelfUiState, bookshelfViewModel::getBooks, modifier)
        }
    }
}

/**
 * Displays a Top Bar with the app name and a navigation icon
 */
@Composable
fun BookshelfTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Bookshelf", style = MaterialTheme.typography.titleLarge) },
        navigationIcon = { IconButton(onClick = {/*TODO*/}) { Icon(
            painter = painterResource(R.drawable.menu), contentDescription = "Menu Button") }
                         },
        modifier = modifier)
}

