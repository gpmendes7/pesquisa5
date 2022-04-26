package app.pareamento.contagem;

import java.io.IOException;
import java.text.ParseException;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ContagemPacientesCOVIDComEvolucaoCasoObito {
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		PareamentoContagemPositivosNegativos pareamento = new PareamentoContagemPositivosNegativos();
		
		pareamento.carregarArquivosCSV("./arquivos/csv/obito/SIVEP_REDOME4(OBITO).csv", 
                                       "./arquivos/csv/Sus_REDOME3(Ajustado).csv");
		
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(31, 50);
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(51, 59);
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(60, 75);
		
		pareamento.gerarArquivoCSVOrdenado("./arquivos/csv/obito/SIVEP_REDOME5(OBITO).csv");
	}

}
