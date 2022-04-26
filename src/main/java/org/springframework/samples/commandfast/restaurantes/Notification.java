package org.springframework.samples.commandfast.restaurantes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.samples.commandfast.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Notification")
public class Notification extends BaseEntity{
	
	@Column(name = "numero_mesa")
	private Integer numeroMesa;
	
	@Column(name = "atendido")
	private Integer atendido;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurante restaurant;
	

}
