package com.example.evan.accelerometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView xText, yText, zText;
    private Sensor mSensor;
    private SensorManager sManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Create Sensor Manager */
        sManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        /* Accelerometer Sensor */
        mSensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        /* Register sensor listener */
        sManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        /* Assign Text View */
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        /* not in use */
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xText.setText("X: "+ event.values[0]);
        yText.setText("Y: "+ event.values[1]);
        zText.setText("Z: "+ event.values[2]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
