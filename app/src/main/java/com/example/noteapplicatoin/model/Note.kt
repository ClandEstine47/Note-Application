package com.example.noteapplicatoin.model

import java.time.LocalDateTime
import java.util.UUID

data class Note  constructor(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
