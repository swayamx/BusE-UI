package com.example.buse_ui.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buse_ui.data.models.MapsData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapsViewModel(
    private val weatherRepository: MapsRepository
) : ViewModel() {


    private val _locationQueryData = MutableStateFlow<MapsData?>(null)
    val locationQueryData = _locationQueryData.asStateFlow()


    private val _showErrorChannel = Channel<Boolean>()
    val showErrorChannel = _showErrorChannel.receiveAsFlow()


    fun fetchLocationQueryData(
        origin: String,
        destination: String,
        key: String
    ) {
        viewModelScope.launch {


            weatherRepository.locationQuery(origin, destination, key).collectLatest { result ->
                when (result) {
                    is Response.Error -> {
                        _showErrorChannel.send(true)
                    }

                    is Response.Success -> {
                        result.data?.let { data ->
                            _locationQueryData.update {
                                data
                            }
                        }
                    }
                }
            }
        }
    }
}