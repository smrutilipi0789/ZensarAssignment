package com.example.dogapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
class DogViewModel(val repository: DoggoImagesRepository = DoggoImagesRepository.getInstance()
) : ViewModel() {

    fun fetchDoggoImagesLiveData(): LiveData<PagingData<String>> {
        return repository.letDoggoImagesLiveData()
            .map { it.map { it.url } }
            .cachedIn(viewModelScope)
    }
}