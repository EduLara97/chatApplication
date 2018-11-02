package com.example.edulara.chatapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_contact.view.*

/**
 * Created by Edwin on 2/11/2018.
 */
class ChatsAdapter(private val contacs : List<Contact>,
                   private val context:Context) : RecyclerView.Adapter<ChatsAdapter.Holder>() {

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        val contact = contacs[p1]
        p0.tvName.text = contact.name
        p0.tvEmail.text = contact.email
        if(contact.image != null || contact.image==""){
            Glide.with(context)
                    .load(contact.image)
                    .into(p0.ivImage)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.item_contact, p0, false))
    }

    override fun getItemCount(): Int {
        return contacs.size
    }

    class Holder(view: View): RecyclerView.ViewHolder(view){
        val tvName = view.tvName!!
        val tvEmail = view.tvEmail!!
        val ivImage = view.ivContact!!
    }
}