package com.example.demo.repositary;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.studentmodel;

@Repository
public interface studentrepositary extends CrudRepository<studentmodel, Integer> {

}
