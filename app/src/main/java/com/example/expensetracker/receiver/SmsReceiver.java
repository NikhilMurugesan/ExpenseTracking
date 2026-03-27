package com.example.expensetracker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.expensetracker.worker.SmsProcessWorker;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for (SmsMessage sms : messages) {
                String body = sms.getDisplayMessageBody();
                String sender = sms.getDisplayOriginatingAddress();

                Data inputData = new Data.Builder()
                        .putString("BODY", body)
                        .putString("SENDER", sender)
                        .build();

                OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SmsProcessWorker.class)
                        .setInputData(inputData)
                        .build();

                WorkManager.getInstance(context).enqueue(workRequest);
            }
        }
    }
}
