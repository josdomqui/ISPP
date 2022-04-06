/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.commandfast.restaurantes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.samples.commandfast.user.User;

import org.springframework.samples.commandfast.model.NamedEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurante extends NamedEntity {

	@Column(name = "city")
	@NotEmpty
	private String city;

	@Column(name = "telephone")
	@Min(1)
	@Digits(fraction = 0, integer = 9)
	private String telephone;

	@Column(name= "address")
	@NotEmpty
	private String address;

	@Column(name = "description")
	@Size(min = 25, max = 250)
	@NotEmpty
	private String description;

	@Column(name = "photo")
	private String photo;
	
	@Column(name = "capacity")
	@NotNull
	@Positive
	private Integer capacity;
	
	@Column(name = "schedule")
	@NotEmpty
	private String schedule;

	@Column(name = "email")
	@NotEmpty
	private String email;

	@ElementCollection(targetClass =  RestauranteType.class)
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private List<RestauranteType> type;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User user;

}
