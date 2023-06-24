package com.example.noteapplicatoin.data

import com.example.noteapplicatoin.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "Grayscale", description = "Endless runner game developed my Mausam Saru Magar."),
            Note(title = "AR Furniture", description = "Augmented reality based application developed for placing 3D furnitures in the real world environment."),
            Note(title = "Canvas", description = "Canvas is a framework that is used in Android Studio for designing creating shapes along with animations."),
            Note(title = "LinkedIn", description = "LinkedIn is an application that is used by employers and employee. It is an open source software that is used by both parties to provide and get jobs."),
            Note(title = "Facebook", description = "Facebook aka Meta is social media application initially developed by Mark Zuberg and his team for communicating people online via application. Now it's one of the mostly used social media application that has ever been created.")
        )
    }
}