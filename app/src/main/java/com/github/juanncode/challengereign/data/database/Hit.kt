package com.github.juanncode.challengereign.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val created_at_i: Int,
    val story_id: Int,
    val author: String,
    val comment_text: String,
    val created_at: String,
    val num_comments: Int,
    val objectID: String,
    val parent_id: Int,
    val points: Int,
    val story_text: String,
    val story_title: String,
    val story_url: String,
    val title: String,
    val url: String,
    var isDeleted: Boolean
)
