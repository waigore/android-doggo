package com.example.dogs.model

data class DogBreed(val name: String) {

}

data class DogPhoto(val url: String) {

}

val mockDogBreeds: List<DogBreed> = listOf(
    DogBreed(name = "Affenpinscher"),
    DogBreed(name = "Alsatian"),
    DogBreed(name = "Akita"),
    DogBreed(name = "Dalmatian")
)

val mockDogPhotos: List<DogPhoto> = listOf(
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_10715.jpg")
)