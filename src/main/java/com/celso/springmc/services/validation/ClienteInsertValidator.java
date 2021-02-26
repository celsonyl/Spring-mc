package com.celso.springmc.services.validation;

import com.celso.springmc.domain.Cliente;
import com.celso.springmc.domain.dto.ClienteNewDTO;
import com.celso.springmc.domain.enums.TipoCliente;
import com.celso.springmc.repositories.ClienteRepository;
import com.celso.springmc.resources.exception.FieldMessage;
import com.celso.springmc.services.validation.utils.ValidaCPF;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann){

    }

    @Override
    public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();

        if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !ValidaCPF.isValidCPF(objDTO.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","CPF Inválido!"));
        }
        if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !ValidaCPF.isValidTCNPJ(objDTO.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","CNPJ Inválido!"));
        }

        Cliente find = clienteRepository.findByEmail(objDTO.getEmail());

        if(find != null){
            list.add(new FieldMessage("email","Email já existe!"));
        }

        for(FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
