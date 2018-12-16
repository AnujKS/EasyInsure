package com.bumperjumper.android.easyinsure.activity;

import android.content.Intent;
import android.os.Build;
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

import androidx.annotation.RequiresApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PolicyFormActivity extends AppCompatActivity {

    private Spinner mSpinnerVehicleType;

    private EditText mName;
    private EditText mDisplacement;
    private EditText mRegistrationNumber;
    private EditText mPolicyDate;
    private EditText mPolicyExpiryDate;
    private EditText mChassisNumber;
    private EditText mDescription;
    private EditText mRegistrationYear;
    private EditText mVehicleCompany;
    private EditText mVehicleModel;
    private EditText mMakeDescription;
    private EditText mModelDescription;
    private EditText mNumberOfSeats;
    private EditText mColour;
    private EditText mEngineNumber;
    private EditText mFuelType;
    private EditText mRegistrationDate;
    private EditText mLocation;

    private CardView mApproveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.policy_form);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Intent i = getIntent();
        PolicyFormPojo formPojo = i.getExtras().getParcelable("RegInfo");

        mSpinnerVehicleType = findViewById(R.id.spinner_vehicleType);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vehicle_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerVehicleType.setAdapter(adapter);

        mName = findViewById(R.id.input_name);
        mDisplacement = findViewById(R.id.input_displacement);
        mRegistrationNumber = findViewById(R.id.input_registrationNumber);
        mPolicyDate = findViewById(R.id.input_policyDate);
        mPolicyExpiryDate = findViewById(R.id.input_policyExpiryDate);
        mChassisNumber = findViewById(R.id.input_chassisNumber);
        mDescription = findViewById(R.id.input_description);
        mRegistrationYear = findViewById(R.id.input_registrationYear);
        mVehicleCompany = findViewById(R.id.input_vehicleCompany);
        mVehicleModel = findViewById(R.id.input_vehicleModel);
        mMakeDescription = findViewById(R.id.input_makeDescription);
        mModelDescription = findViewById(R.id.input_modelDescription);
        mNumberOfSeats = findViewById(R.id.input_numberOfSeats);
        mColour = findViewById(R.id.input_colour);
        mEngineNumber = findViewById(R.id.input_engineNumber);
        mFuelType = findViewById(R.id.input_fuelType);
        mRegistrationDate = findViewById(R.id.input_registrationDate);
        mLocation = findViewById(R.id.input_location);

        mApproveBtn = findViewById(R.id.btn_approve);
        mApproveBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
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

        mSpinnerVehicleType.setSelection(((ArrayAdapter<CharSequence>)mSpinnerVehicleType.getAdapter()).getPosition(policyFormPojo.vehicleType));

        mName.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.name));
        mDisplacement.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.displacement));
        mRegistrationNumber.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.registrationNumber));
        mPolicyDate.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.policyDate));
        mPolicyExpiryDate.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.policyExpiryDate));
        mChassisNumber.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.chassisNumber));
        mDescription.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.description));
        mRegistrationYear.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.registrationYear));
        mVehicleCompany.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.vehicleCompany));
        mVehicleModel.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.vehicleModel));
        mMakeDescription.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.makeDescription));
        mModelDescription.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.modelDescription));
        mNumberOfSeats.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.numberOfSeats));
        mColour.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.colour));
        mEngineNumber.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.engineNumber));
        mFuelType.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.fuelType));
        mRegistrationDate.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.registrationDate));
        mLocation.setText(StringUtils.INSTANCE.getCleansedString(policyFormPojo.location));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private PolicyFormPojo getPolicyFormFromUI() {

        PolicyFormPojo policyFormPojo = new PolicyFormPojo();

        policyFormPojo.vehicleType = mSpinnerVehicleType.getSelectedItem().toString();


        policyFormPojo.name = mName.getText().toString();
        policyFormPojo.displacement = mDisplacement.getText().toString();
        policyFormPojo.registrationNumber = mRegistrationNumber.getText().toString();
        policyFormPojo.policyDate = mPolicyDate.getText().toString();
        policyFormPojo.policyExpiryDate = mPolicyExpiryDate.getText().toString();
        policyFormPojo.chassisNumber = mChassisNumber.getText().toString();
        policyFormPojo.description = mDescription.getText().toString();
        policyFormPojo.registrationYear = mRegistrationYear.getText().toString();
        policyFormPojo.vehicleCompany = mVehicleCompany.getText().toString();
        policyFormPojo.vehicleModel = mVehicleModel.getText().toString();
        policyFormPojo.makeDescription = mMakeDescription.getText().toString();
        policyFormPojo.modelDescription = mModelDescription.getText().toString();
        policyFormPojo.numberOfSeats = mNumberOfSeats.getText().toString();
        policyFormPojo.colour = mColour.getText().toString();
        policyFormPojo.engineNumber = mEngineNumber.getText().toString();
        policyFormPojo.fuelType = mFuelType.getText().toString();
        policyFormPojo.registrationDate = mRegistrationDate.getText().toString();
        policyFormPojo.location = mLocation.getText().toString();

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
