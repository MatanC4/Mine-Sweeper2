package bound;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by Daniel_m on 14/01/2017.
 */

public class BoundService extends Service implements SensorEventListener {

    private boolean isInitial = true;
    private int startX, startY, startZ;
    private int lastX,lastY,lastZ;
    private ServiceBinder serviceBinder;
    private SensorManager sensorManager;
    private BoundServiceListenter listenter;

    @Override
    public IBinder onBind(Intent intent) {
        serviceBinder = new ServiceBinder();
        return serviceBinder;
    }

    public void startListening() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void setListenter(BoundServiceListenter listenter){
        this.listenter = listenter;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if(isInitial){
                isInitial = false;
                startX = (int)Math.round(event.values[0]);;
                startY = (int)Math.round(event.values[1]);
                startZ = (int)Math.round(event.values[2]);
                lastX = startX;
                lastY = startY;
                lastZ = startZ;
            }
            else{
                int x = (int)Math.round(event.values[0]);
                int y = (int)Math.round(event.values[1]);
                int z = (int)Math.round(event.values[2]);

                double lastDistance = Math.sqrt(Math.pow(startX-lastX,2)+Math.pow(startY-lastY,2)+Math.pow(startZ-lastZ,2));
                double currentDistance = Math.sqrt(Math.pow(startX-x,2)+Math.pow(startY-y,2)+Math.pow(startZ-z,2));
                boolean isRecovering = currentDistance<lastDistance;
                lastX = x;
                lastY = y;
                lastZ = z;
                listenter.onAngelChange(isRecovering);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public class ServiceBinder extends Binder{
        public BoundService getService() {
            return BoundService.this;
        }
    }


}

