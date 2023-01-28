package com.example.bluetoothdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    ListView listView;

    public void checkPermission () {
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT);
    }

    public void turnOff(View view) {
        checkPermission();
        bluetoothAdapter.disable();
        if (bluetoothAdapter.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Bluetooth couldn't be disabled", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth is off", Toast.LENGTH_LONG).show();
        }
    }

    public void findDiscoverableDevices(View view) {
        checkPermission();
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(intent);
    }

    public void viewPairedDevices(View view) {
        checkPermission();
        listView = findViewById(R.id.listView);
        Set<BluetoothDevice> bluetoothDevices = bluetoothAdapter.getBondedDevices();
        ArrayList <String> bluetoothDeviceList = new ArrayList<>();
        for (BluetoothDevice bluetoothDevice : bluetoothDevices) {
            bluetoothDeviceList.add(bluetoothDevice.getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, bluetoothDeviceList);
        listView.setAdapter(arrayAdapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
        if (bluetoothAdapter.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Bluetooth is on", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);

            if (bluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Bluetooth turned on", Toast.LENGTH_LONG).show();
            }
        }
    }


}