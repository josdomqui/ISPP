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
package org.springframework.samples.commandfast.subscriptions;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.samples.commandfast.model.BaseEntity;
import org.springframework.samples.commandfast.restaurantes.Restaurante;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subscription")
public class Suscripcion extends BaseEntity {

	@Column(name = "start_date")
	@Past
	private LocalDate startDate;

	@Column(name = "end_date")
	@Future
	private LocalDate endDate;

	@Column(name="active")
	@NotNull
	private Boolean active;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurante")
	private Restaurante restaurant;

}
