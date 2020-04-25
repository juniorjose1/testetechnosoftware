package br.com.alexandre.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandre.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	CidadeService service;
	
	@GetMapping("/1")
	public ResponseEntity<String> importAll() throws Exception{
		String resultado = service.importarArquivoCSV();
		
		return ResponseEntity.status(HttpStatus.OK).body(resultado);
	}

}
