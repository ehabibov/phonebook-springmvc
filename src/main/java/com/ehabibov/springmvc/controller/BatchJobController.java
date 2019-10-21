package com.ehabibov.springmvc.controller;

import com.ehabibov.springmvc.batch.config.BatchJobManager;
import com.ehabibov.springmvc.batch.Jobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/batch/jobs/*")
public class BatchJobController {

    @Autowired
    private BatchJobManager jobManager;

    @GetMapping(Jobs.PHONEBOOK_CSV_JOB)
    public ResponseEntity phoneBookCsvDataload(
            @RequestParam(value = "filePath", required = false) String filePath) throws FileNotFoundException {
        return jobManager.startPhoneBookCsvDataload(filePath);
    }

}