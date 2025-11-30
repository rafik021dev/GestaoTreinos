/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.controller.entity;

import gestaotreinos.model.entity.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import gestaotreinos.enums.QualidadeSono;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import gestaotreinos.model.entity.Sono;
import gestaotreinos.model.dao.ConexaoBD;
import gestaotreinos.model.dao.SonoDAO;



public class SonoController {

    private Connection conn;

    public SonoController() {
        ConexaoBD conexaoBD = new ConexaoBD();
        this.conn = conexaoBD.connection("GestaoTreinos", "postgres", "07171826");
    }

    public String salvarSono(String dataTexto, String horasTexto, String qualidadeTexto, int idUsuario){
        try {
          
            DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataTexto, formatar);
  
            double horas = Double.parseDouble(horasTexto.replace(",", "."));

            QualidadeSono qualidade = QualidadeSono.valueOf(qualidadeTexto.toUpperCase());

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);

            Sono sono = new Sono();
            sono.setData(data);
            sono.setHorasDormidas(horas);
            sono.setQualidade(qualidade);
            sono.setUsuario(usuario);


            if (sono.getHorasDormidas() <= 0) {
                return "Erro: As horas dormidas devem ser maior que zero.";
            }
            if (sono.getHorasDormidas() > 24) {
                return "erro: valor nao pode ser maior que 24";
            }

            SonoDAO dao = new SonoDAO(conn);

            String alerta = "";
            if (sono.getHorasDormidas() < 6.0) {
                List<Sono> historico = dao.listarSonoPorUsuario(idUsuario);
                
                if (historico != null && historico.size() >= 2){
                    Sono ultimo = historico.get(0);
                    Sono penultimo = historico.get(1);
                    
                    if (ultimo.getHorasDormidas() < 6.0 && penultimo.getHorasDormidas() < 6.0) {
                        alerta = "CUIDADO: voce dormiu menos de 6h nos ultimos 3 dias";
                    }
                }
            }
            
            if (dao.inserirSono(sono)) {
                return "Registro de sono realizado";
            } else {
                return "erro: falha ao salvar no banco.";
            }

        } catch (DateTimeParseException e) {
            return "erro: data invalida";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}