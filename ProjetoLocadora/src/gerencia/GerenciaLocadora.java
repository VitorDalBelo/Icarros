package gerencia;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import model.Filme;
import validation.FieldsValidation;

public class GerenciaLocadora extends JFrame {

	private JPanel contentPane;
	private JTextField nomeField;
	private JTable table;
	private static DefaultTableModel model;
	private static ArrayList<Filme> filmes = new ArrayList<Filme>();
	private Vector<String> tableheader = new Vector<String>(Arrays.asList("nome","duracao","genero","lancamento")) ;
	private static String[] generos;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					filmes = BancoDeDados.getFilmes();
					generos = BancoDeDados.getGeneros();
					GerenciaLocadora frame = new GerenciaLocadora();
					frame.setVisible(true);
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String dbDateFormat(String d) {
		String[] nums = d.split("/", 0);
		return nums[2]+"-"+nums[1]+"-"+nums[0];
	}
	public String displayFormat(String d) {
		String[] nums = d.split("-", 0);
		return nums[2]+"-"+nums[1]+"-"+nums[0];
	}
	public String desformatarTime(String valorParaOBanco) {
		return valorParaOBanco.replace("h", ":").replace("m", ":00");	
	}
	public String formatarTime(String valorDoBanco) {
			String[] nums = valorDoBanco.split(":", 0);
			return nums[0]+"h"+nums[1]+"m";		
	}
	public void atualizaTabela() throws SQLException {
		filmes = BancoDeDados.getFilmes();
		Vector<Vector<String>>filmesVector = new Vector<Vector<String>>();
		
		for(int i =0;i<filmes.size();i++) {
			Filme filme = filmes.get(i);
			filmesVector.add(new Vector<String>(Arrays.asList(filme.getNome(),formatarTime(filme.getDuracao()),generos[filme.getGenero()-1],filme.getLancamento().toString())));
		}
		
		
		model.setDataVector(filmesVector,tableheader);
	}
		
