package com.bumperjumper.android.easyinsure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumperjumper.android.easyinsure.R;
import com.bumperjumper.android.easyinsure.RetrofitController;
import com.bumperjumper.android.easyinsure.interfaces.DocuApi;
import com.bumperjumper.android.easyinsure.model.PolicyFormPojo;
import com.bumperjumper.android.easyinsure.utils.StringUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private CardView mApproveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.policy_form);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Intent i = getIntent();
        PolicyFormPojo formPojo = i.getExtras().getParcelable("RegInfo");

        minputName = findViewById(R.id.input_name);
        mSpinnerVehicleType = findViewById(R.id.spinner_vehicleType);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vehicle_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerVehicleType.setAdapter(adapter);

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
                PolicyFormPojo policyFormPojo = getPolicyFormFromUI();

                Toast.makeText(PolicyFormActivity.this, "Please make the premium payment!", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(PolicyFormActivity.this,PaymentsActivity.class);
                intent.putExtra("premium", policyFormPojo.premium != null ? policyFormPojo.premium : "2000");
                startActivity(intent);

                /*approve(policyFormPojo, new ApproveCallback() {
                    @Override
                    public void onComplete(Response<ResponseBody> response, Throwable t) {

                        if(t != null || !response.isSuccessful()) {
                            Toast.makeText(PolicyFormActivity.this, "Uh-oh! There was some issue!", Toast.LENGTH_LONG).show();
                        } else {
                            // 2. Move to payments page
                            Toast.makeText(PolicyFormActivity.this, "Please make the premium payment!", Toast.LENGTH_LONG).show();
                        }
                    }
                });*/
            }
        });

        injectDataIntoInputFields(formPojo);

    }

    private void injectDataIntoInputFields(PolicyFormPojo policyFormPojo) {

        if(policyFormPojo == null) {
            Toast.makeText(PolicyFormActivity.this, "There has been some issue!!", Toast.LENGTH_LONG).show();
            Log.i("INJECT-DATA", "Null from previous activitiy!");
            return;
        }

        minputName.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.name));

        mSpinnerVehicleType.setSelection(((ArrayAdapter<CharSequence>)mSpinnerVehicleType.getAdapter()).getPosition(policyFormPojo.vehicleType));

        minputDisplacement.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.displacement));
        mInputRegistrationNumber.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.registrationNumber));
        mInputPolicyDate.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.policyDate));
        mInputPolicyExpiryDate.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.policyExpiryDate));
        mInputChassisNumber.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.chassisNumber));
        mInputEngineNumber.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.engineNumber));
        mInputVehicleCompany.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.vehicleCompany));
    }


    private PolicyFormPojo getPolicyFormFromUI() {
        PolicyFormPojo policyFormPojo = new PolicyFormPojo();

        policyFormPojo.name = minputName.getText().toString();

        policyFormPojo.vehicleType = mSpinnerVehicleType.getSelectedItem().toString();

        policyFormPojo.displacement = minputDisplacement.getText().toString();
        policyFormPojo.registrationNumber = mInputRegistrationNumber.getText().toString();
        policyFormPojo.policyDate = mInputPolicyDate.getText().toString();
        policyFormPojo.policyExpiryDate = mInputPolicyExpiryDate.getText().toString();
        policyFormPojo.chassisNumber = mInputChassisNumber.getText().toString();
        policyFormPojo.engineNumber = mInputEngineNumber.getText().toString();
        policyFormPojo.vehicleCompany = mInputVehicleCompany.getText().toString();

        return policyFormPojo;
    }

    private void approve(PolicyFormPojo policyFormPojo, final ApproveCallback approveCallback) {

        RetrofitController controller = new RetrofitController();

        Call<ResponseBody> request = controller.getRetrofitInstance().create(DocuApi.class).approve(policyFormPojo);

        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()) {
                    Log.i("APPROVE-API-CALL", "Successful response");
                } else {
                    Log.i("APPROVE-API-CALL", "status code above 300");
                }
                approveCallback.onComplete(response, null);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("APPROVE-API-CALL", "Response failure", t);
                approveCallback.onComplete(null, t);
            }
        });
    }

    interface ApproveCallback {
        void onComplete(Response<ResponseBody> response, Throwable t);
    }

}
