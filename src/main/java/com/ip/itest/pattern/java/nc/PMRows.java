package com.ip.itest.pattern.java.nc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ip.itest.pattern.java.nc.PMDocuments.PMDocumentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class PMRows {
private String key;
private String id;
private Document doc;

}
