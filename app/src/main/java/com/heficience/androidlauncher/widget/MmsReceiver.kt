package com.heficience.androidlauncher.widget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import com.heficience.androidlauncher.contactName
import com.heficience.androidlauncher.messageToSms
import com.heficience.androidlauncher.module_activity.SmsConversationActivity
import com.heficience.androidlauncher.nameOfSms
import com.heficience.androidlauncher.query.ToInternationalNumberPhone

class MmsReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Telephony.Sms.Intents.getMessagesFromIntent(intent) != null) {
            for (sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                SmsConversationActivity().findContactName()
                val nameOfSmsHere = nameOfSms?.text.toString()
                val address =
                    ToInternationalNumberPhone().transform(sms.originatingAddress!!, context)
                if (nameOfSmsHere == address) {
                    val smsData = sms.displayMessageBody.toString()
                    messageToSms?.text =
                        "${messageToSms?.text.toString()}\n---${contactName?.text.toString()}---\n$smsData"
                    val scrollAmount =
                        messageToSms?.layout?.getLineTop(messageToSms!!.lineCount)
                            ?.minus(messageToSms!!.height)
                    messageToSms?.scrollTo(0, scrollAmount!!)
                }
            }
        }
    }
}