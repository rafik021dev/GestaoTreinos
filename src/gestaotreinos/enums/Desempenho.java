package gestaotreinos.enums;

public class Desempenho {
	private int idDesempenho;
	private double mediaTreinos;
	private double mediaSono;
	private double mediaCalorias;
	private double indiceDesempenho;
	
	public int getIdDesempenho() {
		return idDesempenho;
	}
	public void setIdDesempenho(int idDesempenho) {
		this.idDesempenho = idDesempenho;
	}
	public double getMediaTreinos() {
		return mediaTreinos;
	}
	public void setMediaTreinos(double mediaTreinos) {
		this.mediaTreinos = mediaTreinos;
	}
	public double getMediaSono() {
		return mediaSono;
	}
	public void setMediaSono(double mediaSono) {
		this.mediaSono = mediaSono;
	}
	public double getMediaCalorias() {
		return mediaCalorias;
	}
	public void setMediaCalorias(double mediaCalorias) {
		this.mediaCalorias = mediaCalorias;
	}
	public double getIndiceDesempenho() {
		return indiceDesempenho;
	}
	public void setIndiceDesempenho(double indiceDesempenho) {
		this.indiceDesempenho = indiceDesempenho;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Desempenho [idDesempenho=");
		builder.append(idDesempenho);
		builder.append(", mediaTreinos=");
		builder.append(mediaTreinos);
		builder.append(", mediaSono=");
		builder.append(mediaSono);
		builder.append(", mediaCalorias=");
		builder.append(mediaCalorias);
		builder.append(", indiceDesempenho=");
		builder.append(indiceDesempenho);
		builder.append("]");
		return builder.toString();
	}
}
