package com.example.assi1

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var personAdapter: personAdapter

    lateinit var ArrayList: ArrayList<person>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Firebase.firestore
        ArrayList = arrayListOf()
        setupRecycler()
        getAllPerson(db)
        floatingActionButton.setOnClickListener {
            val i = Intent(this, Addperson::class.java)
            startActivity(i)
        }
    }

    private fun getAllPerson(db: FirebaseFirestore) {
        db.collection("person").get().addOnSuccessListener { result ->
            for (document in result) {
                val person = document.toObject<person>()
                ArrayList.add(person)
            }
            personAdapter.differ.submitList(ArrayList)

        }
    }


    private fun setupRecycler() {
        personAdapter = personAdapter()
        rv.apply {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}