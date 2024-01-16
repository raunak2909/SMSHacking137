package gov.rajasthan.smshacking137

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.READ_SMS
import android.Manifest.permission.RECEIVE_SMS
import android.Manifest.permission.SEND_SMS
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object{
        const val SMS_PERMISSION_REQ_CODE: Int = 100
        const val LOC_PERMISSION_REQ_CODE: Int = 200
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkSMSPermission()) {
            //true
            // do you work here!!

        } else {
            reqSMSPermission()
        }

        if(checkLocPermission()){
            // loc work here

        } else {
            reqLocPermission()
        }


    }

    fun checkSMSPermission(): Boolean {

        var receiveSMSPermission = ContextCompat.checkSelfPermission(this, RECEIVE_SMS)
        var readSMSPermission = ContextCompat.checkSelfPermission(this, READ_SMS)
        var sendSMSPermission = ContextCompat.checkSelfPermission(this, SEND_SMS)

        return receiveSMSPermission == PackageManager.PERMISSION_GRANTED
                && readSMSPermission == PackageManager.PERMISSION_GRANTED
                && sendSMSPermission == PackageManager.PERMISSION_GRANTED

    }
    fun checkLocPermission(): Boolean {

        val coarseLocPermission = ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)
        val fineLocPermission = ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)

        return coarseLocPermission == PackageManager.PERMISSION_GRANTED
                && fineLocPermission == PackageManager.PERMISSION_GRANTED

    }

    fun reqSMSPermission() {
        requestPermissions(arrayOf(RECEIVE_SMS, READ_SMS, SEND_SMS), SMS_PERMISSION_REQ_CODE)
    }

    fun reqLocPermission(){
        requestPermissions(arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION), LOC_PERMISSION_REQ_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == SMS_PERMISSION_REQ_CODE){

            if(grantResults[0]==PackageManager.PERMISSION_GRANTED
                && grantResults[1]==PackageManager.PERMISSION_GRANTED
                && grantResults[2]==PackageManager.PERMISSION_GRANTED){
                //true
                // do you work here!!

            } else {
                AlertDialog.Builder(this)
                    .setTitle("Allow Permission")
                    .setMessage("We need this permission for this feature to be enabled!!")
                    .setPositiveButton("Request Permissions"
                    ) { p0, p1 -> reqSMSPermission() }
                    .setNegativeButton("Cancel"){a,b->

                    }.create().show()
            }

        }
        else if(requestCode == LOC_PERMISSION_REQ_CODE){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED
                && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                //true
                // do you work here!!
                // location permission required work

            } else {
                AlertDialog.Builder(this)
                    .setTitle("Allow Permission")
                    .setMessage("We need this Location permission for this feature to be enabled!!")
                    .setPositiveButton("Request Permissions"
                    ) { p0, p1 -> (reqLocPermission()) }
                    .setNegativeButton("Cancel"){a,b->

                    }.create().show()
            }
        }
    }
}