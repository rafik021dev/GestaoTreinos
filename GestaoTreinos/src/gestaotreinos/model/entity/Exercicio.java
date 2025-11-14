package gestaotreinos.model.entity;

public class Exercicio {

	private int idExercicio;
	private int carga;
	private int repeticoes;
	private int series;
	private Treino treino;
	
	public Exercicio() {
		
	}
	
	public Exercicio(int carga,int repeticoes,int series,Treino treino) {
		this.carga = carga;
		this.repeticoes = repeticoes;
		this.series = series;
		this.treino = treino;
	}
	
	public int getIdExercicio() {
		return idExercicio;
	}
	public void setIdExercicio(int idExercicio) {
		this.idExercicio = idExercicio;
	}
	public int getCarga() {
		return carga;
	}
	public void setCarga(int carga) {
		this.carga = carga;
	}
	public int getRepeticoes() {
		return repeticoes;
	}
	public void setRepeticoes(int repeticoes) {
		this.repeticoes = repeticoes;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public Treino getTreino() {
		return treino;
	}
	public void setTreino(Treino treino) {
		this.treino = treino;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Exercicio [idExercicio=");
		builder.append(idExercicio);
		builder.append(", carga=");
		builder.append(carga);
		builder.append(", repeticoes=");
		builder.append(repeticoes);
		builder.append(", series=");
		builder.append(series);
		builder.append(", treino=");
		builder.append(treino);
		builder.append("]");
		return builder.toString();
	}
	
}
