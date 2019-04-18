package com.iiitb.divmon.repository;

import org.springframework.data.repository.CrudRepository;

import com.iiitb.divmon.bean.Bill;

public interface BillRepository extends CrudRepository<Bill, Integer> {

}
