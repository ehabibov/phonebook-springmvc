package com.ehabibov.springmvc.batch;

import com.ehabibov.springmvc.entity.Customer;
import com.ehabibov.springmvc.repository.PhoneBookRepositoryImpl;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InMemoryItemWriter<T> implements ItemWriter<T> {

    @Autowired
    PhoneBookRepositoryImpl repo;

    @Override
    public void write(List<? extends T> items) {
        for (T item : items){
            repo.addObject((Customer) item);
        }
    }
}
