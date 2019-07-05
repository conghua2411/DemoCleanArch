package com.example.cache.db.user

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cache.Constants

@Entity(tableName = Constants.USER_TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "username")
    val username: String?,
    @ColumnInfo(name = "email")
    val email: String?,
    @Embedded
    val address: Address?,
    @ColumnInfo(name = "phone")
    val phone: String?,
    @ColumnInfo(name = "website")
    val website: String?,
    @Embedded
    val company: Company?
) {
    companion object {
        fun createUserFromInfo(
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
                Address(addressStreet, addressSuite, addressCity, addressZipcode, Geo(geoLat, geoLng)),
                phone,
                website,
                Company
                    (companyName, companyCatchPhrase, companyBs)
            )
    }
}