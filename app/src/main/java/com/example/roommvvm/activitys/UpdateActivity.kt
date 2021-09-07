package com.example.roommvvm.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.roommvvm.R
import com.example.roommvvm.databinding.ActivityUpdateBinding
import com.example.roommvvm.model.Note
import com.example.roommvvm.viewmodel.NoteViewModel

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding

    private val noteViewModel : NoteViewModel by lazy {
        ViewModelProvider(this, NoteViewModel.NoteViewModelFactory(this.application)).get(
            NoteViewModel::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val note = intent.getSerializableExtra("UPDATE_NOTE") as Note
        binding.edtTitle.setText(note.title)
        binding.edtDescription.setText(note.description)

        binding.btnSubmit.setOnClickListener{
            note.title = binding.edtTitle.text.toString()
            note.description = binding.edtDescription.text.toString()
            noteViewModel.updateNote(note)
            finish()
        }
    }
}