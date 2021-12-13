package csv;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class PareamentoCSVHandler {
	
	private static ColumnPositionMappingStrategy<PareamentoCSV> strategy;
	
	static {
		strategy = new ColumnPositionMappingStrategy<PareamentoCSV>();
		strategy.setType(PareamentoCSV.class);

		String[] colunas = { "id", "identificacao", "nomeCompleto", "cpf", "municipio", "regiao",
							 "filtroAreaMunicipio", "dataNotificacao", "sexo", "idade", "racaCor",
							 "tipoTeste", "resultadoTeste", "desfecho", "origem", "evolucaoCaso",
							 "intervalo", "sexoRedome", "etniaRedome"};
 
		strategy.setColumnMapping(colunas);
	}
	
	public static List<PareamentoCSV> carregarCSV(String nomeArquivo) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(nomeArquivo));) {
			CsvToBean<PareamentoCSV> csvToBean = new CsvToBeanBuilder<PareamentoCSV>(reader).withMappingStrategy(strategy)
					.withType(PareamentoCSV.class).withSeparator(',').withSkipLines(1).build();
			List<PareamentoCSV> registros = csvToBean.parse();
			return registros;
		}
	}
	
	public static void criarCSV(String nomeArquivo, List<PareamentoCSV> registros) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(nomeArquivo));) {
			StatefulBeanToCsv<PareamentoCSV> beanToCsv = new StatefulBeanToCsvBuilder<PareamentoCSV>(writer)
														.withMappingStrategy(strategy)
														.withSeparator(',')
														.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
														.build();
			beanToCsv.write(registros);
		}
	}

}
