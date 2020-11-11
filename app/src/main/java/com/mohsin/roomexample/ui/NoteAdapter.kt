package com.mohsin.roomexample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mohsin.roomexample.R
import com.mohsin.roomexample.database.Note
import kotlinx.android.synthetic.main.row_note_layout.view.*
import java.util.zip.Inflater

class NoteAdapter(val notes: List<Note>) : RecyclerView.Adapter<NoteAdapter.viewHoler>()  {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHoler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_note_layout,parent,false)

        return viewHoler(view)
    }

    override fun getItemCount(): Int {
       return notes.size
    }

    override fun onBindViewHolder(holder: viewHoler, position: Int) {

        holder.itemView.txtTitleText.text = notes[position].note_title
        holder.itemView.txtNoteText.text = notes[position].note_description

        holder.itemView.setOnClickListener {

            val action = HomeFragmentDirections.actionToAddfragment()
            action.note = notes[position]
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    class viewHoler(view: View) : RecyclerView.ViewHolder(view){

    }
}