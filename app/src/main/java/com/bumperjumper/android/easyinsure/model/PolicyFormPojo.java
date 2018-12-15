package com.bumperjumper.android.easyinsure.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PolicyFormPojo implements Parcelable {
    public String name;
    public String vehicleType;
    public String displacement;
    public String registrationNumber;
    public String policyDate;
    public String policyExpiryDate;
    public String chassisNumber;
    public String engineNumber;
    public String vehicleCompany;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.vehicleType);
        dest.writeString(this.displacement);
        dest.writeString(this.registrationNumber);
        dest.writeString(this.policyDate);
        dest.writeString(this.policyExpiryDate);
        dest.writeString(this.chassisNumber);
        dest.writeString(this.engineNumber);
        dest.writeString(this.vehicleCompany);
    }

    public PolicyFormPojo() {
    }

    protected PolicyFormPojo(Parcel in) {
        this.name = in.readString();
        this.vehicleType = in.readString();
        this.displacement = in.readString();
        this.registrationNumber = in.readString();
        this.policyDate = in.readString();
        this.policyExpiryDate = in.readString();
        this.chassisNumber = in.readString();
        this.engineNumber = in.readString();
        this.vehicleCompany = in.readString();
    }

    public static final Parcelable.Creator<PolicyFormPojo> CREATOR = new Parcelable.Creator<PolicyFormPojo>() {
        @Override
        public PolicyFormPojo createFromParcel(Parcel source) {
            return new PolicyFormPojo(source);
        }

        @Override
        public PolicyFormPojo[] newArray(int size) {
            return new PolicyFormPojo[size];
        }
    };
}
