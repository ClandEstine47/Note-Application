package com.example.noteapplicatoin.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteapplicatoin.model.Note
import com.example.noteapplicatoin.util.DateConverter

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
    )
@TypeConverters(DateConverter::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao
}