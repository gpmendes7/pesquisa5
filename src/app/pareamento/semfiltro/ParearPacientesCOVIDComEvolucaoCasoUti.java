package app.pareamento.semfiltro;

public class ParearPacientesCOVIDComEvolucaoCasoUti {
	
	public static void main(String[] args) throws Exception {
		PareamentoSemFiltro pareamento = new PareamentoSemFiltro("Uti");
		
		pareamento.carregarArquivosCSV("./arquivos/csv/uti/SIVEP_REDOME4(UTI).csv", 
									   "./arquivos/csv/Sus_REDOME(AposUsoObito).csv");
		
		pareamento.parearPacientesEntreSivepESus(26, 
						                         42, 
					                             "./arquivos/txt/uti/RegistrosUsados(PacientesUtiEntre26E42Anos).txt",
					                             "./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre26E42AnosResultadoPositivo).csv", 
					                             "./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre26E42AnosResultadoNegativo).csv",
					                             "./arquivos/csv/uti/SIVEP_REDOME(UTI-PacientesUsadosEntre26E42Anos).csv",
					                             "./arquivos/csv/uti/SIVEP_REDOME(UTI-PacientesNaoUsadosEntre26E42Anos).csv");
		
		pareamento.parearPacientesEntreSivepESus(43, 
								                 53, 
								                 "./arquivos/txt/uti/RegistrosUsados(PacientesUtiEntre43E53Anos).txt",
								                 "./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre43E53AnosResultadoPositivo).csv", 
								                 "./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre43E53AnosResultadoNegativo).csv",
								                 "./arquivos/csv/uti/SIVEP_REDOME(UTI-PacientesUsadosEntre43E53Anos).csv",
								                 "./arquivos/csv/uti/SIVEP_REDOME(UTI-PacientesNaoUsadosEntre43E53Anos).csv");
		
		pareamento.parearPacientesEntreSivepESus(54, 
								                 67, 
								                 "./arquivos/txt/uti/RegistrosUsados(PacientesUtiEntre54E67Anos).txt",
								                 "./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre54E67AnosResultadoPositivo).csv", 
								                 "./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre54E67AnosResultadoNegativo).csv",
								                 "./arquivos/csv/uti/SIVEP_REDOME(UTI-PacientesUsadosEntre54E67Anos).csv",
								                 "./arquivos/csv/uti/SIVEP_REDOME(UTI-PacientesNaoUsadosEntre54E67Anos).csv");
		
		pareamento.criarArquivosCSVSusAtualizado("./arquivos/csv/Sus_REDOME(AposUsoUTI).csv");				
	}

}
