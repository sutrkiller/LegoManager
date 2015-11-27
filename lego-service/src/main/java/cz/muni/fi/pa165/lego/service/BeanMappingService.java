package cz.muni.fi.pa165.lego.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Mapping service between DAO and DTO objects
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Service
public interface BeanMappingService {

    /**
     * Map object by names of its fields.
     *
     * @param object object with filled data you want to map.
     * @param mapToClass class you want to get from mapping service.
     * @param <T> class you want to get from mapping service.
     * @return object with class T with filled data from object param.
     */
    public <T> T mapTo(Object object, Class<T> mapToClass);

    /**
     * Map Collection of objects by names of its fields.
     *
     * @param objects Collection of objects with filled data you want to map.
     * @param mapToClass Type of collection you want to get from mapping service.
     * @param <T> Type of collection you want to get from mapping service.
     * @return List of objects with class T with filled data from objects param.
     */
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
}
