package br.com.jway.claudio.util;

import java.util.List;

import br.com.jway.claudio.dao.EscrituracoesOrigemDao;
import br.com.jway.claudio.dao.GuiasDao;
import br.com.jway.claudio.model.Guias;

public class MainGuias {

	public static void main(String args[]) {
		GuiasDao dao = new GuiasDao();
		Util util = new Util();
		EscrituracoesOrigemDao escrituracoesDao = new EscrituracoesOrigemDao();

		for (Guias guia : dao.findAll()) {
			if (util.isEmptyOrNull(guia.getIdNotasFiscais())) {
				continue;
			}
			String[] ids = guia.getIdNotasFiscais().split(",");

			for (int i = 0; i < ids.length; i++) {
				
			}
		}
	}

}
