package gestaotreinos.model.entity;

public class Usuario {

	private int idUsuario;
	private String nome;
	private char sexo;
	private int idade;
	private double peso;
	private double altura;
	private double metaPeso;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, char sexo, int idade, double peso, double altura) {
		this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
	}
	
	public Usuario(String nome, char sexo, int idade, double peso, double altura, double metaPeso) {
		this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.metaPeso = metaPeso;
	}

	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getMetaPeso() {
		return metaPeso;
	}
	public void setMetaPeso(double metaPeso) {
		this.metaPeso = metaPeso;
	}

	public double calcularIMC() {
		
		if(altura <= 0 || peso <= 0) {
			throw new IllegalArgumentException("peso e altura devem ser números validos para calcular o IMC");
		}
		
		return peso/(altura*altura);	
	}
	
	public double calcularTMB() {
		
		if(idade <= 0 || altura <=0 || peso<=0 ) {
			throw new IllegalArgumentException("Os valores de idade, altura e peso precisam ser válidos.");
		}
			
		double alturaCM = altura*100;
		
		switch (sexo) {
			case 'F' : 
				return 447.6 + (9.2*peso) + (3.1*alturaCM) - (4.3*idade);
				
			case 'M':
				return 88.36 + (13.4*peso) + (4.8*alturaCM) - (5.7*idade);
				
			default:
				throw new IllegalArgumentException("O sexo deve ser M ou F.");
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [idUsuario=");
		builder.append(idUsuario);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", sexo=");
		builder.append(sexo);
		builder.append(", idade=");
		builder.append(idade);
		builder.append(", peso=");
		builder.append(peso);
		builder.append(", altura=");
		builder.append(altura);
		builder.append(", metaPeso=");
		builder.append(metaPeso);
		builder.append("]");
		return builder.toString();
	}
	
}
