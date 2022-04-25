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
import csv.SusRedomeCSV;
import csv.SusRedomeCSVHandler;

public class PreencherSemanaNotificacaoSus {
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		List<SusRedomeCSV> registros = SusRedomeCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME2(Ajustado).csv");
		
        Date dataInicioPandemia = new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime();
		
		for (SusRedomeCSV registro : registros) { 
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); 
			Date dataNotificacao = sdf1.parse(registro.getDataNotificacao()); 
			long semanaNotificacao = DataUtil.obterDiferencaDias(dataNotificacao, dataInicioPandemia)/7;
			registro.setSemanaNotificacao(semanaNotificacao + "");
		}
		
		registros.add(0, new SusRedomeCSV("id", "municipio", "nomeCompleto", "cpf", 
										  "dataNascimento", "municipioNotificacao", "racaCor", 
										  "etnia", "nomeMae", "dataNotificacao", "idade", 
										  "resultadoTeste", "dataTeste", "tipoTeste", "estadoTeste", 
										  "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso", 
										  "sexoRedome", "etniaRedome", "semanaNotificacao"));
		
		SusRedomeCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME3(Ajustado).csv", registros);	
	}
}
