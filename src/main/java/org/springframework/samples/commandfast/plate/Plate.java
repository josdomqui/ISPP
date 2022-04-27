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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.samples.commandfast.line.Line;
import org.springframework.samples.commandfast.model.BaseEntity;
import org.springframework.samples.commandfast.model.NamedEntity;
import org.springframework.samples.commandfast.restaurantes.Restaurante;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "plates")
@Getter
@Setter
public class Plate extends BaseEntity{
	
	@Size(min = 1, max = 50)
	@Column(name = "name")
	private String name;
	
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id")
	private Restaurante restaurant;

}
