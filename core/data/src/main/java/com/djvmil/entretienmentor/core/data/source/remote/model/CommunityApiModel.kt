package com.djvmil.entretienmentor.core.data.source.remote.model

import com.djvmil.entretienmentor.core.data.source.db.util.DEFAULT_ID
import kotlinx.serialization.Serializable

@Serializable
data class CommunityApiModel(
    val id: Int = DEFAULT_ID.toInt(),
    val name: String,
    val description: String,
    val dateCreated: String,
    val dateUpdated: String,
)
