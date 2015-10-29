package cz.muni.fi.pa165.legomanager.exceptions;

/**
 * Exception for database access when entity already exists in DB.
 * 
 * @author Tobias <tobias.kamenicky@gmail.com>
 * @date 2015/10/29
 */
public class EntityAlreadyExistsException extends PersistenceException {

    public EntityAlreadyExistsException(String msg) {
        super(msg);
    }
    public EntityAlreadyExistsException(String msg, Throwable t) {
        super(msg,t);
    }
    
}
