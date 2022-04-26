package app.pareamento.contagem;

import java.io.IOException;
import java.text.ParseException;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ContagemPacientesCOVIDComEvolucaoCasoUti {
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		PareamentoContagemPositivosNegativos pareamento = new PareamentoContagemPositivosNegativos();
		
		pareamento.carregarArquivosCSV("./arquivos/csv/uti/SIVEP_REDOME4(UTI).csv", 
                                       "./arquivos/csv/Sus_REDOME2(Ajustado).csv");
		
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(26, 42);
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(43, 53);
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(54, 67);
		
		pareamento.gerarArquivoCSVOrdenado("./arquivos/csv/uti/SIVEP_REDOME5(UTI).csv");
	}

}
