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
		if(carga <= 0) {
			throw new IllegalArgumentException("A carga deve ser maior que zero.");
		}
		this.carga = carga;
	}
	public int getRepeticoes() {
		return repeticoes;
	}
	public void setRepeticoes(int repeticoes) {
		if(repeticoes <= 0) {
			throw new IllegalArgumentException("As repetições devem ser maiores que zero.");
		}
		this.repeticoes = repeticoes;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		if(series <= 0) {
			throw new IllegalArgumentException("As series devem ser maiores que zero.");
		}
		this.series = series;
	}
	public Treino getTreino() {
		return treino;
	}
	public void setTreino(Treino treino) {
		if(treino == null) {
			throw new IllegalArgumentException("O treino não pode ser nulo.");
		}
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
