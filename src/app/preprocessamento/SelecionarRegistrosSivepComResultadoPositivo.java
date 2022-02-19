package app.preprocessamento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import app.util.StringUtil;
import csv.SivepRedomeCSV;
import csv.SivepRedomeCSVHandler;

public class SelecionarRegistrosSivepComResultadoPositivo {
	
	 private static int count = 0;
	
	 public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		 selecionarRegistrosPositivos("./arquivos/csv/obito/SIVEP_REDOME2(OBITO).csv", 
					                  "./arquivos/csv/obito/SIVEP_REDOME3(OBITO).csv");
			
		 selecionarRegistrosPositivos("./arquivos/csv/uti/SIVEP_REDOME2(UTI).csv", 
	                				  "./arquivos/csv/uti/SIVEP_REDOME3(UTI).csv");
			
		 selecionarRegistrosPositivos("./arquivos/csv/internado/SIVEP_REDOME2(INTERNADO).csv", 
					  				  "./arquivos/csv/internado/SIVEP_REDOME3(INTERNADO).csv");
			
		 selecionarRegistrosPositivos("./arquivos/csv/recuperado/SIVEP_REDOME2(RECUPERADO).csv", 
					  				  "./arquivos/csv/recuperado/SIVEP_REDOME3(RECUPERADO).csv");
		 		
		 System.out.println("registros com resultadoTeste Positivo : " + count);
	}
	
	public static void selecionarRegistrosPositivos(String arquivoEntrada, String arquivoSaida) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SivepRedomeCSV> registros = SivepRedomeCSVHandler.carregarCSV(arquivoEntrada);
		System.out.println("registros.size() : " + registros.size());
		
		List<SivepRedomeCSV> registrosComResultadoTestePositivo = new ArrayList<>();

		for (SivepRedomeCSV registro : registros) {
			String resultadoTeste = StringUtil.normalizarString(registro.getResultadoTeste());
			
			if(resultadoTeste.equals(StringUtil.normalizarString("POSITIVO"))) {
				count++;
				registrosComResultadoTestePositivo.add(registro);
			}
		}
		
		registrosComResultadoTestePositivo.add(0, 
				 new SivepRedomeCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", 
			                 	    "municipio", "id", "sexo", "racaCor", 
			                 	    "dataInternacao", "dataInternacaoRedome", "dataEncerramento", "dataEncerramentoRedome", 
			                 	    "evolucaoCaso", "evolucaoCasoRedome", "dataNotificacao", "resultadoTeste", "sexoRedome", "etniaRedome"));
		
		SivepRedomeCSVHandler.criarCSV(arquivoSaida, registrosComResultadoTestePositivo);
	 }

}
