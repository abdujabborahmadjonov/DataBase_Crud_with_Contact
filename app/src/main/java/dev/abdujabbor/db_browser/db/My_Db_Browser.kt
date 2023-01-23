package dev.abdujabbor.db_browser.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dev.abdujabbor.db_browser.model.Uset

class My_Db_Browser(context: Context) : SQLiteOpenHelper(context, DBNAME, null, 1) {

// data base ni yaratish
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME($ID integer not null primary key autoincrement unique, $NAME text not null,$NUMBER text not null )"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //databasega yozish
    fun addContact(uset: Uset) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, uset.name)
        contentValues.put(NUMBER, uset.number)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    //databasedan oqib olish
    fun getAllContacts(): ArrayList<Uset> {
        val database = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query, null)
        val list = ArrayList<Uset>()
        if (cursor.moveToFirst()) {
            do {
                val uset = Uset(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(uset)
            } while (cursor.moveToNext())
        }
        return list
    }

    //recycleviewdagi biror bir malumotni ochiradi
    fun deleteUser(uset: Uset) {
        val database = writableDatabase
        database.delete(TABLE_NAME, "$ID =?", arrayOf(uset.id.toString()))
    }


    //recycleviewdagi malumotni yangilaydi
    fun updateUser(uset: Uset): Int {
        val db = writableDatabase
        val contentValues = ContentValues()

        contentValues.put(ID, uset.id)
        contentValues.put(NAME, uset.name)
        contentValues.put(NUMBER, uset.number)

        return db.update(TABLE_NAME, contentValues, "$ID = ?", arrayOf(uset.id.toString()))
    }


    //Malumotni saqlash uchun companion object

    companion object {
        val DBNAME = "Conatact"
        val ID = "id"
        val NAME = "name"
        val NUMBER = "number"
        val TABLE_NAME = "MyContacts"

    }


}


