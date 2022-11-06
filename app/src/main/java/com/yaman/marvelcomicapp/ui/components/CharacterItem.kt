package com.yaman.marvelcomicapp.ui.components

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import com.yaman.marvelcomicapp.models.Result
import com.yaman.marvelcomicapp.ui.views.CharDetailsActivity

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterItem(index: Int, item: Result) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), elevation = 4.dp,
        onClick =  {
            context.startActivity(Intent(context,CharDetailsActivity::class.java).putExtra("data", Gson().toJson(item)))
        }
    ) {
        Row(modifier = Modifier.padding(20.dp)) {

            Box(
                modifier = Modifier
                    .padding(end = 15.dp)
                    .height(100.dp)
                    .width(100.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.thumbnail.path + "." + item.thumbnail.extension)
                        .crossfade(true)
                        .build(),
                    contentDescription = "image",
                    //                placeholder = painterResource(R.drawable.placeholder),
                    //                contentScale = ContentScale.Crop,
                    //                modifier = Modifier.clip(CircleShape)
                )
            }
            Column() {
                Text(
                    item.name,
                    style = TextStyle(color = Color.Black),
                )
                Text(
                    item.description,
                    style = TextStyle(color = Color.Blue),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

