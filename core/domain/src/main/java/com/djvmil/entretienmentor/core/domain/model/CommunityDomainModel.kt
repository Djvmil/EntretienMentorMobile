package com.djvmil.entretienmentor.core.domain.model

import com.djvmil.entretienmentor.core.data.model.CommunityDataModel

data class CommunityDomainModel(
    val id: Long,
    val name: String?,
    val description: String?,
    val dateCreated: String?,
    val dateUpdated: String?,
)

internal fun CommunityDataModel.toDomain() =
    CommunityDomainModel(
        id = id,
        name = name,
        description = description,
        dateCreated = dateCreated,
        dateUpdated = dateUpdated)

internal fun CommunityDomainModel.toData() =
    CommunityDataModel(
        id = id,
        name = name,
        description = description,
        dateCreated = dateCreated,
        dateUpdated = dateUpdated)
