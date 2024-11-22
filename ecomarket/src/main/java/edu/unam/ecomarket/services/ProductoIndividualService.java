package edu.unam.ecomarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.repositories.ProductoIndividualRepository;

@Service
public class ProductoIndividualService {
    
    @Autowired
    ProductoIndividualRepository productoIndividualRepository;

    
    
}
