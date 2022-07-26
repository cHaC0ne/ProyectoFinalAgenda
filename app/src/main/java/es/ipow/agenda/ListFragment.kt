package es.ipow.agenda

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import es.ipow.agenda.adapter.ContactAdapter
import es.ipow.agenda.core.SQLiteHelper
import es.ipow.agenda.databinding.FragmentDeleteBinding
import es.ipow.agenda.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _b : FragmentListBinding? = null
    private val b get() = _b!!
    private lateinit var contactsDBHelper : SQLiteHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentListBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsDBHelper = SQLiteHelper(this.context!!)
        initRecyclerView()
    }

    fun initRecyclerView(){
//        val contactList = listOf<Contact>(
//            Contact("2","Marcos", "Redfor", "m@m.com","6655449988"),
//            Contact("3","Pepe", "Perez", "p@m.com","6655449988"))
        b.rvContacts.layoutManager = LinearLayoutManager(this.context)
        b.rvContacts.adapter = ContactAdapter(sqliteToList())
    }

    fun sqliteToList():MutableList<Contact> {
        // Abro la base de datos en modo LECTURA
        val db : SQLiteDatabase = contactsDBHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM contacts", null)
        val contactList = mutableListOf<Contact>()
        // Compruebo si hay algún registro
        if (cursor.moveToFirst()) {
            do {
                contactList.add(Contact(
                    cursor.getInt(0).toString(),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return contactList
    }

}