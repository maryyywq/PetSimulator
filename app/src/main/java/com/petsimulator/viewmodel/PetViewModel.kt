package com.petsimulator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsimulator.converters.toEntity
import com.petsimulator.converters.toPet
import com.petsimulator.database.dao.PetDao
import com.petsimulator.model.Game
import com.petsimulator.model.GameDay
import com.petsimulator.model.Pet
import com.petsimulator.model.PetHouse
import com.petsimulator.model.PetItem
import kotlinx.coroutines.launch

class PetViewModel(
    private val petDao: PetDao
) : ViewModel() {

    private val _pet = MutableLiveData<Pet>()
    val pet: LiveData<Pet> get() = _pet

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    init {
        loadPetData()
    }

    private fun loadPetData() {
        viewModelScope.launch {
            val petEntity = petDao.getPet()
            if (petEntity != null) {
                val petModel = petEntity.toPet()
                _pet.postValue(petModel)
            } else {
                _message.postValue("Питомец не найден в базе данных.")
            }
        }
    }

    fun setPetName(name: String) {
        _pet.value?.apply {
            try {
                setName(name)
                savePet()
            } catch (e: IllegalArgumentException) {
                _message.value = e.message
            }
        }
    }

    fun useItem(item: PetItem) {
        _pet.value?.apply {
            try {
                val res = use(item)
                savePet()
                _message.value = res
            } catch (e: IllegalArgumentException) {
                _message.value = e.message
            }
        }
    }

    fun playWithPet(game: Game) {
        _pet.value?.apply {
            val res = play(game)
            savePet()
            _message.value = res
        }
    }

    fun walkWithPet(gameDay: GameDay) {
        _pet.value?.apply {
            val result = walk(gameDay)
            savePet()
            _message.value = result
        }
    }

    fun sleep(petHouse: PetHouse) {
        _pet.value?.apply {
            val result = sleep(petHouse)
            savePet()
            _message.value = result
        }
    }

    fun pet() {
        _pet.value?.apply {
            val result = pet()
            savePet()
            _message.value = result
        }
    }

    private fun savePet() {
        _pet.value?.let { pet ->
            viewModelScope.launch {
                petDao.updatePet(pet.toEntity())
            }
        }
    }
}
