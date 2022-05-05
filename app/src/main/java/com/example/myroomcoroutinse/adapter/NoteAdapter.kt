package com.example.myroomcoroutinse.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

import com.example.myroomcorotiunes.model.Note
import com.example.myroomcoroutinse.R

class NoteAdapter(var con:Context,private val onClick:(Note)->Unit,private val onDelete:(Note)->Unit): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var noteList:List<Note> = listOf()
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val tv_desc  =itemView.findViewById(R.id.text_item_description) as TextView
        val tv_title =itemView.findViewById(R.id.text_item_title) as TextView
        val img_delete =itemView.findViewById(R.id.btn_delete) as ImageView
        val layout_item =itemView.findViewById(R.id.layout_item) as ConstraintLayout

        fun onBind(note: Note){
            tv_desc .text=note.description
            tv_title.text=note.title

            img_delete.setOnClickListener { onDelete(note) }
            layout_item.setOnClickListener { onClick(note) }

        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(con).inflate(R.layout.note_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.onBind(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun setNotes(noteL:List<Note>){
        this.noteList = noteL
        notifyDataSetChanged()
    }
}