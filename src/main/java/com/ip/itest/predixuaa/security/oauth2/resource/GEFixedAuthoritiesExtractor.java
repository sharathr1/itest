/**
 * 
 */
package com.ip.itest.predixuaa.security.oauth2.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * GEFixedAuthoritiesExtractor
 * 
 * @author "Andy Wickersham (212425740)"
 *
 */
public class GEFixedAuthoritiesExtractor implements AuthoritiesExtractor {
		
	private static final String AUTHORITIES = "authorities";
	
	private static final String MEMBER_OF = "gevdsGroupIDmemberOf";

	private static final String[] AUTHORITY_KEYS = { "authority", "role", "value" };

	@Override
	public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
		String authorities = "ROLE_USER";
		if (map.containsKey(AUTHORITIES)) {
			authorities = asAuthorities(map.get(AUTHORITIES));
		} 
		
		if (map.containsKey(MEMBER_OF)) {
			if (authorities != null) {
				authorities = authorities + ", " + asAuthorities(map.get(MEMBER_OF));
			} else {
				authorities = asAuthorities(map.get(MEMBER_OF));
			}
		}
		return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
	}

	private String asAuthorities(Object object) {
		List<Object> authorities = new ArrayList<Object>();
		if (object instanceof Collection) {
			Collection<?> collection = (Collection<?>) object;
			object = collection.toArray(new Object[0]);
		}
		if (ObjectUtils.isArray(object)) {
			Object[] array = (Object[]) object;
			for (Object value : array) {
				if (value instanceof String) {
					authorities.add(value);
				}
				else if (value instanceof Map) {
					authorities.add(asAuthority((Map<?, ?>) value));
				}
				else {
					authorities.add(value);
				}
			}
			return StringUtils.collectionToCommaDelimitedString(authorities);
		}
		return object.toString();
	}

	private Object asAuthority(Map<?, ?> map) {
		if (map.size() == 1) {
			return map.values().iterator().next();
		}
		for (String key : AUTHORITY_KEYS) {
			if (map.containsKey(key)) {
				return map.get(key);
			}
		}
		return map;
	}

}
