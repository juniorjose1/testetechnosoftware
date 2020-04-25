package br.com.alexandre.service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
}
