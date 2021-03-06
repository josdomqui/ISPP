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
package org.springframework.samples.commandfast.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.samples.commandfast.model.NamedEntity;
import org.springframework.samples.commandfast.restaurantes.Restaurante;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product extends NamedEntity {

	@Column(name = "name")
	@Size(min=2)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	@NotNull(message="Tiene que introducir un precio")
	@Positive
	private Double price;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "restaurant_id")
	private Restaurante restaurant;

}
