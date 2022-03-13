package org.springframework.samples.commandfast.plate;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.model.NamedEntity;
import org.springframework.samples.commandfast.owner.Owner;

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
	
	
	@ManyToMany(mappedBy = "plates")
	private Set<Command> commands;
}
