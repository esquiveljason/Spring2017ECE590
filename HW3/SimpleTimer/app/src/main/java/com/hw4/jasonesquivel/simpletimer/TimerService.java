package com.hw4.jasonesquivel.simpletimer;

import android.app.Service;
import android.os.IBinder;
import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.util.*;

/**
 * Created by Jason Esquivel on 3/3/2017.
 */

public class TimerService extends Service {
    private static Timer timer = new Timer();
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        timer.scheduleAtFixedRate(new sendTimeTask(), 0, 5000);
        return START_STICKY;
    }
    private class sendTimeTask extends TimerTask
    {
        public void run()
        {
            Intent i = new Intent("timerFilter");
            String time = DateFormat.getDateTimeInstance().format(new Date());
            i.putExtra("time",time);
            sendBroadcast(i);
            Log.i("TimerService", time);
        }
    }
}
