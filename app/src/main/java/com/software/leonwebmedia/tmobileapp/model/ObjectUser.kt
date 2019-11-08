package com.software.leonwebmedia.tmobileapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ObjectUser() : Parcelable{
    @SerializedName("id")
    @Expose
    var repoId : Int? = null

    @SerializedName("avatar_url")
    @Expose
    var avatar_url : String? = null

    @SerializedName("name")
    @Expose
    var nameRepo : String? = null

    @SerializedName("forks")
    @Expose
    var folksCounts : String? = null



    @SerializedName("owner")
    @Expose
    var owner : Owner? = null

    constructor(parcel: Parcel) : this() {
        repoId = parcel.readValue(Int::class.java.classLoader) as? Int
        avatar_url = parcel.readString()
        folksCounts = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(repoId)
        parcel.writeString(avatar_url)
        parcel.writeString(folksCounts)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ObjectUser> {
        override fun createFromParcel(parcel: Parcel): ObjectUser {
            return ObjectUser(parcel)
        }

        override fun newArray(size: Int): Array<ObjectUser?> {
            return arrayOfNulls(size)
        }
    }
}
