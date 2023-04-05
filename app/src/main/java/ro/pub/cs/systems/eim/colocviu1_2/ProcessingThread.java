package ro.pub.cs.systems.eim.colocviu1_2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;
    private int sum;
    public ProcessingThread(Context context, Integer sum) {

        this.context = context;
        this.sum = sum;
    }

    @Override
    public void run() {
        Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        while(isRunning) {
            sendMessage(Constants.MESSAGE_1, this.sum);
            sleep();
        }
    }

    private void sendMessage(int messageType, int sum) {
        Intent intent = new Intent();
        if (messageType == Constants.MESSAGE_1) {
            intent.setAction(Constants.ACTION_1);
            intent.putExtra(Constants.DATA, Constants.DATE_DATA + " " + sum);
            Log.d(Constants.TAG, "Sent <ACTION_1>");

        }

        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(Constants.SLEEP_TIME);
        } catch (InterruptedException interruptedException) {
            Log.e(Constants.TAG, interruptedException.getMessage());
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
