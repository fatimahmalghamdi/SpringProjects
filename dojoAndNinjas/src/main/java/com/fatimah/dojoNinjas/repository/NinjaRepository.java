package com.fatimah.dojoNinjas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatimah.dojoNinjas.models.Ninjas;

@Repository
public interface NinjaRepository extends CrudRepository<Ninjas, Long>{
	List<Ninjas> findAll();

}
