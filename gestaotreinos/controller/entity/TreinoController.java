/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.controller.entity;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import gestaotreinos.enums.TipoTreino;
import gestaotreinos.model.dao.ConexaoBD;
import gestaotreinos.model.dao.ExercicioDAO;
import gestaotreinos.model.dao.TreinoDAO;
import gestaotreinos.model.entity.Exercicio;
import gestaotreinos.model.entity.Treino;
import gestaotreinos.model.entity.Usuario;
import java.util.Date;

public class TreinoController {

    private Connection conn;

    public TreinoController() {
       
        ConexaoBD conexaoBD = new ConexaoBD();
        this.conn = conexaoBD.connection("GestaoTreinos", "postgres", "07171826");
    }
    /*
    * metodo que gera exercicios predefinidos para o usuario cadastrar
    */
    public List<Exercicio> gerarExercicios(String tipoTexto) {
        System.out.println("gerarExercicios recebeu: " + tipoTexto);
   
         String textoLimpo = tipoTexto.trim().toUpperCase();
    
        try {
            TipoTreino tipo = TipoTreino.valueOf(textoLimpo);
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
                    lista.add(criarExercicio("Desenvolvimento"));
                    lista.add(criarExercicio("Elevação Lateral"));
                    lista.add(criarExercicio("Elevação Frontal"));
                    break;
                default:
                    break;
            }
            System.out.println("Controller: Tamanho da lista gerada: " + lista.size());
            return lista;
           
        } catch (IllegalArgumentException e) {
            System.out.println("erro: nao existe o Enum ");   
            e.printStackTrace();        
            return null;
        }
    }
    private Exercicio criarExercicio(String nome) {
        Exercicio ex = new Exercicio();
        ex.setNome(nome);

        return ex;
    }

    public String salvarTreino(String dataTexto, String tipoTexto, int idUsuario, List<Exercicio> listaExercicios) {
        try {
            java.text.SimpleDateFormat formatar = new java.text.SimpleDateFormat("dd/MM/yyyy");
            Date data = formatar.parse(dataTexto);

            TipoTreino tipo = TipoTreino.valueOf(tipoTexto.toUpperCase());

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);

            Treino treino = new Treino();
            treino.setData(data);
            treino.setTipo(tipo);
            treino.setUsuario(usuario);
            treino.setExercicios(listaExercicios);

            List<Exercicio> exerciciosValidos = new ArrayList<>();
            for (Exercicio ex : treino.getExercicios()) {
                if (ex.getCarga() > 0 || ex.getRepeticoes() > 0) {
                    exerciciosValidos.add(ex);
                }
            }
            treino.setExercicios(exerciciosValidos);

            if (treino.getExercicios().isEmpty()) {
                return "erro: precisa preencher pelo menos um exercicio.";
            }

            TreinoDAO treinoDAO = new TreinoDAO(conn);
            List<Treino> listaTreinos = treinoDAO.listarTreinosPorUsuario(idUsuario);
            if (listaTreinos != null) {
                for (Treino t : listaTreinos) {
                    if (t.getData().equals(treino.getData()) && t.getTipo() == treino.getTipo()) {
                        return "erro: ja tem um treino de " + treino.getTipo() + " nessa data.";
                    }
                }
            }
            if (treinoDAO.inserirTreino(treino)) {
                int ultimoTreino = treinoDAO.buscarUltimoId(idUsuario);

                ExercicioDAO exercicioDAO = new ExercicioDAO(conn);
                for (Exercicio e : treino.getExercicios()) {
                    exercicioDAO.inserirExercicio(e, ultimoTreino);
                }
                return "Treino registrado";
            } else {
                return "erro: falha ao salvar no banco de dados.";
            }

        } catch (java.text.ParseException e) {
            return "erro: data invalida";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    
    public List<Treino> listarHistorico(int idUsuario) {
        TreinoDAO dao = new TreinoDAO(conn);
        try {
            return dao.listarTreinosPorUsuario(idUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String excluirTreino(int idTreino) {
        TreinoDAO dao = new TreinoDAO(conn);
        if (dao.deletarTreino(idTreino)) {
            return "treino excluido.";
        } else {
            return "erro ao excluir treino.";
        }
    }
}
