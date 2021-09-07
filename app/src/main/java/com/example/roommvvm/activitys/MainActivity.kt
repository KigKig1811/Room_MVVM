package com.example.roommvvm.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roommvvm.R
import com.example.roommvvm.adapter.AdapterNote
import com.example.roommvvm.databinding.ActivityMainBinding
import com.example.roommvvm.model.Note
import com.example.roommvvm.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val noteViewModel : NoteViewModel by lazy {
        ViewModelProvider(this,NoteViewModel.NoteViewModelFactory(this.application)).get(NoteViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initControls()
        initEvents()


    }

    private fun initEvents() {

        binding.btnOpenAdd.setOnClickListener{
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initControls() {

        val adapter: AdapterNote = AdapterNote(this@MainActivity,onItemClick,onItemDelete  )

        binding.rvNote.setHasFixedSize(true)
        binding.rvNote.layoutManager= LinearLayoutManager(this)
        binding.rvNote.adapter = adapter

        noteViewModel.getALlNote().observe(this, Observer {
            adapter.setNotes(it)
        })
    }

    private val onItemClick: (Note)->Unit ={

        val intent = Intent(this,UpdateActivity::class.java)
        intent.putExtra("UPDATE_NOTE",it)
        startActivity(intent)
    }
    private val onItemDelete: (Note)->Unit ={
        noteViewModel.deleteNote(it)
    }
}