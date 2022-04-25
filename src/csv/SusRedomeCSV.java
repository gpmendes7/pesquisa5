package csv;

public class SusRedomeCSV {
	
	private String id;
	private String municipio;
	private String filtroAreaMunicipio;
	private String nomeCompleto;
	private String cpf;
	private String dataNascimento;
	private String municipioNotificacao;
	private String racaCor;
	private String etnia;
	private String nomeMae;
	private String dataNotificacao;
	private String idade;
	private String resultadoTeste;
	private String dataTeste;
	private String tipoTeste;
	private String estadoTeste;
	private String evolucaoCaso;
	private String observacaoExclusao;
	private String sexo;
	private String observacaoUso;
	private String sexoRedome;
	private String etniaRedome;
	private String semanaNotificacao;
	
	public SusRedomeCSV() {
	
	}
	
	public SusRedomeCSV(String id, String municipio, String nomeCompleto, String cpf,
			String dataNascimento, String municipioNotificacao, String racaCor, String etnia, String nomeMae,
			String dataNotificacao, String idade, String resultadoTeste, String dataTeste, String tipoTeste,
			String estadoTeste, String evolucaoCaso, String observacaoExclusao, String sexo, String observacaoUso, String sexoRedome,
			String etniaRedome) {
		this.id = id;
		this.municipio = municipio;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.municipioNotificacao = municipioNotificacao;
		this.racaCor = racaCor;
		this.etnia = etnia;
		this.nomeMae = nomeMae;
		this.dataNotificacao = dataNotificacao;
		this.idade = idade;
		this.resultadoTeste = resultadoTeste;
		this.dataTeste = dataTeste;
		this.tipoTeste = tipoTeste;
		this.estadoTeste = estadoTeste;
		this.evolucaoCaso = evolucaoCaso;
		this.observacaoExclusao = observacaoExclusao;
		this.sexo = sexo;
		this.observacaoUso = observacaoUso;
		this.sexoRedome = sexoRedome;
		this.etniaRedome = etniaRedome;
	}
	
	public SusRedomeCSV(String id, String municipio, String nomeCompleto, String cpf,
			String dataNascimento, String municipioNotificacao, String racaCor, String etnia, String nomeMae,
			String dataNotificacao, String idade, String resultadoTeste, String dataTeste, String tipoTeste,
			String estadoTeste, String evolucaoCaso, String observacaoExclusao, String sexo, String observacaoUso, String sexoRedome,
			String etniaRedome, String semanaNotificacao) {
		this.id = id;
		this.municipio = municipio;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.municipioNotificacao = municipioNotificacao;
		this.racaCor = racaCor;
		this.etnia = etnia;
		this.nomeMae = nomeMae;
		this.dataNotificacao = dataNotificacao;
		this.idade = idade;
		this.resultadoTeste = resultadoTeste;
		this.dataTeste = dataTeste;
		this.tipoTeste = tipoTeste;
		this.estadoTeste = estadoTeste;
		this.evolucaoCaso = evolucaoCaso;
		this.observacaoExclusao = observacaoExclusao;
		this.sexo = sexo;
		this.observacaoUso = observacaoUso;
		this.sexoRedome = sexoRedome;
		this.etniaRedome = etniaRedome;
		this.semanaNotificacao = semanaNotificacao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	public String getFiltroAreaMunicipio() {
		return filtroAreaMunicipio;
	}

	public void setFiltroAreaMunicipio(String filtroAreaMunicipio) {
		this.filtroAreaMunicipio = filtroAreaMunicipio;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getMunicipioNotificacao() {
		return municipioNotificacao;
	}

	public void setMunicipioNotificacao(String municipioNotificacao) {
		this.municipioNotificacao = municipioNotificacao;
	}

	public String getRacaCor() {
		return racaCor;
	}

	public void setRacaCor(String racaCor) {
		this.racaCor = racaCor;
	}

	public String getEtnia() {
		return etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getDataNotificacao() {
		return dataNotificacao;
	}

	public void setDataNotificacao(String dataNotificacao) {
		this.dataNotificacao = dataNotificacao;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getResultadoTeste() {
		return resultadoTeste;
	}

	public void setResultadoTeste(String resultadoTeste) {
		this.resultadoTeste = resultadoTeste;
	}

	public String getDataTeste() {
		return dataTeste;
	}

	public void setDataTeste(String dataTeste) {
		this.dataTeste = dataTeste;
	}

	public String getTipoTeste() {
		return tipoTeste;
	}

	public void setTipoTeste(String tipoTeste) {
		this.tipoTeste = tipoTeste;
	}

	public String getEstadoTeste() {
		return estadoTeste;
	}

	public void setEstadoTeste(String estadoTeste) {
		this.estadoTeste = estadoTeste;
	}

	public String getEvolucaoCaso() {
		return evolucaoCaso;
	}

	public void setEvolucaoCaso(String evolucaoCaso) {
		this.evolucaoCaso = evolucaoCaso;
	}

	public String getObservacaoExclusao() {
		return observacaoExclusao;
	}

	public void setObservacaoExclusao(String observacaoExclusao) {
		this.observacaoExclusao = observacaoExclusao;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getObservacaoUso() {
		return observacaoUso;
	}

	public void setObservacaoUso(String observacaoUso) {
		this.observacaoUso = observacaoUso;
	}

	public String getSexoRedome() {
		return sexoRedome;
	}

	public void setSexoRedome(String sexoRedome) {
		this.sexoRedome = sexoRedome;
	}

	public String getEtniaRedome() {
		return etniaRedome;
	}

	public void setEtniaRedome(String etniaRedome) {
		this.etniaRedome = etniaRedome;
	}
	
	public String getSemanaNotificacao() {
		return semanaNotificacao;
	}
	
	public void setSemanaNotificacao(String semanaNotificacao) {
		this.semanaNotificacao = semanaNotificacao;
	}
	
}
