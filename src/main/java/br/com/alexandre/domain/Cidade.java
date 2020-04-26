package br.com.alexandre.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ibge_id;

	private String uf;

	private String name;

	private Boolean capital;

	private Double lon;

	private Double lat;
	
	private String no_accents;
	
	private String alternative_names;

	private String microregion;

	private String mesoregion;

	

}
