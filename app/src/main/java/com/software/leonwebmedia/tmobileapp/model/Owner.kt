package com.software.leonwebmedia.tmobileapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Owner() : Parcelable {

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(login)
            parcel.writeString(node_id)
            parcel.writeString(avatar_url)
            parcel.writeString(followers_url)
            parcel.writeString(following_url)
        }


    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("login")
    @Expose
    var login : String? = null

    @SerializedName("node_id")
    @Expose
    var node_id : String? = null

    @SerializedName("avatar_url")
    @Expose
    var avatar_url : String? = null

    @SerializedName("gravatar_id")
    @Expose
    var gravatar_id : String? = null

    @SerializedName("url")
    @Expose
    var url : String? = null

    @SerializedName("html_url")
    @Expose
    var html_url : String? = null

    @SerializedName("followers_url")
    @Expose
    var followers_url : String? = null

    @SerializedName("following_url")
    @Expose
    var following_url : String? = null

    @SerializedName("gists_url")
    @Expose
    var gists_url : String? = null

    @SerializedName("starred_url")
    @Expose
    var starred_url : String? = null

    @SerializedName("subscriptions_url")
    @Expose
    var subscriptions_url : String? = null

    @SerializedName("organizations_url")
    @Expose
    var organizations_url : String? = null

    @SerializedName("repos_url")
    @Expose
    var repos_url : String? = null

    @SerializedName("events_url")
    @Expose
    var events_url : String? = null

    @SerializedName("received_events_url")
    @Expose
    var received_events_url : String? = null

    @SerializedName("type")
    @Expose
    var type : String? = null

    constructor(parcel: Parcel) : this() {
        login = parcel.readString()
        node_id = parcel.readString()
        avatar_url = parcel.readString()
        gravatar_id = parcel.readString()
        url = parcel.readString()
        html_url = parcel.readString()
        followers_url = parcel.readString()
        following_url = parcel.readString()
        gists_url = parcel.readString()
        starred_url = parcel.readString()
        subscriptions_url = parcel.readString()
        organizations_url = parcel.readString()
        repos_url = parcel.readString()
        events_url = parcel.readString()
        received_events_url = parcel.readString()
        type = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<Owner> {
        override fun createFromParcel(parcel: Parcel): Owner {
            return Owner(parcel)
        }

        override fun newArray(size: Int): Array<Owner?> {
            return arrayOfNulls(size)
        }
    }


}