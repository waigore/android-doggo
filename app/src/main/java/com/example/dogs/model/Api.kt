package com.example.dogs.model

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object DogApiHelper {
    val baseUrl = "https://dog.ceo/"

    fun getInstance(): Retrofit {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}

interface DogApi {
    @GET("/api/breeds/list/all")
    suspend fun getBreeds(): Response<DogBreedList>

    @GET("/api/breed/{breedName}/images")
    suspend fun getPhotos(@Path("breedName") breedName: String): Response<DogPhotoList>
}