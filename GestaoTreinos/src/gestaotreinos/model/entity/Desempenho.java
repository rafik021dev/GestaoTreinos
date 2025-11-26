package gestaotreinos.model.entity;

public class Desempenho {
	private int idDesempenho;
	private double mediaSono;
	private double mediaCalorias;
	private double mediaTreino;
	private double indiceDesempenho;
	
	public Desempenho() {
		
	}

	public int getIdDesempenho() {
		return idDesempenho;
	}
	public void setIdDesempenho(int idDesempenho) {
		this.idDesempenho = idDesempenho;
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
	public double getMediaTreino() {
		return mediaTreino;
	}
	public void setMediaTreino(double mediaTreino) {
		this.mediaTreino = mediaTreino;
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
		builder.append(", mediaSono=");
		builder.append(mediaSono);
		builder.append(", mediaCalorias=");
		builder.append(mediaCalorias);
		builder.append(", mediaTreino=");
		builder.append(mediaTreino);
		builder.append(", indiceDesempenho=");
		builder.append(indiceDesempenho);
		builder.append("]");
		return builder.toString();
	}
}
