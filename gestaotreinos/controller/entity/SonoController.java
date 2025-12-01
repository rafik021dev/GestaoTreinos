package gestaotreinos.controller.entity;

import gestaotreinos.model.entity.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import gestaotreinos.enums.QualidadeSono;
import gestaotreinos.enums.TipoAlerta;
import gestaotreinos.model.dao.AlertaDAO;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import gestaotreinos.model.entity.Sono;
import gestaotreinos.model.dao.ConexaoBD;
import gestaotreinos.model.dao.SonoDAO;
import gestaotreinos.model.entity.Alerta;

public class SonoController {

    private Connection conn;

    public SonoController() {
        ConexaoBD conexaoBD = new ConexaoBD();
        this.conn = conexaoBD.connection("GestaoTreinos", "postgres", "07171826");
    }

    public String salvarSono(String dataTexto, String horasTexto, String qualidadeTexto, int idUsuario) {
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
                return "Erro: valor nao pode ser maior que 24";
            }

           
            SonoDAO dao = new SonoDAO(conn);
            if (!dao.inserirSono(sono)) {
                return "Erro: falha ao salvar no banco";
            }

            
            if (sono.getHorasDormidas() < 6.0) {
                List<Sono> listaSono = dao.listarSonoPorUsuario(idUsuario);

                if (listaSono != null && listaSono.size() >= 3) {
                    
                    Sono ontem = listaSono.get(1);
                    Sono anteontem = listaSono.get(2);

                    
                    if (ontem.getHorasDormidas() < 6.0 && anteontem.getHorasDormidas() < 6.0) {
                        
                        AlertaDAO alertaDAO = new AlertaDAO(conn);
                        
                        
                        if (!alertaDAO.existeAlerta(idUsuario, TipoAlerta.SONO)) {
                            Alerta alerta = new Alerta();
                            alerta.setData(LocalDate.now());
                            alerta.setMensagem("CUIDADO: voce dormiu menos de 6h nos ultimos 3 dias");
                            alerta.setTipo(TipoAlerta.SONO);
                            alerta.setUsuario(usuario);

                            alertaDAO.inserirAlerta(alerta);
                            System.out.println("novo alerta gerado.");
                        } else {
                            System.out.println("Alerta já existe");
                        }
                    }
                }
            }

            return "Registro de sono realizado";

        } catch (DateTimeParseException e) {
            return "erro: data invalida";
        } catch (NumberFormatException e) {
            return "erro: numero invalido";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}