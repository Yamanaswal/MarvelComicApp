package com.yaman.marvelcomicapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.yaman.marvelcomicapp.models.Result
import com.yaman.marvelcomicapp.view_models.MainViewModel


@Composable
fun CharacterList(
    modifier: Modifier,
    listOfMarvelData: SnapshotStateList<Result>,
    listener: (SwipeRefreshState) -> Unit
) {

    val swipeRefreshState = rememberSwipeRefreshState(false)

    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        listener(swipeRefreshState) }) {
        LazyColumn(modifier = modifier) {
            itemsIndexed(listOfMarvelData) { index, item ->
                CharacterItem(index, item)
            }
        }
    }
}

