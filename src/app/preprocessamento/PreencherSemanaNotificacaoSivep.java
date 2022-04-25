package app.preprocessamento;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import app.util.DataUtil;
import csv.SivepRedomeCSV;
import csv.SivepRedomeCSVHandler;

public class PreencherSemanaNotificacaoSivep {
	
	public static void main(String[] args) throws IOException, ParseException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		preencherSemanaNotificacaoObito();
		preencherSemanaNotificacaoUti();
		preencherSemanaNotificacaoInternado();
		preencherSemanaNotificacaoRecuperado();
	}
	
	public static void preencherSemanaNotificacaoObito() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
        List<SivepRedomeCSV> registros = SivepRedomeCSVHandler.carregarCSV("./arquivos/csv/obito/SIVEP_REDOME3(OBITO).csv");
		
		Date dataInicioPandemia = new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime();
		
		for (SivepRedomeCSV registro : registros) { 
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy"); 
			Date dataNotificacao = sdf1.parse(registro.getDataNotificacao()); 
			long semanaNotificacao = DataUtil.obterDiferencaDias(dataNotificacao, dataInicioPandemia)/7;
			registro.setSemanaNotificacao(semanaNotificacao + "");
		}
		
		registros.add(0, 
				 new SivepRedomeCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", 
			                 	    "municipio", "id", "sexo", "racaCor", 
			                 	    "dataInternacao", "dataInternacaoRedome", "dataEncerramento", "dataEncerramentoRedome", 
			                 	    "evolucaoCaso", "evolucaoCasoRedome", "dataNotificacao", "resultadoTeste", "sexoRedome", "etniaRedome",
			                 	    "semanaNotificacao"));
		
		SivepRedomeCSVHandler.criarCSV("./arquivos/csv/obito/SIVEP_REDOME4(OBITO).csv", registros);	
	}
	
	public static void preencherSemanaNotificacaoUti() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
        List<SivepRedomeCSV> registros = SivepRedomeCSVHandler.carregarCSV("./arquivos/csv/uti/SIVEP_REDOME3(UTI).csv");
		
		Date dataInicioPandemia = new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime();
		
		for (SivepRedomeCSV registro : registros) { 
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy"); 
			Date dataNotificacao = sdf1.parse(registro.getDataNotificacao()); 
			long semanaNotificacao = DataUtil.obterDiferencaDias(dataNotificacao, dataInicioPandemia)/7;
			registro.setSemanaNotificacao(semanaNotificacao + "");
		}
		
		registros.add(0, 
				 new SivepRedomeCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", 
			                 	    "municipio", "id", "sexo", "racaCor", 
			                 	    "dataInternacao", "dataInternacaoRedome", "dataEncerramento", "dataEncerramentoRedome", 
			                 	    "evolucaoCaso", "evolucaoCasoRedome", "dataNotificacao", "resultadoTeste", "sexoRedome", "etniaRedome",
			                 	    "semanaNotificacao"));
		
		SivepRedomeCSVHandler.criarCSV("./arquivos/csv/uti/SIVEP_REDOME4(UTI).csv", registros);	
	}
	
	public static void preencherSemanaNotificacaoInternado() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
        List<SivepRedomeCSV> registros = SivepRedomeCSVHandler.carregarCSV("./arquivos/csv/internado/SIVEP_REDOME3(INTERNADO).csv");
		
		Date dataInicioPandemia = new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime();
		
		for (SivepRedomeCSV registro : registros) { 
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy"); 
			Date dataNotificacao = sdf1.parse(registro.getDataNotificacao()); 
			long semanaNotificacao = DataUtil.obterDiferencaDias(dataNotificacao, dataInicioPandemia)/7;
			registro.setSemanaNotificacao(semanaNotificacao + "");
		}
		
		registros.add(0, 
				 new SivepRedomeCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", 
			                 	    "municipio", "id", "sexo", "racaCor", 
			                 	    "dataInternacao", "dataInternacaoRedome", "dataEncerramento", "dataEncerramentoRedome", 
			                 	    "evolucaoCaso", "evolucaoCasoRedome", "dataNotificacao", "resultadoTeste", "sexoRedome", "etniaRedome",
			                 	    "semanaNotificacao"));
		
		SivepRedomeCSVHandler.criarCSV("./arquivos/csv/internado/SIVEP_REDOME4(INTERNADO).csv", registros);	
	}
	
	public static void preencherSemanaNotificacaoRecuperado() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
        List<SivepRedomeCSV> registros = SivepRedomeCSVHandler.carregarCSV("./arquivos/csv/recuperado/SIVEP_REDOME3(RECUPERADO).csv");
		
		Date dataInicioPandemia = new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime();
		
		for (SivepRedomeCSV registro : registros) { 
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy"); 
			Date dataNotificacao = sdf1.parse(registro.getDataNotificacao()); 
			long semanaNotificacao = DataUtil.obterDiferencaDias(dataNotificacao, dataInicioPandemia)/7;
			registro.setSemanaNotificacao(semanaNotificacao + "");
		}
		
		registros.add(0, 
				 new SivepRedomeCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", 
			                 	    "municipio", "id", "sexo", "racaCor", 
			                 	    "dataInternacao", "dataInternacaoRedome", "dataEncerramento", "dataEncerramentoRedome", 
			                 	    "evolucaoCaso", "evolucaoCasoRedome", "dataNotificacao", "resultadoTeste", "sexoRedome", "etniaRedome",
			                 	    "semanaNotificacao"));
		
		SivepRedomeCSVHandler.criarCSV("./arquivos/csv/recuperado/SIVEP_REDOME4(RECUPERADO).csv", registros);	
	}

}
