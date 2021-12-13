package app.preprocessamento;

import java.io.IOException;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import app.util.StringUtil;
import csv.SusRedomeCSV;
import csv.SusRedomeCSVHandler;

public class PreencherEtniaRedomeSus {
	
	 public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
			preencherEtniaRedomeSus("./arquivos/csv/Sus_REDOME(Ajustado).csv", 
									"./arquivos/csv/Sus_REDOME2(Ajustado).csv");
		}
		 
		 public static void preencherEtniaRedomeSus(String arquivoEntrada, String arquivoSaida) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
			List<SusRedomeCSV> registros = SusRedomeCSVHandler.carregarCSV(arquivoEntrada);
			 
			int count = 0;
				
			for (SusRedomeCSV registro : registros) {
				String racaCor = StringUtil.normalizarString(registro.getRacaCor());
				String etniaRedome = StringUtil.normalizarString(registro.getEtniaRedome());
				
				if(etniaRedome.equals(StringUtil.normalizarString("NAO INFORMADO")) && !racaCor.equals("")) {
					count++;
					registro.setEtniaRedome(racaCor);
				}
			}
			
			registros.add(0, new SusRedomeCSV("id", "municipio", "nomeCompleto", "cpf", 
											  "dataNascimento", "municipioNotificacao", "racaCor", 
											  "etnia", "nomeMae", "dataNotificacao", "idade", 
											  "resultadoTeste", "dataTeste", "tipoTeste", "estadoTeste", 
											  "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso", 
											  "sexoRedome", "etniaRedome"));
					
			System.out.println("Arquivo " + arquivoEntrada);
			System.out.println("registros com atributo etniaRedome modificados " + count);
			
			SusRedomeCSVHandler.criarCSV(arquivoSaida, registros);
		 }

}
