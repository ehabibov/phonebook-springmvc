package com.ehabibov.springmvc.batch.writer;

import com.ehabibov.springmvc.entity.memory.Customer;
import com.ehabibov.springmvc.repository.PhoneBookDatabaseRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DatabaseItemWriter<T> implements ItemWriter<T> {

    @Autowired
    PhoneBookDatabaseRepository repo;

    @Override
    public void write(List<? extends T> items) {
        for (T item : items){
            repo.addObject((Customer) item);
        }
    }
}
