package com.bcan.arabamcomassignment.data.model.response

import com.google.gson.annotations.SerializedName

data class CarDetailResponse(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("location") var location: CarLocation? = CarLocation(),
    @SerializedName("category") var category: CarCategory? = CarCategory(),
    @SerializedName("modelName") var modelName: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("priceFormatted") var priceFormatted: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("dateFormatted") var dateFormatted: String? = null,
    @SerializedName("photos") var photos: ArrayList<String> = arrayListOf(),
    @SerializedName("properties") var properties: ArrayList<CarProperties> = arrayListOf(),
    @SerializedName("text") var text: String? = null,
    @SerializedName("userInfo") var userInfo: UserInfo? = UserInfo()

)

data class UserInfo(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("nameSurname") var nameSurname: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("phoneFormatted") var phoneFormatted: String? = null

)