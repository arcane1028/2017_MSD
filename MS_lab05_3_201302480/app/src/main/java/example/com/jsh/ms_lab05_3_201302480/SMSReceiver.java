package example.com.jsh.ms_lab05_3_201302480;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSReceiver extends BroadcastReceiver {
    public static final String TAG = "SMSReceiver";

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(TAG, "onReceive() 메소드 호출됨." );
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);
        if (messages != null && messages.length>0){
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG, "SMS sender : "+sender);

            String contents = messages[0].getMessageBody().toString();
            Log.i(TAG, "SMS contents : "+contents);

            Date receiveDate = new Date(messages[0].getTimestampMillis());
            Log.i(TAG,"SMS received data : "+receiveDate.toString());

            sendToActivity(context, sender, contents, receiveDate);
        }



        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendToActivity(Context context, String sender, String contents, Date receiveDate) {
        Intent myIntent = new Intent(context, SMSActivity.class);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        myIntent.putExtra("sender", sender);
        myIntent.putExtra("contents", contents);
        myIntent.putExtra("receivedDate",format.format(receiveDate));

        context.startActivity(myIntent);

    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for (int i = 0; i< smsCount; i++)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            }
            else
            {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;

    }
}
