package com.dev.mercadolivre.listener;

import com.dev.mercadolivre.model.EmailEventModel;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EmailListenerEvent {

    Logger logger = Logger.getLogger(EmailListenerEvent.class.getName());

    @EventListener
    public void sendEamil(EmailEventModel eventEmailEvent) {
        logger.info("Enviando email"+eventEmailEvent.getEmail()+" com mensagem: "+eventEmailEvent.getMsg());
    }

}
