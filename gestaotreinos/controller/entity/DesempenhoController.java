package gestaotreinos.controller.entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import gestaotreinos.enums.TipoRelatorio;
import gestaotreinos.model.dao.AlertaDAO;
import gestaotreinos.model.dao.AlimentoDAO;
import gestaotreinos.model.dao.ConexaoBD;
import gestaotreinos.model.dao.DesempenhoDAO;
import gestaotreinos.model.dao.RefeicaoDAO;
import gestaotreinos.model.dao.SonoDAO;
import gestaotreinos.model.dao.TreinoDAO;
import gestaotreinos.model.entity.Alerta;
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
            Date hoje = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(hoje);
            cal.add(Calendar.WEEK_OF_YEAR, -1);
            Date semanaPassada = cal.getTime();

            TreinoDAO tDAO = new TreinoDAO(conn);
            List<Treino> listaTreinos = tDAO.listarTreinosPorUsuario(idUsuario);
            int qtdTreinos = 0;
            if (listaTreinos != null) {
                for (Treino t : listaTreinos) {
                    Date dataTreino = t.getData();
                    if (dataTreino.after(semanaPassada) || dataTreino.equals(semanaPassada)) {
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
                    Date dataSono = s.getData();
                    if (dataSono.after(semanaPassada) || dataSono.equals(semanaPassada)) {
                        somaSono += s.getHorasDormidas();
                        diasSono++;
                    }
                }
            }
            double mediaSono;
            if (diasSono > 0) {
                mediaSono = (somaSono / diasSono);
            } else {
                mediaSono = 0;
            }

            RefeicaoDAO rDAO = new RefeicaoDAO(conn);
            AlimentoDAO aliDAO = new AlimentoDAO(conn);

            List<Refeicao> refeicoes = rDAO.listarPorUsuario(idUsuario);
            double somaCalorias = 0;
            int diasRefeicao = 0;

            if (refeicoes != null) {
                for (Refeicao r : refeicoes) {
                    Date dataRefeicao = r.getData();
                    if (dataRefeicao.after(semanaPassada) || dataRefeicao.equals(semanaPassada)) {
                        List<Alimento> alimentos = aliDAO.listaPorRefeicao(r.getIdRefeicao());
                        for (Alimento a : alimentos) {
                            somaCalorias += a.getCalorias();
                        }
                        diasRefeicao++;
                    }
                }
            }
            double mediaCalorias;
            if (diasRefeicao > 0) {
                mediaCalorias = (somaCalorias / diasRefeicao);
            } else {
                mediaCalorias = 0;
            }

            double notaT;
            if (qtdTreinos >= 5) {
                notaT = 10;
            } else if (qtdTreinos == 4) {
                notaT = 8;
            } else if (qtdTreinos == 3) {
                notaT = 6;
            } else if (qtdTreinos >= 1) {
                notaT = 4;
            } else {
                notaT = 0;
            }
            double notaS;
            if (mediaSono >= 7) {
                notaS = 10;
            } else if (mediaSono == 6) {
                notaS = 8;
            } else if (mediaSono == 5) {
                notaS = 6;
            } else if (mediaSono >= 2) {
                notaS = 4;
            } else {
                notaS = 0;
            }
            double notaF = (notaS + notaT) / 2;

            AlertaDAO alertaDAO = new AlertaDAO(conn);
            List<Alerta> listaAlertas = alertaDAO.listarPorUsuario(idUsuario);

            StringBuilder texto = new StringBuilder();
            texto.append(String.format("Data: %s\n", hoje));
            texto.append(String.format("Treinos: %d sessões\n", qtdTreinos));
            texto.append(String.format("Média Sono: %.1f h\n", mediaSono));
            texto.append(String.format("Média Calorias: %.0f kcal\n", mediaCalorias));

            if (qtdTreinos >= 3) {
                texto.append("\n Otima frequencia de treino\n");
            } else {
                texto.append("\n Tente treinar mais na semana\n");
            }
            if (mediaSono < 6.0) {
                texto.append("\n Tente dormir mais durante as noites\n");
            }

            if (listaAlertas != null) {
                texto.append("\n ALERTAS: \n");

                int numeroAlertas = Math.min(listaAlertas.size(), 4);
                for (int i = 0; i < numeroAlertas; i++) {
                    texto.append(listaAlertas.get(i).getMensagem()).append("\n");
                }
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