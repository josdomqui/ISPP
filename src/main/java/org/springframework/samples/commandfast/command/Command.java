package org.springframework.samples.commandfast.command;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.commandfast.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commands")
@Getter
@Setter
public class Command extends BaseEntity{
	
	Integer quantity;
	Double price;
}
