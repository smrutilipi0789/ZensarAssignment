package com.example.dogapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
class DogViewModel(val repository: DoggoImagesRepository = DoggoImagesRepository.getInstance()
) : ViewModel() { {

    val mDogRepository: DoggoImagesRepository

//    val allDogCollection: LiveData<List<DogData>>
//        get() = mDogRepository.getMu
//        init {
//        mDogRepository = DogRepository()
//    }
    fun fetchDoggoImagesObservable(): Observable<PagingData<String>> {
        return mDogRepository.letDoggoImagesObservable()
            .map { it.map { it.url } }
            .cachedIn(viewModelScope)tableLiveData()

    }
}