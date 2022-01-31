package com.github.juanncode.challengereign.ui.main

import androidx.lifecycle.*
import com.github.juanncode.challengereign.data.database.Hit
import com.github.juanncode.challengereign.data.repository.HitRepository
import kotlinx.coroutines.launch

class MainViewModel(private val hitRepository: HitRepository): ViewModel() {

    sealed class UiHit {
        object Loading : UiHit()
        class GetData(val hits: List<Hit>): UiHit()
        class DeleteHit(val hits: List<Hit>): UiHit()
    }

    private val _modelHit = MutableLiveData<UiHit>()
    val modelHit: LiveData<UiHit> get() = _modelHit

    init {
        loadHit()
    }

    fun loadHit() {
        viewModelScope.launch {
            _modelHit.value = UiHit.Loading
            _modelHit.value = UiHit.GetData(hitRepository.getAllHits())
        }
    }

    fun removeHit(hit: Hit) {
        viewModelScope.launch {
            _modelHit.value = UiHit.DeleteHit(hitRepository.deleteHitDb(hit))
        }
    }

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val hitRepository: HitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(hitRepository) as T
    }
}