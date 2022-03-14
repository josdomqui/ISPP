package org.springframework.samples.commandfast.command;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.commandfast.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commands")
@Getter
@Setter
public class Command extends BaseEntity{
	
	@Column(name = "name",unique = true)
	@NotEmpty
	private String name;
	
	@Column(name = "quantity")
	@NotNull
	Integer quantity;
	
	@Column(name = "price")
	@NotNull
	Double price;
	
	@Column(name = "date")
	//@NotEmpty
	LocalDateTime date;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "line_command", joinColumns = @JoinColumn(name = "command_id"), inverseJoinColumns = @JoinColumn(name = "plate_id"))
//	private List<Plate> plates;
	
//	@OneToMany(mappedBy="command")
	@Column(name = "mesa")
	@NotNull
	private Integer mesa;
	
	
}
