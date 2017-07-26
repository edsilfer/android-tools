package tradeforce.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class JsonCheckOpcao {

    @Nullable @SerializedName("Id") public abstract Integer Id();
	@Nullable @SerializedName("OpcaoId") public abstract Integer OpcaoId();
	@Nullable @SerializedName("TipoId") public abstract Integer TipoId();
	@Nullable @SerializedName("SubTipoId") public abstract Integer SubTipoId();
	@Nullable @SerializedName("Nome") public abstract String Nome();
	@Nullable @SerializedName("Descricao") public abstract String Descricao();
	@Nullable @SerializedName("Duracao") public abstract Integer Duracao();
	

    public static TypeAdapter<JsonCheckOpcao> typeAdapter(Gson gson) {
        return new AutoValue_JsonCheckOpcao.GsonTypeAdapter(gson);
    }

}