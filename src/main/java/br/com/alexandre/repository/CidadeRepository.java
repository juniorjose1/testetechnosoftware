package br.com.alexandre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alexandre.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Modifying
	@Query("DELETE FROM Cidade WHERE name = :nomeCidade")
	public void apagarRegistro(@Param("nomeCidade") String nomeCidade);
	
	@Query("FROM Cidade WHERE ibge_id = :ibgeid")
	public Cidade listarCidadePeloIDIBGE(@Param("ibgeid") String id);
	
	@Query("FROM Cidade WHERE capital = 1 order by name")
	public List<Cidade> capitaisOrdemNome();
	
	@Query("SELECT uf, COUNT(uf) FROM Cidade GROUP BY uf")
	public List<String> quantidadeCidadeEstado();
	
	@Query("SELECT name FROM Cidade where uf = :uf")
	public List<String> listarCidadeMesmoEstado(@Param("uf") String uf);
	
	@Query("SELECT COUNT(*) FROM Cidade")
	public Integer quantidadeRegistroTotal();
	
	@Query(value = "SELECT count(DISTINCT name) FROM Cidade",
			nativeQuery = true)
	public Integer quantidadeDeRegistroSemRepeticao();
	
	@Query(value = "SELECT uf, COUNT(uf) FROM cidade  GROUP BY uf ORDER BY COUNT(uf) DESC LIMIT 1",
			nativeQuery = true)
	public String estadoMaiorQuantidadeCidades();
	
	@Query(value = "SELECT uf, COUNT(uf) FROM cidade  GROUP BY uf ORDER BY COUNT(uf) LIMIT 1",
			nativeQuery = true)
	public String estadoMenorQuantidadeCidades();
	
	@Query("FROM Cidade WHERE name LIKE %:palavra%")
	public List<Cidade> ProcurarPorNome(@Param("palavra") String palavra);
	
	@Query("FROM Cidade WHERE mesoregion LIKE %:palavra%")
	public List<Cidade> ProcurarPorMesoRegion(@Param("palavra") String palavra);
	
	@Query("FROM Cidade WHERE ibge_id LIKE %:palavra%")
	public List<Cidade> ProcurarPorIbgeId(@Param("palavra") String ibgeId);
	
	@Query(value = "SELECT * FROM cidade WHERE IF(capital, 'true', 'false') LIKE %:palavra%",
			nativeQuery = true)
	public List<Cidade> ProcurarPorCapital(@Param("palavra") String palavra);
	
	@Query("FROM Cidade WHERE microregion LIKE %:palavra%")
	public List<Cidade> ProcurarPorMicroRegion(@Param("palavra") String palavra);
	
	@Query("FROM Cidade WHERE alternative_names LIKE %:palavra%")
	public List<Cidade> ProcurarPorAlternativeNames(@Param("palavra") String palavra);
	
	@Query("FROM Cidade WHERE no_accents LIKE %:palavra%")
	public List<Cidade> ProcurarPorNoAccents(@Param("palavra") String palavra);
	
	@Query("FROM Cidade WHERE uf like %:palavra%")
	public List<Cidade> ProcurarPorUf(@Param("palavra") String palavra);

	
}
