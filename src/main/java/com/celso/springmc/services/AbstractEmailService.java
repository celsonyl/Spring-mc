package com.celso.springmc.services;

import com.celso.springmc.domain.Cliente;
import com.celso.springmc.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {
    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmEMail(Pedido obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEMail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(obj.getCliente().getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Pedido Confirmado!\n Código: " + obj.getId());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(obj.toString());

        return simpleMailMessage;
    }

    @Override
    public void sendNewPassword(Cliente cliente, String pass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(cliente,pass);
        sendEMail(sm);
    }

    protected  SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String pass){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(cliente.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Nova solicitação de Senha!");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText("Nova Senha +" + pass + "");

        return simpleMailMessage;
    }
}
