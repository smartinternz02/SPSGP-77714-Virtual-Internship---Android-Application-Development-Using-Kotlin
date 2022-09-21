/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApiService
import kotlinx.coroutines.launch

enum class AmphibianApiStatus { LOADING, ERROR, DONE }

class AmphibianViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<AmphibianApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<AmphibianApiStatus> = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _amphibians = MutableLiveData<List<Amphibian>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val amphibians: LiveData<List<Amphibian>> = _amphibians

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _amphibian = MutableLiveData<Amphibian>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val amphibian: LiveData<Amphibian> = _amphibian

    init {
        getAmphibians()
    }

    private fun getAmphibians() {
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING
            try {
                _amphibians.value = AmphibianApiService.AmphibianApi.retrofitService.getAmphibians()
                _status.value = AmphibianApiStatus.DONE
            } catch (e: Exception) {
                _status.value = AmphibianApiStatus.ERROR
                _amphibians.value = listOf()
            }
        }
    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        _amphibian.value = amphibian
    }
}
