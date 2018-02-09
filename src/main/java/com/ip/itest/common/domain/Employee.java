package com.ip.itest.common.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * https://dzone.com/articles/reduce-boilerplate-code-your
 * @author 999951
 *
 */
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Builder
public class Employee {
	@Getter
	@Setter
	@Id
	private String eId;
	@Getter
	@Setter
	private String eName;


}
