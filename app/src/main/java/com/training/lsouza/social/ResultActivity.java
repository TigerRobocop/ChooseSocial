package com.training.lsouza.social;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    @Bind(R.id.textView_nameResult) TextView mLblNameResult;
    @Bind(R.id.textView_socialResul) TextView mLblSocialResult;
    @Bind(R.id.textView_resultNotifications) TextView mLblNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

//        Intent intentResult = getIntent();

        Bundle extrasResult = getIntent().getExtras();

        String nameResult = extrasResult.getString("RESULT_NAME");
        mLblNameResult.setText(nameResult);

        int socialResult = extrasResult.getInt("RESULT_SOCIAL");
        mLblSocialResult.setText(socialResult);

        boolean isNotifications = extrasResult.getBoolean("RESULT_NOTIFICATIONS");
        if (isNotifications){
            mLblNotifications.setText(R.string.stringResult_yes);
        }else {
            mLblNotifications.setText(R.string.stringResult_no);
        }



    }


}
