/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gestaotreinos.enums.TipoTreino;
import java.util.Date;

public class Treino {

	private int idTreino;
	private Date data;
	private TipoTreino tipo;
	private Usuario usuario;
	private List<Exercicio> exercicios = new ArrayList<>();
	
	public Treino() {
		
	}
	
	public Treino(Usuario usuario,Date data,TipoTreino tipo) {
		setUsuario(usuario);
		setData(data);
		setTipo(tipo);
	}
	
	public int getIdTreino() {
		return idTreino;
	}
	public void setIdTreino(int idTreino) {
		this.idTreino = idTreino;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		if (data == null) {
		    throw new IllegalArgumentException("A data não pode ser nula");
		}
		this.data = data;
	}
	public TipoTreino getTipo() {
		return tipo;
	}

	public void setTipo(TipoTreino tipo) {
		if(tipo == null) {
			throw new IllegalArgumentException("O tipo de trino não pode ser nulo.");
		}
		this.tipo = tipo;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("O usuário deve ser válido.");
		}
		this.usuario = usuario;
	}
	
	public void adicionarExercicio(Exercicio exercicio){
		if(exercicio == null) {
			throw new IllegalArgumentException("O execicio nao pode ser nulo.");
		}
		exercicios.add(exercicio);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Treino [idTreino=");
		builder.append(idTreino);
		builder.append(", data=");
		builder.append(data);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append(", exercicios=");
		builder.append(exercicios);
		builder.append("]");
		return builder.toString();
	}
}
