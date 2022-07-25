package es.ipow.agenda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import es.ipow.agenda.core.SQLiteHelper
import es.ipow.agenda.databinding.FragmentNewBinding

class NewFragment : Fragment() {
    private var _b : FragmentNewBinding? = null
    private val b get() = _b!!
    private lateinit var contactsDBHelper : SQLiteHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentNewBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsDBHelper = SQLiteHelper(this.context!!)
        b.btnNew.setOnClickListener {
            if (b.etNameNew.text.isNotBlank() &&
                b.etMailNew.text.isNotBlank()){
                contactsDBHelper.addData(
                    b.etNameNew.text.toString(),
                    b.etLastNameNew.text.toString(),
                    b.etMailNew.text.toString(),
                    b.etPhoneNew.text.toString())
                b.etNameNew.text.clear()
                b.etLastNameNew.text.clear()
                b.etMailNew.text.clear()
                b.etPhoneNew.text.clear()
                toast("¡Guardado!")

            } else {
                toast("No se ha podido guardar", Toast.LENGTH_LONG)
            }
        }
    }

    fun toast(text:String, length:Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this.context, text, length).show()
    }
}