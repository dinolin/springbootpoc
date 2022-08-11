package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.CompanyModel;

@Repository
public class CompanyRepository {


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addCompany(CompanyModel companyModel){
		System.out.println("EXCUTE INSERT COMPANY");
		jdbcTemplate.update("INSERT INTO CompanyMaster(id, name, CreateDate, ModifyDate) "
	  		+ "VALUES (?,?,NOW(),NOW())",companyModel.getId(),companyModel.getName());
  }


}