package gestaotreinos.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gestaotreinos.enums.TipoRefeicao;

public class Refeicao {
	Usuario usuario;
	private int idRefeicao;
	List<Alimento>alimentos = new ArrayList<>();
	private LocalDate data;
	private TipoRefeicao tipo;
	
	public Refeicao(){
		
	}
	
	public Refeicao(Usuario usuario,LocalDate data, TipoRefeicao tipo) {
		setUsuario(usuario);
		setData(data);
		setTipo(tipo);
	}
		
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("O usuário não deve ser nulo.");
		}
		this.usuario = usuario;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}
	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public int getIdRefeicao() {
		return idRefeicao;
	}
	public void setIdRefeicao(int idRefeicao) {
		this.idRefeicao = idRefeicao;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		if(data == null) {
			throw new IllegalArgumentException("A data não pode ser nulo.");
		}
		this.data = data;
	}
	public TipoRefeicao getTipo() {
		return tipo;
	}
	public void setTipo(TipoRefeicao tipo) {
		if(tipo == null) {
			throw new IllegalArgumentException("O tipo da refeição não pode ser nulo.");
		}
		this.tipo = tipo;
	}
	public void adicionarAlimento(Alimento alimento) {
		if(alimento == null){
			throw new IllegalArgumentException("O alimento nao pode ser nulo.");
		}
	    alimento.setRefeicao(this);
	    alimentos.add(alimento);
	}
	public double calcularCalorias() {
		double total = 0;
		for(int i = 0; i < alimentos.size(); i++) {
			total += alimentos.get(i).getCalorias();
		}
		return total;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Refeicao [usuario=");
		builder.append(usuario);
		builder.append(", idRefeicao=");
		builder.append(idRefeicao);
		builder.append(", alimentos=");
		builder.append(alimentos);
		builder.append(", data=");
		builder.append(data);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append("]");
		return builder.toString();
	}
}
