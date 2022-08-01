package com.fatimah.dojoNinjas.services;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fatimah.dojoNinjas.models.Dojos;
import com.fatimah.dojoNinjas.repository.DojoRepository;

@Service
public class DojoService {
	private DojoRepository dojoRepository;
	public DojoService(DojoRepository dojoRepository) {
		this.dojoRepository = dojoRepository;
	}
	
	public Dojos creatdojo(Dojos dojo) {
		return dojoRepository.save(dojo);
	}
	
	public List<Dojos> getAll(){
		return dojoRepository.findAll();
	}
	
	public Dojos getDojo(Long dojo_id) {
		Optional<Dojos> optionalDojo = dojoRepository.findById(dojo_id);
		if (optionalDojo.isPresent()) {
			Dojos my_dojo = optionalDojo.get();
			return my_dojo;
		}
		else
			return null;
	}

}
