package br.unitins.provaa2.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.provaa2.model.ListaDeCompras;

@FacesConverter(forClass = ListaDeCompras.class)
public class ListadeComprasConverter implements Converter<ListaDeCompras>{

	@Override
	public ListaDeCompras getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, ListaDeCompras value) {
		// TODO Auto-generated method stub
		if(value != null) {
			return value.toString();
		}
		return null;
	}

}
