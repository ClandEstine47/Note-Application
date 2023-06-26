package com.example.noteapplicatoin.screen

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapplicatoin.data.NotesDataSource
import com.example.noteapplicatoin.model.Note
import com.example.noteapplicatoin.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteHomeScreenViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()
//    var noteList = mutableStateListOf<Note>()

    init {
//        noteList.addAll(NotesDataSource().loadNotes())
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNotes().distinctUntilChanged()
                .collect {listofNotes ->
//                    if (listofNotes.isNotEmpty()) {
                        _noteList.value = listofNotes
//                    } else {
//                        Log.d("Empty", "msg: Empty list")
//                    }
                }
        }
    }

    fun addNote (note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun updateNote (note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun removeNote (note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

//    fun getallNotes (): List<Note> {
//        return noteList
//    }
}