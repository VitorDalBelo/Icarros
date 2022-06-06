import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessaArquivoTryCath {

	public static void main(String[] args) {		
		try {
			FileWriter arquivo = new FileWriter("C:\\ArquivoFisico\\teste.txt");
			PrintWriter gravarArquivo = new PrintWriter(arquivo);
			ArrayList<String> array = new ArrayList();
			array.add("Mouse");
			array.add("Teclado");
			array.add("Scanner");
			array.add("Impressora");
			array.add("Monitor");
			
			gravarArquivo.printf("%s","Data do Cadastro 13/05/2022 \n");
			
			for(int i = 0 ; i <array.size();i++ ) {
				
				String result = array.get(i) + "\n";
				gravarArquivo.printf("%s",result);
				System.out.println(result);
			}
			arquivo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("problema com o arquivo");
		}

	}

}
