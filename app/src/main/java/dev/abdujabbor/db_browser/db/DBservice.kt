package dev.abdujabbor.db_browser.db

import dev.abdujabbor.db_browser.model.Uset

interface MyDBService {

    fun createUser(user: Uset)

    fun readUset():List<Uset>

    fun updateUset(user: Uset):Int

    fun deleteUset(user: Uset)

}