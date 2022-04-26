package app.pareamento.contagem;

import java.io.IOException;
import java.text.ParseException;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ContagemPacientesCOVIDComEvolucaoCasoInternado {
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		PareamentoContagemPositivosNegativos pareamento = new PareamentoContagemPositivosNegativos();
		
		pareamento.carregarArquivosCSV("./arquivos/csv/internado/SIVEP_REDOME4(INTERNADO).csv", 
                                       "./arquivos/csv/Sus_REDOME3(Ajustado).csv");
		
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(25, 44);
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(45, 56);
		pareamento.contagemPositivosNegativosPacientesEntreSivepESus(57, 81);
		
		pareamento.gerarArquivoCSVOrdenado("./arquivos/csv/internado/SIVEP_REDOME5(INTERNADO).csv");
	}

}
