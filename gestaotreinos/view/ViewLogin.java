//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package gestaotreinos.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ViewLogin extends JFrame {
    private static final Logger logger = Logger.getLogger(ViewLogin.class.getName());
    private JButton btnCadastrar;
    private JButton btnEntrar;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField txtEmailUsuario;
    private JTextField txtSenhaUsuario;

    public ViewLogin() {
        this.initComponents();
    }

    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.txtEmailUsuario = new JTextField();
        this.jLabel2 = new JLabel();
        this.txtSenhaUsuario = new JTextField();
        this.btnEntrar = new JButton();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.btnCadastrar = new JButton();
        this.setDefaultCloseOperation(3);
        this.txtEmailUsuario.addActionListener(this::txtEmailUsuarioActionPerformed);
        this.jLabel2.setText("Senha");
        this.txtSenhaUsuario.addActionListener(this::txtSenhaUsuarioActionPerformed);
        this.btnEntrar.setText("Entrar");
        this.btnEntrar.addActionListener(this::btnEntrarActionPerformed);
        this.jLabel3.setText("LOGIN");
        this.jLabel4.setText("Email");
        this.btnCadastrar.setText("Cadastrar");
        this.btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(97, 97, 97).addComponent(this.jLabel1).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jLabel4).addComponent(this.jLabel2)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.txtEmailUsuario, -1, 93, 32767).addComponent(this.btnEntrar, -1, 93, 32767).addComponent(this.txtSenhaUsuario)).addContainerGap(140, 32767)).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jLabel3).addGap(172, 172, 172)).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.btnCadastrar).addGap(14, 14, 14)))));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addGap(17, 17, 17).addComponent(this.jLabel3).addGap(29, 29, 29).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.txtEmailUsuario, -2, -1, -2).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jLabel4))).addGap(40, 40, 40).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.txtSenhaUsuario, -2, -1, -2).addComponent(this.jLabel2)).addGap(41, 41, 41).addComponent(this.btnEntrar).addPreferredGap(ComponentPlacement.RELATED, 26, 32767).addComponent(this.btnCadastrar).addGap(15, 15, 15)));
        this.pack();
    }

    private void txtEmailUsuarioActionPerformed(ActionEvent evt) {
    }

    private void txtSenhaUsuarioActionPerformed(ActionEvent evt) {
    }

    private void btnEntrarActionPerformed(ActionEvent evt) {
        this.Login();
    }

    private void btnCadastrarActionPerformed(ActionEvent evt) {
        ViewCadastro oViewCadastro = new ViewCadastro();
        oViewCadastro.setVisible(true);
        this.dispose();
    }

    private void Login() {
        try {
            throw new RuntimeException("Uncompilable code - cannot find symbol\n  symbol:   method autenticacaoUsuario(gestaotreinos.model.entity.Usuario)\n  location: variable oUsuarioDAO of type gestaotreinos.model.dao.UsuarioDAO");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog((Component)null, "ViewLogin" + String.valueOf(erro));
        }
    }

    public static void main(String[] args) {
        try {
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException | ReflectiveOperationException ex) {
            logger.log(Level.SEVERE, (String)null, ex);
        }

        EventQueue.invokeLater(() -> (new ViewLogin()).setVisible(true));
    }
}
