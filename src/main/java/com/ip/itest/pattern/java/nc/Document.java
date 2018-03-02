package com.ip.itest.pattern.java.nc;

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
public class Document {
private String _id;
private String _rev;
private String nc_sequence;
private String requestor_uri;
private String status;

}
