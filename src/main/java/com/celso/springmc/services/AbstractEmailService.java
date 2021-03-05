package com.celso.springmc.services;

import com.celso.springmc.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService{
    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmEMail(Pedido obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEMail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(obj.getCliente().getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Pedido Confirmado!\n Código: "+obj.getId());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(obj.toString());

        return simpleMailMessage;
    }
}
