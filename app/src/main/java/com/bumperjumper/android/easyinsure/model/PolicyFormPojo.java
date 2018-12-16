package com.bumperjumper.android.easyinsure.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;

import androidx.annotation.RequiresApi;

public class PolicyFormPojo implements Parcelable {

    public String registrationNumber;
    public String name;
    public String description;
    public String registrationYear;
    public String vehicleCompany;
    public String vehicleModel;
    public String displacement;
    public String makeDescription;
    public String modelDescription;
    public String chassisNumber;
    public String numberOfSeats;
    public String colour;
    public String engineNumber;
    public String fuelType;
    public String registrationDate;
    public String location;
    public String vehicleType;
    public String premium;
    public String policyDate;
    public String policyExpiryDate;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.registrationNumber);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.registrationYear);
        dest.writeString(this.vehicleCompany);
        dest.writeString(this.vehicleModel);
        dest.writeString(this.displacement);
        dest.writeString(this.makeDescription);
        dest.writeString(this.modelDescription);
        dest.writeString(this.chassisNumber);
        dest.writeString(this.numberOfSeats);
        dest.writeString(this.colour);
        dest.writeString(this.engineNumber);
        dest.writeString(this.fuelType);
        dest.writeString(this.registrationDate);
        dest.writeString(this.location);
        dest.writeString(this.vehicleType);
        dest.writeString(this.premium);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public PolicyFormPojo() {
        this.policyDate = LocalDate.now().toString();
        this.policyDate = LocalDate.now().plusDays(365).toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected PolicyFormPojo(Parcel in) {
        this();

        this.registrationNumber= in.readString();
        this.name= in.readString();
        this.description= in.readString();
        this.registrationYear= in.readString();
        this.vehicleCompany= in.readString();
        this.vehicleModel= in.readString();
        this.displacement= in.readString();
        this.makeDescription= in.readString();
        this.modelDescription= in.readString();
        this.chassisNumber= in.readString();
        this.numberOfSeats= in.readString();
        this.colour= in.readString();
        this.engineNumber= in.readString();
        this.fuelType= in.readString();
        this.registrationDate= in.readString();
        this.location= in.readString();
        this.vehicleType= in.readString();
        this.premium= in.readString();
    }

    public static final Parcelable.Creator<PolicyFormPojo> CREATOR = new Parcelable.Creator<PolicyFormPojo>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
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
