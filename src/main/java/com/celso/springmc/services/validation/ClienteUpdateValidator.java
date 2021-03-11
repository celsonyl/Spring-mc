package com.celso.springmc.services.validation;

import com.celso.springmc.domain.Cliente;
import com.celso.springmc.domain.dto.ClienteDTO;
import com.celso.springmc.domain.dto.ClienteNewDTO;
import com.celso.springmc.domain.enums.TipoCliente;
import com.celso.springmc.repositories.ClienteRepository;
import com.celso.springmc.resources.exception.FieldMessage;
import com.celso.springmc.services.validation.utils.ValidaCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(ClienteUpdate ann) {

    }

    @Override
    public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriID = Integer.parseInt(map.get("id"));

        Cliente find = clienteRepository.findByEmail(objDTO.getEmail());


        if (find != null && !find.getId().equals(uriID)) {
            list.add(new FieldMessage("email", "Email já existe!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
