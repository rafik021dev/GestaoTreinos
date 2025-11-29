package gestaotreinos.model.entity;
import gestaotreinos.enums.TipoRelatorio;

public class Relatorio {
	private int idRelatorio;
	private TipoRelatorio tipo;
	private Usuario usuario;
	private Desempenho desempenho;
	
	public Relatorio() {
		
	}
	public Relatorio(TipoRelatorio tipo, Usuario usuario) {
		setTipo(tipo);
		setUsuario(usuario);
	}
	
	public Desempenho getDesempenho() {
		return desempenho;
	}
	public void setDesempenho(Desempenho desempenho) {
		this.desempenho = desempenho;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		if(usuario == null)
			throw new IllegalArgumentException("O usuário não pode ser nulo.");
		this.usuario = usuario;
	}
	public int getIdRelatorio() {
		return idRelatorio;
	}
	public void setIdRelatorio(int idRelatorio) {
		this.idRelatorio = idRelatorio;
	}
	public TipoRelatorio getTipo() {
		return tipo;
	}
	public void setTipo(TipoRelatorio tipo) {
		if(tipo == null)
			throw new IllegalArgumentException("O tipo não pode ser nulo.");
		this.tipo = tipo;
		
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Relatorio [idRelatorio=");
		builder.append(idRelatorio);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append(", desempenho=");
		builder.append(desempenho);
		builder.append("]");
		return builder.toString();
	}
}	

