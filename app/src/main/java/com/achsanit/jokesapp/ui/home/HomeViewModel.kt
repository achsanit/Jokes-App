package com.achsanit.jokesapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achsanit.jokesapp.data.Repository
import com.achsanit.jokesapp.data.response.ResultItem
import com.achsanit.jokesapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    val query: MutableStateFlow<String> = MutableStateFlow("")

    private val _jokes : MutableLiveData<Resource<List<ResultItem?>?>> = MutableLiveData()
    val jokes : LiveData<Resource<List<ResultItem?>?>> = _jokes

    private val _randomJoke: MutableLiveData<Resource<ResultItem?>> = MutableLiveData()
    val randomJoke: LiveData<Resource<ResultItem?>> = _randomJoke

    fun searchJokes(query: String) {
        _jokes.postValue(Resource.Loading())
        viewModelScope.launch {
            _jokes.postValue(repository.listJokes(query))
        }
    }

    fun randomJoke() {
        _randomJoke.postValue(Resource.Loading())
        viewModelScope.launch {
            _randomJoke.postValue(repository.randomJoke())
        }
    }

}