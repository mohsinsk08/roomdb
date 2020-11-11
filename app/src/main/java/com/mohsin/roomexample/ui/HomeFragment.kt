package com.mohsin.roomexample.ui

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mohsin.roomexample.R
import com.mohsin.roomexample.database.NoteDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        launch {
            val list_notes = NoteDatabase(requireActivity()).getNoteDao().getAllNotes()
            val adapter = NoteAdapter(list_notes)

            recyclerView.adapter = adapter
        }


        btnAddNote.setOnClickListener {
            val action = HomeFragmentDirections.actionToAddfragment()
            Navigation.findNavController(it).navigate(action)

        }
    }

}