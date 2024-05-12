package com.djvmil.entretienmentor.core.data.model

import com.djvmil.entretienmentor.core.data.source.db.util.DEFAULT_ID
import com.djvmil.entretienmentor.core.data.source.remote.model.CommunityApiModel
import com.djvmil.sqldelight.CommunityTable

class CommunityDataModel(
    val id: Long = DEFAULT_ID,
    val name: String?,
    val description: String?,
    val dateCreated: String?,
    val dateUpdated: String?
)

internal fun CommunityApiModel.toData() =
    CommunityDataModel(
        id = id.toLong(),
        name = name,
        description = description,
        dateCreated = dateCreated,
        dateUpdated = dateUpdated,
    )

internal fun CommunityTable.toData() =
    CommunityDataModel(
        id = id,
        name = name,
        description = description,
        dateCreated = dateCreated,
        dateUpdated = dateUpdated,
    )

internal fun CommunityDataModel.toDatabase() =
    CommunityTable(
        id = 1,
        name = name ?: "null",
        description = description,
        dateCreated = dateCreated,
        dateUpdated = dateUpdated,
    )
