package tradeforce.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class JsonCheckTipo {

    @Nullable @SerializedName("Id") public abstract Integer Id();
	@Nullable @SerializedName("Nome") public abstract String Nome();
	@Nullable @SerializedName("ObterLocalizacao") public abstract Boolean ObterLocalizacao();
	

    public static TypeAdapter<JsonCheckTipo> typeAdapter(Gson gson) {
        return new AutoValue_JsonCheckTipo.GsonTypeAdapter(gson);
    }

}