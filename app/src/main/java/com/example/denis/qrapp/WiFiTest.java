package com.example.denis.qrapp;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WiFiTest extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        String ssid = "TECH_D003866";
        String pass = "RPTCPTNJ";
        final WifiConfiguration wifiConfiguration = new WifiConfiguration();
       // wifiConfiguration.SSID = String.format("\"%s\"", ssid);
        //wifiConfiguration.preSharedKey = String.format("\"%s\"", pass);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        Button connect = (Button) findViewById(R.id.connect);
        Button diss = (Button) findViewById(R.id.dissconnect);

        final WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Connect", "onClick: "+ username.getText() + password.getText());
                wifiConfiguration.SSID = String.format("\"%s\"", username.getText());
                wifiConfiguration.preSharedKey = String.format("\"%s\"", password.getText());
                int netId = wifiManager.addNetwork(wifiConfiguration);
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();
                Log.d("....................", "onClick: " + wifiManager.getConnectionInfo());

            }
        });


        diss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Diss", "onClick: dissconect");
                wifiManager.disconnect();

            }
        });





    }






}
