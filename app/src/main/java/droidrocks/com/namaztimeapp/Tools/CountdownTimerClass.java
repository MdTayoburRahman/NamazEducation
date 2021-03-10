package droidrocks.com.namaztimeapp.Tools;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class CountdownTimerClass {

    private static final String TAG = "CountdownTimerClass";
    long days;
    long hours;
    long minutes;
    long seconds;
    Context mContext;

    public CountdownTimerClass(Context mContext) {
        this.mContext = mContext;
    }

    public long TimeCountDownDay(long total_millis){

        //1000 = 1 second interval
        CountDownTimer cdt = new CountDownTimer(total_millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                 days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "onFinish: CountDown Finish");
            }
        };
        cdt.start();
        return days;

    }


    public long TimeCountDownHours(long total_millis){

        //1000 = 1 second interval
        CountDownTimer cdt = new CountDownTimer(total_millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                 hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "onFinish: CountDown Finish");
            }
        };
        cdt.start();
        return hours;

    }
    public long TimeCountDownMin(long total_millis){

        //1000 = 1 second interval
        CountDownTimer cdt = new CountDownTimer(total_millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                 minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "onFinish: CountDown Finish");
            }
        };
        cdt.start();
        return minutes;

    }

    public long TimeCountDownSecond(long total_millis){

        //1000 = 1 second interval
        CountDownTimer cdt = new CountDownTimer(total_millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "onFinish: CountDown Finish");
            }
        };
        cdt.start();
        return seconds;

    }


}
