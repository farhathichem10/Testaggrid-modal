package com.essat.test.service;


import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.essat.test.entity.Libre_demande;
import com.essat.test.repository.Libredemrep;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LibreDemandeService {

	@Autowired
	Libredemrep demandeDAO;


	@Transactional
	public ResponseMessage createDem(MultipartFile file, String dem) throws IOException {
		ResponseMessage responseMessage = new ResponseMessage();
		Libre_demande arti = new ObjectMapper().readValue(dem, Libre_demande.class);
		arti.setFichierJoint(file.getBytes());
		arti.setFileName(file.getOriginalFilename());
		arti.setContentType(file.getContentType());
		arti.setPath("DB");
		try {
			demandeDAO.save(arti);
			responseMessage.setCode("0");
			responseMessage.setMessage("Demande created");
		} catch (Exception e) {
			responseMessage.setCode("1");
			responseMessage.setMessage("Demande Not created");
		}
		return responseMessage;
	}
	  public Libre_demande getFile(Long id) {
		    return  demandeDAO.gettByid(id);
		  }
	  
		public Libre_demande getDocument(Long  docId) {

			Libre_demande document = demandeDAO.gettByid(docId);
			
			return document;
		}

}
	