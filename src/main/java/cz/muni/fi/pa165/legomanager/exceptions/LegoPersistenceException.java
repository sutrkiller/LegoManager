package cz.muni.fi.pa165.legomanager.exceptions;

/**
 *LegoPersistenceException for database access
 * 
 * @author Tobias <tobias.kamenicky@gmail.com>
 * @date 2015/10/29
 */
public class LegoPersistenceException extends Exception {
    public LegoPersistenceException(String msg){
        super(msg);
    }
    
    public LegoPersistenceException(String msg, Throwable e) {
        super(msg,e);
    }      
}
