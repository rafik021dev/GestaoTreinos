//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package gestaotreinos.view;

import gestaotreinos.model.dao.ConexaoBD;
import gestaotreinos.model.dao.UsuarioDAO;
import gestaotreinos.model.entity.Usuario;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ViewCadastro extends JFrame {
    private static final Logger logger = Logger.getLogger(ViewCadastro.class.getName());
    private ButtonGroup buttonGroup1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JSeparator jSeparator4;
    private JSeparator jSeparator5;
    private JSeparator jSeparator6;
    private JSeparator jSeparator7;
    private JSpinner txtAltura;
    private JTextField txtEmail;
    private JRadioButton txtFeminino;
    private JSpinner txtIdade;
    private JRadioButton txtMasculino;
    private JSpinner txtMetaPeso;
    private JTextField txtNome;
    private JSpinner txtPesoAtual;
    private JPasswordField txtSenha;

    public ViewCadastro() {
        this.initComponents();
    }

    private void initComponents() {
        this.buttonGroup1 = new ButtonGroup();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel5 = new JLabel();
        this.jSeparator1 = new JSeparator();
        this.jLabel6 = new JLabel();
        this.jSeparator2 = new JSeparator();
        this.jSeparator3 = new JSeparator();
        this.jLabel7 = new JLabel();
        this.jSeparator4 = new JSeparator();
        this.txtMasculino = new JRadioButton();
        this.txtFeminino = new JRadioButton();
        this.jLabel4 = new JLabel();
        this.txtIdade = new JSpinner();
        this.jLabel8 = new JLabel();
        this.jSeparator5 = new JSeparator();
        this.jLabel9 = new JLabel();
        this.jSeparator6 = new JSeparator();
        this.jLabel10 = new JLabel();
        this.txtPesoAtual = new JSpinner();
        this.jLabel11 = new JLabel();
        this.txtMetaPeso = new JSpinner();
        this.jSeparator7 = new JSeparator();
        this.txtNome = new JTextField();
        this.txtEmail = new JTextField();
        this.txtSenha = new JPasswordField();
        this.jButton2 = new JButton();
        this.jLabel12 = new JLabel();
        this.txtAltura = new JSpinner();
        this.setDefaultCloseOperation(3);
        this.jLabel1.setText("CADASTRO");
        this.jLabel2.setText("Nome");
        this.jLabel3.setText("Email");
        this.jLabel5.setText("Senha");
        this.jLabel6.setText("Dados de Acesso");
        this.jLabel6.setToolTipText("");
        this.jLabel6.setCursor(new Cursor(0));
        this.jLabel7.setText("Sobre Você");
        this.buttonGroup1.add(this.txtMasculino);
        this.txtMasculino.setText("Masculino");
        this.txtMasculino.addActionListener(this::txtMasculinoActionPerformed);
        this.buttonGroup1.add(this.txtFeminino);
        this.txtFeminino.setText("Feminino");
        this.txtFeminino.addActionListener(this::txtFemininoActionPerformed);
        this.jLabel4.setText("Sexo:");
        this.txtIdade.setModel(new SpinnerNumberModel(18, 1, 120, 1));
        this.jLabel8.setText("Idade:");
        this.jLabel8.setToolTipText("");
        this.jLabel9.setText("Suas Metas");
        this.jLabel10.setText("Peso Atual:");
        this.txtPesoAtual.setModel(new SpinnerNumberModel((double)0.0F, (Comparable)null, (Comparable)null, (double)0.0F));
        this.jLabel11.setText("Meta de Peso:");
        this.txtMetaPeso.setModel(new SpinnerNumberModel((double)0.0F, (Comparable)null, (Comparable)null, (double)0.0F));
        this.txtMetaPeso.setCursor(new Cursor(0));
        this.txtMetaPeso.setName("");
        this.txtNome.addActionListener(this::txtNomeActionPerformed);
        this.txtSenha.addActionListener(this::txtSenhaActionPerformed);
        this.jButton2.setText("Realizar Cadastro");
        this.jButton2.addActionListener(this::jButton2ActionPerformed);
        this.jLabel12.setText("Altura");
        this.txtAltura.setModel(new SpinnerNumberModel(0, 0, (Comparable)null, 1));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jSeparator1).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(252, 252, 252).addComponent(this.jLabel1)).addGroup(layout.createSequentialGroup().addGap(39, 39, 39).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jLabel7, -1, -1, 32767).addComponent(this.jSeparator4)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.jLabel8)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.txtIdade, -2, -1, -2).addGroup(layout.createSequentialGroup().addComponent(this.txtMasculino).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.txtFeminino)))).addComponent(this.jSeparator3, -2, 493, -2).addComponent(this.jSeparator5, -2, 493, -2).addGroup(layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.jSeparator6, Alignment.LEADING).addComponent(this.jLabel9, Alignment.LEADING)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel11).addComponent(this.jLabel10)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.txtPesoAtual, -1, 96, 32767).addComponent(this.txtMetaPeso))).addGroup(layout.createSequentialGroup().addComponent(this.jLabel12).addGap(18, 18, 18).addComponent(this.txtAltura, -2, -1, -2))))).addGroup(Alignment.LEADING, layout.createSequentialGroup().addGap(38, 38, 38).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jLabel6, -1, 493, 32767).addComponent(this.jSeparator2).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel2).addComponent(this.jLabel3, -2, 35, -2).addComponent(this.jLabel5)).addGap(30, 30, 30).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.txtEmail, -1, 206, 32767).addComponent(this.txtNome).addComponent(this.txtSenha, -2, 103, -2)))))).addGroup(layout.createSequentialGroup().addGap(204, 204, 204).addComponent(this.jButton2))).addGap(0, 39, 32767)).addComponent(this.jSeparator7, Alignment.TRAILING)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1, -2, 44, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSeparator1, -2, 10, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLabel6, -2, 16, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSeparator2, -2, 10, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.txtNome, -2, -1, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.txtEmail, -2, -1, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.txtSenha, -2, -1, -2)).addGap(18, 18, 18).addComponent(this.jSeparator3, -2, 10, -2).addGap(18, 18, 18).addComponent(this.jLabel7).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSeparator4, -2, 10, -2).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.txtMasculino).addComponent(this.txtFeminino).addComponent(this.jLabel4)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.txtIdade, -2, -1, -2).addComponent(this.jLabel8)).addPreferredGap(ComponentPlacement.RELATED, 10, 32767).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel12).addComponent(this.txtAltura, -2, -1, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jSeparator5, -2, 10, -2).addGap(18, 18, 18).addComponent(this.jLabel9).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSeparator6, -2, 10, -2).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel10).addComponent(this.txtPesoAtual, -2, -1, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel11).addComponent(this.txtMetaPeso, -2, -1, -2)).addGap(18, 18, 18).addComponent(this.jSeparator7, -2, 10, -2).addGap(18, 18, 18).addComponent(this.jButton2).addGap(22, 22, 22)));
        this.pack();
    }

    private void txtFemininoActionPerformed(ActionEvent evt) {
    }

    private void txtMasculinoActionPerformed(ActionEvent evt) {
    }

    private void txtNomeActionPerformed(ActionEvent evt) {
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        this.Cadastrar();
    }

    private void Cadastrar() {
        String nome = this.txtNome.getText().trim();
        String email = this.txtEmail.getText().trim();
        String senha = (new String(this.txtSenha.getPassword())).trim();
        char sexo = (char)(this.txtFeminino.isSelected() ? 70 : (this.txtMasculino.isSelected() ? 77 : 32));
        int idade = Integer.parseInt(this.txtIdade.getValue().toString());
        double altura = Double.parseDouble(this.txtAltura.getValue().toString().replace(",", ".")) / (double)100.0F;
        double peso = Double.parseDouble(this.txtPesoAtual.getValue().toString().replace(",", "."));
        Double metaPeso = null;
        String metaStr = this.txtMetaPeso.getValue().toString();
        if (!metaStr.isEmpty()) {
            try {
                double valorMeta = Double.parseDouble(metaStr.replace(",", "."));
                if (valorMeta > (double)0.0F) {
                    metaPeso = valorMeta;
                }
            } catch (NumberFormatException var15) {
                metaPeso = null;
            }
        }

        Usuario usuario = new Usuario(nome, sexo, idade, peso, altura, metaPeso);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        UsuarioDAO usuarioDAO = new UsuarioDAO(ConexaoBD.conectaBD());
        boolean sucesso = usuarioDAO.inserir(usuario);
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário.");
        }
        ViewPrincipal oViewPrincipal = new ViewPrincipal(usuario);
                oViewPrincipal.setVisible(true);
                dispose();
    }

    private void txtSenhaActionPerformed(ActionEvent evt) {
    }

    public static void main(String[] args) {
       /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */

        EventQueue.invokeLater(() -> (new ViewCadastro()).setVisible(true));
    }
}
