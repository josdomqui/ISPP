package org.springframework.samples.commandfast.command;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.commandfast.line.Line;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.samples.commandfast.model.BaseEntity;

import com.sun.istack.NotNull;

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
	
	@Column(name = "price")
	@NotNull
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
