package tradeforce.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@SuppressWarnings("serial")
@DatabaseTable(tableName = "checktipo")
public class CheckTipo implements Serializable {

    public static final String ID = "Id";
    public static final String NOME = "Nome";
    public static final String OBTERLOCALIZACAO = "ObterLocalizacao";


    public static final String PDV = "PDV";
    public static final String INTERVALO = "Intervalo";


    @DatabaseField(columnName = ID, generatedId = true)
    private int Id;

    @DatabaseField(columnName = NOME)
    private String Nome;

    @DatabaseField(columnName = OBTERLOCALIZACAO)
    private boolean ObterLocalizacao;

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

    public boolean getObterLocalizacao() {
        return ObterLocalizacao;
    }

    public void setObterLocalizacao(boolean obterLocalizacao) {
        ObterLocalizacao = obterLocalizacao;
    }

}
