package app.pareamento;

import static app.util.ConversaoSusSivep.converterSexoSivepParaSus;
import static app.util.DataUtil.alterarDiasEmData;
import static app.util.DataUtil.dataEstaEmIntervalo;
import static app.util.StringUtil.normalizarString;
import static modelo.RegioesAdministrativas.obterNomeRegiaoMunicipio;
import static modelo.RegioesAdministrativas.obterRegiaoMunicipio;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import app.util.StringUtil;
import csv.SivepRedomeCSV;
import csv.SusRedomeCSV;

public class FiltrosPareamento {
	
	public static List<SivepRedomeCSV> filtrarRegistrosSivepPorFaixaEtaria(List<SivepRedomeCSV> registros, int idadeMinima, int idadeMaxima) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> Integer.parseInt(r.getIdade()) >= idadeMinima && Integer.parseInt(r.getIdade()) <= idadeMaxima)
						 .collect(Collectors.toList());
	}
	
	public static List<SivepRedomeCSV> filtrarRegistrosSivepPorResultadoPositivo(List<SivepRedomeCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> StringUtil.normalizarString(r.getResultadoTeste()).equals(StringUtil.normalizarString("Positivo")))
						 .collect(Collectors.toList());
	}
	
	public static List<SusRedomeCSV> filtrarRegistrosSusNaoUsados(List<SusRedomeCSV> registrosSus) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		return registrosSus.stream()
							.filter(r -> r.getObservacaoUso() == null || r.getObservacaoUso().equals(""))
							.collect(Collectors.toList());
	}
	
	public static List<SusRedomeCSV> filtrarRegistrosSusPorFaixaEtaria(List<SusRedomeCSV> registrosSus, int idadeMinima, int idadeMaxima) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		return registrosSus.stream()
						   .filter(r -> Integer.parseInt(r.getIdade()) >= idadeMinima && Integer.parseInt(r.getIdade()) <= idadeMaxima)
						   .collect(Collectors.toList());
	}
	
	public static List<SusRedomeCSV> filtrarRegistrosSusPorSexo(List<SusRedomeCSV> registrosSus, SivepRedomeCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		String sexoSus = converterSexoSivepParaSus(registroSivepFiltrado.getSexo());
		
		return registrosSus.stream()
						   .filter(r -> StringUtil.normalizarString(r.getSexo()).equals(StringUtil.normalizarString(sexoSus)))
						   .collect(Collectors.toList());		
	}
	
	public static List<SusRedomeCSV> filtrarRegistrosSusPorDataNotificacao(List<SusRedomeCSV> registrosSus, SivepRedomeCSV registroSivepFiltrado, int numeroSemanas) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNotificacaoSivep = sdf1.parse(registroSivepFiltrado.getDataNotificacao());
		Date data1 = alterarDiasEmData(dataNotificacaoSivep, numeroSemanas * -7);
		Date data2 = alterarDiasEmData(dataNotificacaoSivep, numeroSemanas * 7);

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		
		List<SusRedomeCSV> registrosSusFiltradosPorDataNotificacao = new ArrayList<SusRedomeCSV>();
		
		for (SusRedomeCSV registro : registrosSus) {
			Date dataNotificacaoSus = sdf2.parse(registro.getDataNotificacao());
			if(dataEstaEmIntervalo(data1, data2, dataNotificacaoSus)) {
				registrosSusFiltradosPorDataNotificacao.add(registro);
			}
		}
		
		return registrosSusFiltradosPorDataNotificacao;
	}
	
	
	public static List<SusRedomeCSV> filtrarRegistrosSusPorMunicipio(List<SusRedomeCSV> registrosSus, SivepRedomeCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		List<SusRedomeCSV> registrosSusFiltradosPorMunicipio = registrosSus.stream()
						   .filter(r -> StringUtil.normalizarString(r.getMunicipio()).equals(StringUtil.normalizarString(registroSivepFiltrado.getMunicipio())))
						   .collect(Collectors.toList());
		
		registrosSusFiltradosPorMunicipio.stream().forEach(r -> r.setFiltroAreaMunicipio(r.getMunicipio()));
		
		return registrosSusFiltradosPorMunicipio;
	}
	
	public static List<SusRedomeCSV> filtrarRegistrosSusPorAreaMunicipio(List<SusRedomeCSV> registrosSus, SivepRedomeCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		List<SusRedomeCSV> registrosSusFiltradosPorAreaMunicipio = new ArrayList<SusRedomeCSV>();
		
		String[] regiao = obterRegiaoMunicipio(registroSivepFiltrado.getMunicipio());
		
		if(regiao != null) {
			for (SusRedomeCSV registroSus : registrosSus) {
				String municipioRegistroNormalizado = normalizarString(registroSus.getMunicipio());
				
				for (String municipioRegiao : regiao) {
					String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
							
					if(municipioRegistroNormalizado.equals(municipioRegiaoNormalizado)) {
						registrosSusFiltradosPorAreaMunicipio.add(registroSus);
						break;
					}
				}
				
			}
			
			registrosSusFiltradosPorAreaMunicipio.stream().forEach(r -> r.setFiltroAreaMunicipio(obterNomeRegiaoMunicipio(r.getMunicipio())));
		}

		return registrosSusFiltradosPorAreaMunicipio;
	}
	
	public static List<SusRedomeCSV> filtrarRegistrosSusPorRacaCor(List<SusRedomeCSV> registrosSus, SivepRedomeCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		return registrosSus.stream()
						   .filter(r -> StringUtil.normalizarString(r.getEtniaRedome()).equals(StringUtil.normalizarString(registroSivepFiltrado.getEtniaRedome())))
						   .collect(Collectors.toList());
	}
	
	public static List<SusRedomeCSV> filtrarRegistrosSusPorTipoTesteComCovid(List<SusRedomeCSV> registrosSus) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		List<SusRedomeCSV> registrosSusFiltradosSusPorRTPCR = registrosSus.stream()
																				    .filter(r -> StringUtil.normalizarString(r.getTipoTeste()).equals(StringUtil.normalizarString("RT-PCR")))
																				    .collect(Collectors.toList());
				
		List<SusRedomeCSV> registrosSusFiltradosSusPorAntigeno = registrosSus.stream()
																					  .filter(r -> StringUtil.normalizarString(r.getTipoTeste()).equals(StringUtil.normalizarString("TESTE RÁPIDO - ANTÍGENO")))
																					  .collect(Collectors.toList());
		
		List<SusRedomeCSV> registrosSusFiltradosSusPorEnzimaElisaIgm = registrosSus.stream()
																					   .filter(r -> StringUtil.normalizarString(r.getTipoTeste()).equals(StringUtil.normalizarString("Enzimaimunoensaio - ELISA IgM")))
																					   .collect(Collectors.toList());
		
		List<SusRedomeCSV> registrosSusOutros = new ArrayList<SusRedomeCSV>(registrosSus);
		registrosSusOutros.removeAll(registrosSusFiltradosSusPorRTPCR);
		registrosSusOutros.removeAll(registrosSusFiltradosSusPorAntigeno);
		registrosSusOutros.removeAll(registrosSusFiltradosSusPorEnzimaElisaIgm);
		
		List<SusRedomeCSV> registrosSusFitradosPorTipoTesteComCovid = new ArrayList<SusRedomeCSV>();
		registrosSusFitradosPorTipoTesteComCovid.addAll(registrosSusFiltradosSusPorRTPCR);
		registrosSusFitradosPorTipoTesteComCovid.addAll(registrosSusFiltradosSusPorAntigeno);
		registrosSusFitradosPorTipoTesteComCovid.addAll(registrosSusFiltradosSusPorEnzimaElisaIgm);
		registrosSusFitradosPorTipoTesteComCovid.addAll(registrosSusOutros);
		
		return registrosSusFitradosPorTipoTesteComCovid;
	}
	
	public static List<SusRedomeCSV> filtrarRegistrosSusPorResultado(List<SusRedomeCSV> registrosSus, String resultadoTeste) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registrosSus.stream()
					       .filter(r -> StringUtil.normalizarString(r.getResultadoTeste()).equals(StringUtil.normalizarString(resultadoTeste)))
					       .collect(Collectors.toList());
	}
	
}
