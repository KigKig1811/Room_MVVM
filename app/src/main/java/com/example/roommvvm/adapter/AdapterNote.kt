package com.example.roommvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.roommvvm.R
import com.example.roommvvm.databinding.ItemNoteBinding
import com.example.roommvvm.model.Note

class AdapterNote
    (
    private val context : Context,
    private val onClick: (Note)->Unit,
    private val onDelete: (Note)->Unit
): RecyclerView.Adapter<AdapterNote.NoteViewHolder>() {

    private var notes: List<Note> = listOf()

    fun setNotes(notes: List<Note>){
        this.notes =notes
        notifyDataSetChanged()
    }



   inner class NoteViewHolder( itemView: View):RecyclerView.ViewHolder(itemView) {

       private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
       private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
       private val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
       private val layoutItem: CardView = itemView.findViewById(R.id.layout_item)

       fun onBind(note: Note){

           tvDescription.text = note.description
           tvTitle.text =note.title

           btnDelete.setOnClickListener{ onDelete(note)}

           layoutItem.setOnClickListener{ onClick(note)}
       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return  NoteViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(notes[position])
    }

    override fun getItemCount(): Int {
       return notes.size
    }


}