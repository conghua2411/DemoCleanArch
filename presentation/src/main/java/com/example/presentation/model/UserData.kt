package com.example.presentation.model

data class UserData(
    val id: Long?,
    val name: String?,
    val username: String?,
    val email: String?,
    val address: Address?,
    val phone: String?,
    val website: String?,
    val company: Company?
) {
    companion object {
        fun createUserModelFromInfo(
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
        ): UserData =
            UserData(
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
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val geo: Geo?
)

data class Geo(
    val lat: String?,
    val lng: String?
)

data class Company(
    val name: String?,
    val catchPhrase: String?,
    val bs: String?
)