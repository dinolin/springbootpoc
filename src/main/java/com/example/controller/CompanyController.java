package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.CompanyModel;
import com.example.service.CompanyService;


@RestController
public class CompanyController {
	
	@Autowired
	CompanyModel companyModel;
	
	
	@Autowired
	CompanyService companyService;

	
    @RequestMapping("/addCompany")
    public String addInitCompany(){
    	companyModel = new CompanyModel();
    	companyModel.setName("DELTA Inc Co");
    	companyService.addCompany(companyModel);
        return "New Company added";
    }
    
    @PostMapping("/createCompany/{name}")
    public String createCompany(@PathVariable("name") String companyName) {
    	companyModel = new CompanyModel();
    	companyModel.setName(companyName);
    	companyService.addCompany(companyModel);
        return "New Company Created";
    
    }
    
    

}
