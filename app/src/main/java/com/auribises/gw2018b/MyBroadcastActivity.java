package com.auribises.gw2018b;

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

public class MyBroadcastActivity extends AppCompatActivity {


    MyReceiver myReceiver;
    YourReceiver yourReceiver;

    void initYourReceiver(){

        yourReceiver = new YourReceiver();

        // It will subscribe the Actions which BroadcastReceiver can receive
        // Actions - User Defined Actions
        IntentFilter filter = new IntentFilter();
        filter.addAction("a.b.c.d");
        filter.addAction("kuch.bhi.ho.sakta.hai");
        filter.addAction("anything");

        // register yourReceiver to receive all these actions
        LocalBroadcastManager.getInstance(this).registerReceiver(yourReceiver,filter);
    }

    void initMyReceiver(){

        myReceiver = new MyReceiver();

        // It will subscribe the Actions which BroadcastReceiver can receive
        // Actions - System Actions
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");

        // register myReceiver to receive all these actions
        registerReceiver(myReceiver,filter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_broadcast);
        initMyReceiver();
        initYourReceiver();
    }

    public void clickHandler(View view){

        //Intent intent = new Intent("a.b.c.d");
        //LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        showNotification();

    }

    // Nested Class
    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            // fetch the package name
            String packName = intent.getData().getSchemeSpecificPart();

            if(action.equals(Intent.ACTION_BATTERY_LOW)){
                showNotification();
            }

            if(action.equals(Intent.ACTION_POWER_CONNECTED)){
                //...
            }

            if(action.equals(Intent.ACTION_PACKAGE_ADDED)){
                Log.i("MyBroadcastActivity","==Package Added== "+packName);
            }

            if(action.equals(Intent.ACTION_PACKAGE_REMOVED)){
                Log.i("MyBroadcastActivity","==Package Removed== "+packName);
            }

        }

    }

    class YourReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if(action.equals("a.b.c.d")){
                Toast.makeText(MyBroadcastActivity.this,"a.b.c.d Received !!",Toast.LENGTH_LONG).show();
            }

        }
    }

    void showNotification(){

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("myChannelId","MyChannel",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"myChannelId");
        builder.setContentTitle("This is Title");
        builder.setContentText("This is Text");
        builder.setSmallIcon(R.drawable.ic_menu_camera);

        // Requires permission Vibrate in Manifest
        builder.setDefaults(Notification.DEFAULT_ALL); // LED, Sound and Vibration


        Intent intent = new Intent(MyBroadcastActivity.this,AllStudentsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,111,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("This is Big Text"));
        builder.addAction(R.drawable.ic_menu_camera,"Camera",pendingIntent);
        builder.addAction(R.drawable.ic_menu_send,"Send",pendingIntent);


        Notification notification = builder.build();

        notificationManager.notify(101,notification);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(yourReceiver);
    }
}
