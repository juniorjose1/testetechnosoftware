package br.com.alexandre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alexandre.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
