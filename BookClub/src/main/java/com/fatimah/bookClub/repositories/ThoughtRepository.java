package com.fatimah.bookClub.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatimah.bookClub.models.Thoughts;

@Repository
public interface ThoughtRepository extends CrudRepository<Thoughts, Long>{
	List<Thoughts> findAll();

}
