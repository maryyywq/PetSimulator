package com.petsimulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petsimulator.model.*

class OwnerViewModel(private val owner: Owner) : ViewModel() {

    // Живые данные для наблюдения
    private val _ownerName = MutableLiveData(owner.ownerName)
    val ownerName: LiveData<String> get() = _ownerName

    private val _ownerAge = MutableLiveData(owner.ownerAge)
    val ownerAge: LiveData<Int> get() = _ownerAge

    private val _money = MutableLiveData(owner.money)
    val money: LiveData<Int> get() = _money

    private val _pet = MutableLiveData(owner.pet)
    val pet: LiveData<Pet> get() = _pet

    private val _inventory = MutableLiveData(owner.inventory)
    val inventory: LiveData<List<PetItem>> get() = _inventory

    fun setOwnerName(name: String) {
        owner.setOwnerName(name)
        _ownerName.value = name
    }

    fun setOwnerAge(age: Int) {
        owner.setOwnerAge(age)
        _ownerAge.value = age
    }

    fun setMoney(amount: Int) {
        owner.setMoney(amount)
        _money.value = amount
    }

    fun getPet(): Pet? {
        return owner.pet
    }

    fun petPet() {
        owner.pet()
        updatePetState()
    }

    fun playWithPet(game: Game) {
        owner.play(game)
        updatePetState()
    }

    //Методы для инвентаря
    fun addItem(item: PetItem) {
        owner.addItem(item)
        _inventory.value = owner.inventory
    }

    fun removeItem(name: String) {
        owner.removeItem(name)
        _inventory.value = owner.inventory
    }

    fun getItem(name: String): PetItem? {
        return owner.getItem(name)
    }

    fun sortItemsByValue() {
        owner.sortItemsByValue()
        _inventory.value = owner.inventory
    }

    fun sortItemsByCost() {
        owner.sortItemsByCost()
        _inventory.value = owner.inventory
    }

    //Вспомогательный метод для обновления состояния питомца
    private fun updatePetState() {
        //Принудительное обновление состояния питомцев
        _pet.value = owner.pet
    }
}
