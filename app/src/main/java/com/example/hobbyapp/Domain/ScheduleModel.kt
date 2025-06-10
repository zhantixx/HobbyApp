package com.example.hobbyapp.Domain

import android.os.Parcel
import android.os.Parcelable

data class ScheduleModel(
    val day: String,
    val time: String,
    val subject: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(day)
        parcel.writeString(time)
        parcel.writeString(subject)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ScheduleModel> {
        override fun createFromParcel(parcel: Parcel) = ScheduleModel(parcel)
        override fun newArray(size: Int) = arrayOfNulls<ScheduleModel?>(size)
    }
}
