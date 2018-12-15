package com.bumperjumper.android.easyinsure.activity;

<<<<<<< HEAD
=======
import android.content.Intent;
import android.os.Build;
>>>>>>> passed data to policy form
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bumperjumper.android.easyinsure.R;
import com.bumperjumper.android.easyinsure.model.PolicyFormPojo;
import com.bumperjumper.android.easyinsure.utils.StringUtils;

public class PolicyFormActivity extends AppCompatActivity {

    private EditText minputName;

    private Spinner mSpinnerVehicleType;

    private EditText minputDisplacement;
    private EditText mInputRegistrationNumber;
    private EditText mInputPolicyDate;
    private EditText mInputPolicyExpiryDate;
    private EditText mInputChassisNumber;
    private EditText mInputEngineNumber;
    private EditText mInputVehicleCompany;

    private Button mApproveBtn;

    private PolicyFormPojo mPolicyFormPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        PolicyFormPojo formPojo = i.getExtras().getParcelable("RegInfo");

        minputName = findViewById(R.id.input_name);
        mSpinnerVehicleType = findViewById(R.id.spinner_vehicleType);
        minputDisplacement = findViewById(R.id.input_displacement);
        mInputRegistrationNumber = findViewById(R.id.input_registrationNumber);
        mInputPolicyDate = findViewById(R.id.input_policyDate);
        mInputPolicyExpiryDate = findViewById(R.id.input_policyExpiryDate);
        mInputChassisNumber = findViewById(R.id.input_chassisNumber);
        mInputEngineNumber = findViewById(R.id.input_engineNumber);
        mInputVehicleCompany = findViewById(R.id.input_vehicleCompany);

        mApproveBtn = findViewById(R.id.btn_approve);
        mApproveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO
                // 1. Make the update call
                // 2. Move to payments page
            }
        });

    }

    private void injectDataIntoInputFields(PolicyFormPojo policyFormPojo) {

        minputName.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.name));

//        mSpinnerVehicleType.set(StringUtils.INSTANCE.getCleansedString(policyFormPojo.erVehicleType));

        minputDisplacement.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.displacement));
        mInputRegistrationNumber.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.registrationNumber));
        mInputPolicyDate.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.policyDate));
        mInputPolicyExpiryDate.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.policyExpiryDate));
        mInputChassisNumber.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.chassisNumber));
        mInputEngineNumber.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.engineNumber));
        mInputVehicleCompany.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.vehicleCompany));
    }



}
