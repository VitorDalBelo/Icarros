package ArmazenaCalc;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Calc extends JFrame {

	private JPanel contentPane;
	private JTextField textResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//System.out.println("Working Directory = " + System.getProperty("user.dir"));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calc frame = new Calc();
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
	static Double valor1=0.,valor2;
	static String sinal="";
    static String operacao="";
	static boolean showResult = false;
	static ArrayList<String> operacoes = new ArrayList<String>();
	static final String CAMINHO = "/home/vitor/eclipse-workspace/Calculadora/db.txt";
	public static void onClickBotaoNumerico(String valor, JTextField textResultado) {
		if(showResult) {
			textResultado.setText("");
			showResult=false;
		}
		if(textResultado.getText().equals("0")) {
			textResultado.setText("");
			String dado = textResultado.getText()+valor;
			textResultado.setText(dado);	
			operacao+=valor;
		}
		else {
			String dado = textResultado.getText()+valor;
			textResultado.setText(dado);	
			operacao+=valor;
		}
	}
	public static void onClickOperacao(String valor, JTextField textResultado) {
		String conteudo = textResultado.getText();
		if(!conteudo.isBlank()) {
			valor1=Double.parseDouble(conteudo);
			textResultado.setText("");	
		}
		sinal=valor;
		operacao+=valor;
	}
	
	public Calc() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblCalculadoraPadro = new JLabel("Calculadora padrão");
		lblCalculadoraPadro.setBounds(30, 0, 174, 30);
		contentPane.add(lblCalculadoraPadro);
		
		textResultado = new JTextField();
		textResultado.setBounds(51, 31, 338, 44);
		textResultado.setEditable(false);
		contentPane.add(textResultado);
		textResultado.setColumns(10);
		
		JButton button7 = new JButton("7");
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickBotaoNumerico("7",textResultado);
			}
		});
		button7.setBounds(20, 87, 50, 50);
		contentPane.add(button7);
		
		JButton button = new JButton("8");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickBotaoNumerico("8",textResultado);
			}
		});
		button.setBounds(74, 87, 50, 50);
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickBotaoNumerico("9",textResultado);
			}
		});
		btnNewButton.setBounds(128, 87, 50, 50);
		contentPane.add(btnNewButton);
		
		JButton button_1 = new JButton("4");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickBotaoNumerico("4",textResultado);
			}
		});
		button_1.setBounds(20, 151, 50, 50);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("5");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onClickBotaoNumerico("5",textResultado);
			}
		});
		button_2.setBounds(74, 151, 50, 50);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("6");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickBotaoNumerico("6",textResultado);
			}
		});
		button_3.setBounds(128, 151, 50, 50);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("1");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickBotaoNumerico("1",textResultado);
				
			}
		});
		button_4.setBounds(20, 212, 50, 50);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("2");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickBotaoNumerico("2",textResultado);
				
			}
		});
		button_5.setBounds(74, 212, 50, 50);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("3");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickBotaoNumerico("3",textResultado);
			}
		});
		button_6.setBounds(128, 212, 50, 50);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("0");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(!textResultado.getText().equals("0")) {
					String dado = textResultado.getText()+"0";
					if(!textResultado.getText().isBlank())operacao+=dado;
					textResultado.setText(dado);	
				}
			}
		});
		button_7.setBounds(20, 270, 107, 25);
		contentPane.add(button_7);
		

		
		JButton button_8 = new JButton("+");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickOperacao("+",textResultado);				
			}
		});
		button_8.setBounds(328, 87, 50, 50);
		contentPane.add(button_8);
		
		JButton button_9 = new JButton("=");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!showResult)valor2=Double.parseDouble(textResultado.getText());
				} catch (Exception e2) {
					return;
				}	
				String result = null;
				switch (sinal) {

				case "+":

					result=String.valueOf(valor1+valor2);
					textResultado.setText(result);
					if(operacao.contains("+"))operacao+=" = "+result;
					else operacao+="+"+String.valueOf(valor2)+" = "+result;
					showResult=true;
//					System.out.println(operacao);
					

				break;

				case "-":

					result=String.valueOf(valor1-valor2);
					textResultado.setText(result);
					if(operacao.contains("-"))operacao+=" = "+String.valueOf(result);
					else operacao+="-"+String.valueOf(valor2)+" = "+result;
					showResult=true;

				break;

				case "*":

					result=String.valueOf(valor1*valor2);
					textResultado.setText(result);
					if(operacao.contains("*"))operacao+=" = "+result;
					else operacao+="*"+String.valueOf(valor2)+" = "+result;
					showResult=true;
				break;
				case "/":
					result=String.valueOf(valor1/valor2);
					textResultado.setText(result);
					if(operacao.contains("/"))operacao+=" = "+result;
					else operacao+="/"+String.valueOf(valor2)+" = "+result;
					showResult=true;
				break;
				default:
					textResultado.setText(textResultado.getText());
					operacao="";
					valor2=null;
				}
				


			System.out.println("Valor1: "+String.valueOf(valor1)+" Valor2: "+String.valueOf(valor2)+" Sinal: "+sinal+" Operacao: "+operacao);	
			if(result!=null) {
				FileWriter arquivo;
				try {
					arquivo = new FileWriter(CAMINHO);
					PrintWriter gravarArquivo = new PrintWriter(arquivo);
					operacao=String.valueOf(valor1)+sinal+String.valueOf(valor2)+" = "+String.valueOf(result)+"\n";
					operacoes.add(operacao);
					for(int i =0; i<operacoes.size();i++) {
						gravarArquivo.append(operacoes.get(i));						
					}
					System.out.println(operacao);
					arquivo.close();
					
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Problemas com o arquivo , verifique no código fonte se o CAMINHO do arquivo está certo");
					e1.printStackTrace();
				}
				finally{
					operacao=result;
					valor1=Double.parseDouble(result);
				}
			}

			
				
			}
		});
		button_9.setBounds(203, 215, 57, 80);
		contentPane.add(button_9);
		
		JButton button_10 = new JButton("-");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickOperacao("-",textResultado);	
				
			}
		});
		button_10.setBounds(328, 144, 50, 50);
		contentPane.add(button_10);
		
		JButton button_11 = new JButton("/");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickOperacao("/",textResultado);	
				
			}
		});
		button_11.setBounds(328, 200, 50, 50);
		contentPane.add(button_11);
		
		JButton button_12 = new JButton("*");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickOperacao("*",textResultado);					
			}
		});
		button_12.setBounds(328, 256, 50, 50);
		contentPane.add(button_12);
		
		JButton button_13 = new JButton(",");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean jaTem=textResultado.getText().contains(".");
				if(textResultado.getText().isBlank()||showResult)  onClickBotaoNumerico( "0.",textResultado);
				else if(!jaTem) onClickBotaoNumerico( ".",textResultado);
			}
		});
		button_13.setBounds(128, 270, 50, 25);
		contentPane.add(button_13);
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileWriter arquivo;
				try {
					arquivo = new FileWriter(CAMINHO);
					PrintWriter gravarArquivo = new PrintWriter(arquivo);
					gravarArquivo.append("");
					arquivo.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Problemas com o arquivo , verifique no código fonte se o CAMINHO do arquivo está certo");	
					e1.printStackTrace();
				}
				textResultado.setText("");
				valor1=0.;
				valor2=0.;
				operacao="";
				showResult=false;
				operacoes = new ArrayList<String>();
				
			}
		});
		btnC.setBounds(203, 87, 57, 25);
		contentPane.add(btnC);
		
		JButton button_14 = new JButton("<");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(showResult) {
					valor1=0.;
					valor2=0.;
					sinal="";
					textResultado.setText("");
				}
				else {
					String dado = textResultado.getText();
					textResultado.setText(dado.substring(0,dado.length()-1));
				}
			}
		});
		button_14.setBounds(203, 124, 57, 25);
		contentPane.add(button_14);
		
		JButton button_15 = new JButton("%");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String conteudo = textResultado.getText();
				if(!conteudo.isBlank()) {
					Double valor=Double.parseDouble(conteudo)/100;
					textResultado.setText(String.valueOf(valor));	
				}
			}
		});
		button_15.setBounds(203, 150, 57, 25);
		contentPane.add(button_15);
		
		JButton btnHist = new JButton("Hist");
		btnHist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hist h = new Hist();
				h.setVisible(true);
				
			}
		});
		btnHist.setBounds(203, 178, 70, 25);
		contentPane.add(btnHist);
	}
}
