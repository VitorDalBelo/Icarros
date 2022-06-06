package validation;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;

import javax.swing.JOptionPane;

public class FieldsValidation {
	private static final java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static boolean fieldRequired(String fieldValue) {
		return 	fieldValue.isBlank();
	}
	public static boolean scoreValidation(String fieldValue) {
		Double score = Double.parseDouble(fieldValue);
		return !(score <= 10 && score >= 0);
	}
	
	public static boolean absentValidation(String fieldValue) {
		Integer absent = Integer.parseInt(fieldValue);
		return !(absent >= 0);
	}
	public static boolean validateDate(String strDate) {
	    String dateFormat = "uuuu-MM-dd";

	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter
	    .ofPattern(dateFormat)
	    .withResolverStyle(ResolverStyle.STRICT);
	    try {
	        LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
	        return true;
	    } catch (DateTimeParseException e) {
	       return false;
	    }
	}
	public static boolean timeValidation (String time) {
		try {
			String[] nums = time.split(":", 0);
			int horas = Integer.parseInt(nums[0]);
			int minutos = Integer.parseInt(nums[1]);
			return horas<=4 && minutos<60;
		} catch (Exception e) {
			return false;
		}
	}
	public static void sqlExceptionHandler(SQLException e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
	public static boolean validateAll(String nome, String duracao, String lancamento ,String sinopse) {
		try {
			if(fieldRequired(nome)) throw new Exception("O campo nome é obrigatório");
			if(fieldRequired(duracao)) throw new Exception("O campo duracao é obrigatório");
			else if(!timeValidation(duracao)) throw new Exception("Valor inválido no campo duracao.\n(obs:Não será tolerada duração maior que 4:59) ");
			if(fieldRequired(lancamento)) throw new Exception("O campo lancamento é obrigatório");
			else if(!validateDate(lancamento)) throw new Exception("Data inválida");
			if(fieldRequired(sinopse)) throw new Exception("O campo sinopse é obrigatório");
			return true;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return false;
		}
	}

}
