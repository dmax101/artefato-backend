package br.inatel.icc.idp.artefato.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.icc.idp.artefato.model.DTO.BasicMessageDTO;
import br.inatel.icc.idp.artefato.model.DTO.PaymentDTO;
import br.inatel.icc.idp.artefato.service.OrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payment")
@Validated
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<?> paymentConfirmation(@RequestBody @Valid PaymentDTO paymentDTO) {

        Pair<Boolean, BasicMessageDTO> orderExecution = orderService.execute(paymentDTO);

        if (Boolean.TRUE.equals(orderExecution.getFirst())) {

            log.info("Payement realized!");

            return ResponseEntity.ok(orderExecution.getSecond());
        }

        log.error("Error on payment!");

        return ResponseEntity.ok(orderExecution.getSecond());
    }
}
