package com.mohsin.roomexample.ui


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.mohsin.roomexample.R
import com.mohsin.roomexample.database.Note
import com.mohsin.roomexample.database.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class AddNoteFragment : BaseFragment() {

    private var notes:Note? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        setHasOptionsMenu(true)

        arguments?.let {
            notes = AddNoteFragmentArgs.fromBundle(it).note
            edNoteTitle.setText(notes?.note_title)
            edNoteDescription.setText(notes?.note_description)


        }

        btn_done.setOnClickListener {

            var title = edNoteTitle.text.toString().trim()
            var note = edNoteDescription.text.toString().trim()



//            doDatabaseThing(instanceNote)

            launch {
                context.let {view ->
                    var instanceNote = Note(title,note)
                    if(notes == null){

                        NoteDatabase(requireActivity()).getNoteDao().insertData(instanceNote)

                        Toast.makeText(context,"Data Saved!",Toast.LENGTH_LONG).show()
                    }else{
                        instanceNote.note_id = notes!!.note_id
                        NoteDatabase(requireActivity()).getNoteDao().updateData(instanceNote)
                        Toast.makeText(context,"Data Updated!",Toast.LENGTH_LONG).show()
                    }
                    val action = AddNoteFragmentDirections.actionToHomefragment()
                    Navigation.findNavController(it).navigate(action)


                }
            }
        }
    }

    fun deleteDialog(){
        AlertDialog.Builder(requireContext()).apply {

            setTitle("Are You Sure ?")
            setMessage("You Cannot Undo this operation")
            setPositiveButton("Yes"){_,_->
                launch {

                        NoteDatabase(requireActivity()).getNoteDao().deleteData(notes!!)
                        Toast.makeText(context,"Data Deleted!",Toast.LENGTH_LONG).show()
                        val action = AddNoteFragmentDirections.actionToHomefragment()
                        Navigation.findNavController(requireView()).navigate(action)


                }

            }
            setNegativeButton("No"){_,_->
                cancel()
            }

        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_delete,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){
            R.id.menuDelete ->{
                if(notes != null){
                    deleteDialog()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
/*
    fun doDatabaseThing(note: Note){
        class SaveData : AsyncTask<Void,Void,Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                NoteDatabase(requireActivity()).getNoteDao().insertData(note)

                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

                Toast.makeText(context,"Data Saved!",Toast.LENGTH_LONG).show()
            }

        }
        SaveData().execute()
    }
*/

}