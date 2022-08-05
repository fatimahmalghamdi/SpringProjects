package com.fatimah.languages.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatimah.languages.models.Languages;

@Repository
public interface LanguagesRepository extends CrudRepository<Languages, Long>{
	List<Languages> findAll();
	Optional<Languages> findById(Long id);

}
