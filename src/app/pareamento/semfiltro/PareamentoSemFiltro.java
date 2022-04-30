package app.pareamento.semfiltro;

import static app.pareamento.FiltrosPareamento.filtrarRegistrosSivepPorFaixaEtaria;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusNaoUsados;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorResultado;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SivepRedomeCSV;
import csv.SivepRedomeCSVHandler;
import csv.SusRedomeCSV;
import csv.SusRedomeCSVHandler;
import csv.SusRedomeCSVHandler2;

public class PareamentoSemFiltro {
	
	private final static int NUMERO_POSITIVO_NEGATIVOS = 2; 

	private List<SivepRedomeCSV> registrosSivep;
	private List<SusRedomeCSV> registrosSus;
	private List<SusRedomeCSV> registrosSusAtualizado;
	private File file;
	private FileWriter fileWriter;
	private String situacao;
	
	public PareamentoSemFiltro(String situacao) {
		this.situacao = situacao;
	}

	public void carregarArquivosCSV(String csvSivep, String csvSus) throws IOException {
		registrosSivep = SivepRedomeCSVHandler.carregarCSV(csvSivep);
		registrosSus = SusRedomeCSVHandler.carregarCSV(csvSus);
		registrosSusAtualizado = new ArrayList<>(registrosSus);
	}
	
	public void criarArquivosCSVSusAtualizado(String csvSusAtualizado) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		registrosSusAtualizado.add(0, new SusRedomeCSV("id", "municipio", "nomeCompleto", 
									                   "cpf", "dataNascimento", "municipioNotificacao", 
									                   "racaCor", "etnia", "nomeMae", 
									                   "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
									                   "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso", "sexoRedome", "etniaRedome", "semanaNotificacao"));

