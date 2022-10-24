package com.example.dogs.model

data class DogBreedList(
    val message: Map<String, List<String>>
)

data class DogPhotoList(
    val message: List<String>
)

data class DogBreed(val name: String) {

}

data class DogPhoto(val url: String) {

}

val mockDogBreeds: List<DogBreed> = listOf(
    DogBreed(name = "hound"),
    DogBreed(name = "spaniel"),
)

val mockDogPhotos: List<DogPhoto> = listOf(
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_10715.jpg")
)

val houndPhotos: List<DogPhoto> = listOf(
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_10715.jpg")
)

val spanielPhotos: List<DogPhoto> = listOf(
    DogPhoto(url = "https://images.dog.ceo/breeds/spaniel-blenheim/n02086646_1002.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/spaniel-blenheim/n02086646_1077.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/spaniel-blenheim/n02086646_1094.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/spaniel-blenheim/n02086646_1150.jpg"),
    DogPhoto(url = "https://images.dog.ceo/breeds/spaniel-blenheim/n02086646_118.jpg")
)