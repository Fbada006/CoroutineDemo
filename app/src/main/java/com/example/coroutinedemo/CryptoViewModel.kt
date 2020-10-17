package com.example.coroutinedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinedemo.api.CryptoInterface
import com.example.coroutinedemo.api.RetrofitClient
import com.example.coroutinedemo.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CryptoViewModel : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()

    val uiState: LiveData<UiState>
        get() = _uiState

    private val _cryptos = MutableLiveData<List<Crypto>>()

    val cryptos: LiveData<List<Crypto>>
        get() = _cryptos

    init {
        getAllCryptos()
    }

    private fun getAllCryptos() {
        _uiState.postValue(Loading)
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val allCoins =
                    RetrofitClient.build().create(CryptoInterface::class.java).getAllCoinsData()
                _cryptos.postValue(allCoins)
                _uiState.postValue(Success)
            } catch (ex: Exception) {
                ex.printStackTrace()
                _uiState.postValue(Error(ex))
            }
        }
    }
}