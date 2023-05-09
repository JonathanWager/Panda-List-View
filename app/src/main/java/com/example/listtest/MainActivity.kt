package com.example.listtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var notesList: MutableList<String>
    private lateinit var notesListView: ListView
    private lateinit var notesAdapter: ArrayAdapter<String>
    private lateinit var notesInput: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesList = mutableListOf()
        notesListView = findViewById(R.id.notes_list)
        notesInput = findViewById(R.id.notes_input)
        saveButton  = findViewById(R.id.save_button)

        notesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notesList)
        notesListView.adapter = notesAdapter


        saveButton.setOnClickListener {
            val newNote = notesInput.text.toString()
            if (newNote.isNotEmpty()) {
                notesList.add(newNote)
                notesAdapter.notifyDataSetChanged()
                notesInput.text.clear()
            }


        }
    }
}
