package com.guru.virtandroid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SharedViewModel:ViewModel() {


    val  input1:MutableLiveData<String> = MutableLiveData<String>("")
    val  input2:MutableLiveData<String> = MutableLiveData<String>("")


    val emailList:MutableLiveData<List<String>> = MutableLiveData(emptyList())


    fun fetchList()
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                emailList.postValue(Repo().fetchEmailLists(listOf(1,3,10)))
            }
        }
    }
}