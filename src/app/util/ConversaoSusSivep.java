package app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static app.util.StringUtil.normalizarString;

public class ConversaoSusSivep {
	
	public static String normalizarSexo(String sexo) {
		if(normalizarString(sexo).equals("F")) {
			return "Feminino";
		} else if(normalizarString(sexo).equals("M")) {
			return "Masculino";
		} else {
			return null;
		}
	}
	
	public static String converterDataNotificacaoSusParaSivep(String dataNotificacaoSivep) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		
	    Date dataNotificacao = sdf1.parse(dataNotificacaoSivep);
			
	    return sdf2.format(dataNotificacao);
	}

}
