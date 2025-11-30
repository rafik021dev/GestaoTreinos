/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import gestaotreinos.enums.TipoTreino;
import gestaotreinos.model.dao.ExercicioDAO;
import gestaotreinos.model.dao.TreinoDAO;
import gestaotreinos.model.entity.Exercicio;
import gestaotreinos.model.entity.Treino;

public class TreinoBO {

    private Connection conn;

    public TreinoBO(Connection conn) {
        this.conn = conn;
    }
    /*
     * metodo para criar exercicios vazios para preencher na view
     */
    private Exercicio criarExercicio(String nome) {
        Exercicio ex = new Exercicio();
        ex.setNome(nome);
        ex.setCarga(0);
        ex.setSeries(0);
        ex.setRepeticoes(0);
        return ex;
    }
/*
 * verifica o tipo de treino selecionado na view e baseado no tipo cria novos exercicios vazios usando o metodo criarExercicio
 */
    public List<Exercicio> gerarExercicio(TipoTreino tipo) {
        List<Exercicio> lista = new ArrayList<>();

        switch (tipo) {
            case PEITO:
                lista.add(criarExercicio("Supino Reto"));
                lista.add(criarExercicio("Supino Inclinado"));
                lista.add(criarExercicio("Crucifixo"));
                break;

            case COSTAS:
                lista.add(criarExercicio("Puxada Alta"));
                lista.add(criarExercicio("Remada Curvada"));
                lista.add(criarExercicio("Levantamento Terra"));
                break;

            case PERNA:
                lista.add(criarExercicio("Agachamento Livre"));
                lista.add(criarExercicio("Leg Press"));
                lista.add(criarExercicio("Cadeira Extensora"));
                break;

            case BRACO:
                lista.add(criarExercicio("Rosca Direta"));
                lista.add(criarExercicio("Rosca Martelo"));
                lista.add(criarExercicio("Tríceps Testa"));
                break;

            case OMBRO:
                lista.add(criarExercicio("Desenvolvimento "));
                lista.add(criarExercicio("Elevação Lateral"));
                lista.add(criarExercicio("Elevação Frontal"));
                break;         
                
            default:            
                break;
        }

        return lista;
    }
    /*
     * cria uma lista apenas com exercicios que foram preenchidos na view(valores de carga ou repetiçao alterados)
     */
    public boolean registrarTreino(Treino treino) throws SQLException {
        
        List<Exercicio> exerciciosValidos = new ArrayList<>();
        
        for (Exercicio exercicio : treino.getExercicios()) {

            if (exercicio.getCarga() > 0 || exercicio.getRepeticoes() > 0) {
                exerciciosValidos.add(exercicio);
            }
        }
        /*
         * adiciona os exercicios validos ao objeto treino
         */
        treino.setExercicios(exerciciosValidos);

        if (treino.getExercicios().isEmpty()) {
            throw new IllegalArgumentException("voce precisa inserir pelo menos um exercicio.");
        }
        
        TreinoDAO treinoDAO = new TreinoDAO(conn);    
        treinoDAO.inserirTreino(treino);       
       
        int idTreino = treinoDAO.buscarUltimoId(treino.getUsuario().getIdUsuario());
            
        ExercicioDAO exercicioDAO = new ExercicioDAO(conn);
        
        for (Exercicio exercicio : treino.getExercicios()) {
            exercicioDAO.inserirExercicio(exercicio, idTreino);
        }
        return true;
    }
}
