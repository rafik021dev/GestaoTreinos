package gestaotreinos.model.entity;
import gestaotreinos.enums.TipoRelatorio;

public class Relatorio {
	private int idRelatorio;
	private TipoRelatorio tipo;
	
	public Relatorio() {
		
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
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Relatorio [idRelatorio=");
		builder.append(idRelatorio);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append("]");
		return builder.toString();
	}	
}
