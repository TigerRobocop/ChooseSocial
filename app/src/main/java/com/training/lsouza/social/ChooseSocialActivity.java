package com.training.lsouza.social;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChooseSocialActivity extends AppCompatActivity {

    @Bind(R.id.groupSocial)
    RadioGroup mGroupSocial;
    @Bind(R.id.rd_facebook)
    RadioButton mBtnFacebook;
    @Bind(R.id.rd_twitter)
    RadioButton mBtnTwitter;
    @Bind(R.id.rd_gplus)
    RadioButton mBtnGplus;
    @Bind(R.id.btn_saveChoice)
    Button mBtnSaveChoice;

    int mCurrentChoice = R.string.btn_chooseSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_social);

        ButterKnife.bind(this);



        if(savedInstanceState != null){
            mCurrentChoice = savedInstanceState.getInt("SOCIAL_CHOICE");
            if (mCurrentChoice != 0) {
                setRadioSelection(mCurrentChoice);
            }
        }else{
            Intent intent = getIntent();
            mCurrentChoice = intent.getIntExtra("SAVED_CHOICE", -1);
            setRadioSelection(mCurrentChoice);
        }

        mBtnSaveChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("CHOICE_FROM_INTENT", mCurrentChoice);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        mGroupSocial.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_facebook:
                        mCurrentChoice = R.string.lblSocial_facebook;
                        break;
                    case R.id.rd_twitter:
                        mCurrentChoice = R.string.lblSocial_twitter;
                        break;
                    case R.id.rd_gplus:
                        mCurrentChoice = R.string.lbSocial_gplus;
                        break;
                    default:
                        mCurrentChoice = R.string.btn_chooseSocial;
                        break;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("SOCIAL_CHOICE", mCurrentChoice);
    }

    private void setRadioSelection(int index) {
        switch (index) {
            case R.string.lblSocial_facebook:
                mBtnFacebook.setChecked(true);
                break;
            case  R.string.lblSocial_twitter:
                mBtnTwitter.setChecked(true);
                break;
            case R.string.lbSocial_gplus:
                mBtnGplus.setChecked(true);
                break;
            default:
                mGroupSocial.clearCheck();
        }
    }

}