	public GerenciaLocadora() throws SQLException, ParseException {
		SimpleDateFormat displayDate = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dbDate = new SimpleDateFormat("yyyy-MM-dd");
		MaskFormatter timeMask = new MaskFormatter("##h##m");
		MaskFormatter dateMask = new MaskFormatter("##/##/####");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema De Locação de filmes");
		lblNewLabel.setBounds(12, 0, 227, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNomefilme = new JLabel("Nome_Filme");
		lblNomefilme.setBounds(12, 27, 104, 15);
		contentPane.add(lblNomefilme);
		
		nomeField = new JTextField();
		nomeField.setBounds(182, 27, 114, 19);
		contentPane.add(nomeField);
		nomeField.setColumns(10);
		
		JLabel lblDuracaofilme = new JLabel("Duracao_filme");
		lblDuracaofilme.setBounds(12, 55, 104, 15);
		contentPane.add(lblDuracaofilme);
		


		JFormattedTextField duracaoField = new JFormattedTextField(timeMask);
		duracaoField.setBounds(182, 53, 114, 19);
		contentPane.add(duracaoField);
		
		
		
		JLabel lblLancamentofilme = new JLabel("Lancamento_filme");
		lblLancamentofilme.setBounds(12, 82, 145, 29);
		contentPane.add(lblLancamentofilme);
		

		JFormattedTextField lancamentoField = new JFormattedTextField(dateMask);
		lancamentoField.setBounds(182, 87, 114, 19);
		contentPane.add(lancamentoField);
		
		JLabel lblGenerofilme = new JLabel("Genero_Filme");
		lblGenerofilme.setBounds(12, 123, 104, 15);
		contentPane.add(lblGenerofilme);
		//String[] generos = new String[] {"ação", "romance", "comédia", "ficção ciéntifica"};
		JComboBox generoField = new JComboBox();
		generoField.setModel(new DefaultComboBoxModel(generos));
		generoField.setBounds(182, 118, 114, 24);
		contentPane.add(generoField);
		
		JLabel lblSinopse = new JLabel("Sinopse");
		lblSinopse.setBounds(12, 154, 70, 15);
		contentPane.add(lblSinopse);
		
		JEditorPane sinopseInsert = new JEditorPane();
		sinopseInsert.setBounds(84, 154, 217, 77);
		contentPane.add(sinopseInsert);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(321, -1, 329, 128);
		contentPane.add(scrollPane);

		JEditorPane sinopseArea = new JEditorPane();
		sinopseArea.setBounds(325, 133, 329, 155);
		contentPane.add(sinopseArea);

		
		JLabel image = new JLabel("");
		image.setBounds(658, 27, 232, 308);
		contentPane.add(image);
		
		table = new JTable(){
	         public boolean editCellAt(int row, int column, java.util.EventObject e) {
	             return false;
	          }
	       };

	       table.addMouseListener(new MouseAdapter() {
	    	   public void mouseClicked(MouseEvent e) {
 		    	  Filme filme;
				try {
					filme = filmes.get(table.getSelectedRow());
					String sinopse = filme.getSinopse();
					nomeField.setText(filme.getNome());
					generoField.setSelectedIndex(filme.getGenero()-1);
					sinopseArea.setText(sinopse);
					sinopseInsert.setText(sinopse);
					duracaoField.setText(formatarTime(filme.getDuracao()));
					lancamentoField.setText(displayDate.format(filme.getLancamento()));
					image.setIcon(new ImageIcon(getClass().getResource(filme.getImg())));	
				} 
				catch (Exception e2) {
						System.out.println(e2.getLocalizedMessage());
						image.setIcon(null);	    	   
					}
	    	   }
	    	   });


		model = new DefaultTableModel();
		table.setModel(model);
		atualizaTabela();
		
		scrollPane.setViewportView(table);
		

		
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = nomeField.getText();
				String duracao = desformatarTime(duracaoField.getText());
				String lancamento = dbDateFormat(lancamentoField.getText());
				int genero = generoField.getSelectedIndex()+1;
				String sinopse = sinopseInsert.getText() ;	
				System.out.println(lancamento);
				if(FieldsValidation.validateAll(nome, duracao, lancamento, sinopse)) {
					try {
						BancoDeDados.inserirFilme(nome, duracao, lancamento, sinopse, genero);
						atualizaTabela();
						nomeField.setText("");
						generoField.setSelectedIndex(0);
						sinopseArea.setText("");
						sinopseInsert.setText("");
						duracaoField.setText("");
						lancamentoField.setText("");
					} catch (SQLException e) {
						FieldsValidation.sqlExceptionHandler(e);					
					}					
				}

			}
		});
		btnInserir.setBounds(12, 243, 117, 25);
		contentPane.add(btnInserir);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowIndex =table.getSelectedRow();
				if(rowIndex>=0) {
					String nome = nomeField.getText();
					String duracao = desformatarTime(duracaoField.getText());
					
					String lancamento = dbDate.format(Date.parse(lancamentoField.getText()));
					
					int genero = generoField.getSelectedIndex()+1;
					String sinopse = sinopseInsert.getText() ;	
					try {
						BancoDeDados.editarFilme(filmes.get(rowIndex).getId(),nome,lancamento, sinopse,duracao, genero);
						atualizaTabela();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				
			}
		});
		btnModificar.setBounds(12, 273, 117, 25);
		contentPane.add(btnModificar);
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowIndex =table.getSelectedRow();
				if(rowIndex>=0) {
					
					
					BancoDeDados.deletarFilme(filmes.get(rowIndex).getId());
					
					image.setIcon(null);
					sinopseArea.setText("");
					try {
						atualizaTabela();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
			}
		});
		btnDeletar.setBounds(12, 310, 117, 25);
		contentPane.add(btnDeletar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nomeField.setText("");
				generoField.setSelectedIndex(0);
				sinopseArea.setText("");
				sinopseInsert.setText("");
				duracaoField.setText("");
				lancamentoField.setText("");
				image.setIcon(null);
				table.clearSelection();
			}
		});
		btnLimpar.setBounds(12, 344, 117, 25);
		contentPane.add(btnLimpar);
		


	}
}
