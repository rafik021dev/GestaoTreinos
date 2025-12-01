/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.entity;

import java.time.LocalDate;

import gestaotreinos.enums.QualidadeSono;
import java.util.Date;

public class Sono {
	
	private Usuario usuario;
	private int idSono;
	private Date data;
	private double horasDormidas;
	private QualidadeSono qualidade;
	
	public Sono () {
		
	}
	
	public Sono(Date data, double horasDormidas, QualidadeSono qualidade, Usuario usuario) {
		setUsuario(usuario);
	    setData(data);
	    setHorasDormidas(horasDormidas);
	    setQualidade(qualidade);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("O usuário não pode ser nulo.");
		}
		this.usuario = usuario;
	}

	public int getIdSono() {
		return idSono;
	}
	public void setIdSono(int idSono) {
		this.idSono = idSono;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		if(data == null) {
			throw new IllegalArgumentException("A data não pode ser nulo.");
		}
		this.data = data;
	}
	public double getHorasDormidas() {
		return horasDormidas;
	}
	public void setHorasDormidas(double horasDormidas) {
		if(horasDormidas <= 0){
			throw new IllegalArgumentException("A quantidade de horas dormidas precisa ser maior do que zero.");
		}
		this.horasDormidas = horasDormidas;
	}
	public QualidadeSono getQualidade() {
		return qualidade;
	}
	public void setQualidade(QualidadeSono qualidade) {
		if(qualidade == null) {
			throw new IllegalArgumentException("A qualidade do sono não pode ser nulo.");
		}
		this.qualidade = qualidade;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sono [idSono=");
		builder.append(idSono);
		builder.append(", data=");
		builder.append(data);
		builder.append(", horasDormidas=");
		builder.append(horasDormidas);
		builder.append(", qualidade=");
		builder.append(qualidade);
		builder.append("]");
		return builder.toString();
	}

	
}
