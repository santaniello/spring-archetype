package br.com.springarchetype.resolvers;

import java.util.Locale;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

public class XmlViewResolver implements ViewResolver {

	private Jaxb2Marshaller marshaller;

	public XmlViewResolver(Jaxb2Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MarshallingView view = new MarshallingView();
		view.setMarshaller(marshaller);
		return view;
	}

}
