package com.github.juanncode.challengereign.data

import com.github.juanncode.challengereign.data.service.models.hit.Hit
import com.github.juanncode.challengereign.data.database.Hit as HitDb

fun Hit.toRoomHit(): HitDb {
    return HitDb(
        created_at_i = created_at_i ?: 0,
        story_id = story_id ?: 0,
        author = author ?: "",
        comment_text = comment_text ?: "",
        created_at = created_at ?: "",
        num_comments = num_comments ?: 0,
        objectID = objectID ?: "",
        parent_id = parent_id ?: 0,
        points = points ?: 0,
        story_text = story_text ?: "",
        story_title = story_title ?: "",
        story_url = story_url ?: "",
        title = title ?: "",
        url = url ?: "",
        isDeleted = false
    )
}
