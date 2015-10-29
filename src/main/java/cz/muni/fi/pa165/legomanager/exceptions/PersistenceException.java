/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.exceptions;

/**
 *
 * @author Tobias
 */
public class PersistenceException extends Exception {
    public PersistenceException(String msg){
        super(msg);
    }
    
    public PersistenceException(String msg, Throwable e) {
        super(msg,e);
    }
    
    
    
}
