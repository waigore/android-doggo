package com.example.dogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dogs.model.DogBreed
import com.example.dogs.model.DogPhoto
import com.example.dogs.ui.DogViewModel

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
    }
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