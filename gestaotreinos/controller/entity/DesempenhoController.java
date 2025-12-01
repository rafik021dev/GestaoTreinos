/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.controller.entity;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import gestaotreinos.enums.TipoRelatorio;
import gestaotreinos.model.dao.AlimentoDAO;
import gestaotreinos.model.dao.ConexaoBD;
import gestaotreinos.model.dao.DesempenhoDAO;
import gestaotreinos.model.dao.RefeicaoDAO;
import gestaotreinos.model.dao.SonoDAO;
import gestaotreinos.model.dao.TreinoDAO;
import gestaotreinos.model.entity.Alimento;
import gestaotreinos.model.entity.Desempenho;
import gestaotreinos.model.entity.Refeicao;
import gestaotreinos.model.entity.Sono;
import gestaotreinos.model.entity.Treino;
import gestaotreinos.model.entity.Usuario;

public class DesempenhoController {

    private Connection conn;

    public DesempenhoController() {
        ConexaoBD conexaoBD = new ConexaoBD();
        this.conn = conexaoBD.connection("GestaoTreinos", "postgres", "07171826");
    }

    public Desempenho gerarRelatorioSemanal(int idUsuario) {
        try {
            LocalDate hoje = LocalDate.now();
            LocalDate semanaPassada = hoje.minusWeeks(1);

            TreinoDAO tDAO = new TreinoDAO(conn);
            List<Treino> listaTreinos = tDAO.listarTreinosPorUsuario(idUsuario);
            int qtdTreinos = 0;
            if (listaTreinos != null) {
                for (Treino t : listaTreinos) {
                    if (t.getData().isAfter(semanaPassada) || t.getData().equals(semanaPassada)) {
                        qtdTreinos++;
                    }
                }
            }

            SonoDAO DAO = new SonoDAO(conn);
            List<Sono> listaSono = DAO.listarSonoPorUsuario(idUsuario);
            double somaSono = 0;
            int diasSono = 0;
            if (listaSono != null) {
                for (Sono s : listaSono) {
                    if (s.getData().isAfter(semanaPassada) || s.getData().equals(semanaPassada)) {
                        somaSono += s.getHorasDormidas();
                        diasSono++;
                    }
                }
            }
            double mediaSono;
            if(diasSono > 0){
            mediaSono = (somaSono / diasSono);
            }else{
                mediaSono = 0;
            }

            RefeicaoDAO rDAO = new RefeicaoDAO(conn);
            AlimentoDAO aliDAO = new AlimentoDAO(conn);
          
            List<Refeicao> refeicoes = rDAO.listarPorUsuario(idUsuario);
            double somaCalorias = 0;
            int diasRefeicao = 0;
            
            if (refeicoes != null) {
                for (Refeicao r : refeicoes) {
                    if (r.getData().isAfter(semanaPassada) || r.getData().equals(semanaPassada)) {
                        List<Alimento> alimentos = aliDAO.listaPorRefeicao(r.getIdRefeicao());
                        for (Alimento a : alimentos) {
                            somaCalorias += a.getCalorias();
                        }
                        diasRefeicao++;
                    }
                }
            }
            double mediaCalorias;
            if(diasRefeicao > 0){
                mediaCalorias = (somaCalorias / diasRefeicao);
            }else{
                mediaCalorias = 0;
            }

            double notaT;
            if(qtdTreinos >= 5){
                notaT = 10;
            }else if(qtdTreinos == 4){
                notaT = 8;
            }else if(qtdTreinos == 3){
                notaT = 6;
            }else if (qtdTreinos >= 1){
                notaT = 4;
            }else{
                notaT = 0;
            }
            double notaS;
            if(mediaSono >= 7){
                notaS = 10;
            }else if(mediaSono == 6){
                notaS = 8;
            }else if(mediaSono == 5){
                notaS = 6;
            }else if (mediaSono >= 2){
                notaS = 4;
            }else{
                notaS = 0;
            }
            double notaF = (notaS + notaT)/2;
            

            StringBuilder texto = new StringBuilder();
            texto.append("Data: ").append(hoje).append("\n");
            texto.append("Treinos: ").append(qtdTreinos).append("\n");
            texto.append("Média Sono: ").append(String.format("%.1f", mediaSono)).append("h\n");
            texto.append("Média Calorias: ").append(String.format("%.0f", mediaCalorias)).append("kcal\n\n");
            
            if (qtdTreinos >= 3){
                texto.append("otima frequencia de treino\n");
            }else{
                texto.append("tente treinar mais na semana\n");      
            }     
            if (mediaSono < 6.0){
                texto.append("tente dormir mais durante as noites\n");
            }
            
            Desempenho des = new Desempenho();
            gestaotreinos.model.dao.UsuarioDAO usuarioDAO = new gestaotreinos.model.dao.UsuarioDAO(conn);
            Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
           
            if (usuario == null) {
                return null; 
            }
            des.setUsuario(usuario);
          
            des.setDataGeracao(hoje);
            des.setTipo(TipoRelatorio.SEMANAL);
            des.setMediaTreino(qtdTreinos);
            des.setMediaSono(mediaSono);
            des.setMediaCalorias(mediaCalorias);
            des.setIndiceDesempenho(notaF); 
            des.setTextoResumo(texto.toString());

            DesempenhoDAO dao = new DesempenhoDAO(conn);
            dao.inserirDesempenho(des);
            return des;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
