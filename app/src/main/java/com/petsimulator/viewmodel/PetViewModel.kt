package com.petsimulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petsimulator.model.Pet
import com.petsimulator.model.PetItem
import com.petsimulator.model.PetHouse
import com.petsimulator.model.Weather

class PetViewModel(private val pet: Pet) : ViewModel() {

    // Живые данные для наблюдения за состоянием питомца
    private val _name = MutableLiveData(pet.name)
    val name: LiveData<String> get() = _name

    private val _age = MutableLiveData(pet.age)
    val age: LiveData<Int> get() = _age

    private val _satiety = MutableLiveData(pet.satiety)
    val satiety: LiveData<Int> get() = _satiety

    private val _energy = MutableLiveData(pet.energy)
    val energy: LiveData<Int> get() = _energy

    private val _health = MutableLiveData(pet.health)
    val health: LiveData<Int> get() = _health

    private val _mood = MutableLiveData(pet.mood)
    val mood: LiveData<String> get() = MutableLiveData<String>(_mood.value.toString()) // Преобразование настроения в строку для UI

    // Методы для управления питомцем
    fun feed(item: PetItem) {
        pet.use(item)
        updateState()
    }

    fun sleep(house: PetHouse) {
        pet.sleep(house)
        updateState()
    }

    fun walk(weather: Weather) {
        pet.walk(weather)
        updateState()
    }

    fun performSound() {
        pet.performSound()
    }

    fun performMove() {
        pet.performMove()
    }

    // Метод обновления состояния питомца в ViewModel
    private fun updateState() {
        _satiety.value = pet.satiety
        _energy.value = pet.energy
        _health.value = pet.health
        _mood.value = pet.mood
    }
}
