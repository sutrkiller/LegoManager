package cz.muni.fi.pa165.lego.service.config;

import cz.muni.fi.pa165.lego.service.PieceServiceImpl;
import cz.muni.fi.pa165.lego.service.facade.ModelFacadeImpl;
import cz.muni.fi.pa165.legomanager.PersistenceApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses={PieceServiceImpl.class, ModelFacadeImpl.class})
public class ServiceConfiguration {
	

	@Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();		
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}
	
	/**
	 * Custom config for Dozer if needed
	 */
	public class DozerCustomConfig extends BeanMappingBuilder {
	    @Override
	    protected void configure() {
	        //mapping(Category.class, CategoryDTO.class);
	    }
	}
	
}

