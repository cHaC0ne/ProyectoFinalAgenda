package es.ipow.agenda.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ipow.agenda.Contact
import es.ipow.agenda.R
import es.ipow.agenda.databinding.LayoutItemBinding

class ContactViewHolder (view: View): RecyclerView.ViewHolder(view){
    val b = LayoutItemBinding.bind(view) // layout_item.xml

    fun render(contactModel: Contact){
        b.tvId.text = contactModel.id
        b.tvName.text = contactModel.name
        b.tvSurName.text = contactModel.last_name
        b.tvMail.text = contactModel.mail
        b.tvPhone.text = contactModel.phone
    }
}