package com.example.roommvvm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.roommvvm.model.Note
import com.example.roommvvm.repository.NoteRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NoteViewModel(application: Application ): ViewModel() {

    private val noteRepository : NoteRepository = NoteRepository(application)

    fun insertNote(note: Note)  = viewModelScope.launch {
        noteRepository.insertNote(note)

    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)

    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)

    }

    fun getALlNote(): LiveData<List<Note>> = noteRepository.getAllNote()

    class NoteViewModelFactory(private val application: Application):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(NoteViewModel::class.java)){

                return NoteViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }

    }

}