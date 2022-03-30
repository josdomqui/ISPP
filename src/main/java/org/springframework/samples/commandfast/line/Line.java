package org.springframework.samples.commandfast.line;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.model.BaseEntity;
import org.springframework.samples.commandfast.plate.Plate;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lines")
@Getter
@Setter
public class Line extends BaseEntity{
	
	
	@Column(name = "quantity")
	@NotNull
	Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "command_id")
	private Command command;
	
	@ManyToOne
	@JoinColumn(name = "plate_id")
	private Plate plate;
	
	
}
