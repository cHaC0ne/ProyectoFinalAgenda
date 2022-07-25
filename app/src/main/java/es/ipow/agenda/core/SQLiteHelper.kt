package es.ipow.agenda.core

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context):SQLiteOpenHelper (context, "addressBook.db",null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Esto debe hacerse dentro de un bloque try-catch
        val commandCreate = "CREATE TABLE contacts (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "last_name TEXT," +
                "email TEXT," +
                "phone TEXT)"
        db!!.execSQL(commandCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    // Creamos una función para añadir datos
    fun addData (name: String, last_name: String, email: String, phone: String,) {
        // ContentValues tiene una estructura de tipo Map()
        val data = ContentValues()
        data.put("name", name)
        data.put("last_name", last_name)
        data.put("email", email)
        data.put("phone", phone)
        // Abro la DB en modo ESCRITURA
        val db = this.writableDatabase
        db.insert("contacts", null,data)
        db.close()
    }
}