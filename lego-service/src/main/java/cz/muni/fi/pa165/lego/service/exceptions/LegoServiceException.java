package cz.muni.fi.pa165.lego.service.exceptions;

/**
 * LegoServiceException for service
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public class LegoServiceException extends RuntimeException {

    public LegoServiceException(String msg){
        super(msg);
    }
    
    public LegoServiceException(String msg, Throwable e) {
        super(msg,e);
    }      
}
