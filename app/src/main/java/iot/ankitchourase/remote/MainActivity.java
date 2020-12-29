package iot.ankitchourase.remote;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    CheckBox enable_bt,visible_bt;
    ImageView search_bt;
    TextView name_bt;
    ListView listView;
    Button button9;
    Button button12;
    Button button13;
    Button button14;
    Button button15;

    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enable_bt = findViewById(R.id.enable_bt);
        visible_bt = findViewById(R.id.visible_bt);
        search_bt = findViewById(R.id.search_bt);
        name_bt = findViewById(R.id.name_bt);
        listView = findViewById(R.id.list_view);

        name_bt.setText(getLocalBluetoothName());

        BA = BluetoothAdapter.getDefaultAdapter();

        button9 = findViewById(R.id.button9);

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button9.getText().equals("OFF"))
                {
                    button9.setText("ON");
                    button9.setBackgroundResource(R.color.green);
                    button9.setBackgroundResource(R.drawable.custom_button_2);
                }
                else if(button9.getText().equals("ON"))
                {
                    button9.setText("OFF");
                    button9.setBackgroundResource(R.color.red);
                    button9.setBackgroundResource(R.drawable.custom_button);
                }
            }
        });

        button12 = findViewById(R.id.button12);

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button12.getText().equals("OFF"))
                {
                    button12.setText("ON");
                    button12.setBackgroundResource(R.color.green);
                    button12.setBackgroundResource(R.drawable.custom_button_2);
                }
                else if (button12.getText().equals("ON"))
                {
                    button12.setText("OFF");
                    button12.setBackgroundResource(R.color.red);
                    button12.setBackgroundResource(R.drawable.custom_button);
                }
            }
        });

        button14 = findViewById(R.id.button14);

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button14.getText().equals("OFF"))
                {
                    button14.setText("ON");
                    button14.setBackgroundResource(R.color.green);
                    button14.setBackgroundResource(R.drawable.custom_button_2);
                }
                else if (button14.getText().equals("ON"))
                {
                    button14.setText("OFF");
                    button14.setBackgroundResource(R.color.red);
                    button14.setBackgroundResource(R.drawable.custom_button);
                }
            }
        });

        button13 = findViewById(R.id.button13);

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button13.getText().equals("OFF"))
                {
                    button13.setText("ON");
                    button13.setBackgroundResource(R.color.green);
                    button13.setBackgroundResource(R.drawable.custom_button_2);

                }
                else if (button13.getText().equals("ON"))
                {
                    button13.setText("OFF");
                    button13.setBackgroundResource(R.color.red);
                    button13.setBackgroundResource(R.drawable.custom_button);
                }
            }
        });

        button15 = findViewById(R.id.button15);

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button15.getText().equals("LIGHTS OFF"))
                {
                    button15.setText("LIGHTS ON");
                    button15.setBackgroundResource(R.color.green);
                    button15.setBackgroundResource(R.drawable.main_button);
                }
                else if (button15.getText().equals("LIGHTS ON"))
                {
                    button15.setText("LIGHTS OFF");
                    button15.setBackgroundResource(R.color.red);
                    button15.setBackgroundResource(R.drawable.custom_button);
                }
            }
        });

        if (BA == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (BA.isEnabled()) {
            enable_bt.setChecked(true);
        }

        enable_bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
                if (!isChecked) {
                    BA.disable();
                    Toast.makeText(MainActivity.this, "Turned OFF", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intentOn , 0);
                    Toast.makeText(MainActivity.this, "Turned ON", Toast.LENGTH_SHORT).show();
                }
            }
        });

        visible_bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(getVisible,0);
                    Toast.makeText(MainActivity.this, "Visible for 2 min", Toast.LENGTH_SHORT).show();
                }
            }
        });

        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list();
            }
        });
    }

    private void list() {
        pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();

        for (BluetoothDevice bt : pairedDevices){
            list.add(bt.getName());
        }

        Toast.makeText(this, "Showing Devices", Toast.LENGTH_SHORT).show();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    public String getLocalBluetoothName(){
        if (BA == null) {
            BA = BluetoothAdapter.getDefaultAdapter();
        }
        String name = BA.getName();
        if (name == null){
            name = BA.getAddress();
        }

        return name;
    }
}