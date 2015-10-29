package cz.muni.fi.pa165.legomanager.exceptions;

/**
 *Exception for database access when entity not found in DB.
 * 
 * @author Tobias <tobias.kamenicky@gmail.com>
 * @date 2015/10/29
 */
public class EntityNotExistsException extends LegoPersistenceException {

    public EntityNotExistsException(String msg) {
        super(msg);
    }
    
    public EntityNotExistsException(String msg, Throwable e) {
        super(msg,e);
    }
    
}
