package com.fatimah.dojoNinjas.services;

import org.springframework.stereotype.Service;

import com.fatimah.dojoNinjas.models.Ninjas;
import com.fatimah.dojoNinjas.repository.NinjaRepository;

@Service
public class NinjaService {
	private NinjaRepository ninjaRepository;
	public NinjaService(NinjaRepository ninjaRepository) {
		this.ninjaRepository = ninjaRepository;
	}
	
	public Ninjas creatNinja(Ninjas ninja) {
		return ninjaRepository.save(ninja);
	}

}
