package com.fatimah.dojoNinjas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatimah.dojoNinjas.models.Dojos;

@Repository
public interface DojoRepository extends CrudRepository<Dojos, Long>{
	List<Dojos> findAll();
	Optional<Dojos> findById(Long id);

}
