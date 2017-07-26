package tradeforce.model;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class CheckerOptions  implements Serializable {

	private int DuracaoIntervalo;
	
	private List<CheckTipo> Tipos;
	
	private List<CheckSubTipo> SubTipos;
	
	private List<TiposLocalizacao> TiposLocalizacao;
	
	private List<CheckOpcao> Opcoes;

	public int getDuracaoIntervalo() {
		return DuracaoIntervalo;
	}

	public void setDuracaoIntervalo(int duracaoIntervalo) {
		DuracaoIntervalo = duracaoIntervalo;
	}

	public List<CheckTipo> getTipos() {
		return Tipos;
	}

	public List<CheckOpcao> getOpcoes() {
		return Opcoes;
	}

	public void setOpcoes(List<CheckOpcao> opcoes) {
		Opcoes = opcoes;
	}

	public void setTipos(List<CheckTipo> tipos) {
		Tipos = tipos;
	}

	public List<CheckSubTipo> getSubTipos() {
		return SubTipos;
	}

	public void setSubTipos(List<CheckSubTipo> subTipos) {
		SubTipos = subTipos;
	}

	public List<TiposLocalizacao> getTiposLocalizacao() {
		return TiposLocalizacao;
	}

	public void setTiposLocalizacao(List<TiposLocalizacao> tipos) {
		this.TiposLocalizacao = tipos;
	}
	
}
