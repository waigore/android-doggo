package com.example.dogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dogs.model.DogBreed
import com.example.dogs.model.DogPhoto
import com.example.dogs.ui.DogViewModel
import com.example.dogs.ui.theme.DogsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val dogViewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showDogPhotos by remember {mutableStateOf(false) }

            DogsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        DogBreedNavBar(dogViewModel, onDogBreedClick = {
                            showDogPhotos = true
                            dogViewModel.refresh(dogBreedName = it)
                        })
                        if (dogViewModel.hasSubBreeds) {
                            DogSubBreedList(dogViewModel)
                        }
                        if (showDogPhotos) {
                            DogPhotoList(dogViewModel)
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    /*
    val vmPrev: DogViewModelPreview = DogViewModelPreview(
        dogBreeds = muta)
    DogsTheme {
        Column {
            DogBreedNavBar()
            DogPhotoList()
        }
    }
     */
}