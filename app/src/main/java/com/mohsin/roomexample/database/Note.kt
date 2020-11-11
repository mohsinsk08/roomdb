package com.mohsin.roomexample.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    var note_title: String,
    var note_description: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var note_id: Int = 0
}