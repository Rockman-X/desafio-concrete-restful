package com.example.desafioconcreterestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.desafioconcreterestful.entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
