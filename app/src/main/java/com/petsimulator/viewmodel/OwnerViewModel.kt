package com.petsimulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsimulator.converters.toEntity
import com.petsimulator.converters.toOwner
import com.petsimulator.database.dao.OwnerDao
import com.petsimulator.database.dao.PetDao
import com.petsimulator.model.Game
import com.petsimulator.model.Owner
import kotlinx.coroutines.launch

class OwnerViewModel(
    private val ownerDao: OwnerDao,
) : ViewModel() {

    private val _owner = MutableLiveData<Owner>()
    val owner: LiveData<Owner> get() = _owner

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    init {
        loadOwnerData()
    }

    private fun loadOwnerData() {
        viewModelScope.launch {
            val ownerWithPet = ownerDao.getOwner()
            if (ownerWithPet != null) {
                val ownerModel = ownerWithPet.toOwner()
                _owner.postValue(ownerModel)
            }
            else {
                _message.postValue("Владелец не найден в базе данных.")
            }
        }
    }

    fun setOwnerName(name: String) {
        _owner.value?.apply {
            try {
                setOwnerName(name)
                saveOwner()
            } catch (e: IllegalArgumentException) {
                _message.value = e.message
            }
        }
    }

    fun addMoney(amount: Int) {
        _owner.value?.apply {
            setMoney(money + amount)
            saveOwner()
        }
    }

    fun removeMoney(amount: Int) {
        _owner.value?.apply {
            if (money >= amount) {
                setMoney(money - amount)
                saveOwner()
            } else {
                _message.value = "Недостаточно денег!"
            }
        }
    }

    private fun saveOwner() {
        _owner.value?.let { owner ->
            viewModelScope.launch {
                ownerDao.updateOwner(owner.toEntity())
            }
        }
    }
}
