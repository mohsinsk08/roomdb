package com.mohsin.roomexample.database

import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    suspend fun insertData(note: Note)

    @Query("Select * from Note")
    suspend fun getAllNotes() : List<Note>

    @Update
    suspend fun updateData(note: Note)

    @Delete
    suspend fun deleteData(note: Note)
}