package com.example.noteapplicatoin.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.noteapplicatoin.data.NotesDataSource
import com.example.noteapplicatoin.model.Note

class NoteHomeScreenViewModel: ViewModel() {

    var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote (note: Note) {
        noteList.add(note)
    }

    fun removeNote (note: Note) {
        noteList.remove(note)
    }

    fun getallNotes (): List<Note> {
        return noteList
    }
}