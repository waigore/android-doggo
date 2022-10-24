package com.example.dogs.model

import android.util.Log
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepository @Inject constructor () {
    val dogApi = DogApiHelper.getInstance().create(DogApi::class.java)

    suspend fun getAllBreeds(): List<DogBreed> {
        val response: Response<DogBreedList> = dogApi.getBreeds()
        if (response.isSuccessful) {
            Log.i("DogRepository", "Breed response is successful...")
            val dogBreedList: DogBreedList = response.body()!!
            return dogBreedList.message.keys.map { DogBreed(name = it, subBreeds = dogBreedList.message[it] ?: emptyList()) }
        }
        else {
            Log.w("DogRepository", "Response has error...${response.errorBody()?.string()}")

            return emptyList()
        }
    }

    suspend fun getPhotosByBreed(breedName: String, fromIndex: Int = 0, toIndex: Int = -1): Pair<List<DogPhoto>, Boolean> {
        val response: Response<DogPhotoList> = dogApi.getPhotos(breedName)
        if (response.isSuccessful) {
            Log.i("DogRepository", "Photo response is successful...")
            val dogPhotoList: DogPhotoList = response.body()!!
            val mappedList = dogPhotoList.message.map { DogPhoto(url = it) }
            val actualToIndex = if (toIndex >= mappedList.size) mappedList.size else toIndex
            return Pair(if (toIndex != -1) mappedList.subList(fromIndex, actualToIndex) else mappedList, mappedList.size -1 > toIndex)
        }
        else {
            Log.w("DogRepository", "Response has error...${response.errorBody()?.string()}")
            return Pair(emptyList(), false)
        }
    }
}