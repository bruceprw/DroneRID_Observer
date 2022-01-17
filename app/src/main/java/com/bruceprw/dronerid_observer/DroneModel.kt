package com.bruceprw.dronerid_observer
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize
@IgnoreExtraProperties
@Parcelize
data class DroneModel(val basicID : String, val data: String, val auth: String = "", val selfID : String = "",
                      val system : String = "", val operator : String = "") : Parcelable {
}