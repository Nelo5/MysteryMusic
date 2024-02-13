package com.example.mysterymusic;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.TextView;

public class MyService extends Service  implements SensorEventListener {

    private MediaPlayer mediaPlayer;

    Sensor sensorl;
    SensorManager sensorManager;

    @Override
    public void onCreate(){
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this,R.raw.music);
        mediaPlayer.setLooping(true);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorl = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (sensorl != null){
            sensorManager.registerListener(this,sensorl,SensorManager.SENSOR_DELAY_NORMAL);
        }

    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        System.out.println(event.values[0]);
        if (event.values[0] < 50)
        {
            mediaPlayer.start();
        }
        else
        {
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    

}