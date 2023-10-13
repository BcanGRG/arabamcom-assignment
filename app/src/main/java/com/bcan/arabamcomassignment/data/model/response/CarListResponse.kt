package com.bcan.arabamcomassignment.data.model.response

import com.google.gson.annotations.SerializedName

data class CarListResponse(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("location") var location: CarLocation? = CarLocation(),
    @SerializedName("category") var category: CarCategory? = CarCategory(),
    @SerializedName("modelName") var modelName: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("priceFormatted") var priceFormatted: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("dateFormatted") var dateFormatted: String? = null,
    @SerializedName("photo") var photo: String? = null,
    @SerializedName("properties") var properties: ArrayList<CarProperties> = arrayListOf()

)

data class CarLocation(

    @SerializedName("cityName") var cityName: String? = null,
    @SerializedName("townName") var townName: String? = null

)

data class CarCategory(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null

)

data class CarProperties(

    @SerializedName("name") var name: String? = null,
    @SerializedName("value") var value: String? = null

)