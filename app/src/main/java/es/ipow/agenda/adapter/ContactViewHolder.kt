package es.ipow.agenda.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ipow.agenda.Contact
import es.ipow.agenda.R

class ContactViewHolder (view: View): RecyclerView.ViewHolder(view){
    val name = view.findViewById<TextView>(R.id.tvName)

    fun render(contactModel: Contact){
        name.text = contactModel.name
    }
}