package cz.muni.fi.pa165.lego.service;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Mapping service between DAO and DTO objects
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public interface BeanMappingService {
	
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
}
