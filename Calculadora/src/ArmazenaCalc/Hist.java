package ArmazenaCalc;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Hist extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hist frame = new Hist();
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
	static final String CAMINHO = "/home/vitor/eclipse-workspace/Calculadora/db.txt";
	public Hist() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 43, 416, 208);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		JLabel lblHistrico = new JLabel("Histórico");
		lblHistrico.setBounds(12, 0, 70, 15);
		contentPane.add(lblHistrico);
		
	    try {
	        FileReader arq = new FileReader(CAMINHO);
	        BufferedReader lerArq = new BufferedReader(arq);

	        String linha = lerArq.readLine(); 
	        while (linha != null) {
	          
	          textArea.setText(textArea.getText()+linha+"\n");
	          linha = lerArq.readLine(); 
	        }

	        arq.close();
	      } catch (IOException e) {
	          System.err.printf("Erro na abertura do arquivo: %s.\n",
	            e.getMessage());
	      }
	}
}
