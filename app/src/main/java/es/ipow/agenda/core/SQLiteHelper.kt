package es.ipow.agenda.core

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.fragment.app.Fragment

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
    fun addData (name: String, last_name: String, email: String, phone: String) {
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

    // Creamos una función para BORRAR datos
    fun deleteData (id: String) : Int {
        val args = arrayOf(id)

        val db = this.writableDatabase
        // La ejecución de este comando devuelve el número de registros afectados
        val affectedRows = db.delete("contacts", "_id = ?",args)
        // Alternativamente. Pero esta forma no devuelve nada
        // db.execSQL("DELETE FROM friends WHERE _id = ?", args)
        db.close()
        return affectedRows
    }

    // Creamos una función para MODIFICAR datos
    fun updateData (id:String, name: String, last_name: String, email: String, phone: String) : Int {
        val args = arrayOf(id)

        // ContentValues tiene una estructura de tipo Map()
        val data = ContentValues()
        data.put("name", name)
        data.put("last_name", last_name)
        data.put("email", email)
        data.put("phone", phone)
        // Abro la DB en modo ESCRITURA
        val db = this.writableDatabase
        // La ejecución de este comando devuelve el número de registros afectados
        val affectedRows = db.update("contacts", data, "_id = ?",args)
        db.close()
        return affectedRows
    }
}