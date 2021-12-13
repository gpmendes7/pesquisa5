package app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversaoSusSivep {
	
	public static String converterSexoSivepParaSus(String sexoSivep) {
		if(sexoSivep.equals("F")) {
			return "Feminino";
		} else {
			return "Masculino";
		}
	}
	
	public static String converterDataNotificacaoSusParaSivep(String dataNotificacaoSivep) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		
	    Date dataNotificacao = sdf1.parse(dataNotificacaoSivep);
			
	    return sdf2.format(dataNotificacao);
	}

}
