package br.com.alexandre.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandre.domain.Cidade;
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
	
	@GetMapping("/2")
	public ResponseEntity<List<Cidade>> listarCapitalOrdem(){
		List<Cidade> capitaisOrdenadas = service.capitaisOrdemNome();
		
		return ResponseEntity.status(HttpStatus.OK).body(capitaisOrdenadas);
	}
	
	@GetMapping("/3")
	public ResponseEntity<List<String>> estadoMaioreMenorQuantidadeCidades(){
		List<String> estados = service.estadoMaioreMenorQuantidadeCidades();
		
		return ResponseEntity.status(HttpStatus.OK).body(estados);
	}
	
	@GetMapping("/4")
	public ResponseEntity<List<String>> quantidadeCidadeEstado(){
		List<String> estados = service.quantidadeCidadeEstado();
		
		return ResponseEntity.status(HttpStatus.OK).body(estados);
	}
	
	@GetMapping("/5")
	public ResponseEntity<Cidade> listarCidadePeloIDIBGE(
			@RequestParam(name = "ibgeid") String ibgeId){
		Cidade cidade = service.listarCidadePeloIDIBGE(ibgeId);
		
		return ResponseEntity.status(HttpStatus.OK).body(cidade);
	}
	
	@GetMapping("/6")
	public ResponseEntity<List<String>> listarCidadesMesmoEstado(
			@RequestParam(name = "uf") String uf){
		List<String> cidades = service.listarCidadeMesmoEstado(uf);
		
		return ResponseEntity.status(HttpStatus.OK).body(cidades);
	}
	
	@PostMapping("/7")
	public ResponseEntity<Cidade> save(Cidade cidade){
		Cidade cidadeCadastrada = service.adicionarCidade(cidade);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeCadastrada);
	}
	
	@DeleteMapping("/8")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@RequestParam(name = "nome") String nomeCidade){
		service.deletarCidadePorNome(nomeCidade);
	}
	
	@GetMapping("/9")
	public ResponseEntity<List<Cidade>> selecionarColunaEProcurarPor(
			@RequestParam(name = "coluna") String coluna,
			@RequestParam(name = "palavra") String palavra){
		
		List<Cidade> resultados = service.selecionarColunaEProcurarPor(coluna, palavra);
		
		return ResponseEntity.status(HttpStatus.OK).body(resultados);
		
	}
	
	@GetMapping("/10/name")
	public ResponseEntity<Integer> quantidadeDeRegistroSemRepeticao(){
		Integer quantidade = service.quantidadeDeRegistroSemRepeticao();
		
		return ResponseEntity.status(HttpStatus.OK).body(quantidade);
	}
	
	@GetMapping("/11/total")
	public ResponseEntity<Integer> quantidadeRegistroTotal(){
		Integer qtdTotal = service.quantidadeRegistroTotal();
		
		return ResponseEntity.status(HttpStatus.OK).body(qtdTotal);
	}
	

}
