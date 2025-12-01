/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.entity;

import java.time.LocalDate;
import gestaotreinos.enums.TipoRelatorio;
import java.util.Date;

public class Desempenho {
    private int idDesempenho;
    private double mediaSono;
    private double mediaCalorias;
    private double mediaTreino;
    private double indiceDesempenho;
    private Usuario usuario;
    private Date dataGeracao;
    private TipoRelatorio tipo;
    private String textoResumo;

    public Desempenho() {
        
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        if(usuario == null) {
            throw new IllegalArgumentException("O usuário não deve ser nulo.");
        }
        this.usuario = usuario;
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
    public Date getDataGeracao() {
        return dataGeracao;
    }
    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
    public TipoRelatorio getTipo() {
        return tipo;
    }
    public void setTipo(TipoRelatorio tipo) {
        this.tipo = tipo;
    }
    public String getTextoResumo() {
        return textoResumo;
    }
    public void setTextoResumo(String textoResumo) {
        this.textoResumo = textoResumo;
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
        builder.append(", data=");
        builder.append(dataGeracao);
        builder.append("]");
        return builder.toString();
    }
}