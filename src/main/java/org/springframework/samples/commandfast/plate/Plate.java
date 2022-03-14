package org.springframework.samples.commandfast.plate;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


import org.springframework.samples.commandfast.model.NamedEntity;


import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "plates")
@Getter
@Setter
public class Plate extends NamedEntity{
	
	@Column(name = "cost")
	@NotEmpty
	Double cost; 
	
	@Column(name = "category")
	@NotEmpty
	String category; 
	
//	
//	@ManyToMany(mappedBy = "plates")
//	private Set<Command> commands;
}
