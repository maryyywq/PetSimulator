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

    private val _pets = MutableLiveData(owner.pets)
    val pets: LiveData<Map<String, Pet>> get() = _pets

    private val _inventory = MutableLiveData(owner.inventory)
    val inventory: LiveData<List<PetItem>> get() = _inventory

    // Методы управления владельцем
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

    // Методы для питомцев
    fun addPet(pet: Pet) {
        owner.addPet(pet)
        _pets.value = owner.pets
    }

    fun removePet(name: String) {
        owner.removePet(name)
        _pets.value = owner.pets
    }

    fun getPet(name: String): Pet? {
        return owner.getPet(name)
    }

    fun petPet(pet: Pet) {
        owner.pet(pet)
        updatePetState(pet)
    }

    fun playWithPet(pet: Pet, game: Game) {
        owner.play(pet, game)
        updatePetState(pet)
    }

    // Методы для инвентаря
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

    // Вспомогательный метод для обновления состояния питомца
    private fun updatePetState(pet: Pet) {
        // Принудительное обновление состояния питомцев
        _pets.value = owner.pets
    }
}
