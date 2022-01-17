package com.bruceprw.dronerid_observer

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class activity_main : Activity() {

    val ENABLE_BT_REQUEST_CODE = 21
    val bluetoothAdapter : BluetoothAdapter? by lazy { BluetoothAdapter.getDefaultAdapter() }
    private val bluetoothLeScanner by lazy { bluetoothAdapter?.bluetoothLeScanner}
    private var scanning = false
    private val handler = Handler()
    private val SCAN_PERIOD: Long = 10000
    private val scanSettings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.broadcast_card)

        val startScanButton = findViewById<Button>(R.id.start_scan_button)

        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }

        if (bluetoothAdapter?.isEnabled == false) {
            promptEnableBt(false)
        }
        startScanButton.setOnClickListener {view ->
            showMessage(view, "Scan started!")
            startBleScan()

        }
    }

    private fun promptEnableBt(flag: Boolean) {
        if (flag == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            ActivityCompat.startActivityForResult(this, enableBtIntent, ENABLE_BT_REQUEST_CODE, null)
        }
    }

    private fun requestLocationPermission() {

    }

    // TODO: Validate location permissions
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            LOCATION_PERMISSION
//        }
//    }

    private fun startBleScan() {
        if (bluetoothLeScanner != null) {
            bluetoothLeScanner!!.startScan(null, scanSettings, leScanCallback)

        }
    }

    private fun scanLeDevice() {


//        if (bluetoothLeScanner != null) {
//            if (!scanning) { // Stops scanning after a pre-defined scan period.
//                handler.postDelayed({
//                    scanning = false
//                    bluetoothLeScanner.stopScan(leScanCallback)
//                }, SCAN_PERIOD)
//                scanning = true
//                bluetoothLeScanner.startScan(leScanCallback)
//            } else {
//                scanning = false
//                bluetoothLeScanner.stopScan(leScanCallback)
//            }
//        }
    }
    private val leDeviceListAdapter = LeDeviceListAdapter()


    // Device scan callback - called when scan finds result
    private val leScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            leDeviceListAdapter.addDevice(result.device)
            leDeviceListAdapter.notifyDataSetChanged()
            val view = findViewById<ConstraintLayout>(R.id.main_layout)

            showMessage(view,"BLE FOUND!" )
        }
    }

    private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}