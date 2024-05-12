package com.djvmil.entretienmentor.feature.ui.community.model

import com.djvmil.entretienmentor.core.domain.model.CommunityDomainModel

data class CommunityUiModel(
    val id: Int?,
    val name: String?,
    val description: String?,
    val dateCreated: String?,
    val dateUpdated: String?
)

internal fun CommunityDomainModel.toUi() =
    CommunityUiModel(
        id = id.toInt(),
        name = name,
        description = description,
        dateCreated = dateCreated,
        dateUpdated = dateUpdated,
    )

internal fun CommunityUiModel.toDomain() =
    CommunityDomainModel(
        id = id!!.toLong(),
        name = name,
        description = description,
        dateCreated = dateCreated,
        dateUpdated = dateUpdated,
    )
