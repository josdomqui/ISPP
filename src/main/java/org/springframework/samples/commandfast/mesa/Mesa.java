package org.springframework.samples.commandfast.mesa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	@NotEmpty
	Integer number;
	
	@Column(name = "costumer")
	@NotEmpty
	Integer costumer;
	
	@ManyToOne
	@JoinColumn(name = "command_id")
	private Command command;
}
