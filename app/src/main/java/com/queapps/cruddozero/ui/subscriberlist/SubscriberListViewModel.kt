package com.queapps.cruddozero.ui.subscriberlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.queapps.cruddozero.data.db.entity.SubscriberEntity
import com.queapps.cruddozero.repository.SubscriberRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SubscriberListViewModel(
    private val subscriberRepository: SubscriberRepository,
) : ViewModel() {

    private val _allSubscribersEvent = MutableLiveData<List<SubscriberEntity>>()
    val allSubscribersEvent: LiveData<List<SubscriberEntity>>
        get() = _allSubscribersEvent

    fun getSubscribers() = viewModelScope.launch {
        subscriberRepository.getAllSubscribers().collectLatest {
            _allSubscribersEvent.postValue(it)
        }
    }
}
