package droidrocks.com.namaztimeapp.kibla;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Compass implements SensorEventListener {

    private static final String TAG = "CompassTag";
    private final float INITIAL_ALPHA = 0.97f;

    public interface CompassListener {
        void onNewAzimuth(float azimuth);

        void onMagField(float strength);
    }

    private CompassListener listener;

    private SensorManager sensorManager;
    private Sensor gsensor;
    private Sensor msensor;

    private float[] mGravity = new float[3];
    private float[] mGeomagnetic = new float[3];
    private float[] R = new float[9];
    private float[] I = new float[9];

    private float azimuth;
    private float magStrength;
    private boolean statusFlag = false;
    private boolean sensorData = false;

    public Compass(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        gsensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        msensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public boolean getStatus() {
        statusFlag = false;
        if (msensor != null) statusFlag = true;
        return statusFlag;
    }

    public boolean getSensorData() {
        return sensorData;
    }

    public void start() {
        sensorManager.registerListener(this, gsensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, msensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    public void setListener(CompassListener l) {
        listener = l;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        synchronized (this) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                mGravity[0] = INITIAL_ALPHA * mGravity[0] + (1 - INITIAL_ALPHA)
                        * event.values[0];
                mGravity[1] = INITIAL_ALPHA * mGravity[1] + (1 - INITIAL_ALPHA)
                        * event.values[1];
                mGravity[2] = INITIAL_ALPHA * mGravity[2] + (1 - INITIAL_ALPHA)
                        * event.values[2];
            }

            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {

                mGeomagnetic[0] = INITIAL_ALPHA * mGeomagnetic[0] + (1 - INITIAL_ALPHA)
                        * event.values[0];
                mGeomagnetic[1] = INITIAL_ALPHA * mGeomagnetic[1] + (1 - INITIAL_ALPHA)
                        * event.values[1];
                mGeomagnetic[2] = INITIAL_ALPHA * mGeomagnetic[2] + (1 - INITIAL_ALPHA)
                        * event.values[2];

                if (Math.abs(mGeomagnetic[2]) > Math.abs(mGeomagnetic[1])) {
                    magStrength = Math.round(Math.abs(mGeomagnetic[2]));
                } else {
                    magStrength = Math.round(Math.abs(mGeomagnetic[1]));
                }
                if (listener != null) {
                    listener.onMagField(magStrength);
                }
            }
/*
            if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
                Log.d(TAG, "sensorChanged (" + event.values[0] + ", " + event.values[1] + ", " + event.values[2] + ")");
                azimuth = Math.round(event.values[0]); //compassBearingRelativeToTrueNorth
                if (listener != null) {
                    listener.onNewAzimuth(azimuth);
                }
            }
*/
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                sensorData = true;
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);

                azimuth = (float) Math.toDegrees(orientation[0]);
                azimuth = (azimuth + 360) % 360;
                // Log.d(TAG, "azimuth (deg): " + azimuth);
                if (listener != null) {
                    listener.onNewAzimuth(azimuth);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
