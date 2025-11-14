package gestaotreinos.model.entity;

import java.time.LocalDate;

public class Treino {

	private int idTreino;
	private LocalDate data;
	private String tipo;
	private Usuario usuario;
	
	public Treino() {
		
	}
	
	public Treino(Usuario usuario,LocalDate data,String tipo) {
		this.usuario = usuario;
		this.data = data;
		this.tipo = tipo;
	}
	
	public int getIdTreino() {
		return idTreino;
	}
	public void setIdTreino(int idTreino) {
		this.idTreino = idTreino;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Treino [idTreino=");
		builder.append(idTreino);
		builder.append(", data=");
		builder.append(data);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append("]");
		return builder.toString();
	}


}
