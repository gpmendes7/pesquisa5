package app.pareamento.semfiltro;

public class ParearPacientesCOVIDComEvolucaoCasoInternado {
	
	public static void main(String[] args) throws Exception {
		PareamentoSemFiltro pareamento = new PareamentoSemFiltro("Internado");
		
		pareamento.carregarArquivosCSV("./arquivos/csv/internado/SIVEP_REDOME4(INTERNADO).csv", 
									   "./arquivos/csv/Sus_REDOME(AposUsoUTI).csv");
		
		pareamento.parearPacientesEntreSivepESus(25, 
						                         44, 
					                             "./arquivos/txt/internado/RegistrosUsados(PacientesInternadoEntre25E44Anos).txt",
					                             "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre25E44AnosResultadoPositivo).csv", 
					                             "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre25E44AnosResultadoNegativo).csv",
					                             "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesUsadosEntre25E44Anos).csv",
					                             "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesNaoUsadosEntre25E44Anos).csv");
		
		pareamento.parearPacientesEntreSivepESus(45, 
								                 56, 
								                 "./arquivos/txt/internado/RegistrosUsados(PacientesInternadoEntre45E56Anos).txt",
								                 "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre45E56AnosResultadoPositivo).csv", 
								                 "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre45E56AnosResultadoNegativo).csv",
								                 "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesUsadosEntre45E56Anos).csv",
								                 "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesNaoUsadosEntre45E56Anos).csv");
		
		pareamento.parearPacientesEntreSivepESus(57, 
								                 81, 
								                 "./arquivos/txt/internado/RegistrosUsados(PacientesInternadoEntre57E81Anos).txt",
								                 "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre57E81AnosResultadoPositivo).csv", 
								                 "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre57E81AnosResultadoNegativo).csv",
								                 "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesUsadosEntre57E81Anos).csv",
								                 "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesNaoUsadosEntre57E81Anos).csv");		
		
		pareamento.criarArquivosCSVSusAtualizado("./arquivos/csv/Sus_REDOME(AposUsoInternado).csv");		
	}

}
