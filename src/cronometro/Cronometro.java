package cronometro;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Color;

public class Cronometro extends javax.swing.JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JLabel lblTiempo;
	private JButton btnIniciar;
	private JButton btnPausar;
	private JButton btnReanudar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cronometro frame = new Cronometro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//CREAMOS VARIABLES
	private Timer tiempo;
	private int centesimas_segundos = 0;
	private int segundos = 0;
	private int minutos = 0;
	private int horas = 0;
	
	private ActionListener acciones = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			centesimas_segundos++;
			
			//CONDICIONAL
			if(centesimas_segundos == 100){
				segundos++;
				centesimas_segundos = 0;
			}
			if(segundos == 60){
				minutos++;
				segundos = 0;
			}
			if(minutos == 60){
				horas++;
				minutos = 0;
			}
			if(horas == 24){
				horas = 0;
			}
			
			actualizarEtiquetaTiempo();
			
		}
	};
	
	
	private void actualizarEtiquetaTiempo() {
		String texto = (horas <= 9 ? "0":"")+horas+":"+(minutos <= 9 ? "0":"")+minutos+":"+(segundos <= 9 ? "0":"")+segundos+":"+(centesimas_segundos <= 9 ? "0":"")+centesimas_segundos;
		lblTiempo.setText(texto);
		
	}
	
	//CONSTRUCTOR CRONOMETRO
	public Cronometro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cronometro.class.getResource("/icon/Clock.gif")));
		
		this.setLocationRelativeTo(null);
		this.setTitle("Cronometro");
		tiempo = new Timer(10, acciones);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 246);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("Cronometro");
		lblTitulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 48));
		lblTitulo.setBounds(220, 11, 297, 57);
		contentPane.add(lblTitulo);
		
		lblTiempo = new JLabel("00:00:00:00");
		lblTiempo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30));
		lblTiempo.setBounds(36, 111, 181, 36);
		contentPane.add(lblTiempo);
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnIniciar.addActionListener(this);
		btnIniciar.setBounds(279, 111, 133, 36);
		contentPane.add(btnIniciar);
		
		btnPausar = new JButton("Pausar");
		btnPausar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnPausar.addActionListener(this);
		btnPausar.setBounds(422, 111, 133, 36);
		contentPane.add(btnPausar);
		
		btnReanudar = new JButton("Reanudar");
		btnReanudar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnReanudar.addActionListener(this);
		btnReanudar.setBounds(565, 111, 133, 36);
		contentPane.add(btnReanudar);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReanudar) {
			actionPerformedBtnReanudarJButton(e);
		}
		if (e.getSource() == btnPausar) {
			actionPerformedBtnPausarJButton(e);
		}
		if (e.getSource() == btnIniciar) {
			actionPerformedBtnIniciarJButton(e);
		}
	}
	
	protected void actionPerformedBtnIniciarJButton(ActionEvent e) {
		
		tiempo.start();
		btnIniciar.setEnabled(false);
		btnIniciar.setText("Reanudar");
		btnPausar.setEnabled(true);
		btnReanudar.setEnabled(true);
	
	}
	
	protected void actionPerformedBtnPausarJButton(ActionEvent e) {
		
		tiempo.stop();
		btnIniciar.setEnabled(true);
		
	}
	
	protected void actionPerformedBtnReanudarJButton(ActionEvent e) {
		
		if(tiempo.isRunning()) {
			tiempo.stop();
		}
		
		centesimas_segundos = 0;
		segundos = 0;
		minutos = 0;
		horas = 0;
		
		actualizarEtiquetaTiempo();
		btnIniciar.setText("Iniciar");
		btnIniciar.setEnabled(true);
		btnPausar.setEnabled(false);
		btnReanudar.setEnabled(false);
		
	}
}