        SusRedomeCSVHandler.criarCSV(csvSusAtualizado, registrosSusAtualizado); 
	}

	public void parearPacientesEntreSivepESus(int idadeMinima, int idadeMaxima, String arquivoTxt,
			String csvResultadoPositivo, String csvResultadoNegativo, String csvSivepUsados, String csvSivepNaoUsados)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		List<SivepRedomeCSV> registrosSivepFiltrados = filtrarRegistrosSivepPorFaixaEtaria(registrosSivep, idadeMinima, idadeMaxima);

		List<SusRedomeCSV> registrosSusTotaisFiltradosComResultadoPositivo = new ArrayList<>();
		List<SusRedomeCSV> registrosSusTotaisFiltradosComResultadoNegativo = new ArrayList<>();

		file = new File(arquivoTxt);
		fileWriter = new FileWriter(file);

		fileWriter.write("***************************\n");
		fileWriter.write("Número de registros do sivep com evolução caso " + situacao + " na faixa etaria de " + idadeMinima
				+ " a " + idadeMaxima + " anos usados para filtrar registros no sus: " + registrosSivepFiltrados.size()
				+ "\n");
		fileWriter.write("***************************\n");

		List<SivepRedomeCSV> registrosSivepNaoUsados = new ArrayList<>();

		for (SivepRedomeCSV registroSivepFiltrado : registrosSivepFiltrados) {
			fileWriter.write("***************************\n");
			int registro = (registrosSivepFiltrados.indexOf(registroSivepFiltrado) + 1);
			fileWriter.write("registro " + registro + "\n");

			fileWriter.write("registro do sivep com identificacao " + registroSivepFiltrado.getIdentificacao() + "\n");
			fileWriter.write("registro do sivep com nomeCompleto " + registroSivepFiltrado.getNomeCompleto() + "\n");
			fileWriter
					.write("registro do sivep com dataNascimento " + registroSivepFiltrado.getDataNascimento() + "\n");

			int filtragem = 1;
			List<SusRedomeCSV> registrosSusFiltradosRegistroSivepComResultadoPositivo = new ArrayList<>();
			List<SusRedomeCSV> registrosSusFiltradosRegistroSivepComResultadoNegativo = new ArrayList<>();
			
			List<SusRedomeCSV> registrosSusFiltradosRegistroSivep = filtrarRegistrosSusNaoUsados(registrosSusAtualizado);
						
			registrosSusFiltradosRegistroSivepComResultadoPositivo.addAll(
					obterRegistrosSusUsadosComResultadoPositivo(registrosSusFiltradosRegistroSivep, NUMERO_POSITIVO_NEGATIVOS));
			
			registrosSusFiltradosRegistroSivepComResultadoNegativo.addAll(
					obterRegistrosSusUsadosComResultadoNegativo(registrosSusFiltradosRegistroSivep, NUMERO_POSITIVO_NEGATIVOS));
			
			fileWriter.write("---------------------------\n");
			fileWriter.write("Resultados finais após filtragem " + filtragem + "\n");

			if (registrosSusFiltradosRegistroSivepComResultadoPositivo.size() < NUMERO_POSITIVO_NEGATIVOS
					|| registrosSusFiltradosRegistroSivepComResultadoNegativo.size() < NUMERO_POSITIVO_NEGATIVOS) {
				fileWriter.write("Número de registros do sus com resultado Positivo e Negativo insuficientes! Registro "
						+ registro + " não será usado!\n");
				fileWriter.write(
						"Registros do sus filtrados usados vão ser desmarcados para uso posterior para filtro de outro registro sivep!\n");

				registrosSusFiltradosRegistroSivepComResultadoPositivo.stream().forEach(r -> r.setObservacaoUso(""));
				registrosSusFiltradosRegistroSivepComResultadoPositivo.stream()
						.forEach(r -> r.setFiltroAreaMunicipio(""));

				registrosSusFiltradosRegistroSivepComResultadoNegativo.stream().forEach(r -> r.setObservacaoUso(""));
				registrosSusFiltradosRegistroSivepComResultadoNegativo.stream()
						.forEach(r -> r.setFiltroAreaMunicipio(""));

				registrosSivepNaoUsados.add(registroSivepFiltrado);
			} else {
				fileWriter.write("Número final de registros do sus usados com resultado Positivo: "
						+ registrosSusFiltradosRegistroSivepComResultadoPositivo.size() + "\n");
				fileWriter.write("Número final de registros do sus usados com resultado Negativo: "
						+ registrosSusFiltradosRegistroSivepComResultadoNegativo.size() + "\n");

				registrosSusTotaisFiltradosComResultadoPositivo
						.addAll(registrosSusFiltradosRegistroSivepComResultadoPositivo);
				registrosSusTotaisFiltradosComResultadoNegativo
						.addAll(registrosSusFiltradosRegistroSivepComResultadoNegativo);
			}

			fileWriter.write("***************************\n");
		}

		fileWriter.flush();
		fileWriter.close();

		registrosSusTotaisFiltradosComResultadoPositivo.add(0,
				new SusRedomeCSV("id", "municipio", "filtroAreaMunicipio", "nomeCompleto", "cpf",
								 "dataNascimento", "municipioNotificacao", "racaCor", "etnia", "nomeMae", "dataNotificacao",
								 "idade", "resultadoTeste", "dataTeste", "tipoTeste", "estadoTeste", "evolucaoCaso",
								 "observacaoExclusao", "sexo", "observacaoUso", "sexoRedome", "etniaRedome", "semanaNotificacao"));
		SusRedomeCSVHandler2.criarCSV(csvResultadoPositivo, registrosSusTotaisFiltradosComResultadoPositivo);

		registrosSusTotaisFiltradosComResultadoNegativo.add(0,
				new SusRedomeCSV("campo1", "municipio", "filtroAreaMunicipio", "nomeCompleto", "cpf",
						         "dataNascimento", "municipioNotificacao", "racaCor", "etnia", "nomeMae", "dataNotificacao",
						         "idade", "resultadoTeste", "dataTeste", "tipoTeste", "estadoTeste", "evolucaoCaso",
						         "observacaoExclusao", "sexo", "observacaoUso", "sexoRedome", "etniaRedome", "semanaNotificacao"));

		SusRedomeCSVHandler2.criarCSV(csvResultadoNegativo, registrosSusTotaisFiltradosComResultadoNegativo);

		registrosSivepFiltrados.removeAll(registrosSivepNaoUsados);

		registrosSivepFiltrados.add(0,
				new SivepRedomeCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", "municipio",
								   "id", "sexo", "racaCor", "dataInternacao", "dataInternacaoRedome", "dataEncerramento",
								   "dataEncerramentoRedome", "evolucaoCaso", "evolucaoCasoRedome", "dataNotificacao",
								   "resultadoTeste", "sexoRedome", "etniaRedome", "semanaNotificacao"));

		SivepRedomeCSVHandler.criarCSV(csvSivepUsados, registrosSivepFiltrados);

		registrosSivepNaoUsados.add(0,
				new SivepRedomeCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", "municipio",
								   "id", "sexo", "racaCor", "dataInternacao", "dataInternacaoRedome", "dataEncerramento",
								   "dataEncerramentoRedome", "evolucaoCaso", "evolucaoCasoRedome", "dataNotificacao",
								   "resultadoTeste", "sexoRedome", "etniaRedome", "semanaNotificacao"));

		SivepRedomeCSVHandler.criarCSV(csvSivepNaoUsados, registrosSivepNaoUsados);
	}

	private List<SusRedomeCSV> obterRegistrosSusUsadosComResultadoNegativo(List<SusRedomeCSV> registrosSusFiltradosRegistroSivep, int qtd)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		List<SusRedomeCSV> registrosSusFiltradosComResultadoNegativo = filtrarRegistrosSusPorResultado(
				registrosSusFiltradosRegistroSivep, "Negativo");
		fileWriter.write("Filtrou " + registrosSusFiltradosComResultadoNegativo.size()
				+ " registros do sus com resultado Negativo\n");

		registrosSusFiltradosComResultadoNegativo.stream().limit(qtd)
				.forEach(r -> r.setObservacaoUso("Registro usado por " + situacao));

		List<SusRedomeCSV> registrosSusFiltradosComResultadoNegativoUsados = registrosSusFiltradosComResultadoNegativo
				.stream().filter(r -> r.getObservacaoUso() != null && !r.getObservacaoUso().equals(""))
				.collect(Collectors.toList());
		if (registrosSusFiltradosComResultadoNegativoUsados.size() > 0) {
			fileWriter.write("Foram usados " + registrosSusFiltradosComResultadoNegativoUsados.size()
					         + " registros do sus com resultado Negativo\n");
		}

		return registrosSusFiltradosComResultadoNegativoUsados;
	}

	private List<SusRedomeCSV> obterRegistrosSusUsadosComResultadoPositivo(List<SusRedomeCSV> registrosSusFiltradosRegistroSivep, int qtd) 
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		List<SusRedomeCSV> registrosSusFiltradosComResultadoPositivo = filtrarRegistrosSusPorResultado(
				registrosSusFiltradosRegistroSivep, "Positivo");
		fileWriter.write("Filtrou " + registrosSusFiltradosComResultadoPositivo.size()
				+ " registros do sus com resultado Positivo\n");

		registrosSusFiltradosComResultadoPositivo.stream().limit(qtd)
				.forEach(r -> r.setObservacaoUso("Registro usado por " + situacao));

		List<SusRedomeCSV> registrosSusFiltradosComResultadoPositivoUsados = registrosSusFiltradosComResultadoPositivo
				.stream().filter(r -> r.getObservacaoUso() != null && !r.getObservacaoUso().equals(""))
				.collect(Collectors.toList());

		if (registrosSusFiltradosComResultadoPositivoUsados.size() > 0) {
			fileWriter.write("Foram usados " + registrosSusFiltradosComResultadoPositivoUsados.size()
					+ " registros do sus com resultado Positivo\n");
		}

		return registrosSusFiltradosComResultadoPositivoUsados;
	}

}
