package com.example.assi1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_person.view.*

class personAdapter : RecyclerView.Adapter<personAdapter.ViewHolder>() {
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item)

    private val differCallback = object : DiffUtil.ItemCallback<person>() {
        override fun areItemsTheSame(oldItem: person, newItem: person): Boolean {
            return oldItem.Number == newItem.Number
        }

        override fun areContentsTheSame(oldItem: person, newItem: person): Boolean {
            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = differ.currentList[position]
        holder.itemView.apply {
            name_R.text = person.name
            number_R.text = person.Number.toString()
            textView7.text = person.address
            setOnClickListener {
                onItemClickListener?.let { it(person) }
            }


        }

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((person) -> Unit)? = null
    fun setOnItemClickListener(listener: (person) -> Unit) {
        onItemClickListener = listener
    }
}