/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

public class CouchBaseDB {
	public static void main(String[] args) {
		CouchbaseEnvironment cEnv= DefaultCouchbaseEnvironment.builder().connectTimeout(10000).build();
		// Create a cluster reference
		CouchbaseCluster cluster = CouchbaseCluster.create(cEnv,"couchbase://localhost");

		// Connect to the bucket and open i
		Bucket bucket = cluster.openBucket("default");

		// Create a JSON document and store it with the ID "helloworld"
		JsonObject content = JsonObject.create().put("hello", "world");
		JsonDocument inserted = bucket.upsert(JsonDocument.create("helloworld", content));

		// Read the document and print the "hello" field
		JsonDocument found = bucket.get("helloworld");
		System.out.println("Couchbase is the best database in the " + found.content().getString("hello"));

		// Close all buckets and disconnect
		cluster.disconnect();
	}
}
