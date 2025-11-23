package gestaotreinos.model.entity;

import gestaotreinos.enums.TipoMacro;

public class Alimento {
	private int idAlimento;
	private String nomeAlimento;
	private double quantidade;
	private double calorias;
	private TipoMacro tipo;
	private Refeicao refeicao;
	
	public Alimento() {
		
	}
	
	public Alimento(String nomeAlimento,double quantidade,double calorias,TipoMacro tipo) {
		setNomeAlimento(nomeAlimento);
		setQuantidade(quantidade);
		setCalorias(calorias);
		setTipo(tipo);
	}
	
	public Refeicao getRefeicao() {
		return refeicao;
	}
	public void setRefeicao(Refeicao refeicao) {
		if(refeicao == null) {
			throw new IllegalArgumentException("A refeicao não pode ser nulo.");
		}
		this.refeicao = refeicao;
	}
	public int getIdAlimento() {
		return idAlimento;
	}
	public void setIdAlimento(int idAlimento) {
		this.idAlimento = idAlimento;
	}
	public String getNomeAlimento() {
		return nomeAlimento;
	}
	public void setNomeAlimento(String nomeAlimento){
		if(nomeAlimento.length() == 0) {
			throw new IllegalArgumentException("O alimento deve ter um nome.");
		}
		this.nomeAlimento = nomeAlimento;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		if(quantidade <= 0) {
			throw new IllegalArgumentException("A quantidade deve ser maior que zero.");	
		}
		this.quantidade = quantidade;
	}
	public double getCalorias() {
		return calorias;
	}
	public void setCalorias(double calorias) {
		if(calorias <= 0) {
			throw new IllegalArgumentException("As calorias devem ser maior que zero.");	
		}
		this.calorias = calorias;
	}
	public TipoMacro getTipo() {
		return tipo;
	}
	public void setTipo(TipoMacro tipo) {
		if(tipo == null) {
			throw new IllegalArgumentException("O tipo de macronutriente não pode ser nulo.");
		}
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Alimento [idAlimento=");
		builder.append(idAlimento);
		builder.append(", nomeAlimento=");
		builder.append(nomeAlimento);
		builder.append(", quantidade=");
		builder.append(quantidade);
		builder.append(", calorias=");
		builder.append(calorias);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", refeicao=");
		builder.append(refeicao);
		builder.append("]");
		return builder.toString();
	}
}
