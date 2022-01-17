package com.bruceprw.dronerid_observer

import android.R

import android.bluetooth.BluetoothDevice

import androidx.recyclerview.widget.RecyclerView.ViewHolder

import android.widget.TextView

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View

import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView


// Adapter for holding devices found through scanning.
class LeDeviceListAdapter : BaseAdapter() {
    private val mLeDevices: ArrayList<BluetoothDevice>
//    private val mInflator: LayoutInflater
    fun addDevice(device: BluetoothDevice) {
        if (!mLeDevices.contains(device)) {
            mLeDevices.add(device)
        }
    }

    fun getDevice(position: Int): BluetoothDevice {
        return mLeDevices[position]
    }

    fun clear() {
        mLeDevices.clear()
    }

    override fun getCount(): Int {
        return mLeDevices.size
    }

    override fun getItem(i: Int): Any {
        return mLeDevices[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

//    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View? {
//        var view: View? = view
//        val viewHolder: ViewHolder
//        // General ListView optimization code.
//        if (view == null) {
//            view = mInflator.inflate(R.layout.listitem_device, null)
//            viewHolder = ViewHolder()
//            viewHolder.deviceAddress = view.findViewById(R.id.device_address) as TextView
//            viewHolder.deviceName = view.findViewById(R.id.device_name) as TextView
//            view.setTag(viewHolder)
//        } else {
//            viewHolder = view.getTag()
//        }
//        val device = mLeDevices[i]
//        val deviceName = device.name
//        if (deviceName != null && deviceName.length > 0) viewHolder.deviceName.setText(deviceName) else viewHolder.deviceName.setText(
//            R.string.unknown_device
//        )
//        viewHolder.deviceAddress.setText(device.address)
//        return view
//    }

    init {
        mLeDevices = ArrayList()
//        mInflator = this@LeDeviceListAdapter.getLayoutInflater()
    }
}