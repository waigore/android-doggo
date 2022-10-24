package com.example.dogs.ui

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface IDogViewModel {
    var dogBreeds: SnapshotStateList<DogBreed>
    var dogPhotos: SnapshotStateList<DogPhoto>
    var dogPhotoStartIndex: Int
    var dogPhotoEndIndex: Int
}

@HiltViewModel
class DogViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dogRepository: DogRepository): IDogViewModel, ViewModel() {
    private val _areDogPhotosRefreshing = MutableStateFlow(false)

    val areDogPhotosRefreshing: StateFlow<Boolean>
        get() = _areDogPhotosRefreshing.asStateFlow()


    override var dogBreeds = mutableStateListOf<DogBreed>()
    override var dogPhotos = mutableStateListOf<DogPhoto>()
    override var dogPhotoStartIndex: Int = 0
    override var dogPhotoEndIndex: Int = -1

    var currentDogBreedName by mutableStateOf("")
    var hasMorePhotos by mutableStateOf(false)

    init {
        //dogBreeds.addAll(mockDogBreeds)
        //dogPhotos.addAll(mockDogPhotos)
        viewModelScope.launch {
            val l: List<DogBreed> = dogRepository.getAllBreeds()
            Log.i("DogViewModel", "breeds: ${l.size}")
            dogBreeds.addAll(l)
        }
    }

    fun refresh(dogBreedName: String) {
        viewModelScope.launch {
            _areDogPhotosRefreshing.emit(true)
            //delay(timeMillis = 2000)
            fetchInitialDogBreedPhotos(dogBreedName)
            currentDogBreedName = dogBreedName
            _areDogPhotosRefreshing.emit(false)
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            _areDogPhotosRefreshing.emit(true)
            fetchNextDogBreedPhotos(currentDogBreedName)
            _areDogPhotosRefreshing.emit(false)
        }
    }

    private suspend fun fetchInitialDogBreedPhotos(dogBreedName: String) {
        dogPhotos.clear()

        /*
        when (dogBreedName) {
            "hound" -> dogPhotos.addAll(houndPhotos)
            "spaniel" -> dogPhotos.addAll(spanielPhotos)
            else -> {
                dogPhotos.addAll(mockDogPhotos)
            }
        }
         */
        dogPhotoStartIndex = 0
        dogPhotoEndIndex = 10

        val p = dogRepository.getPhotosByBreed(dogBreedName, fromIndex = dogPhotoStartIndex, toIndex = dogPhotoEndIndex)
        dogPhotos.addAll(p.first)
        hasMorePhotos = p.second
    }

    private suspend fun fetchNextDogBreedPhotos(dogBreedName: String) {
        dogPhotoStartIndex = dogPhotoEndIndex
        dogPhotoEndIndex = dogPhotoStartIndex + 10

        val p = dogRepository.getPhotosByBreed(dogBreedName, fromIndex = dogPhotoStartIndex, toIndex = dogPhotoEndIndex)
        dogPhotos.addAll(p.first)
        hasMorePhotos = p.second
    }
}