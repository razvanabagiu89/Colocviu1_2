package ro.pub.cs.systems.eim.colocviu1_2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Colocviu1_2MainActivity extends AppCompatActivity {

    EditText editTextNextTerm;
    EditText editTextAllTerms;

    Button buttonAdd;
    Button buttonCompute;

    Integer previous = 0;
    String previousAllTerms = "";


    private int serviceStatus = Constants.SERVICE_STOPPED;
    private PracticalTest01ServiceBroadcastReceiver practicalTest01ServiceBroadcastReceiver = new PracticalTest01ServiceBroadcastReceiver();
    private IntentFilter intentFilter = new IntentFilter();
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.buttonAdd) {
                if(editTextAllTerms.getText().length() == 0) {
                    editTextAllTerms.setText(editTextNextTerm.getText().toString());
                }
                else {
                    if(editTextNextTerm.getText().length() != 0) {
                        editTextAllTerms.setText(editTextAllTerms.getText().toString() + "+" + editTextNextTerm.getText().toString());
                    }
                }
            }
            else if (view.getId() == R.id.buttonCompute) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_2SecondaryActivity.class);
                String allTerms = editTextAllTerms.getText().toString();
                intent.putExtra(Constants.DATA, allTerms);

                if(previousAllTerms.equals(allTerms)) {
                    Toast.makeText(getApplicationContext(), "Same terms " + previous, Toast.LENGTH_LONG).show();
                }
                else {
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    previousAllTerms = allTerms;
                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_2_main);


        editTextNextTerm = (EditText) findViewById(R.id.editTextNextTerm);
        editTextAllTerms = (EditText) findViewById(R.id.editTextAllTerms);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonCompute = (Button) findViewById(R.id.buttonCompute);


        buttonAdd.setOnClickListener(buttonClickListener);
        buttonCompute.setOnClickListener(buttonClickListener);

        intentFilter.addAction(Constants.ACTION_1);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString(Constants.NEXT, editTextNextTerm.getText().toString());
        savedInstanceState.putString(Constants.ALL, editTextAllTerms.getText().toString());
        savedInstanceState.putString(Constants.PREV, previous.toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.NEXT)) {
            editTextNextTerm.setText(savedInstanceState.getString(Constants.NEXT));
        } else {
            editTextNextTerm.setText("");
        }
        if (savedInstanceState.containsKey(Constants.ALL)) {
            editTextAllTerms.setText(savedInstanceState.getString(Constants.ALL));
        } else {
            editTextAllTerms.setText("");
        }
        if (savedInstanceState.containsKey(Constants.PREV)) {
            previous = Integer.parseInt(savedInstanceState.getString(Constants.PREV));
            Toast.makeText(this, String.valueOf(previous), Toast.LENGTH_LONG).show();
        } else {
            previous = 0;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            previous = resultCode;
            Toast.makeText(this, String.valueOf(resultCode), Toast.LENGTH_LONG).show();

            if (resultCode > Constants.LIMIT) {
                Intent intentService = new Intent(getApplicationContext(), Colocviu1_2Service.class);
                intentService.putExtra(Constants.DATA, resultCode);
                getApplicationContext().startService(intentService);
                serviceStatus = Constants.SERVICE_STARTED;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(practicalTest01ServiceBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(practicalTest01ServiceBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, Colocviu1_2Service.class);
        stopService(intent);
    }
}