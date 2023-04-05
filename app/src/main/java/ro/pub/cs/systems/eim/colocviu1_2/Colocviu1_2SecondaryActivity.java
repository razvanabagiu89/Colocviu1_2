package ro.pub.cs.systems.eim.colocviu1_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;


public class Colocviu1_2SecondaryActivity extends AppCompatActivity {

    private EditText editTextSum;

    private Button buttonOk;
    private Button buttonCancel;

//    private ButtonClickListener buttonClickListener = new ButtonClickListener();
//    private class ButtonClickListener implements View.OnClickListener {
//
////        @Override
////        public void onClick(View view) {
////            if(view.getId() == R.id.buttonOk) {
////                setResult(Integer.parseInt(editTextSum.getText().toString()), null);
////            }
////            else if(view.getId() == R.id.buttonCancel) {
////                setResult(RESULT_CANCELED, null);
////            }
////            finish();
////        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if(intent != null) {
            if(intent.getExtras().containsKey(Constants.DATA)) {
                String allTerms = intent.getStringExtra(Constants.DATA);
                // split allTerms by + and compute sum
                String[] terms = allTerms.split("\\+");
                int sum = 0;
                for (String term : terms) {
                    sum += Integer.parseInt(term);
                }
                setResult(sum, null);
                finish();
            }
        }
    }
}
