package br.com.leandro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.leandro.model.Lancamento;
import br.com.leandro.repository.Lancamentos;

@FacesConverter(forClass = Lancamento.class)
public class LancamentoConverter implements Converter {

	@Inject
	private Lancamentos lancamentos;
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String val) {
		Lancamento ret = null;
		if (val != null && !"".equals(val)){
			ret = this.lancamentos.porId(new Long(val));
		}
		return ret;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
		if (obj != null) {
			Lancamento l = (Lancamento) obj;
			return l.getId() == null ? null : l.getId().toString();
		}
		return null;
	}

}
