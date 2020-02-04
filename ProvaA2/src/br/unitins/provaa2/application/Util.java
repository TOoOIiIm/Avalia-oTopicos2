package br.unitins.provaa2.application;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {
	
	public static String encryptByApache(String value) {
		return DigestUtils.sha256Hex(value);
	}
	public static void redirect(String url) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			addMessageError("Erro ao redirecionar a página.");
			e.printStackTrace();
		}
	}
	public static void addMessageError(String message) {
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(message));
	}
	
	//public static boolean verifyCards(List<Cartao> card) {
	//	for (Cartao cartao : card) {
	//		if(cartao.getBandeira() == null) {
				
	//		}
	//	}
	//	return false;
	//}

}
