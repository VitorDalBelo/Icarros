package gerencia;

import java.sql.SQLException;
import java.util.ArrayList;

public class Teste {

	public static void main(String[] args) {
		
	
		boolean t =BancoDeDados.estaConectado();
		System.out.println(t);
		try {
			System.out.println(BancoDeDados.getGenero(1));
//			ArrayList<String> generos = BancoDeDados.getGeneros();
//			for(int i = 0 ;i<generos.size();i++) {	
//				System.out.println(String.valueOf(i+1)+ generos.get(i));
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
