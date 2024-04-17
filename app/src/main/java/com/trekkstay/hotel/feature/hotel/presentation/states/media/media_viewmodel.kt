package com.trekkstay.hotel.feature.hotel.presentation.states.media

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekkstay.hotel.feature.hotel.domain.repositories.MediaRepo
import kotlinx.coroutines.launch

class MediaViewModel(private val mediaRepo: MediaRepo) : ViewModel() {
    private val _state = MutableLiveData<MediaState>()
    val state: LiveData<MediaState> = _state

    fun processAction(action: MediaAction) {
        viewModelScope.launch {
            when (action) {
                is UploadMediaAction -> {
                    _state.postValue(MediaState.UploadMediaCalling)
                    val result = mediaRepo.uploadMedia(action.media,action.extension)
                    result.fold(
                        { failure -> _state.postValue(MediaState.InvalidUploadMedia(failure.message)) },
                        { media ->  _state.postValue(MediaState.SuccessUploadMedia(media)) }
                    )
                }

            }
        }
    }
}


