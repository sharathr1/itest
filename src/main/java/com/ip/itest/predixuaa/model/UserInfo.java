package com.ip.itest.predixuaa.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * UserInfo
 * 
 * @author Andy Wickersham (212425740)
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class UserInfo implements Serializable {

	private static long serialVersionUID = -5397509916926006362L;

	private String sub;

	private String country;

	private String employeetype;

	private String firstname;

	private String gehrbusinesssegment;

	private String mail;

	private String gessobusinessunit;

	private String title;

	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private String[] gevdsGroupIDmemberOf;

	private String lastname;

	private String gessocompanyname;

	private String gehrindustrygroup;

	private String gessojobfunction;

	@JsonProperty("phone_number")
	private String phoneNumber;

	private String street;

	private String location;

	private String state;

	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("user_name")
	private String userName;

	@JsonProperty("given_name")
	private String givenName;

	@JsonProperty("family_name")
	private String familyName;

	private String email;

	private String name;

	public String getUserLogon() {
		if (getUserId() == null) {
			return getSub();
		}

		return getUserId();
	}
}
