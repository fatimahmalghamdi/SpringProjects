package com.fatimah.languages.services;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fatimah.languages.models.Languages;
import com.fatimah.languages.repositories.LanguagesRepository;

@Service
public class LanguagesService {
	private LanguagesRepository languagesRepository;
	public LanguagesService(LanguagesRepository languagesRepo) {
		this.languagesRepository = languagesRepo;
	}
	// get all languages:
	public List<Languages> getlangs() {
		List<Languages> allthelangs = languagesRepository.findAll();
		return allthelangs;
	}
	
	//get language by Id:
	public Languages currlang(Long id) {
		Optional<Languages> thelang = languagesRepository.findById(id);
		if (thelang.isPresent()) {
			Languages mylang = thelang.get();
			return mylang;
		} else
			return null;
	}
	
	//save new language:
	public Languages createlang(Languages lang) {
		double xx = (double)lang.getCurrVersion();
		lang.setCurrVersion(xx);
		return languagesRepository.save(lang);
	}
	
	//update language:
	public Languages editlang(Long id, Languages editlang) {
		Languages thelang = currlang(id);
		thelang.setLangname(editlang.getLangname());
		thelang.setCreator(editlang.getCreator());
		double ver = (double) editlang.getCurrVersion();
		thelang.setCurrVersion(ver);
		return languagesRepository.save(thelang);
	}
	
	
	
	//delete language:
	public void deletelang(Long id) {
		Languages currentlang = currlang(id);
		languagesRepository.delete(currentlang);	
	}
	
	

}
