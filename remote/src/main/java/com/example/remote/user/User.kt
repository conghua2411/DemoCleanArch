package com.example.remote.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("address")
    val address: Address?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("company")
    val company: Company?
) {
    companion object {
        fun createUserRemoteFromInfo(
            id: Long?,
            name: String?,
            userName: String?,
            email: String?,
            addressStreet: String?,
            addressSuite: String?,
            addressCity: String?,
            addressZipcode: String?,
            geoLat: String?,
            geoLng: String?,
            phone: String?,
            website: String?,
            companyName: String?,
            companyCatchPhrase: String?,
            companyBs: String?
        ): User =
            User(
                id,
                name,
                userName,
                email,
                Address(
                    addressStreet,
                    addressSuite,
                    addressCity,
                    addressZipcode,
                    Geo(geoLat, geoLng)
                ),
                phone,
                website,
                Company
                    (companyName, companyCatchPhrase, companyBs)
            )
    }
}

data class Address(
    @SerializedName("street")
    val street: String?,
    @SerializedName("suite")
    val suite: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("zipcode")
    val zipcode: String?,
    @SerializedName("geo")
    val geo: Geo?
)

data class Geo(
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lng")
    val lng: String?
)

data class Company(
    @SerializedName("name")
    val name: String?,
    @SerializedName("catchPhrase")
    val catchPhrase: String?,
    @SerializedName("bs")
    val bs: String?
)