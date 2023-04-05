package ro.pub.cs.systems.eim.colocviu1_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PracticalTest01ServiceBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String data = "";
        String action = intent.getAction();

        assert action != null;
        if(action.equals(Constants.ACTION_1)) {
            data = intent.getStringExtra(Constants.DATA);
            Toast.makeText(context, data, Toast.LENGTH_LONG).show();
        }
        Log.d(Constants.TAG, data);
    }
}
