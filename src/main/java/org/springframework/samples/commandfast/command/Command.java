package org.springframework.samples.commandfast.command;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.samples.commandfast.model.BaseEntity;
import org.springframework.samples.commandfast.pet.Pet;
import org.springframework.samples.commandfast.plate.Plate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commands")
@Getter
@Setter
public class Command extends BaseEntity{
	
	@Column(name = "quantity")
	@NotEmpty
	Integer quantity;
	
	@Column(name = "price")
	@NotEmpty
	Double price;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "line_command", joinColumns = @JoinColumn(name = "command_id"), inverseJoinColumns = @JoinColumn(name = "plate_id"))
	private List<Plate> plates;
	
	@OneToMany(mappedBy="command")
	private Set<Mesa> mesas;
	
	
}
