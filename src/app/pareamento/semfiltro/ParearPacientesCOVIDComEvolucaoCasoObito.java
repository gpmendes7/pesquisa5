package app.pareamento.semfiltro;

public class ParearPacientesCOVIDComEvolucaoCasoObito {

	public static void main(String[] args) throws Exception {
		PareamentoSemFiltro pareamento = new PareamentoSemFiltro("Obito");

		pareamento.carregarArquivosCSV("./arquivos/csv/obito/SIVEP_REDOME4(OBITO).csv",
				                       "./arquivos/csv/Sus_REDOME3(Ajustado).csv");

		pareamento.parearPacientesEntreSivepESus(31, 
				                                 50,
												 "./arquivos/txt/obito/RegistrosUsados(PacientesObitoEntre31E50Anos).txt",
												 "./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre31E50AnosResultadoPositivo).csv",
												 "./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre31E50AnosResultadoNegativo).csv",
												 "./arquivos/csv/obito/SIVEP_REDOME(OBITO-PacientesUsadosEntre31E50Anos).csv",
												 "./arquivos/csv/obito/SIVEP_REDOME(OBITO-PacientesNaoUsadosEntre31E50Anos).csv");

		pareamento.parearPacientesEntreSivepESus(51, 
				                                 59,
												 "./arquivos/txt/obito/RegistrosUsados(PacientesObitoEntre51E59Anos).txt",
												 "./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre51E59AnosResultadoPositivo).csv",
												 "./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre51E59AnosResultadoNegativo).csv",
												 "./arquivos/csv/obito/SIVEP_REDOME(OBITO-PacientesUsadosEntre51E59Anos).csv",
												 "./arquivos/csv/obito/SIVEP_REDOME(OBITO-PacientesNaoUsadosEntre51E59Anos).csv");

		pareamento.parearPacientesEntreSivepESus(60, 
				                                 75,
												 "./arquivos/txt/obito/RegistrosUsados(PacientesObitoEntre60E75Anos).txt",
												 "./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre60E75AnosResultadoPositivo).csv",
												 "./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre60E75AnosResultadoNegativo).csv",
												 "./arquivos/csv/obito/SIVEP_REDOME(OBITO-PacientesUsadosEntre60E75Anos).csv",
												 "./arquivos/csv/obito/SIVEP_REDOME(OBITO-PacientesNaoUsadosEntre60E75Anos).csv");

		pareamento.criarArquivosCSVSusAtualizado("./arquivos/csv/Sus_REDOME(AposUsoObito).csv");
	}

}
