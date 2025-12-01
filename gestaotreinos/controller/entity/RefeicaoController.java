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
import java.util.List;
import gestaotreinos.enums.TipoRefeicao;
import gestaotreinos.model.dao.AlimentoDAO;
import gestaotreinos.model.dao.RefeicaoDAO;
import gestaotreinos.model.dao.ConexaoBD;
import gestaotreinos.model.dao.RefeicaoDAO;
import gestaotreinos.model.entity.Alimento;
import gestaotreinos.model.entity.Refeicao;
import gestaotreinos.model.entity.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RefeicaoController {

    private Connection conn;

    public RefeicaoController() {
        ConexaoBD conexaoBD = new ConexaoBD();
        this.conn = conexaoBD.connection("GestaoTreinos", "postgres", "07171826");
    }

    public String salvarRefeicao(String dataTexto, String tipoTexto, int idUsuario, List<Alimento> listaAlimentos)throws ParseException {
        try {
            
            SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formatar.parse(dataTexto);
      
            TipoRefeicao tipo = TipoRefeicao.valueOf(tipoTexto.toUpperCase());

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);

            Refeicao refeicao = new Refeicao();
            refeicao.setData(data);
            refeicao.setTipo(tipo);
            refeicao.setUsuario(usuario);            
            refeicao.setAlimentos(listaAlimentos);

            List<Alimento> alimentosValidos = new ArrayList<>();
            
            if (listaAlimentos != null) {
                for (Alimento alimento : listaAlimentos) {                  
                    if (alimento.getNomeAlimento() != null) {
                        if (alimento.getCalorias() > 0 || alimento.getQuantidade() > 0) {
                            alimentosValidos.add(alimento);
                        }
                    }
                }
            }
            refeicao.setAlimentos(alimentosValidos);
            
            if (refeicao.getAlimentos().isEmpty()) {
                return "erro: adicione alimentos validos";
            }
                RefeicaoDAO refeicaoDAO = new RefeicaoDAO(conn);
                refeicaoDAO.inserir(refeicao);
                
                int ultimaRefeicao = refeicaoDAO.buscarUltimoId(idUsuario);
                
                AlimentoDAO alimentoDAO = new AlimentoDAO(conn);
                
                for(Alimento alimento: refeicao.getAlimentos()){
                    alimentoDAO.inserirAlimento(alimento,ultimaRefeicao);
                }
                 
                return "refeicao registrada";
        } catch (DateTimeParseException e) {
            return "erro: data invalida.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    public List<Refeicao> listarHistorico(int idUsuario) {
        try {
            RefeicaoDAO rDao = new RefeicaoDAO(conn);
            List<Refeicao> lista = rDao.listarPorUsuario(idUsuario);
            
            if (lista != null) {
                AlimentoDAO aDao = new AlimentoDAO(conn);
                for (Refeicao r : lista) {
                    List<Alimento> alimentos = aDao.listaPorRefeicao(r.getIdRefeicao());
                    r.setAlimentos(alimentos);
                }
            }
            return lista;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String excluirRefeicao(int idRefeicao) {
        RefeicaoDAO dao = new RefeicaoDAO(conn);
        if (dao.deletarRefeicao(idRefeicao)) {
            return "Refeiçao excluída.";
        } else {
            return "erro ao excluir refeicao.";
        }
    }
}
