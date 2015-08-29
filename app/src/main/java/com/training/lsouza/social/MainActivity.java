package com.training.lsouza.social;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_chooseSocial) Button mBtnChooseSocial;


    int mChoice = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mBtnChooseSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseSocialActivity.class);
                intent.putExtra("SAVED_CHOICE", mChoice);

                startActivityForResult(intent, 2);
            }
        });
            }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK){
            int choiceFromIntent = data.getIntExtra("CHOICE_FROM_INTENT", -1);
           // Toast.makeText(this, Integer.toString(choiceFromIntent), Toast.LENGTH_SHORT).show();
            mChoice = choiceFromIntent;
            mBtnChooseSocial.setText(choiceFromIntent);
            //code that sets the btn text to whatever result comes from intent
        }
    }
}
