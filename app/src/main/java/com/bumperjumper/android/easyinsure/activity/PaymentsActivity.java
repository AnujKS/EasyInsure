package com.bumperjumper.android.easyinsure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumperjumper.android.easyinsure.R;
import com.bumperjumper.android.easyinsure.model.PremiumAmoutPojo;

import java.math.BigDecimal;

public class PaymentsActivity extends AppCompatActivity {

    private TextView mTotalPremium;
    private TextView mPremiumAmount;
    private TextView mTaxAmount;
    private TextView mTotalPremiumAmount;

    private Button mPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.payments_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);

        mTotalPremium = findViewById(R.id.text_totalPremium);
        mPremiumAmount = findViewById(R.id.text_premiumAmount);
        mTaxAmount = findViewById(R.id.text_taxAmount);
        mTotalPremiumAmount = findViewById(R.id.text_totalPremiumAmount);

        mPayment = findViewById(R.id.button_payment);

        mPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to next activity
                Intent intent = new Intent(PaymentsActivity.this, PaymentDoneActivity.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();
        String premium = i.getStringExtra("premium");
        PremiumAmoutPojo premiumAmoutPojo = new PremiumAmoutPojo(new BigDecimal(premium));

        mTotalPremium.setText(premiumAmoutPojo.getTotalAmount().toString());
        mPremiumAmount.setText(premiumAmoutPojo.getPremium().toString());
        mTaxAmount.setText(premiumAmoutPojo.getTax().toString());
        mTotalPremiumAmount.setText(premiumAmoutPojo.getTotalAmount().toString());
    }
}
