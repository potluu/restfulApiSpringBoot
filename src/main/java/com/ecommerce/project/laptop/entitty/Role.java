package com.ecommerce.project.laptop.entitty;

import javax.persistence.*;

import com.ecommerce.project.laptop.common.ERole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "roles")
@Builder
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 20)
	private String name;

	@JsonBackReference
	@ManyToMany(mappedBy = "roles")
	private Collection<Account> accounts;
	
}