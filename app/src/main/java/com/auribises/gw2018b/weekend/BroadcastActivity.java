package com.auribises.gw2018b.weekend;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.auribises.gw2018b.AllStudentsActivity;
import com.auribises.gw2018b.R;

public class BroadcastActivity extends AppCompatActivity {

    MyBroadcastReceiver myBroadcastReceiver;
    YourBroadcastReceiver yourBroadcastReceiver;

    void init(){

        myBroadcastReceiver = new MyBroadcastReceiver();

        IntentFilter intentFilter = new IntentFilter();

        // System Defined Actions
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);

        intentFilter.addDataScheme("package");

        registerReceiver(myBroadcastReceiver,intentFilter);


        yourBroadcastReceiver = new YourBroadcastReceiver();
        IntentFilter yourFilter = new IntentFilter();
        yourFilter.addAction("a.b.c.d");
        yourFilter.addAction("kuch.bhi");
        yourFilter.addAction("any.name.of.your.choice");

        LocalBroadcastManager.getInstance(this).registerReceiver(yourBroadcastReceiver,yourFilter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        init();
    }

    // Will Receive the System Actions
    class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            String packName = intent.getData().getSchemeSpecificPart();

            if(action.equals(Intent.ACTION_BATTERY_LOW)){

            }

            if(action.equals(Intent.ACTION_PACKAGE_ADDED)){
                Log.i("BroadcastActivity","==Package Added=="+packName);
            }

            if(action.equals(Intent.ACTION_PACKAGE_REMOVED)){
                Log.i("BroadcastActivity","==Package Removed=="+packName);
            }

            //..

        }
    }

    // Will Receive the User-Defined Actions
    class YourBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("a.b.c.d")){
                Toast.makeText(context,"a.b.c.d Received",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }

    public void clickHandler(View view){

        //Intent intent = new Intent("a.b.c.d");

        // To Broadcast within the App and outside the App
        //sendBroadcast(intent);

        // To Broadcast within the App
        //LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        showNotification();

    }


    void showNotification(){

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("notiId","notiChannel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"notiId");
        builder.setContentTitle("This is Title");
        builder.setContentText("This is Text");
        builder.setSmallIcon(R.drawable.ic_menu_camera);

        // Lights i.e. LED, Sound, Vibration
        // Requires VIBRATE Permission
        builder.setDefaults(Notification.DEFAULT_ALL);

        Intent intent = new Intent(BroadcastActivity.this, AllStudentsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,111,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);


        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("This is Big Text"));
        builder.addAction(R.drawable.ic_menu_camera,"Camera",pendingIntent);
        builder.addAction(R.drawable.ic_menu_send,"Send",pendingIntent);


        Notification notification = builder.build();

        notificationManager.notify(123,notification);

    }
}
