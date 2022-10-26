package com.example.dogs

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.launch

@Composable
fun DogPhotoList(vm: DogViewModel) {

    LazyColumn {
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
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)) {
                    Text("Load more doggos")
                }
            }
        }
    }
}

@Composable
fun AlphabetScroller(dogBreeds: List<DogBreed>, currScrolledInitial: String, onItemClick: (Int) -> Unit = {}) {
    val existingInitials by remember {
        derivedStateOf {
            dogBreeds.map { it -> it.name[0].uppercase() }.distinct().joinToString("")
        }
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        ('A'..'Z').forEach {
            Surface(
                color = if (currScrolledInitial == "$it") MaterialTheme.colors.primarySurface else Color.Transparent,
                shape = RoundedCornerShape(4.dp),
                //elevation = 3.dp,
                modifier = Modifier.padding(start = 3.dp).border(0.dp, Color.Transparent)) {
                Text("${it}", modifier = Modifier
                    .padding(start = 2.dp)
                    .clickable(enabled = existingInitials.contains(char = it)) {
                        val c = it
                        val i = dogBreeds.indexOfFirst { it.name.uppercase()[0] == c }
                        onItemClick(i)
                    },
                    fontWeight = if (currScrolledInitial == "$it") FontWeight.Bold else FontWeight.Normal,
                    //color = if (existingInitials.contains(char = it)) Color.Black else Color.Gray)
                    color = when {
                        currScrolledInitial == "$it" -> Color.White
                        existingInitials.contains(char = it) -> Color.Black
                        else -> Color.Gray
                    }
                )
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DogBreedNavBar(vm: DogViewModel, onDogBreedClick: (String)-> Unit = {}) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val currScrolledBreedName by remember {
        derivedStateOf {
            if (vm.dogBreeds.size > listState.firstVisibleItemIndex)
                vm.dogBreeds[listState.firstVisibleItemIndex].name
            else ""
        }
    }
    val currScrolledInitial by remember {
        derivedStateOf {
            if (!currScrolledBreedName.isEmpty()) currScrolledBreedName[0].uppercase() else ""
        }
    }

    AlphabetScroller(vm.dogBreeds, currScrolledInitial, onItemClick = {
        coroutineScope.launch {
            listState.animateScrollToItem(index = it)
        }
    })

    LazyRow (state = listState) {

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