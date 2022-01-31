package com.github.juanncode.challengereign.data.service.models.hit

data class CommentText(
    val fullyHighlighted: Boolean,
    val matchLevel: String,
    val matchedWords: List<String>,
    val value: String
)