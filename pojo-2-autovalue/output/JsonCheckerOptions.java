package tradeforce.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class JsonCheckerOptions {

    @Nullable @SerializedName("DuracaoIntervalo") public abstract Integer DuracaoIntervalo();
	@Nullable @SerializedName("Tipos") public abstract List<CheckTipo> Tipos();
	@Nullable @SerializedName("SubTipos") public abstract List<CheckSubTipo> SubTipos();
	@Nullable @SerializedName("TiposLocalizacao") public abstract List<TiposLocalizacao> TiposLocalizacao();
	@Nullable @SerializedName("Opcoes") public abstract List<CheckOpcao> Opcoes();
	

    public static TypeAdapter<JsonCheckerOptions> typeAdapter(Gson gson) {
        return new AutoValue_JsonCheckerOptions.GsonTypeAdapter(gson);
    }

}