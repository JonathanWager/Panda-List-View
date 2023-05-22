package com.example.listtest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import yuku.ambilwarna.AmbilWarnaDialog

class EditNoteActivity : AppCompatActivity() {
    private lateinit var titleEditText:EditText
    private lateinit var noteEditText: EditText
    private lateinit var editSaveButton: Button
    private lateinit var sizePicker: NumberPicker
    private lateinit var imageButton:ImageView
    private  var position:Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        titleEditText = findViewById(R.id.titles_input)
        noteEditText = findViewById(R.id.notes_input)
        editSaveButton = findViewById(R.id.save_button)
        sizePicker = findViewById(R.id.numberPicker)
        imageButton = findViewById(R.id.img_set_textsize)

        sizePicker.minValue = 16
        sizePicker.maxValue = 30
        sizePicker.wrapSelectorWheel = false


        titleEditText.setText(intent.getStringExtra("title"))
        titleEditText.textSize = intent.getFloatExtra("size",-16f)
        titleEditText.setTextColor(intent.getIntExtra("color",-1))
        noteEditText.setText(intent.getStringExtra("note"))
        noteEditText.textSize = intent.getFloatExtra("size",-16f)
        noteEditText.setTextColor(intent.getIntExtra("color",-1))
        position = intent.getIntExtra("position", -1)

        imageButton.setOnClickListener{
            val textSize = sizePicker.value.toFloat()
            titleEditText.textSize = textSize
            noteEditText.textSize = textSize
        }

        editSaveButton.setOnClickListener {
            val modifiedTitle = titleEditText.text.toString()
            val modifiedNote = noteEditText.text.toString()
            val modifiedColor = titleEditText.currentTextColor
            val modifiedSize = titleEditText.textSize

            if (position != -1 && modifiedTitle.isNotEmpty() && modifiedNote.isNotEmpty()) {
                val intent = Intent()
                intent.putExtra("position", position)
                intent.putExtra("modifiedTitle", modifiedTitle)
                intent.putExtra("modifiedNote", modifiedNote)
                intent.putExtra("modifiedColor", modifiedColor)
                intent.putExtra("modifiedSize", modifiedSize)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
    fun changeColor(view: View){
        val initialColor = titleEditText.currentTextColor

        val colorPickerDialog = AmbilWarnaDialog(this, initialColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog?) {
                // Handle dialog cancellation
            }

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                titleEditText.setTextColor(color)
                noteEditText.setTextColor(color)
            }
        })
        colorPickerDialog.show()
    }
}