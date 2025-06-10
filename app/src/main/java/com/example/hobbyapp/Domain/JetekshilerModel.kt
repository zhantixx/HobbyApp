package com.example.hobbyapp.Domain

import android.os.Parcel
import android.os.Parcelable

data class JetekshilerModel(
    val Address:String="",
    val Biography:String="",
    val id:Int=0,
    val Name:String="",
    val Picture:Int=0,
    val Special:String="",
    val Experience:Int=0,
    val Location:String="",
    val Mobile:String="",
    val peoples:String="",
    val Rating:Double=0.0,
    val schedule: List<ScheduleModel>? = null,
    val Category: String

):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.createTypedArrayList(ScheduleModel.CREATOR),
        parcel.readString().toString()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Address)
        parcel.writeString(Biography)
        parcel.writeInt(id)
        parcel.writeString(Name)
        parcel.writeInt(Picture)
        parcel.writeString(Special)
        parcel.writeInt(Experience)
        parcel.writeString(Location)
        parcel.writeString(Mobile)
        parcel.writeString(peoples)
        parcel.writeDouble(Rating)
        parcel.writeTypedList(schedule)
        parcel.writeString(Category)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<JetekshilerModel> {
        override fun createFromParcel(parcel: Parcel): JetekshilerModel {
            return JetekshilerModel(parcel)
        }

        override fun newArray(size: Int): Array<JetekshilerModel?> {
            return arrayOfNulls(size)
        }
    }
}
