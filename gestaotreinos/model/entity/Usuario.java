/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.entity;

public class Usuario {

	private int idUsuario;
	private String nome;
	private char sexo;
	private int idade;
	private double peso;
	private double altura;
	private Double metaPeso;
    private String email;
    private String senha;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, char sexo, int idade, double peso, double altura) {
		 setNome(nome);
		 setSexo(sexo);
		 setIdade(idade);
		 setPeso(peso);
		 setAltura(altura);
	}
	
	public Usuario(String nome, char sexo, int idade, double peso, double altura, double metaPeso) {
		setNome(nome);
	    setSexo(sexo);
	    setIdade(idade);
	    setPeso(peso);
	    setAltura(altura);
	    setMetaPeso(metaPeso);
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
		if(nome.length() == 0) {
			throw new IllegalArgumentException("O nome não pode estar em branco");
		}
		this.nome = nome;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		if(sexo != 'F' && sexo != 'M') {
		throw new IllegalArgumentException("Sexo inválido.");
	}
		this.sexo = sexo;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		if(idade <= 0) {
			throw new IllegalArgumentException("A idade deve ser maior que zero");
		}
		this.idade = idade;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		if(peso <= 0) {
			throw new IllegalArgumentException("O peso deve ser maior que zero.");
		}
		this.peso = peso;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		if(altura <= 0) {
			throw new IllegalArgumentException("A altura deve ser maior que zero.");
		}
		this.altura = altura;
	}
	public Double getMetaPeso() {
		return metaPeso;
	}
	public void setMetaPeso(Double metaPeso) {
		if(metaPeso <= 0) {
			throw new IllegalArgumentException("A meta de peso deve ser maior que zero.");
		}
		this.metaPeso = metaPeso;
	}

	public double calcularIMC() {
		
		if(altura <= 0 || peso <= 0) {
			throw new IllegalArgumentException("Peso e altura devem ser números validos para calcular o IMC");
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
	
	public void atualizarPeso(double novoPeso) {
		if(novoPeso <= 0 || novoPeso > 500) {
			throw new IllegalArgumentException("Deve ser um peso válido.");
		}
			this.peso = novoPeso;
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
		builder.append(", calcularIMC()=");
		builder.append(calcularIMC());
		builder.append(", calcularTMB()=");
		builder.append(calcularTMB());
		builder.append("]");
		return builder.toString();
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
        
        
}
