package modelo;

import static app.util.StringUtil.normalizarString;

public class RegioesAdministrativas {
	
	private static final String[] BAIA_ILHA_GRANDE = {"Angra dos Reis", "Mangaratiba", "Paraty", "Parati"};
	
	private static final String[] BAIXADA_LITORANEA = {"Araruama", "Armação dos Búzios", "Arraial do Cabo", "Cabo Frio", 
													   "Casimiro de Abreu", "Iguaba Grande", "Rio das Ostras", 
													   "São Pedro da Aldeia", "Saquarema"};
	
	private static final String[] CENTRO_SUl = {"Areal", "Comendador Levy Gasparian", "Engenheiro Paulo de Frontin", 
												"Mendes", "Miguel Pereira", "Paracambi", "Paraíba do Sul",
												"Paty do Alferes", "Sapucaia", "Três Rios", "Vassouras"};
	
	private static final String[] MEDIO_PARAIBA = {"Barra do Piraí", "Barra Mansa", "Itatiaia",
												   "Pinheiral", "Piraí", "Porto Real", "Quatis",
												   "Resende", "Rio Claro", "Rio das Flores",
												   "Valença", "Volta Redonda"};
	
	private static final String[] METROPOLITANA1 = {"Belford Roxo", "Duque de Caxias", "Itaguaí",
													"Japeri", "Magé", "Mesquita", "Nilópolis", 
													"Nova Iguaçu", "Queimados", "Rio de Janeiro", 
													"São João de Meriti", "Seropédica"};
	
	private static final String[] METROPOLITANA2 = {"Itaboraí", "Maricá", "Niterói", 
													"Rio Bonito", "São Gonçalo", "Silva Jardim", 
													"Tanguá"};
	
	private static final String[] NOROESTE = {"Aperibé", "Bom Jesus de Itabapoana", "Bom Jesus do Itabapoana", "Cambuci", 
											  "Cardoso Moreira", "Italva", "Itaocara",
											  "Itaperuna", "Laje do Muriaé", "Miracema",
											  "Natividade", "Porciúncula", "Santo Antônio de Pádua", 
											  "São José de Ubá", "Varre-Sai"};
	
	private static final String[] NORTE = {"Campos dos Goytacazes", "Carapebus", "Conceição de Macabu", 
								           "Macaé", "Quissamã", "São Fidélis", 
								           "São Francisco de Itabapoana", "São João da Barra"};
	
	private static final String[] SERRANA = {"Bom Jardim", "Cachoeiras de Macacu", "Cantagalo", 
											 "Carmo", "Cordeiro", "Duas Barras",
											 "Guapimirim", "Macuco", "Nova Friburgo",
											 "Petrópolis", "Santa Maria Madalena", "São José do Vale do Rio Preto", 
											 "São Sebastião do Alto", "Sumidouro", "Teresópolis", 
											 "Trajano de Moraes", "Trajano de Morais"};
	
	public static String obterNomeRegiaoMunicipio(String municipio) {
		String municipioNormalizado = normalizarString(municipio);
				
	    for (String municipioRegiao : BAIA_ILHA_GRANDE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Baía da Ilha Grande";
	        }
	    }
	    
	    for (String municipioRegiao : BAIXADA_LITORANEA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Baixada Litorânea";
	        }
	    }
	    
	    for (String municipioRegiao : CENTRO_SUl) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Centro-Sul";
	        }
	    }
	    
	    for (String municipioRegiao : MEDIO_PARAIBA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Médio Paraíba";
	        }
	    }
	    
	    for (String municipioRegiao : METROPOLITANA1) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Metropolitana I";
	        }
	    }
	    
	    for (String municipioRegiao : METROPOLITANA2) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Metropolitana II";
	        }
	    }
	    
	    for (String municipioRegiao : NOROESTE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Noroeste";
	        }
	    }
	    
	    for (String municipioRegiao : NORTE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Norte";
	        }
	    }
	    
	    for (String municipioRegiao : SERRANA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Serrana";
	        }
	    }
	    
	    return null;
	}
	
	public static String[] obterRegiaoMunicipio(String municipio) {
		String municipioNormalizado = normalizarString(municipio);
				
	    for (String municipioRegiao : BAIA_ILHA_GRANDE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return BAIA_ILHA_GRANDE;
	        }
	    }
	    
	    for (String municipioRegiao : BAIXADA_LITORANEA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return BAIXADA_LITORANEA;
	        }
	    }
	    
	    for (String municipioRegiao : CENTRO_SUl) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return CENTRO_SUl;
	        }
	    }
	    
	    for (String municipioRegiao : MEDIO_PARAIBA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return MEDIO_PARAIBA;
	        }
	    }
	    
	    for (String municipioRegiao : METROPOLITANA1) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return METROPOLITANA1;
	        }
	    }
	    
	    for (String municipioRegiao : METROPOLITANA2) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return METROPOLITANA2;
	        }
	    }
	    
	    for (String municipioRegiao : NOROESTE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return NOROESTE;
	        }
	    }
	    
	    for (String municipioRegiao : NORTE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return NORTE;
	        }
	    }
	    
	    for (String municipioRegiao : SERRANA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return SERRANA;
	        }
	    }
	    
	    return null;
	}

}
