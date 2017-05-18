package br.com.leandro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.leandro.model.Pessoa;
import br.com.leandro.repository.Pessoas;

@FacesConverter(forClass=Pessoa.class)
public class PessoaConverter implements Converter {

	@Inject
	private Pessoas pessoas;
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
		Pessoa ret = null;
		
		if (value != null) {
			ret = this.pessoas.porId(new Long(value));
		}
		return ret;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object value) {
		if (value != null){
			return (((Pessoa)value).getId().toString());
		}
		return null;
	}

}
