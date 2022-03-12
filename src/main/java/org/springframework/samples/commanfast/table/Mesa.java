package org.springframework.samples.commanfast.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Table;

import org.springframework.samples.commandfast.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mesas")
@Getter
@Setter
public class Mesa extends BaseEntity{
	@Column(name = "number")
	@NotEmpty
	Integer number;
	@Column(name = "costumer")
	@NotEmpty
	Integer costumer;
}
