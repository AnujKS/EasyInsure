package com.bumperjumper.android.easyinsure.interfaces;

import com.bumperjumper.android.easyinsure.model.DocumentData;
import com.bumperjumper.android.easyinsure.model.PolicyFormPojo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DocuApi {

    @POST("registration")
    @Headers({"Content-Type: application/json", "User-Agent: EazyInsure"})
    public Call<PolicyFormPojo> postDocument(@Body DocumentData data);

    @POST("approve")
    @Headers({"Content-Type: application/json", "User-Agent: EazyInsure"})
    Call<ResponseBody> approve(@Body PolicyFormPojo policyFormPojo);
}
