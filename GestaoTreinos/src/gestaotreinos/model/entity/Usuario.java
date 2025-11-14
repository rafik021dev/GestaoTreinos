package gestaotreinos.model.entity;

public class Usuario {

	private int idUsuario;
	private String nome;
	private String sexo;
	private int idade;
	private double peso;
	private double altura;
	private double metaPeso;
	
	public Usuario(String nome, String sexo, int idade, double peso, double altura) {
		this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
	}
	public Usuario(String nome, String sexo, int idade, double peso, double altura, double metaPeso) {
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
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
	
	
}
