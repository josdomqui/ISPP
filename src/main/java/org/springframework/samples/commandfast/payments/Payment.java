package org.springframework.samples.commandfast.payments;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.samples.commandfast.model.BaseEntity;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment extends BaseEntity{
	
	
	@NotNull
	@Min(0)
	Double amount;
	
	@NotNull
	LocalDate date;
	
	@ManyToOne
	private Mesa table;
	
}
