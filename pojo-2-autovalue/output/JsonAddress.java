package com.edsilfer.android.domain.entity;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class JsonAddress {

    @Nullable @SerializedName("streetAddress1") public abstract String streetAddress1();
	@Nullable @SerializedName("streetAddress2") public abstract String streetAddress2();
	@Nullable @SerializedName("number") public abstract String number();
	@Nullable @SerializedName("complement") public abstract String complement();
	@Nullable @SerializedName("neighborhood") public abstract String neighborhood();
	@Nullable @SerializedName("city") public abstract City city();
	@Nullable @SerializedName("state") public abstract State state();
	@Nullable @SerializedName("country") public abstract Country country();
	@Nullable @SerializedName("zipCode") public abstract String zipCode();
	@Nullable @SerializedName("startLiving") public abstract Date startLiving();
	@Nullable @SerializedName("endLiving") public abstract Date endLiving();
	

    public static TypeAdapter<JsonAddress> typeAdapter(Gson gson) {
        return new AutoValue_JsonAddress.GsonTypeAdapter(gson);
    }

}