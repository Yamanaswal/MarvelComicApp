package com.yaman.marvelcomicapp.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MarvelResponse(
    @PrimaryKey()
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    @Embedded
    val data: Data,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("status")
    val status: String
)

@Entity
data class Data(
    @PrimaryKey()
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    @Embedded
    var results: List<Result>,
    @SerializedName("total")
    val total: Int
)

@Entity
data class Result(
    @SerializedName("comics")
    @Embedded
    val comics: Comics,
    @SerializedName("description")
    val description: String,
    @SerializedName("events")
    @Embedded
    val events: Events,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("series")
    @Embedded
    val series: Series,
    @SerializedName("stories")
    @Embedded
    val stories: Stories,
    @SerializedName("thumbnail")
    @Embedded
    val thumbnail: Thumbnail,
    @SerializedName("urls")
    @Embedded
    val urls: List<Url>
)

@Entity
data class Comics(
    @PrimaryKey()
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("returned")
    val returned: Int
)

@Entity
data class Events(
    @PrimaryKey()
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("returned")
    val returned: Int
)

@Entity
data class Series(
    @PrimaryKey()
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("returned")
    val returned: Int
)

@Entity
data class Stories(
    @PrimaryKey()
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemXXX>,
    @SerializedName("returned")
    val returned: Int
)

@Entity
data class Thumbnail(
    @PrimaryKey()
    @SerializedName("extension")
    val extension: String,
    @SerializedName("path")
    val path: String
)

@Entity
data class Url(
    @PrimaryKey()
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)

@Entity
data class Item(
    @PrimaryKey()
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)

@Entity
data class ItemXXX(
    @PrimaryKey()
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("type")
    val type: String
)
const val MarvelListData :  String = "marvel_list_data"