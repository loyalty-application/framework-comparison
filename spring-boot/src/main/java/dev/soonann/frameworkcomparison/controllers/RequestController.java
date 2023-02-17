package dev.soonann.frameworkcomparison.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import java.util.*;

import dev.soonann.frameworkcomparison.models.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RequestController {

    @Autowired
    public RequestController() {

    }

    @PostMapping
    public ResponseEntity<Double> registerRequest(@RequestBody TransactionDTO transactionDTO) {

        System.out.println(transactionDTO);
        List<RequestDTO> requests = transactionDTO.getRequests();
        Double sum = 0.0;
        for (RequestDTO request : requests) {
            if (request.getMerchant().equals("A")) {
                sum += request.getAmount();
            }
        }

        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    // @PostMapping(path = "/file")
    // public ResponseEntity<Object> registerFile(@RequestBody RegistrationDTO registrationDTO) {
        
    //     return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
    // }
}