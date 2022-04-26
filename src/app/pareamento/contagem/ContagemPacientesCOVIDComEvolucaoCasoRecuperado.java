package app.pareamento.contagem;

import java.io.IOException;
import java.text.ParseException;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ContagemPacientesCOVIDComEvolucaoCasoRecuperado {
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		PareamentoContagemPositivosNegativos pareamento = new PareamentoContagemPositivosNegativos();
		
		pareamento.carregarArquivosCSV("./arquivos/csv/recuperado/SIVEP_REDOME4(RECUPERADO).csv", 
                                       "./arquivos/csv/Sus_REDOME3(Ajustado).csv");
		
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(22, 42);
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(43, 53);
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(54, 74);
		
		pareamento.gerarArquivoCSVOrdenado("./arquivos/csv/recuperado/SIVEP_REDOME5(RECUPERADO).csv");
	}

}
