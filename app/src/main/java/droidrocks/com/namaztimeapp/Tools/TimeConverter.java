package droidrocks.com.namaztimeapp.Tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class TimeConverter {
    String inputTime;
    String newTime;
    String newAddedTime;
    String SubtractTime;
    Date dateObj;
    private String MiliTime;
    private String _24Time;

    public TimeConverter() {
    }

    public String TimeConvertTo24Hr(String s){
        try {
            String _24HourTime = s;
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            Date _24HourDt = _24HourSDF.parse(_24HourTime);
            _24Time = _24HourSDF.format(_24HourDt);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "TimeConvertTo24Hr: "+e.getMessage());
        }
        return _24Time;

    }

    @SuppressLint("SimpleDateFormat")
    public String TimeConvertTO(String s) {

        try {
            String _24HourTime = s;
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
            Date _24HourDt = _24HourSDF.parse(_24HourTime);

            newTime = _12HourSDF.format(_24HourDt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newTime;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String ExtactEndTime(Context mContext, String currentTime, int addTimeInMinute) {


        LocalTime lt = LocalTime.parse(currentTime);
        newAddedTime = String.valueOf(lt.plusMinutes(addTimeInMinute));

        try {
            String myTime = currentTime;
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date d = df.parse(myTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MINUTE, addTimeInMinute);
            newAddedTime = df.format(cal.getTime());

        } catch (Exception e) {
            Toast.makeText(mContext, "Time Formatting Error =" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return newAddedTime;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String SubtractTime(Context mContext, String currentTime, int addTimeInMinute) {


        LocalTime lt = LocalTime.parse(currentTime);
        newAddedTime = String.valueOf(lt.plusMinutes(addTimeInMinute));

        try {
            String myTime = currentTime;
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date d = df.parse(myTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MINUTE, addTimeInMinute);
            SubtractTime = df.format(cal.getTime());

        } catch (Exception e) {
            Toast.makeText(mContext, "Time Formatting Error =" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return SubtractTime;
    }

    public String ExtractMiliSecond(Context mContext, String currentTime) {

        try {
            String myTime = currentTime;
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date d = df.parse(myTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);

            MiliTime = String.valueOf(cal.getTimeInMillis());

        } catch (Exception e) {
            Toast.makeText(mContext, "Time Formatting Error =" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return MiliTime;

    }

    public long ConvertToMiliSecond(String currentTime){

        String myTime = currentTime;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date d = null;
        try {
            d = df.parse(myTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);


        long Time = cal.getTimeInMillis();

        return Time;
    }


    public Date DateTimeObject (String inputTime){

        String myTime = inputTime;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm a");
        Date d = null;
        try {
            d = df.parse(myTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        Date Time = cal.getTime();

        return Time;
    }

    public int ConvertMillisecondToHour(long milliseconds){
        int hours   = (int) ((milliseconds / (1000*60*60)) % 24);
        return hours;
    }

    public int ConvertMillisecondToMinute(long milliseconds){
        int minutes = (int) ((milliseconds / (1000*60)) % 60);
        return minutes;
    }

    public int ConvertMillisecondToSecond(long milliseconds){
        int seconds = (int) (milliseconds / 1000) % 60 ;
        return seconds;
    }






}




