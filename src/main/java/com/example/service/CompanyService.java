package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.CompanyModel;
import com.example.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;
	public void addCompany(CompanyModel companyModel){
		companyRepository.addCompany(companyModel);
	}

	
}