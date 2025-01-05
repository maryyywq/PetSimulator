package com.petsimulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petsimulator.model.Pet
import com.petsimulator.model.PetItem

class PetItemViewModel(private val petItem: PetItem) : ViewModel() {

    // LiveData для наблюдения за полями PetItem
    private val _name = MutableLiveData(petItem.name)
    val name: LiveData<String> get() = _name

    private val _value = MutableLiveData(petItem.value)
    val value: LiveData<Int> get() = _value

    private val _cost = MutableLiveData(petItem.cost)
    val cost: LiveData<Int> get() = _cost

    private val _petUser = MutableLiveData<Class<out Pet>>(petItem.petUser)
    val petUser: LiveData<Class<out Pet>> get() = _petUser

    // Методы для изменения полей
    fun setName(newName: String) {
        petItem.setName(newName)
        _name.value = newName
    }

    fun setValue(newValue: Int) {
        petItem.setValue(newValue)
        _value.value = newValue
    }

    fun setCost(newCost: Int) {
        petItem.setCost(newCost)
        _cost.value = newCost
    }

    fun setPetUser(newPetUser: Class<out Pet>) {
        petItem.setPetUser(newPetUser)
        _petUser.value = newPetUser
    }

    // Получение базового объекта PetItem
    fun getPetItem(): PetItem {
        return petItem
    }
}
