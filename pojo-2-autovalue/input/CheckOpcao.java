package tradeforce.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@SuppressWarnings("serial")
@DatabaseTable(tableName = "checkopcao")
public class CheckOpcao  implements Serializable {
	
	public static final String ID = "Id";
	public static final String OPCAOID = "OpcaoId";
	public static final String TIPOID = "TipoId";
	public static final String SUBTIPOID = "SubTipoId";
	public static final String NOME = "Nome";
	public static final String DESCRICAO = "Descricao";
	public static final String DURACAO = "Duracao";

	@DatabaseField(columnName = ID, generatedId = true)
    private int Id;
	
	@DatabaseField(columnName = OPCAOID)
    private int OpcaoId;
	
	@DatabaseField(columnName = TIPOID)
    private int TipoId;	
	
	@DatabaseField(columnName = SUBTIPOID)
    private int SubTipoId;	
	
	@DatabaseField(columnName = NOME)
    private String Nome;
	
	@DatabaseField(columnName = DESCRICAO)
    private String Descricao;
	
	@DatabaseField(columnName = DURACAO)
    private int Duracao;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getOpcaoId() {
		return OpcaoId;
	}

	public void setOpcaoId(int opcaoId) {
		OpcaoId = opcaoId;
	}

	public int getTipoId() {
		return TipoId;
	}

	public void setTipoId(int tipoId) {
		TipoId = tipoId;
	}

	public int getSubTipoId() {
		return SubTipoId;
	}

	public void setSubTipoId(int subTipoId) {
		SubTipoId = subTipoId;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public int getDuracao() {
		return Duracao;
	}

	public void setDuracao(int duracao) {
		Duracao = duracao;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

}
