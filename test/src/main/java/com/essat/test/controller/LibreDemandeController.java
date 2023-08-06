package com.essat.test.controller;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.essat.test.entity.Libre_demande;
import com.essat.test.repository.Libredemrep;
import com.essat.test.service.LibreDemandeService;
import com.essat.test.service.ResponseMessage;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
@CrossOrigin("*")
@RestController
@RequestMapping(value="demande")
public class LibreDemandeController {


	@Autowired
	Libredemrep demandeDAO;
	
	



	
	

	@Autowired
	LibreDemandeService LibreService;
	
	 
	  @CrossOrigin
	  @PostMapping("/createDemande")
	  public ResponseEntity<ResponseMessage> createDemande(@RequestParam("file") MultipartFile file,@RequestParam("demande") String dem) throws IOException {	        
	    ResponseMessage responseMessage;
	    responseMessage = this.LibreService.createDem(file,dem);
	    if(responseMessage.getCode()=="0") {
	    	return ResponseEntity.ok(responseMessage);
	    } else {
	    	return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	    }
	  }
	  
	 /* @CrossOrigin
	  @PostMapping("/getListDemande")
	  public Libre_demande getListDemande(@RequestBody Libre_demande dem) throws IOException {	        
		  String codSoc = dem.getCodSoc();
			
			Libre_demande d = this.demandeDAO.getListDemande();
			System.out.println(codSoc);
			return d;
	    }*/
	
	@CrossOrigin
	@GetMapping("/getDemande")
	public ResponseEntity<List<Libre_demande>> getAllDemande() {

		return ResponseEntity.ok(demandeDAO.findAll());
	}
	@CrossOrigin
	@PostMapping(value="/addDemandeWithoutFile")
	public ResponseEntity<Libre_demande> addDemande(@RequestBody Libre_demande demande ) {

		return ResponseEntity.ok(demandeDAO.save(demande));
	}
	/*@CrossOrigin
	@GetMapping("/getDec")
	public ResponseEntity<List<Decision>> getDecidion() {

		return ResponseEntity.ok(decidionDao.getDecidion());
	}*/
	/*@CrossOrigin
	@GetMapping("/getDemandes")
	public ResponseEntity<List<Libre_demande>> getDemande() {

		return ResponseEntity.ok(demandeDAO.getDemRh());
	}*/
	

	

	
	
	
	
/*	@CrossOrigin
	@GetMapping("/getListDemande/{CodSoc}")
	public ResponseEntity<List<Libre_demande>> getDemande(@PathVariable String CodSoc) {
List<Libre_demande> d = demandeDAO.getListDemande(CodSoc);
System.out.println(d);
		return ResponseEntity.ok(d);
	}*/

	
	


	
	
	
	@CrossOrigin
	@GetMapping("/getbyid/{id}")

	public ResponseEntity<Libre_demande> findDemandeById(@PathVariable Long id) {
		return ResponseEntity.ok(
				demandeDAO.gettByid(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDemande(@PathVariable Long id) {
		demandeDAO.deleteById(id);
		return ResponseEntity.ok("Deleted");
	}
	/*@CrossOrigin
	@GetMapping("/get")
	public ResponseEntity<List<Libre_demande>> getDemandeList() {

		return ResponseEntity.ok(demandeService.getDemande());
	}
	
	@DeleteMapping("/deleteClient/{id}")
	public ResponseEntity<Boolean> deleteClient(@PathVariable Long id) throws Exception{
		return ResponseEntity.ok(demandeService.deleteDemande(id));
	}*/
	


	  @CrossOrigin
	  @GetMapping("/downloadFile/{documentId}")
		public void downloadFile(@PathVariable("documentId") Long documentId, HttpServletResponse response)
				throws IOException {

			Libre_demande document = LibreService.getDocument(documentId);
			try {
				response.setHeader("Content-Disposition", "inline;filename=\"" + document.getFileName() + "\"");
				response.setContentType(document.getContentType());


				InputStream ins = new ByteArrayInputStream(document.getFichierJoint());
				IOUtils.copy(ins, response.getOutputStream());

			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	  @GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
	    Libre_demande fileDB = LibreService.getFile(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
	        .body(fileDB.getFichierJoint());
	  }
	  @GetMapping("/all")
	  public List<Libre_demande> getdem() {
	   return demandeDAO.findAll();
	  }
	  
	  
	
	

		  
		  
		  
	  








	
		  
		  
		  
	  
	 
	  
	  
	

}
