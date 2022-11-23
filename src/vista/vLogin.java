package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Usuario;
import dao.douUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class vLogin extends JFrame {

	private JPanel contentPane;
	douUsuario dao=new douUsuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vLogin frame = new vLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public vLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(10, 11, 103, 14);
		contentPane.add(lblNewLabel);
		
		JTextField txtUser = new JTextField();
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtUser.getText().length()>10) {
					e.consume();
					
				}
			}
		});
		txtUser.setBounds(10, 51, 86, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(10, 114, 116, 14);
		contentPane.add(lblNewLabel_1);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtPassword.getText().length()>10) {
					e.consume();
				}
			}
		});
		txtPassword.setBounds(10, 165, 111, 20);
		contentPane.add(txtPassword);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user=new Usuario();
				user.setUser(txtUser.getText());
				user.setPassword(String.valueOf(txtPassword.getPassword()));
				if(dao.loginUsuario(user)) {
					JOptionPane.showMessageDialog(null, "BIENVENIDO");
					vCargar cargando=new vCargar();
					setVisible(false);
					cargando.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecta");
					 
					
				}
			}
		});
		btnEntrar.setBounds(72, 227, 89, 23);
		contentPane.add(btnEntrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "BAY BAY");
				System.exit(0);
			}
		});
		btnCancelar.setBounds(252, 227, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\ALUMNO\\Downloads\\images (2).jpg"));
		lblNewLabel_2.setBounds(165, 0, 225, 204);
		contentPane.add(lblNewLabel_2);
	}

}
