package com.example.appthitracnghiem.ui.home.category.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.SearchResponse
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel() {
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val listSearchLiveData = MutableLiveData<ArrayList<SearchResponse.Results>>()

    fun searchSubject(requestSearch: RequestSearch) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.searchSubject(requestSearch) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> listSearchLiveData.value = result.data.result
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
