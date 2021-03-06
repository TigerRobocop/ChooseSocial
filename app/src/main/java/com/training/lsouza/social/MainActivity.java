package com.training.lsouza.social;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_chooseSocial)
    Button mBtnChooseSocial;
    @Bind(R.id.btn_submit)
    Button mBtnSubmit;
    @Bind(R.id.editText_inputName)
    EditText mInputName;
    @Bind(R.id.chk_isNotifications)
    CheckBox mChkIsNotifications;
    @BindString(R.string.name_error)
    String toastError;


    int mChoice = R.string.btn_chooseSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            mChoice = savedInstanceState.getInt("SOCIAL_CHOICE");
            if (mChoice != 0) {

                mBtnChooseSocial.setText(mChoice);
            }
        }

        mBtnChooseSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseSocialActivity.class);
                intent.putExtra("SAVED_CHOICE", mChoice);

                startActivityForResult(intent, 2);
            }
        });


        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent(MainActivity.this, ResultActivity.class);
                Bundle extras = new Bundle();

                String name = mInputName.getText().toString();

                if (name.trim().equals("") || mChoice == R.string.btn_chooseSocial) {
                    Toast.makeText(MainActivity.this, toastError, Toast.LENGTH_SHORT).show();

                } else {
                    extras.putString("RESULT_NAME", name);
                    extras.putInt("RESULT_SOCIAL", mChoice);
                    extras.putBoolean("RESULT_NOTIFICATIONS", mChkIsNotifications.isChecked());

                    intentResult.putExtras(extras);

                    startActivity(intentResult);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK) {
            int choiceFromIntent = data.getIntExtra("CHOICE_FROM_INTENT", -1);

            mChoice = choiceFromIntent;
            mBtnChooseSocial.setText(choiceFromIntent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("SOCIAL_CHOICE", mChoice);
    }
}
