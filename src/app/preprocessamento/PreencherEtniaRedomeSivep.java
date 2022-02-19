package app.preprocessamento;

import java.io.IOException;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import app.util.StringUtil;
import csv.SivepRedomeCSV;
import csv.SivepRedomeCSVHandler;

public class PreencherEtniaRedomeSivep {
	
	 public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		preencherEtniaRedomeSivep("./arquivos/csv/obito/SIVEP_REDOME(OBITO).csv", 
				                  "./arquivos/csv/obito/SIVEP_REDOME2(OBITO).csv");
		
		preencherEtniaRedomeSivep("./arquivos/csv/uti/SIVEP_REDOME(UTI).csv", 
                				  "./arquivos/csv/uti/SIVEP_REDOME2(UTI).csv");
		
		preencherEtniaRedomeSivep("./arquivos/csv/internado/SIVEP_REDOME(INTERNADO).csv", 
				  				  "./arquivos/csv/internado/SIVEP_REDOME2(INTERNADO).csv");
		
		preencherEtniaRedomeSivep("./arquivos/csv/recuperado/SIVEP_REDOME(RECUPERADO).csv", 
				  				  "./arquivos/csv/recuperado/SIVEP_REDOME2(RECUPERADO).csv");
	}
	 
	 public static void preencherEtniaRedomeSivep(String arquivoEntrada, String arquivoSaida) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SivepRedomeCSV> registros = SivepRedomeCSVHandler.carregarCSV(arquivoEntrada);
		 
		int count = 0;
			
		for (SivepRedomeCSV registro : registros) {
			String racaCor = StringUtil.normalizarString(registro.getRacaCor());
			String etniaRedome = StringUtil.normalizarString(registro.getEtniaRedome());
			
			if(etniaRedome.equals(StringUtil.normalizarString("NAO INFORMADO")) && !racaCor.equals("")) {
				count++;
				registro.setEtniaRedome(racaCor);
			}
		}
		
		registros.add(0, new SivepRedomeCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", 
				                            "municipio", "id", "sexo", "racaCor", 
				                            "dataInternacao", "dataInternacaoRedome", "dataEncerramento", "dataEncerramentoRedome", 
				                            "evolucaoCaso", "evolucaoCasoRedome", "dataNotificacao", "resultadoTeste", "sexoRedome", "etniaRedome"));
				
		System.out.println("Arquivo " + arquivoEntrada);
		System.out.println("registros com atributo etniaRedome modificados " + count);
		
		SivepRedomeCSVHandler.criarCSV(arquivoSaida, registros);
	 }

}
