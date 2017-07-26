package tradeforce.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@SuppressWarnings("serial")
@DatabaseTable(tableName = "tipolocalizacao")
public class TiposLocalizacao  implements Serializable {
	
	public static final String ID = "Id";
	public static final String NOME = "Nome";

	@DatabaseField(columnName = ID, generatedId = true)
    private int Id;
	
	@DatabaseField(columnName = NOME)
    private String Nome;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

}
