package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by karinsai on 4/15/2016.
 */
public class SystemOverlayActivity extends AppCompatActivity {

    private Context mContext;
    private int REQUEST_OVERLAY_PERMISSION = 100;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_main);
        mContext = this;
        requestManageOverlayPermission();
    }



    public void requestManageOverlayPermission() {
        //first check whether the permission is granted
        //NB! this is only for API level 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                //construct an intent
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                //request the permission
                startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_OVERLAY_PERMISSION) {
            //check whether permission was granted
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(mContext,"No", Toast.LENGTH_LONG).show();
                } else {
                    //SYSTEM_ALERT_WINDOW permission granted

                    Toast.makeText(mContext,"ddd", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
