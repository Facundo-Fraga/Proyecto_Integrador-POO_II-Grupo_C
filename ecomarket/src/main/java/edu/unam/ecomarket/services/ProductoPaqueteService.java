package edu.unam.ecomarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.repositories.ProductoPaqueteRepository;


@Service
public class ProductoPaqueteService {
    
    @Autowired
    ProductoPaqueteRepository productoPaqueteRepositorio;

    

}
