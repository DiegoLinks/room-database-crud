package com.example.roomdatabasecrud

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.util.*

class ContactsListAdapter internal constructor(
        context: Context/*, val btDelete: DeleteClickListener*/
) : RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contact = Collections.emptyList<Contact>() // Cached copy of contact

    var deleteClickListener: DeleteClickListener? = null
    var changeClickListener: ChangeClickListener? = null

    interface DeleteClickListener {
        fun onBtnClick(position: Int)
    }

    interface ChangeClickListener {
        fun onBtnClick(contact: Contact)
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val phone: TextView = itemView.findViewById(R.id.tvPhoneNumber)
        val delete: Button = itemView.findViewById(R.id.btDelete)
        val change: Button = itemView.findViewById(R.id.btChange)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = inflater.inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = contact[position]
        holder.name.text = current.name
        holder.phone.text = current.phoneNumber
        holder.delete.setOnClickListener {
            deleteClickListener?.onBtnClick(current.id!!)
        }
        holder.change.setOnClickListener {
            changeClickListener?.onBtnClick( contact[position])
        }
    }

    internal fun setContacts(words: List<Contact>) {
        this.contact = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = contact.size
}


