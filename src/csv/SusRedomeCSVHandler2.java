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

public class SusRedomeCSVHandler2 {
	
	private static ColumnPositionMappingStrategy<SusRedomeCSV> strategy;
	
	static {
		strategy = new ColumnPositionMappingStrategy<SusRedomeCSV>();
		strategy.setType(SusRedomeCSV.class);

		String[] colunas = { "id", "municipio", "filtroAreaMunicipio", "nomeCompleto", "cpf", "dataNascimento", "municipioNotificacao",
							 "racaCor", "etnia", "nomeMae", "dataNotificacao", "idade", "resultadoTeste", 
							 "dataTeste", "tipoTeste", "estadoTeste", "evolucaoCaso", "observacaoExclusao",
							 "sexo", "observacaoUso", "sexoRedome", "etniaRedome" };
 
		strategy.setColumnMapping(colunas);
	}
	
	public static List<SusRedomeCSV> carregarCSV(String nomeArquivo) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(nomeArquivo));) {
			CsvToBean<SusRedomeCSV> csvToBean = new CsvToBeanBuilder<SusRedomeCSV>(reader).withMappingStrategy(strategy)
					.withType(SusRedomeCSV.class).withSeparator(',').withSkipLines(1).build();
			List<SusRedomeCSV> registros = csvToBean.parse();
			return registros;
		}
	}
	
	public static void criarCSV(String nomeArquivo, List<SusRedomeCSV> registros) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(nomeArquivo));) {
			StatefulBeanToCsv<SusRedomeCSV> beanToCsv = new StatefulBeanToCsvBuilder<SusRedomeCSV>(writer)
														.withMappingStrategy(strategy)
														.withSeparator(',')
														.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
														.build();
			beanToCsv.write(registros);
		}
	}

}
