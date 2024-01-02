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

@Serializable
data class Books(
    val kind: String,
    val totalItems: Int,
    val items: List<Item>
)

@Serializable
data class Item(
    val accessInfo: AccessInfo,
    val etag: String,
    val id: String,
    val kind: String,
    val saleInfo: SaleInfo,
    val searchInfo: SearchInfo,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class AccessInfo(
    val accessViewStatus: String,
    val country: String,
    val embeddable: Boolean,
    val epub: Epub,
    val pdf: Pdf,
    val publicDomain: Boolean,
    val quoteSharingAllowed: Boolean,
    val textToSpeechPermission: String,
    val viewability: String,
    val webReaderLink: String
)

@Serializable
data class Epub(
    val acsTokenLink: String,
    val isAvailable: Boolean
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)

@Serializable
data class IndustryIdentifier(
    val identifier: String,
    val type: String
)

@Serializable
data class SearchInfo(
    val textSnippet: String
)

@Serializable
data class ListPrice(
    val amount: Double,
    val currencyCode: String
)

@Serializable
data class ListPriceX(
    val amountInMicros: Int,
    val currencyCode: String
)
@Serializable
data class Offer(
    val finskyOfferType: Int,
    val giftable: Boolean,
    val listPrice: ListPriceX,
    val retailPrice: RetailPrice
)

@Serializable
data class PanelizationSummary(
    val containsEpubBubbles: Boolean,
    val containsImageBubbles: Boolean
)

@Serializable
data class Pdf(
    val acsTokenLink: String,
    val isAvailable: Boolean
)

@Serializable
data class ReadingModes(
    val image: Boolean,
    val text: Boolean
)

@Serializable
data class RetailPrice(
    val amountInMicros: Int,
    val currencyCode: String
)

@Serializable
data class RetailPriceX(
    val amount: Double,
    val currencyCode: String
)

@Serializable
data class SaleInfo(
    val buyLink: String,
    val country: String,
    val isEbook: Boolean,
    val listPrice: ListPrice,
    val offers: List<Offer>,
    val retailPrice: RetailPriceX,
    val saleability: String
)

@Serializable
data class VolumeInfo(
    val allowAnonLogging: Boolean,
    val authors: List<String>,
    val averageRating: Int,
    val canonicalVolumeLink: String,
    val categories: List<String>,
    val contentVersion: String,
    val description: String,
    val imageLinks: ImageLinks,
    val industryIdentifiers: List<IndustryIdentifier>,
    val infoLink: String,
    val language: String,
    val maturityRating: String,
    val pageCount: Int,
    val panelizationSummary: PanelizationSummary,
    val previewLink: String,
    val printType: String,
    val publishedDate: String,
    val publisher: String,
    val ratingsCount: Int,
    val readingModes: ReadingModes,
    val subtitle: String,
    val title: String
)