package com.petsimulator.viewmodel

import android.app.Application
import android.net.ipsec.ike.exceptions.InvalidMajorVersionException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsimulator.converters.toEntity
import com.petsimulator.converters.toItems
import com.petsimulator.converters.toOwner
import com.petsimulator.converters.toPet
import com.petsimulator.database.AppDatabase
import com.petsimulator.database.dao.ItemDao
import com.petsimulator.database.dao.OwnerDao
import com.petsimulator.database.dao.PetDao
import com.petsimulator.model.Color
import com.petsimulator.model.Game
import com.petsimulator.model.GameDay
import com.petsimulator.model.Owner
import com.petsimulator.model.Pet
import com.petsimulator.model.PetHouse
import com.petsimulator.model.PetItem
import com.petsimulator.model.Sex
import kotlinx.coroutines.launch

class OwnerViewModel(
    application: Application
) : ViewModel() {

    private val ownerDao: OwnerDao = AppDatabase.getInstance(application).ownerDao()
    private val petDao: PetDao = AppDatabase.getInstance(application).petDao()
    private val itemDao: ItemDao = AppDatabase.getInstance(application).itemDao()

    private val _owner = MutableLiveData<Owner>()
    val owner: LiveData<Owner> get() = _owner

    private val _pet = MutableLiveData<Pet>()
    val pet: LiveData<Pet> get() = _pet

    private val _items = MutableLiveData<MutableList<PetItem>>()
    val items: LiveData<MutableList<PetItem>> get() = _items

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    init {
        loadOwnerData()
    }

    private fun loadOwnerData() {
        viewModelScope.launch {
            val owner = ownerDao.getOwner()
            val pet = petDao.getPet()
            val items = itemDao.getItems()

            if (owner != null && pet != null) {
                val petModel = pet.toPet()
                val itemsModel = items.toItems()

                val ownerModel = owner.toOwner(pet = petModel, items = itemsModel)
                _owner.postValue(ownerModel)
                _pet.postValue(petModel)
                _items.postValue(itemsModel.toMutableList())
            }
            else {
                _message.postValue("Информация не найдена.")
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

    fun setPet(pet: Pet) {
        _owner.value?.apply {
            try {
                setPet(pet)
                _pet.postValue(pet)
                savePet()
            } catch (e: IllegalArgumentException) {
                _message.value = e.message
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

    fun setPetColor(color: Color) {
        _pet.value?.apply {
            try {
                setColor(color)
                savePet()
            } catch (e: IllegalArgumentException) {
                _message.value = e.message
            }
        }
    }

    fun setPetSex(sex: Sex) {
        _pet.value?.apply {
            try {
                setSex(sex)
                savePet()
            } catch (e: IllegalArgumentException) {
                _message.value = e.message
            }
        }
    }

    fun addItem(item: PetItem) {
        _items.value?.apply {
            try {
                items.value?.add(item)
                addItemDB(item)
            }
            catch (e: Exception) {
                _message.value = e.message
            }
        }
    }

    fun deleteItem(item: PetItem) {
        _items.value?.apply {
            try {
                items.value?.remove(item)
                deleteItemDB(item)
            }
            catch (e: Exception) {
                _message.value = e.message
            }
        }
    }

    private fun addItemDB(item: PetItem) {
        viewModelScope.launch {
            itemDao.insertItem(item.toEntity())
        }
    }

    private fun deleteItemDB(item: PetItem) {
        viewModelScope.launch {
            itemDao.deleteItem(item.toEntity())
        }
    }

    fun useItem(item: PetItem) {
        _pet.value?.apply {
            try {
                if (items.value?.contains(item) == true) {
                    val res = use(item)
                    savePet()
                    deleteItem(item)

                    _message.value = res
                }
                else throw IllegalArgumentException("Неизвестный предмет!")
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

    fun petYourPet() {
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
