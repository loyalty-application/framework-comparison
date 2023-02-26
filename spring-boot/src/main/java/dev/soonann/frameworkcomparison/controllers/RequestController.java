package dev.soonann.frameworkcomparison.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import dev.soonann.frameworkcomparison.models.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RequestController {

    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public ResponseEntity<Double> registerRequest(@RequestBody TransactionDTO transactionDTO) {

        // System.out.println(transactionDTO);
        List<RequestDTO> requests = transactionDTO.getTransactions();
        Double sum = 0.0;
        for (RequestDTO request : requests) {
            if (request.getMerchant().equals("A")) {
                sum += request.getAmount();
            }
        }

        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    @PostMapping(path = "/file")
    public ResponseEntity<Double> registerFile(@RequestParam("file") MultipartFile file) throws FileNotFoundException, IOException, CsvValidationException {
        
        Double sum = 0.0;

        // Upload File
        File path = new File(file.getOriginalFilename());
        path.createNewFile();
        FileOutputStream output = new FileOutputStream(path);
        output.write(file.getBytes());
        output.close();

        // Read File
        // List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(path))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                if (values[2].equals("A")) {
                    // records.add(Arrays.asList(values));
                    sum += Double.parseDouble(values[5]);
                }
            }
        }
        // System.out.println(records);
        return new ResponseEntity<>(sum, HttpStatus.CREATED);
    }
}