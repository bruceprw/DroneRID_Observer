package com.bruceprw.dronerid_observer

import android.bluetooth.BluetoothDevice
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class BleDeviceListAdapter () : RecyclerView.Adapter<BleDeviceListAdapter.ViewHolder>() {


    /*
     * Inflate our views using the layout defined in broadcast_card.xml
     */
    private val mLeDevices = arrayListOf<BluetoothDevice>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.broadcast_card, parent, false)

        return ViewHolder(v)
    }

    /*
     * Bind the data to the child views of the ViewHolder
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = mLeDevices[position]
        holder.droneNameTxt.text = "SwanDrone"
        holder.droneDataTxt.text = info.address
        holder.droneDistanceTxt.text = "2m"
    }




    fun addDevice(device: BluetoothDevice, view: View) {
        if (!mLeDevices.contains(device)) {
            mLeDevices.add(device)
            //device.alias = "${distance}m"
            showMessage(view, "new BLE device found! Name=${device.name} address = ${device.address}" )
            Log.d("SCANRESULT", "BLE device found! Name=${device.name}, address=${device.address}")

        }
    }

    fun clean(){
        mLeDevices.clear()
    }
    private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    override fun getItemCount(): Int {
        return mLeDevices.size
    }


    /*
     * The parent class that handles layout inflation and child view use
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var droneNameTxt = itemView.findViewById<View>(R.id.drone_name_text) as TextView
        var droneDataTxt = itemView.findViewById<View>(R.id.drone_data_text) as TextView
        var droneDistanceTxt = itemView.findViewById<View>(R.id.distance_text) as TextView

        init {
//            itemView.setOnClickListener { view ->
//                Log.i("ReviewClickListener", "Review clicked: ${reviewModelList[adapterPosition].title}")
//                val intent = Intent(view.context, activity_load_review::class.java)
//                intent.putExtra("review", reviewModelList[adapterPosition])
//                view.context.startActivity(intent)
//            }
        }
        fun getDevice(position: Int): BluetoothDevice {
            return mLeDevices[position]
        }

        fun clear() {
            mLeDevices.clear()
        }

        fun getCount(): Int {
            return mLeDevices.size
        }

        fun getItem(i: Int): Any {
            return mLeDevices[i]
        }

        fun getItemId(i: Int): Long {
            return i.toLong()
        }

        override fun onClick(v: View) {


        }
    }
}


