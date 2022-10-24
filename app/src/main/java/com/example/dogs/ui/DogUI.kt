package com.example.dogs

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dogs.model.DogBreed
import com.example.dogs.model.DogPhoto
import com.example.dogs.ui.DogViewModel
import androidx.compose.runtime.*
//import com.google.accompanist.swiperefresh.SwipeRefresh
//import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun DogPhotoList(vm: DogViewModel) {
    /*
    val scrollState = rememberLazyListState()
    val endOfListReached by remember {
        derivedStateOf { scrollState.isScrolledToEnd() }
    }
     */
    LazyColumn/*(
        state = scrollState
        )*/ {
        items(vm.dogPhotos) {dogPhoto ->
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(dogPhoto.url)
                .crossfade(true)
                .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
        }
        if (vm.hasMorePhotos) {
            item {
                Button(onClick = {
                    vm.loadMore()
                }, modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    Text("Load more doggos")
                }
            }
        }

    }

    /*
    LaunchedEffect(endOfListReached) {
        Log.i("DogPhotoList", "end of list reached!")
    }
     */
}

@Composable
fun DogBreedNavBar(vm: DogViewModel, onDogBreedClick: (String)-> Unit = {}) {
    LazyRow {
        items(vm.dogBreeds) { dogBreed ->
            Button(onClick = {
                onDogBreedClick(dogBreed.name)
            }, modifier = Modifier.padding(4.dp)) {
                Text(dogBreed.name)
            }
        }
    }
}

@Composable
fun DogSubBreedList(vm: DogViewModel) {
    LazyRow {
        items(vm.getSubBreeds()) {subBreed ->
            OutlinedButton(onClick = {}, modifier = Modifier.padding(4.dp)) {
                Text(subBreed)
            }
        }
    }
}