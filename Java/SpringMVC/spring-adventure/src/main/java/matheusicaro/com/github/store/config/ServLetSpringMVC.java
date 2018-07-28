package matheusicaro.com.github.store.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import matheusicaro.com.github.store.infra.FileSaver;

public class ServLetSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {
				AppWebConfiguration.class,
				JPAConfiguration.class,
				FileSaver.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String [] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
	    CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
	    encodingFilter.setEncoding("UTF-8");

	    return new Filter[] {encodingFilter};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
	    registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
