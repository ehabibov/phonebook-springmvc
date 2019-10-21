package com.ehabibov.springmvc.batch.config;

import com.ehabibov.springmvc.batch.Jobs;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource("classpath:application.properties")
public class BatchJobManager {

    @Value("${batch.csv.dataload.file.path}")
    private String phoneBookCsvPath;
    private JobLauncher jobLauncher;
    private Job job;

    @Autowired
    public BatchJobManager(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public ResponseEntity startPhoneBookCsvDataload(String filePath) throws FileNotFoundException {
        if (filePath == null) {
            filePath = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX.concat(phoneBookCsvPath)).getPath();
        }
        Map<String, JobParameter> parameterMap = new HashMap<>();
        parameterMap.put(Jobs.JOB_PARAM_FILE_NAME, new JobParameter(filePath));
        parameterMap.put("JobID", new JobParameter(String.valueOf(System.currentTimeMillis())));
        try {
            jobLauncher.run(job, new JobParameters(parameterMap));
        } catch (Exception e) {
            return new ResponseEntity<>("Failure: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
