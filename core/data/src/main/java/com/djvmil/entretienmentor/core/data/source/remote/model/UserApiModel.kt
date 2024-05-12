package com.djvmil.entretienmentor.core.data.source.remote.model

import com.djvmil.entretienmentor.core.data.source.db.util.DEFAULT_ID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApiModel(
    val country: String? = null,
    val firstname: String? = null,
    val roles: List<RolesUserApi?>? = null,
    val userID: Int? = DEFAULT_ID.toInt(),
    val birthDate: String? = null,
    val isEmailVerified: String? = null,
    val lastname: String? = null,
    val dateUpdated: String? = null,
    @SerialName("isPhoneNumbeVerified") val isPhoneNumberVerified: String? = null,
    val phoneNumber: String? = null,
    val dateCreated: String? = null,
    val genre: String? = null,
    val email: String? = null,
    val username: String? = null
)

@Serializable
data class RolesUserApi(
    val role: String? = null,
    val dateCreated: String? = null,
    val roleID: Int? = null,
    val dateUpdated: String? = null
)
