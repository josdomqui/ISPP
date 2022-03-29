package org.springframework.samples.commandfast.command;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.commandfast.line.Line;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.samples.commandfast.model.BaseEntity;



import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commands")
@Getter
@Setter
public class Command extends BaseEntity{
	
	@Column(name = "costumers")
	@NotNull
	@Min(1)
	@Max(4)
	private Integer costumers;
	
	@Column(name = "price")
	Double price;
	
//	@Column(name = "date")
//	//@NotEmpty
//	LocalDateTime date;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "command")
	private Set<Line> lines;
	
	@ManyToOne
	@JoinColumn(name="mesa_id")
	private Mesa mesa;
	
	
}
