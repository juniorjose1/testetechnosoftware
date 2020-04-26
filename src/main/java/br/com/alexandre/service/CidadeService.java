package br.com.alexandre.service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import br.com.alexandre.domain.Cidade;
import br.com.alexandre.repository.CidadeRepository;
import br.com.alexandre.service.exception.ObjectNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	public String importarArquivoCSV() throws Exception {

		String msg = "Registros Importados para o Banco de Dados com Sucesso !";
		String msgErro = "Registros Já Importados !";

		Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/arquivos/cidades.csv"));

		CsvToBean<Cidade> csvToBean = new CsvToBeanBuilder<Cidade>(reader)
        		.withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
				.withType(Cidade.class)
				.build();

		List<Cidade> cidades = csvToBean.parse();

		if (repo.count() == 0) {
			for (Cidade cidadePercorrida : cidades) {
				repo.save(cidadePercorrida);
			}
		} else {
			return msgErro;
		}

		return msg;
	}
	
	public Cidade adicionarCidade(Cidade cidade) {
		Cidade cidadeCadastrada = repo.save(cidade);
		
		return cidadeCadastrada;
	}
	
	@Transactional
	public void deletarCidadePorNome(String nomeCidade) {
		repo.apagarRegistro(nomeCidade);
	}
	
	public List<Cidade> capitaisOrdemNome(){
		List<Cidade> capitaisOrdenadas = repo.capitaisOrdemNome();
		return capitaisOrdenadas;
	}
	
	public List<String> quantidadeCidadeEstado(){		
		List<String> cidades = repo.quantidadeCidadeEstado();
		return cidades;
	}
	
	public Cidade listarCidadePeloIDIBGE(String ibgeId) {
		Optional<Cidade> cidade = repo.listarCidadePeloIDIBGE(ibgeId);
		
		return cidade.orElseThrow(() -> new ObjectNotFoundException("ibge_id "
				+ "inexistente !" + ", Tipo: " + Cidade.class.getName()));
	}
	
	public List<String> listarCidadeMesmoEstado(String uf){
		Optional<List<String>> cidades = repo.listarCidadeMesmoEstado(uf);
		
		return cidades.orElseThrow(() -> new ObjectNotFoundException("Estado "
				+ "inexistente !" + ", Tipo: " + Cidade.class.getName()));
	}
	
	public Integer quantidadeRegistroTotal() {
		Integer qtdTotal = repo.quantidadeRegistroTotal();
		
		return qtdTotal;
	}
	
	
	public Integer quantidadeDeRegistroSemRepeticao(){
		Integer registros = repo.quantidadeDeRegistroSemRepeticao();
		
		return registros;
	}
	
	public List<String> estadoMaioreMenorQuantidadeCidades(){
		String estadoMaiorCidade = repo.estadoMaiorQuantidadeCidades();
		String estadoMenorCidade = repo.estadoMenorQuantidadeCidades();
		
		List<String> estados = new ArrayList<>();
		
		estados.add(estadoMaiorCidade);
		estados.add(estadoMenorCidade);

		return estados;
	}
	
	
	public List<Cidade> selecionarColunaEProcurarPor(String coluna, String palavra){
		
		String colunaSelecionada = coluna;
		
		Optional<List<Cidade>> resultado = Optional.empty();
				
		if(colunaSelecionada.equals("name")) {
			resultado = repo.ProcurarPorNome(palavra);
			return resultado.orElseThrow(() -> new ObjectNotFoundException("Nenhum registro "
					+ "encontrado na coluna |name| correspondente a " + "|" + palavra + "|"
					+ ", Tipo: " + Cidade.class.getName()));
			
		} else if(colunaSelecionada.equals("mesoregion")) {
			resultado = repo.ProcurarPorMesoRegion(palavra);
			return resultado.orElseThrow(() -> new ObjectNotFoundException("Nenhum registro "
					+ "encontrado na coluna |mesoregion| correspondente a " + "|" + palavra + "|"
					+ ", Tipo: " + Cidade.class.getName()));
			
		} else if(colunaSelecionada.equals("ibge_id")) {
			resultado = repo.ProcurarPorIbgeId(palavra);
			return resultado.orElseThrow(() -> new ObjectNotFoundException("Nenhum registro "
					+ "encontrado na coluna |ibge_id| correspondente a " + "|" + palavra + "|"
					+ ", Tipo: " + Cidade.class.getName()));
			
		} else if(colunaSelecionada.equals("capital")) {
			resultado = repo.ProcurarPorCapital(palavra);
			return resultado.orElseThrow(() -> new ObjectNotFoundException("Nenhum registro "
					+ "encontrado na coluna |capital| correspondente a " + "|" + palavra + "|"
					+ ", Tipo: " + Cidade.class.getName()));
			
		} else if(colunaSelecionada.equals("microregion")) {
			resultado = repo.ProcurarPorMicroRegion(palavra);
			return resultado.orElseThrow(() -> new ObjectNotFoundException("Nenhum registro "
					+ "encontrado na coluna |microregion| correspondente a " + "|" + palavra + "|"
					+ ", Tipo: " + Cidade.class.getName()));
			
		} else if(colunaSelecionada.equals("alternative_names")) {
			resultado = repo.ProcurarPorAlternativeNames(palavra);
			return resultado.orElseThrow(() -> new ObjectNotFoundException("Nenhum registro "
					+ "encontrado na coluna |alternative_names| correspondente a " + "|" + palavra + "|"
					+ ", Tipo: " + Cidade.class.getName()));
			
		} else if(colunaSelecionada.equals("no_accents")) {
			resultado = repo.ProcurarPorNoAccents(palavra);
			return resultado.orElseThrow(() -> new ObjectNotFoundException("Nenhum registro "
					+ "encontrado na coluna |no_accents| correspondente a " + "|" + palavra + "|"
					+ ", Tipo: " + Cidade.class.getName()));
			
		} else if(colunaSelecionada.equals("uf")) {
			resultado = repo.ProcurarPorUf(palavra);
			return resultado.orElseThrow(() -> new ObjectNotFoundException("Nenhum registro "
					+ "encontrado na coluna |uf| correspondente a " + "|" + palavra + "|"
					+ ", Tipo: " + Cidade.class.getName()));
		}else {
			
			return resultado.orElseThrow(() -> new ObjectNotFoundException("Coluna "
					+ "Não Encontrada ou tipo não suportado !" + ", Tipo: " + Cidade.class.getName()));
		} 
			
	}
	
}
