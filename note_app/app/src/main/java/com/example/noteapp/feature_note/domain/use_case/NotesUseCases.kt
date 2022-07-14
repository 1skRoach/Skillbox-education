package com.example.noteapp.feature_note.domain.use_case

data class NotesUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNotesUseCase,
    val addNote: AddNoteUseCase,
    val getNote: GetNoteUseCase
)
