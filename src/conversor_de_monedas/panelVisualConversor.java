package conversor_de_monedas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class panelVisualConversor {

	private JFrame frame;
	private JButton btn;
	private JComboBox cmb;
	private JLabel lbl;
	private JTextField txt;

	private double dolar = 0.00025;
	private double euro = 0.00022;
	private double libra = 0.00019;
	private double valorInput = 0.00;
	private JLabel lblNewLabel_3;
	
	
	
	    public enum Moneda{
	    Pesos__A__Dolar,
		Pesos__A___Euro,
		Pesos__A__Libra,
		Dolar__A__Pesos,
		Euro___A__Pesos,
		Libra__A__Pesos
	}
	 
	 
	 
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					panelVisualConversor window = new panelVisualConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public panelVisualConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(2, 157, 255));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 531, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setForeground(new Color(218, 165, 32));
		txt.setHorizontalAlignment(SwingConstants.LEFT);
		txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					convertir();
				}
			}
		});
		txt.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt.setBounds(255, 96, 219, 28);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setForeground(new Color(0, 0, 0));
		cmb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmb.setBackground(new Color(194, 211, 247));
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(255, 135, 219, 28);
		frame.getContentPane().add(cmb);
		//evento del boton
		btn = new JButton("Convertir");
		btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn.setForeground(new Color(0, 128, 255));
		btn.setBackground(new Color(230, 212, 137));
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				convertir();
			}
		});
		btn.setBounds(255, 187, 104, 22);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("0.000");
		lbl.setForeground(new Color(255, 255, 255));
		lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBounds(39, 182, 190, 33);
		frame.getContentPane().add(lbl);
		
		JLabel lblNewLabel = new JLabel("Digita el numero a convertir");
		lblNewLabel.setForeground(new Color(194, 211, 247));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(39, 98, 190, 22);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Convertidor de Monedas");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel_1.setBounds(96, 11, 325, 51);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cambia la Convercion");
		lblNewLabel_2.setForeground(new Color(194, 211, 247));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(39, 141, 206, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Solo Convierte a pesos Colombianos");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_3.setBounds(326, 247, 189, 14);
		frame.getContentPane().add(lblNewLabel_3);
	}
	public void convertir(){
		if(Validar(txt.getText())) {
			Moneda moneda = (Moneda)cmb.getSelectedItem();
			
			switch (moneda) {
		case Pesos__A__Dolar:
			pesos_A_moneda(dolar);
			break;
		case Pesos__A___Euro:
			pesos_A_moneda(euro);
			break;
		case Pesos__A__Libra:
			pesos_A_moneda(libra);
			break;
		case Dolar__A__Pesos:
			moneda_A_pesos(dolar);
			break;
		case Euro___A__Pesos:
			moneda_A_pesos(euro);
			break;
		case Libra__A__Pesos:
			moneda_A_pesos(libra);
			break;
			default:
				throw new IllegalArgumentException("Unexpected value:" + moneda);
		}
		}
		
		
	}
	public void pesos_A_moneda(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(redondeo(res));
	}
	public void moneda_A_pesos(double moneda) {
		double res = valorInput / moneda;
		try {
			lbl.setText(redondeo(res));
		}catch(ArithmeticException ex) {
			System.out.println("el numero deve ser superior a 0"+ ex);
		}
		
	}
	public String redondeo(double valor) {
		DecimalFormat Df = new DecimalFormat("0.00");
		Df.setRoundingMode(RoundingMode.HALF_UP);
		return Df.format(valor);
	}
	
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if(x>0);
			valorInput = x;
			return true;
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null,"salamente numero !!");
			txt.setText(" ");
			return false;
		}
	}
}
