package pl.akademiakodu.kwejkapp.service;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by itml on 24.06.2017.
 */
public class MemNotFoundException extends Exception {

    public MemNotFoundException(){
        super("Mem not found");
    }
}
