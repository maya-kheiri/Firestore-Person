package com.example.assi1

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_addperson.*


class Addperson : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addperson)
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Fatch")
        val db = Firebase.firestore
        save.setOnClickListener {
            val name = Name.text.toString()
            val number = Number.text.toString()
            val address = addr.text.toString()
            val person = person(name, number.toInt(), address)
            SavePerson(person, db)
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    private fun SavePerson(person: person, db: FirebaseFirestore) {
        progressDialog.show()
        db.collection("person").add(person).addOnSuccessListener { documentReference ->
            progressDialog.dismiss()
        }
    }

}