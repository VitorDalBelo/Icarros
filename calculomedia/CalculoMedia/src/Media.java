import java.util.Scanner;

public class Media {

	public static void main(String[] args) {
		String nome ;
		double somaNotas = 0;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Entre com o nome do Aluno:");
		nome = teclado.nextLine();
		int qtdeNotas=1;
		while(qtdeNotas<=4) {
			
			
			System.out.printf("Entre com a Nota %d", qtdeNotas);
			String linha = teclado.nextLine();
		    
			try{
		        double valor = Double.parseDouble(linha) ;
				somaNotas += valor;
				qtdeNotas++;
		    }

		    catch(Exception e){
		        System.out.printf("Você não digitou um número inteiro!\n");
		    }

		}
		
		System.out.println("Resultado");
		System.out.println("==============================================================");
		
		System.out.printf("A média do aluno %s é  : %2f",nome,somaNotas/4.0);
		
		
		
		
	}

}
