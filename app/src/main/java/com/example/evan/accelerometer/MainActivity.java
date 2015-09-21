package com.example.evan.accelerometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.Window;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    /* Accelerometer Variables */
    private TextView xTexta, yTexta, zTexta;
    private Sensor mSensor;

    /* Sensor Listener */
    private SensorManager sManager;

    /* Gyroscope Variables */
    private TextView xTextg, yTextg, zTextg;
    private Sensor mSensor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Create Sensor Manager */
        sManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        /* Accelerometer Sensor */
        mSensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        /* Gyroscope Sensor */
        mSensor2 = sManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        /* Register sensor listener */
        sManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sManager.registerListener(this, mSensor2, SensorManager.SENSOR_DELAY_NORMAL);

        /* Assign Text View */
        xTexta = (TextView)findViewById(R.id.xTexta);
        yTexta = (TextView)findViewById(R.id.yTexta);
        zTexta = (TextView)findViewById(R.id.zTexta);

        xTextg = (TextView)findViewById(R.id.xTextg);
        yTextg = (TextView)findViewById(R.id.yTextg);
        zTextg = (TextView)findViewById(R.id.zTextg);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        /* not in use */
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized(this) {
            switch(event.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    xTexta.setText("X: " + event.values[0]);
                    yTexta.setText("Y: " + event.values[1]);
                    zTexta.setText("Z: " + event.values[2]);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    xTextg.setText("X: " + event.values[0]);
                    yTextg.setText("Y: " + event.values[1]);
                    zTextg.setText("Z: " + event.values[2]);
                    break;
            }
        }
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

    @Override
    protected void onStop() {
        super.onStop();
        sManager.unregisterListener(this, mSensor);
        sManager.unregisterListener(this, mSensor2);
    }
}
