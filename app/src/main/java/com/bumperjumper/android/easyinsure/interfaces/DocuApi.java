package com.bumperjumper.android.easyinsure.interfaces;

import com.bumperjumper.android.easyinsure.model.DocumentData;
import com.bumperjumper.android.easyinsure.model.PolicyFormPojo;
import com.google.gson.JsonElement;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DocuApi {

    @POST("transform-text")
    @Headers({"Content-Type: application/json", "User-Agent: EazyInsure"})
    public Call<JsonElement> postDocument(@Body DocumentData data);

    @POST("approve")
    @Headers({"Content-Type: application/json", "User-Agent: EazyInsure"})
    Call<ResponseBody> approve(@Body PolicyFormPojo policyFormPojo);
}
