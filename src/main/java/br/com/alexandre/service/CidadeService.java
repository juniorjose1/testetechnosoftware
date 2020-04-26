package br.com.alexandre.service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import br.com.alexandre.domain.Cidade;
import br.com.alexandre.repository.CidadeRepository;

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
		Cidade cidade = repo.listarCidadePeloIDIBGE(ibgeId);
		
		return cidade;
	}
	
	public List<String> listarCidadeMesmoEstado(String uf){
		List<String> cidades = repo.listarCidadeMesmoEstado(uf);
		return cidades;
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
		
		if(colunaSelecionada.equals("name")) {
			List<Cidade> resultadoNome = repo.ProcurarPorNome(palavra);
			return resultadoNome;
		} else if(colunaSelecionada.equals("mesoregion")) {
			List<Cidade> resultadoMesoRegion = repo.ProcurarPorMesoRegion(palavra);
			return resultadoMesoRegion;
		} else if(colunaSelecionada.equals("ibge_id")) {
			List<Cidade> resultadoIbge = repo.ProcurarPorIbgeId(palavra);
			return resultadoIbge;
		} else if(colunaSelecionada.equals("capital")) {
			List<Cidade> resultadoCapital = repo.ProcurarPorCapital(palavra);
			return resultadoCapital;
		} else if(colunaSelecionada.equals("microregion")) {
			List<Cidade> resultadoMicroRegion = repo.ProcurarPorMicroRegion(palavra);
			return resultadoMicroRegion;
		} else if(colunaSelecionada.equals("alternative_names")) {
			List<Cidade> resultadoAlternativeNames = repo.ProcurarPorAlternativeNames(palavra);
			return resultadoAlternativeNames;
		} else if(colunaSelecionada.equals("no_accents")) {
			List<Cidade> resultadoNoAccents = repo.ProcurarPorNoAccents(palavra);
			return resultadoNoAccents;
		} else if(colunaSelecionada.equals("uf")) {
			List<Cidade> resultadoUf = repo.ProcurarPorUf(palavra);
			return resultadoUf;
		}
		
		return null;
		
	}
	
}
