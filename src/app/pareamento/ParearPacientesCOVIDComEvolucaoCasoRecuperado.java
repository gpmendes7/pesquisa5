package app.pareamento;

public class ParearPacientesCOVIDComEvolucaoCasoRecuperado {
	
	public static void main(String[] args) throws Exception {
		Pareamento pareamento = new Pareamento("Recuperado");
			
		pareamento.carregarArquivosCSV("./arquivos/csv/recuperado/SIVEP_REDOME2(RECUPERADO).csv", 
									   "./arquivos/csv/Sus_REDOME(AposUsoInternado).csv");
		
		pareamento.parearPacientesEntreSivepESus(22, 
							                     42, 
						                         "./arquivos/txt/recuperado/RegistrosUsados(PacientesRecuperadoEntre22E42Anos).txt",
						                         "./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre22E42AnosResultadoPositivo).csv", 
						                         "./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre22E42AnosResultadoNegativo).csv",
						                         "./arquivos/csv/recuperado/SIVEP_REDOME(RECUPERADO-PacientesUsadosEntre22E42Anos).csv",
						                         "./arquivos/csv/recuperado/SIVEP_REDOME(RECUPERADO-PacientesNaoUsadosEntre22E42Anos).csv");
					
		pareamento.parearPacientesEntreSivepESus(43,
								                 53, 
								                 "./arquivos/txt/recuperado/RegistrosUsados(PacientesRecuperadoEntre43E53Anos).txt",
								                 "./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre43E53AnosResultadoPositivo).csv", 
								                 "./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre43E53AnosResultadoNegativo).csv",
								                 "./arquivos/csv/recuperado/SIVEP_REDOME(RECUPERADO-PacientesUsadosEntre43E53Anos).csv",
								                 "./arquivos/csv/recuperado/SIVEP_REDOME(RECUPERADO-PacientesNaoUsadosEntre43E53Anos).csv");
					
		pareamento.parearPacientesEntreSivepESus(54,
							                     74, 
							                     "./arquivos/txt/recuperado/RegistrosUsados(PacientesRecuperadoEntre54E74Anos).txt",
							                     "./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre54E74AnosResultadoPositivo).csv", 
							                     "./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre54E74AnosResultadoNegativo).csv",
							                     "./arquivos/csv/recuperado/SIVEP_REDOME(RECUPERADO-PacientesUsadosEntre54E74Anos).csv",
							                     "./arquivos/csv/recuperado/SIVEP_REDOME(RECUPERADO-PacientesNaoUsadosEntre54E74Anos).csv");
		
		pareamento.criarArquivosCSVSusAtualizado("./arquivos/csv/Sus_REDOME(AposUsoRecuperado).csv");
	}

}
