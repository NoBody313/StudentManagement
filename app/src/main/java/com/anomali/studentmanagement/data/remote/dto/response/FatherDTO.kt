package com.anomali.studentmanagement.data.remote.dto.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class FatherDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phone_number") val phoneNumber: Int,
    @SerializedName("place_of_birth") val placeOfBirth: Date,
    @SerializedName("date_of_birth") val dateOfBirth: String,
    @SerializedName("occupation") val occupation: String,
    @SerializedName("address") val address: String
)
