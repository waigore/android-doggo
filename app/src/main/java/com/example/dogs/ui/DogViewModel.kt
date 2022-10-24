package com.example.dogs.ui

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.dogs.model.DogBreed
import com.example.dogs.model.DogPhoto
import com.example.dogs.model.mockDogBreeds
import com.example.dogs.model.mockDogPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface IDogViewModel {
    var dogBreeds: SnapshotStateList<DogBreed>
    var dogPhotos: SnapshotStateList<DogPhoto>
}

class DogViewModelPreview(
    override var dogBreeds: SnapshotStateList<DogBreed>,
    override var dogPhotos: SnapshotStateList<DogPhoto>) : IDogViewModel {
}

@HiltViewModel
class DogViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle): IDogViewModel, ViewModel() {
    override var dogBreeds = mutableStateListOf<DogBreed>()
    override var dogPhotos = mutableStateListOf<DogPhoto>()

    init {
        dogBreeds.addAll(mockDogBreeds)
        dogPhotos.addAll(mockDogPhotos)
    }
}