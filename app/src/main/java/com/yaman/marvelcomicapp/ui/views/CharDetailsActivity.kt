package com.yaman.marvelcomicapp.ui.views

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.gson.Gson
import com.yaman.marvelcomicapp.ui.views.ui.theme.MarvelComicAppTheme
import com.yaman.marvelcomicapp.models.Result

class CharDetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val data = intent.getStringExtra("data")
            val result = Gson().fromJson(data, Result::class.java)
            Log.e("TAG", "onCreate: $result")

            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
//                    Text(text = "HIIII")
                SetViews(result)
            }
        }
    }

    @Composable
    private fun SetViews(result: Result) {

        Card(modifier = Modifier.fillMaxWidth(), elevation = 2.dp) {
            Column {
                Box(Modifier.padding(15.dp).fillMaxWidth() ) {
                    SetImageViewAsync(image = result.thumbnail.path + "." + result.thumbnail.extension)
                }
                Text(
                    text = "Character Name",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    color = Color.Black
                )
                Text(
                    text = result.name,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    color = Color.Blue
                )
                Text(
                    text = "Description",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    color = Color.Black
                )
                Text(
                    text = result.description,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    color = Color.Blue
                )

            }

        }

    }

    @Composable
    fun SetImageViewAsync(image: String) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = "image",
            //                placeholder = painterResource(R.drawable.placeholder),
            //                contentScale = ContentScale.Crop,
            //                modifier = Modifier.clip(CircleShape)
        )
    }
}