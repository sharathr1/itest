package com.ip.itest.common.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Employee {
	@Getter
	@Setter
	@Id
	private String e_id;
	@Getter
	@Setter
	private String e_name;

}
