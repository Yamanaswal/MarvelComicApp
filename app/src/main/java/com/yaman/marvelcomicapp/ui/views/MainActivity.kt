package com.yaman.marvelcomicapp.ui.views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModelProvider
import com.yaman.marvelcomicapp.models.Result
import com.yaman.marvelcomicapp.ui.components.CharacterList
import com.yaman.marvelcomicapp.ui.components.SearchBar
import com.yaman.marvelcomicapp.ui.theme.MarvelComicAppTheme
import com.yaman.marvelcomicapp.view_models.MainViewModel

class MainActivity : ComponentActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getMarvelCharList()

        setContent {
            MarvelComicAppTheme {
//                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetViews()
                    setObservers()
                }

            }
        }
    }

    @Composable
    fun SetViews() {
        val textState = remember { mutableStateOf(TextFieldValue("")) }

        Column {
            SearchBar(state = textState) {
                filterList(it.text)
            }
            CharacterList(Modifier.fillMaxSize(), marvelListResponse) {
                it.isRefreshing = true
            }
        }
    }

    private fun filterList(text: String) {
        if(text.isNotEmpty()){
            marvelListResponse.clear()
            val list = marvelListResponseSearch.filter { it.name.lowercase().contains(text.lowercase()) }
            marvelListResponse.addAll(list)
        }else{
            marvelListResponse.clear()
            marvelListResponse.addAll(marvelListResponseSearch)
        }
    }

    private val marvelListResponse = mutableStateListOf<Result>()
    private val marvelListResponseSearch = mutableStateListOf<Result>()

    private fun setObservers() {

        viewModel.getMarvelResponse.observe(this) { res ->
            if (res.response?.code == 200) {
                marvelListResponse.clear()
                marvelListResponse.addAll(res?.response?.data?.results!!)
                marvelListResponseSearch.addAll(res.response?.data?.results!!)
            } else {
                Toast.makeText(this, res.response?.status, Toast.LENGTH_SHORT).show()
            }
        }
    }

}

