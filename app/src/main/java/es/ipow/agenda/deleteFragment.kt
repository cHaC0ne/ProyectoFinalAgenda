package es.ipow.agenda

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.ipow.agenda.core.GetSpinner
import es.ipow.agenda.core.SQLiteHelper
import es.ipow.agenda.databinding.FragmentDeleteBinding

class deleteFragment : Fragment() {
    private var _b : FragmentDeleteBinding? = null
    private val b get() = _b!!
    private lateinit var contactsDBHelper : SQLiteHelper
    private val spnOpt = arrayOf("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentDeleteBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsDBHelper = SQLiteHelper(this.context!!)
        val arrLenguajes = arrayOf("Python", "C++", "Java", "Kotlin", "PHP", "Eiffel", "Pascal", "Basic")
        GetSpinner(b.spinnerContactDelete, spnOpt, sqliteToArray())

        b.btnDel.setOnClickListener {

        }
    }

    fun sqliteToArray():Array<String> {
        val sqliteData = arrayListOf<String>()
        // Abro la base de datos en modo LECTURA
        val db : SQLiteDatabase = contactsDBHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM contacts", null)

        // Compruebo si hay algún registro
        if (cursor.moveToFirst()) {
            do {
                sqliteData.add(cursor.getInt(0).toString() + ": " +
                        cursor.getString(1).toString())
            } while (cursor.moveToNext())
        }
        cursor.close()
        return sqliteData.toTypedArray()
    }

}