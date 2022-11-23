package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.Producto;
import Modelo.Usuario;
import dao.daoProducto;
import dao.douUsuario;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vProducto extends JFrame {
	daoProducto dou=new daoProducto();
	int fila= -1;
	DefaultTableModel modelo=new DefaultTableModel();
	ArrayList<Producto>lista=new ArrayList<Producto>();
	Producto producto=new Producto();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vProducto frame = new vProducto();
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
	public vProducto() {
		setTitle("CRUD USUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 499);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblID = new JLabel("1");
		lblID.setBounds(120, 11, 46, 14);
		contentPane.add(lblID);
		
		JLabel lblNewLabel_1 = new JLabel("DESCRIPCION");
		lblNewLabel_1.setBounds(10, 39, 79, 14);
		contentPane.add(lblNewLabel_1);
		
		JTextField txtDescripcion = new JTextField();
		txtDescripcion.setBounds(120, 36, 86, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10); 
		
		JLabel lblPrecio = new JLabel("PRECIO ");
		lblPrecio.setBounds(10, 70, 79, 14);
		contentPane.add(lblPrecio);
		
		JTextField txtPrecio = new JTextField();
		txtPrecio.setBounds(120, 67, 86, 20);
		txtPrecio.setColumns(10);
		contentPane.add(txtPrecio);
		
		JTextField txtCantidad = new JTextField();
		txtCantidad.setBounds(120, 98, 86, 20);
		txtCantidad.setColumns(10);
		contentPane.add(txtCantidad);
		
		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setBounds(10, 101, 67, 14);
		contentPane.add(lblCantidad);
		
		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.setBounds(0, 188, 89, 23);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Producto producto=new Producto();
					producto.setDescripcion(txtDescripcion.getText());
					producto.setPrecio(txtPrecio.getText());
					producto.setCantidad(txtCantidad.getText());
					if(dou.insertarProducto(producto)) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRERTAMENTE");
					}else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				}catch(Exception ex ) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		contentPane.add(btnAgregar);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(110, 188, 89, 23);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtDescripcion.getText().equals("")||txtPrecio.getText().equals("")||txtCantidad.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"campos vacios");
						return;
					}
		
					producto.setDescripcion(txtDescripcion.getText());
					producto.setPrecio(txtPrecio.getText());
					producto.setCantidad(txtCantidad.getText());
					if(dao.editarProducto(producto)) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "SE EDITO CORRERTAMENTE");
					}else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
					
				}
				
			}
		});
		contentPane.add(btnEditar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(213, 188, 89, 23);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion = JOptionPane.showConfirmDialog(null, "ESTAS SEGURO DE ELIMINAR ESTE USUARIO?");
					if(opcion ==0) {
						
					}
				if(dao.eliminarProducto(lista.get(fila).getId())) {
					actualizarTabla();
					JOptionPane.showMessageDialog(null, "ELIMNAR CORRECTAMENTE!!");
				}else {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}catch(Exception ex ) {
				JOptionPane.showMessageDialog(null, "ERROR");
			}
			}
		});
		contentPane.add(btnEliminar);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.setBounds(335, 188, 89, 23);
		contentPane.add(btnBorrar);

	JLabel lblProvedor = new JLabel("PROVEDOR");
	lblProvedor.setBounds(10, 126, 67, 14);
	contentPane.add(lblProvedor);
	
	JTextField txtProvedor = new JTextField();
	txtProvedor.setBounds(120, 123, 86, 20);
	contentPane.add(txtProvedor);
	txtProvedor.setColumns(10);
	
	JPanel panel = new JPanel();
	panel.setBounds(10, 222, 414, 227);
	contentPane.add(panel);
	panel.setLayout(null);
	
	JTable tblProducto = new JTable();
	tblProducto.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
		}
	));
	modelo.addColumn("ID");
	modelo.addColumn("DESCRIPCION");
	modelo.addColumn("PRECIO");
	modelo.addColumn("CANTIDAD");
	modelo.addColumn("PROVEDOR");
	tblProducto.setModel(modelo);
	tblProducto.setBounds(0, 0, 414, 227);
	panel.add(tblProducto);
	actualizarTabla();
	
	}
	public void actualizarTabla () {
		while(modelo.getRowCount()>0) {
			modelo.removeRow(0);
		}
		lista=dao.fetchProductos();
		for(Producto u: lista) {
			Object o[]=new Object[4];
			o[0]=u.getId();
			o[1]=u.getDescripcion();
			o[2]=u.getPrecio();
			o[3]=u.getCantidad();
			o[4]=u.getProvedor();
			modelo.addRow(o);
		}
	}
	}

