package com.ehabibov.springmvc.batch.config;

import com.ehabibov.springmvc.batch.InMemoryItemWriter;
import com.ehabibov.springmvc.batch.Jobs;
import com.ehabibov.springmvc.batch.mapper.CustomerMapper;
import com.ehabibov.springmvc.entity.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job readCSVFilesJob(Step step) {
        return jobBuilderFactory
                .get(Jobs.PHONEBOOK_CSV_JOB)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(ItemReader<Customer> itemReader, ItemWriter<Customer> itemWriter) {
        return stepBuilderFactory
                .get(Jobs.PHONEBOOK_CSV_STEP)
                .<Customer, Customer>chunk(3)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Customer> reader(@Value("#{jobParameters['" + Jobs.JOB_PARAM_FILE_NAME + "']}") String fileName) {

        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(fileName));
        reader.setLinesToSkip(1);

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("name", "phoneNumbers");
        lineTokenizer.setDelimiter(";");

        BeanWrapperFieldSetMapper<Customer> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Customer.class);
        beanWrapperFieldSetMapper.setDistanceLimit(0);

        DefaultLineMapper lineMapper = new DefaultLineMapper();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new CustomerMapper());

        reader.setLineMapper(lineMapper);
        return reader;
    }

    @Bean
    public InMemoryItemWriter<Customer> writer() {
        return new InMemoryItemWriter<>();
    }
}
