package com.example.roommvvm.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.roommvvm.R
import com.example.roommvvm.databinding.ActivityAddBinding
import com.example.roommvvm.model.Note
import com.example.roommvvm.viewmodel.NoteViewModel

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    private val noteViewModel : NoteViewModel by lazy {
        ViewModelProvider(this, NoteViewModel.NoteViewModelFactory(this.application)).get(
            NoteViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener{
            val note = Note(binding.edtTitle.text.toString(),binding.edtDescription.text.toString())
            noteViewModel.insertNote(note)
            finish()
        }
    }
}