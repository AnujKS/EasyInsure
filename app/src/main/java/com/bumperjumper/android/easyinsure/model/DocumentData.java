package com.bumperjumper.android.easyinsure.model;

import com.google.gson.annotations.SerializedName;

public class DocumentData {

    @SerializedName("data")
    String data;

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
