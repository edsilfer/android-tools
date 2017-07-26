package tradeforce.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class JsonCheckSubTipo {

    @Nullable @SerializedName("Id") public abstract Integer Id();
	@Nullable @SerializedName("Nome") public abstract String Nome();
	

    public static TypeAdapter<JsonCheckSubTipo> typeAdapter(Gson gson) {
        return new AutoValue_JsonCheckSubTipo.GsonTypeAdapter(gson);
    }

}