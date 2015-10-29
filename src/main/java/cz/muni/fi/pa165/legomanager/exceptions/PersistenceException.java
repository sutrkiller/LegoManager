package cz.muni.fi.pa165.legomanager.exceptions;

/**
 *PersistenceException for database access
 * 
 * @author Tobias <tobias.kamenicky@gmail.com>
 * @date 2015/10/29
 */
public class PersistenceException extends Exception {
    public PersistenceException(String msg){
        super(msg);
    }
    
    public PersistenceException(String msg, Throwable e) {
        super(msg,e);
    }      
}
