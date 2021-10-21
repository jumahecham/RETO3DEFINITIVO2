/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RETO3DEFINITIVO2.RETO3DEFINITIVO2;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosMensaje {
    @Autowired
    private RepositorioMensaje metodosCrud;

    public List<Mensaje> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Mensaje> getMessage(int messageId) {
        return metodosCrud.getMessage(messageId);
    }

    public Mensaje save(Mensaje messageText){
        if(messageText.getIdMessage()==null){
            return metodosCrud.save(messageText);
        }else{
            Optional<Mensaje> e= metodosCrud.getMessage(messageText.getIdMessage());
            if(e.isEmpty()){
                return metodosCrud.save(messageText);
            }else{
                return messageText;
            }
        }
    }

    public Mensaje update(Mensaje messageText){
        if(messageText.getIdMessage()!=null){
            Optional<Mensaje> e= metodosCrud.getMessage(messageText.getIdMessage());
            if(!e.isEmpty()){
                if(messageText.getMessageText()!=null){
                    e.get().setMessageText(messageText.getMessageText());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return messageText;
            }
        }else{
            return messageText;
        }
    }

    public boolean deleteMessage(int messageId) {
        Boolean aBoolean = getMessage(messageId).map(messageText -> {
            metodosCrud.delete(messageText);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
