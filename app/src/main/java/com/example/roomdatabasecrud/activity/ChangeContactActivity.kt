package com.example.roomdatabasecrud.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.roomdatabasecrud.Contact
import com.example.roomdatabasecrud.ContactViewModel
import com.example.roomdatabasecrud.R

class ChangeContactActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editNumber: EditText

    private lateinit var wordViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_contact)
        editName = findViewById(R.id.edit_name)
        editNumber = findViewById(R.id.edit_number)

        wordViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        val bundle = intent.getBundleExtra("bundle")
        var person  = bundle.getParcelable("contact") as Contact

        editName.setText(person.name)
        editNumber.setText(person.phoneNumber)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            wordViewModel.change(contact = Contact(
                    person.id,
                    editName.text.toString(),
                    editNumber.text.toString())
            )

            val intent = Intent(this@ChangeContactActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
