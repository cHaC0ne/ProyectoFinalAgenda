package es.ipow.agenda

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.ipow.agenda.core.GetSpinner
import es.ipow.agenda.core.SQLiteHelper
import es.ipow.agenda.core.hideKeyboard
import es.ipow.agenda.core.toast
import es.ipow.agenda.databinding.FragmentUpdateBinding

class updateFragment : Fragment() {
    private var _b : FragmentUpdateBinding? = null
    private val b get() = _b!!
    private lateinit var contactsDBHelper : SQLiteHelper
    private val spnOpt = arrayOf("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentUpdateBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsDBHelper = SQLiteHelper(this.context!!)
        GetSpinner(b.spinnerContactUpdate, spnOpt, sqliteToArray())

        b.btnUpdate.setOnClickListener {
            val endIndex = spnOpt[0].indexOf(':')
            val id = spnOpt[0].substring(0, endIndex)
            val affectedRows = contactsDBHelper.updateData(id,
                b.etNameUpdate.text.toString(),
                b.etLastNameUpdate.text.toString(),
                b.etMailUpdate.text.toString(),
                b.etPhoneUpdate.text.toString())
            hideKeyboard()
            b.etNameUpdate.text.clear()
            b.etLastNameUpdate.text.clear()
            b.etMailUpdate.text.clear()
            b.etPhoneUpdate.text.clear()
            GetSpinner(b.spinnerContactUpdate, spnOpt, sqliteToArray())
            if (affectedRows > 0) {
                toast("Has modificado $affectedRows registros")
            } else {
                toast("No se han modificado registros")
            }
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
                        cursor.getString(1) + " " + cursor.getString(2))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return sqliteData.toTypedArray()
    }

}