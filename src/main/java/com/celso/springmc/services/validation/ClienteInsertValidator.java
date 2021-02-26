package com.celso.springmc.services.validation;

import com.celso.springmc.domain.dto.ClienteNewDTO;
import com.celso.springmc.domain.enums.TipoCliente;
import com.celso.springmc.resources.exception.FieldMessage;
import com.celso.springmc.services.validation.utils.ValidaCPF;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

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


        for(FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
