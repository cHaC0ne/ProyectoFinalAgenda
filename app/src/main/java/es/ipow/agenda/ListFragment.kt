package es.ipow.agenda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import es.ipow.agenda.adapter.ContactAdapter
import es.ipow.agenda.databinding.FragmentDeleteBinding
import es.ipow.agenda.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _b : FragmentListBinding? = null
    private val b get() = _b!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentListBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    fun initRecyclerView(){
        val contactList = listOf<Contact>(
            Contact("Marcos", "Redfor", "m@m.com","6655449988"),
            Contact("Pepe", "Perez", "p@m.com","6655449988"))
        b.rvContacts.layoutManager = LinearLayoutManager(this.context)
        b.rvContacts.adapter = ContactAdapter(contactList)
    }

}