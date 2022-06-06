package gerencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import model.Filme;

public class BancoDeDados {
	private static Connection connection=null;
	private static java.sql.Statement statement=null;
	private static ResultSet resultset=null;

	static {
		
		String servidor ="jdbc:mysql://localhost:3306/locadora";
		String usuario ="root";
		String senha="";
		String driver ="com.mysql.cj.jdbc.Driver";
		try{
			Class.forName(driver);
			connection=DriverManager.getConnection(servidor,usuario,senha);
			statement=connection.createStatement();

		}catch (Exception e){
			System.out.println("Erro:"+e.getMessage());
		}
	}

	public static boolean estaConectado() {
		if(connection !=null) {
			return true;
		}else {
			return false;
		}		
	}


	// Criando o método para fazer a consulta de dados em SQL para trazeer os registros
	public static String[] getGeneros() throws SQLException {

		ArrayList<String> generos = new ArrayList<String>();
		String query = "Select * from genero";
		resultset = statement.executeQuery(query);
		statement=connection.createStatement();
		
		while(resultset.next()) {
			
			String genero= resultset.getString("genero");
			generos.add(genero);
		
		}
		String[] a = new String[generos.size()];
		return generos.toArray(a);
	}
	public static String getGenero(int id) throws SQLException {

		String query = "Select * from genero where id_genero = '"+id+"';";
		resultset = statement.executeQuery(query);
		statement=connection.createStatement();
		resultset.next();
		return resultset.getString("genero");
	}
	
	public static Filme getFilme(int id) throws SQLException {

		String query = "Select * from filme where id_filme = '"+id+"';";
		resultset = statement.executeQuery(query);
		statement=connection.createStatement();
		
		resultset.next();
		return new Filme(
				resultset.getInt("id_filme"),
				resultset.getString("nome_filme"),
				resultset.getString("duracao_filme"),
				resultset.getDate("data_lancamento_filme"),
				resultset.getString("imagem_filme"),
				resultset.getString("date_modificacao_filme"),
				resultset.getString("sinopse_filme"),
				resultset.getInt("id_cliente"),
				resultset.getInt("id_genero")
				);
		
	}	
	public static ArrayList<HashMap<String,Object>> getClientes() throws SQLException {

		ArrayList<HashMap<String,Object>> clientes = new ArrayList<HashMap<String,Object>>();
		String query = "Select * from clientes";
		resultset = statement.executeQuery(query);
		statement=connection.createStatement();
		while(resultset.next()) {
			HashMap<String,Object> cliente = new HashMap<String,Object>();
			int id = resultset.getInt("id_cliente");
			String nome= resultset.getString("nome_cliente");
			int idade = resultset.getInt("idade_cliente");
			cliente.put("id", id);
			cliente.put("nome", nome);
			cliente.put("idade", idade);
			clientes.add(cliente);
		}
		return clientes;
	}
	
	public static Vector<Vector<String>>  getFilmesAsVector() throws SQLException {

		Vector<Vector<String>> filmes = new Vector<Vector<String>> ();
		String query = "select * from filme f inner join genero g on f.id_genero = g.id_genero";
		resultset = statement.executeQuery(query);
		statement=connection.createStatement();
		while(resultset.next()) {
			
			
			String nome= resultset.getString("nome_filme");
			String duracao = resultset.getString("duracao_filme");
			String lancamento = resultset.getString("data_lancamento_filme");
			String sinopse= resultset.getString("sinopse_filme");
			String genero = resultset.getString("genero");
			System.out.println(sinopse);
			filmes.add(new Vector<String>(Arrays.asList(nome,duracao,genero,lancamento,sinopse)));
		}
		System.out.println(filmes);
		return filmes;
	}
	
	public static ArrayList<Filme> getFilmes() throws SQLException {

		ArrayList<Filme> filmes = new ArrayList<Filme> ();
		String query = "select * from filme ";
		resultset = statement.executeQuery(query);
		statement=connection.createStatement();
		while(resultset.next()) {
			
			Filme filme = new Filme(
					resultset.getInt("id_filme"),
					resultset.getString("nome_filme"),
					resultset.getString("duracao_filme"),
					resultset.getDate("data_lancamento_filme"),
					resultset.getString("imagem_filme"),
					resultset.getString("date_modificacao_filme"),
					resultset.getString("sinopse_filme"),
					resultset.getInt("id_cliente"),
					resultset.getInt("id_genero")
					);
			filmes.add(filme);
		}
		
		return filmes;
	}



	//Criando um novo método para fazer a atualização dos registros
	public static void editarFilme(int id,String nome,String lancamento,String sinopse,String duracao, int genero) {
		// Vai verificar se existe uma exceção
		try {
			String query="update filme set nome_filme='"+nome+"',duracao_filme='"+duracao+"',sinopse_filme='"+sinopse+"',data_lancamento_filme='"+lancamento+"',id_genero="+genero+" where id_filme="+id+";";
			System.out.println(query);
			statement.executeUpdate(query);
		}catch(Exception e) {
			System.out.println("Erro: "+e.getMessage());
		}
	}



	public static void inserirFilme(String nome,String duracao,String lancamento,String sinopse,int genero) throws SQLException {

			String img = nome.replace(" ", "_").toUpperCase();
			String query="insert into filme "+ "values"+ "(null,'"+nome+"','"+duracao+"','"+lancamento+"','"+sinopse+"','../img/"+img+".jpg',now(),null,"+genero+")";
			System.out.println(query);
			statement.executeUpdate(query);

	}
	public static void modificaFilme(String nome,String duracao,String lancamento,String sinopse,int genero) {
		//Vai verificar se existe uma exceção
		try {
			//Linha de execução da sintaxe em SQL
			String img = nome.replace(" ", "_").toUpperCase();
			String query="update into filme "+ "values"+ "(null,'"+nome+"','"+duracao+"','"+lancamento+"','"+sinopse+"','../img/"+img+".jpg',now(),null,"+genero+")";
		System.out.println(query);
			statement.executeUpdate(query);
		}catch(Exception e) {
			System.out.println("Erro: "+e.getLocalizedMessage());
		}
	}


	// Criando o método de exclusão de registro
	public static void deletarFilme(int id) {
		// Vai verificar se existe uma exceção
		try {
			String query = "delete from filme where id_filme ="+id+";";
			System.out.println(query);
			statement.executeUpdate(query);
		}catch(Exception e) {
			System.out.println("Erro: "+e.getMessage());
		}
	}
}