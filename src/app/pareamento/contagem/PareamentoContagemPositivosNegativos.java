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
	
		List<SusRedomeCSV> registrosSusTotaisFiltradosComResultadoPositivo = new ArrayList<>();
		List<SusRedomeCSV> registrosSusTotaisFiltradosComResultadoNegativo = new ArrayList<>();

		for (SivepRedomeCSV registroSivepFiltrado : registrosSivepFiltrados) {
			int numeroSemanas = 1;
			
			List<SusRedomeCSV> registrosSusFiltradosRegistroSivep = registrosSus;
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorFaixaEtaria(registrosSusFiltradosRegistroSivep, idadeMinima, idadeMaxima);
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorRacaCor(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorDataNotificacao(registrosSusFiltradosRegistroSivep, registroSivepFiltrado, numeroSemanas);
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorMunicipio(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorAreaMunicipio(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorSexo(registrosSusFiltradosRegistroSivep,registroSivepFiltrado);
			
			registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorTipoTesteComCovid(registrosSusFiltradosRegistroSivep);
			
		    registrosSusTotaisFiltradosComResultadoPositivo
						.addAll(filtrarRegistrosSusPorResultado(registrosSusFiltradosRegistroSivep, "Positivo"));
		    
			registrosSusTotaisFiltradosComResultadoNegativo
						.addAll(filtrarRegistrosSusPorResultado(registrosSusFiltradosRegistroSivep, "Negativo"));
			
			registroSivepFiltrado.setQtdPositivos(registrosSusTotaisFiltradosComResultadoPositivo.size() + "");
			
			registroSivepFiltrado.setQtdNegativos(registrosSusTotaisFiltradosComResultadoNegativo.size() + "");
			
			registrosSivepOrdenado.add(registroSivepFiltrado);
		}
		
	}

}
