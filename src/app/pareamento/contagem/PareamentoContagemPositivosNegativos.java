package app.pareamento.contagem;

import static app.pareamento.FiltrosPareamento.filtrarRegistrosSivepPorFaixaEtaria;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorAreaMunicipio;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorDataNotificacao;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorFaixaEtaria;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorMunicipio;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorRacaCor;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorResultado;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorSexo;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorTipoTesteComCovid;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SivepRedomeCSV;
import csv.SivepRedomeCSVHandler;
import csv.SusRedomeCSV;
import csv.SusRedomeCSVHandler;

public class PareamentoContagemPositivosNegativos {
	
	private List<SivepRedomeCSV> registrosSivep;
	private List<SivepRedomeCSV> registrosSivepOrdenado;
	private List<SusRedomeCSV> registrosSus;

	public void carregarArquivosCSV(String csvSivep, String csvSus) throws IOException {
		registrosSivep = SivepRedomeCSVHandler.carregarCSV(csvSivep);
		registrosSus = SusRedomeCSVHandler.carregarCSV(csvSus);
		registrosSivepOrdenado = new ArrayList<>();
	}
	
	public void gerarArquivoCSVOrdenado(String csvSivepOrdenado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		registrosSivepOrdenado.sort((r1, r2) -> {
			Integer qtdNeg1 = Integer.parseInt(r1.getQtdNegativos());
			Integer qtdNeg2 = Integer.parseInt(r2.getQtdNegativos());
			
			Integer qtdPos1 = Integer.parseInt(r1.getQtdPositivos());
			Integer qtdPos2 = Integer.parseInt(r2.getQtdPositivos());
			
	        if (qtdNeg1.compareTo(qtdNeg2) == 0) {
	            return qtdPos1.compareTo(qtdPos2);
	        } else {
	            return qtdNeg1.compareTo(qtdNeg2);
	        } 
	    });
		
		registrosSivepOrdenado.add(0,
				new SivepRedomeCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", "municipio",
						"id", "sexo", "racaCor", "dataInternacao", "dataInternacaoRedome", "dataEncerramento",
						"dataEncerramentoRedome", "evolucaoCaso", "evolucaoCasoRedome", "dataNotificacao",
						"resultadoTeste", "sexoRedome", "etniaRedome", "semanaNotificacao", "qtdPositivos", "qtdNegativos"));

		SivepRedomeCSVHandler.criarCSV(csvSivepOrdenado, registrosSivepOrdenado);
	}

	public void contagemPositivosNegativosPacientesEntreSivepESus(int idadeMinima, int idadeMaxima)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		List<SivepRedomeCSV> registrosSivepFiltrados = filtrarRegistrosSivepPorFaixaEtaria(registrosSivep, idadeMinima, idadeMaxima);
	
		for (SivepRedomeCSV registroSivepFiltrado : registrosSivepFiltrados) {
			int numeroSemanas = 1;
			
			List<SusRedomeCSV> registrosSusFiltradosRegistroSivep = registrosSus;
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorFaixaEtaria(registrosSusFiltradosRegistroSivep, idadeMinima, idadeMaxima);
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorRacaCor(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorDataNotificacao(registrosSusFiltradosRegistroSivep, registroSivepFiltrado, numeroSemanas);
			
			//registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorMunicipio(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorAreaMunicipio(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorSexo(registrosSusFiltradosRegistroSivep,registroSivepFiltrado);
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorTipoTesteComCovid(registrosSusFiltradosRegistroSivep);
			
			registroSivepFiltrado.setQtdPositivos(filtrarRegistrosSusPorResultado(registrosSusFiltradosRegistroSivep, "Positivo").size() + "");
			
			registroSivepFiltrado.setQtdNegativos(filtrarRegistrosSusPorResultado(registrosSusFiltradosRegistroSivep, "Negativo").size() + "");
			
			registrosSivepOrdenado.add(registroSivepFiltrado);
		}	
	}

}
