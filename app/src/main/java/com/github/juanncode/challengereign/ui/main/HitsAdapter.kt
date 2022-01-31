package com.github.juanncode.challengereign.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.juanncode.challengereign.databinding.ItemHitBinding
import com.github.juanncode.challengereign.utils.convertDate
import kotlin.properties.Delegates
import com.github.juanncode.challengereign.data.database.Hit as HitDb

@SuppressLint("NotifyDataSetChanged")
class HitsAdapter(private val listener: (HitDb) -> Unit): RecyclerView.Adapter<HitsAdapter.ViewHolder>() {

    var hits : MutableList<HitDb> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hit = hits[position]
        holder.bind(hit)
        holder.itemView.setOnClickListener { listener(hit) }

    }

    override fun getItemCount(): Int = hits.size

    class ViewHolder(private val binding: ItemHitBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(hit: HitDb) {
            with(binding) {
                textTitle.text = hit.story_title
                textAuthorAndCreated.text = "${hit.author} - ${convertDate(hit.created_at)}"
            }
        }
    }
}