package org.springframework.samples.commandfast.plate;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.samples.commandfast.line.Line;
import org.springframework.samples.commandfast.model.NamedEntity;
import org.springframework.samples.commandfast.restaurantes.Restaurante;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "plates")
@Getter
@Setter
public class Plate extends NamedEntity{
	
	@Column(name = "cost")
	@NotNull(message="Tiene que introducir un precio")
	@Positive	
	Double cost; 
	
	@Column(name = "category")
	@NotEmpty
	String category;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "plate")
	private Set<Line> lines;
	
	@Column(name = "image")
	String image;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "restaurant_id")
	private Restaurante restaurant;

}
