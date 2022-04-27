package org.springframework.samples.commandfast.mesa;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tables")
@Getter
@Setter
public class Mesa extends BaseEntity{
	@Column(name = "number")
	@NotNull
	Integer number;
	
	@Column(name = "costumer")
	@NotNull
	Integer costumer;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mesa")
	private Set<Command> commands;
}
