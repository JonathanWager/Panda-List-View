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
    private lateinit var textSizeList: MutableList<Float>
    private lateinit var textColorList: MutableList<Int>
    private lateinit var notesListView: ListView
    private lateinit var notesAdapter: ArrayAdapter<String>
    private lateinit var notesInput: EditText
    private lateinit var titleInput: EditText
    private lateinit var saveButton: Button
    private lateinit var imageButton: ImageView
    private lateinit var sizePicker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesList = mutableListOf()
        textSizeList = mutableListOf()
        textColorList = mutableListOf()
        notesListView = findViewById(R.id.notes_list)
        notesInput = findViewById(R.id.notes_input)
        saveButton  = findViewById(R.id.save_button)
        titleInput = findViewById(R.id.titles_input)
        imageButton = findViewById(R.id.img_set_textsize)
        sizePicker = findViewById(R.id.numberPicker)

        sizePicker.minValue = 16
        sizePicker.maxValue = 30
        sizePicker.wrapSelectorWheel = false


        notesAdapter = object : ArrayAdapter<String>(
            this,
            R.layout.list_item,
            R.id.item_text_note,
            notesList
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val titleTextView = view.findViewById<TextView>(R.id.item_text_note)

                val textSize = textSizeList[position]
                val textColor = textColorList[position]
                titleTextView.textSize = textSize
                titleTextView.setTextColor(textColor)

                return view
            }
        }


        notesListView.adapter = notesAdapter

        notesListView.setOnItemLongClickListener { _, _, position, _ ->
            notesAdapter.remove(notesAdapter.getItem(position))
            textSizeList.removeAt(position)
            textColorList.removeAt(position)
            true
        }

        saveButton.setOnClickListener {
            val newNoteCard = titleInput.text.toString() + "\n" + notesInput.text.toString()
            if (newNoteCard.isNotEmpty()) {
                notesList.add(newNoteCard)
                val textSize = sizePicker.value.toFloat()
                val textColor = titleInput.currentTextColor
                textSizeList.add(textSize)
                textColorList.add(textColor)
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
