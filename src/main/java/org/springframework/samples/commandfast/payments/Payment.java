package org.springframework.samples.commandfast.payments;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.samples.commandfast.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment extends BaseEntity{
	
	
	@NotNull
	@Min(0)
	private Double amount;
	
	@NotNull
	private LocalDate date;
	
	@ManyToOne
	private Mesa table;
	
	@NotNull
	private Boolean payHere=false;
	
	@NotNull
	private Boolean creditCard=false;
	
}
