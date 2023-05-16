package com.example.listtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import yuku.ambilwarna.AmbilWarnaDialog
import android.widget.NumberPicker

class MainActivity : AppCompatActivity() {

    private lateinit var notesList: MutableList<String>
    private lateinit var notesListView: ListView
    private lateinit var notesAdapter: ArrayAdapter<String>
    private lateinit var notesInput: EditText
    private lateinit var titleInput: EditText
    private lateinit var saveButton: Button
    private lateinit var imageButton: ImageButton
    private lateinit var sizePicker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesList = mutableListOf()
        notesListView = findViewById(R.id.notes_list)
        notesInput = findViewById(R.id.notes_input)
        saveButton  = findViewById(R.id.save_button)
        titleInput = findViewById(R.id.titles_input)
        imageButton = findViewById(R.id.imageButton2)
        sizePicker = findViewById(R.id.numberPicker)

        sizePicker.minValue = 12
        sizePicker.maxValue = 30
        sizePicker.wrapSelectorWheel = false


        notesAdapter = object : ArrayAdapter<String>(
            this,
            R.layout.list_item,
            R.id.text1,
            notesList
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val titleTextView = view.findViewById<TextView>(R.id.text1)

                val textSize = sizePicker.value.toFloat()
                titleTextView.textSize = textSize

                return view
            }
        }


        notesListView.adapter = notesAdapter

        notesListView.setOnItemLongClickListener { _, _, position, _ ->
            notesAdapter.remove(notesAdapter.getItem(position))
            true
        }

        saveButton.setOnClickListener {
            val newNoteCard = titleInput.text.toString() + "\n\n " + notesInput.text.toString()
            if (newNoteCard.isNotEmpty()) {
                notesList.add(newNoteCard)
                notesAdapter.notifyDataSetChanged()
                titleInput.text.clear()
                notesInput.text.clear()
            }
        }

        imageButton.setOnClickListener{
            val textSize = sizePicker.value.toFloat()
            titleInput.textSize = textSize
            notesInput.textSize = textSize
        }


    }
    fun changeColor(view: View){
        val initialColor = titleInput.currentTextColor

        val colorPickerDialog = AmbilWarnaDialog(this, initialColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog?) {
                // Handle dialog cancellation
            }

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                titleInput.setTextColor(color)
                notesInput.setTextColor(color)
            }
        })
        colorPickerDialog.show()
    }
}
