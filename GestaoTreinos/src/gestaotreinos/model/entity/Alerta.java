package gestaotreinos.model.entity;

import java.time.LocalDate;
import ENUMs.TipoAlerta;

public class Alerta {

	private Usuario usuario;
	private LocalDate data;
	private TipoAlerta tipo;
	private String mensagem;
	
	public Alerta() {
		
	}
	
	public Alerta(Usuario usuario, LocalDate data,TipoAlerta tipo) {
		 setUsuario(usuario);
		 setData(data);
		 setTipo(tipo);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("O usuário deve ser válido.");
		}
		this.usuario = usuario;
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
	public TipoAlerta getTipo() {
		return tipo;
	}
	public void setTipo(TipoAlerta tipo) {
		if(tipo == null) {
			throw new IllegalArgumentException("O tipo não pode ser nulo.");
		}
		this.tipo = tipo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Alerta [usuario=");
		builder.append(usuario);
		builder.append(", data=");
		builder.append(data);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", mensagem=");
		builder.append(mensagem);
		builder.append("]");
		return builder.toString();
	}
	
	
}
