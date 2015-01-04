/**
 * Copyright (C) 2015 BITPlan GmbH
 *
 * Pater-Delp-Str. 1
 * D-47877 Willich-Schiefbahn
 *
 * http://www.bitplan.com
 * 
 */
package com.bitplan.mediawiki.japi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.apache.ApacheHttpClient;

public class TestGetCSV {

	private ApacheHttpClient client;

	/**
	 * public String getCSVAsString(String urlString) { }
	 */

	/**
	 * get the a CSV File from the given urlString
	 * 
	 * @param urlString
	 * @param csvFile
	 * @throws IOException
	 */
	public void getCSVAsFile(String urlString, File csvFile) throws IOException {
		client = ApacheHttpClient.create();
		WebResource webResource = client.resource(urlString);
		WebResource.Builder wb = webResource
				.accept("application/json,application/pdf,text/plain,image/jpeg,application/xml,application/vnd.ms-excel");
		ClientResponse response = wb.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP error code : " + response.getStatus());
		}

		InputStream input = response.getEntity(InputStream.class);

		byte[] byteArray = org.apache.commons.io.IOUtils.toByteArray(input);

		FileOutputStream fos = new FileOutputStream(csvFile);
		fos.write(byteArray);
		fos.flush();
		fos.close();
	}

	@Test
	public void testGetCSV() throws IOException {
		File csvFile=new File("/tmp/ExampleWikis.csv");
		getCSVAsFile("http://mediawiki-japi.bitplan.com/mediawiki-japi/index.php/Special:Ask/-5B-5BCategory:ExampleWiki-5D-5D-20-5B-5Bsiteurl::%2B-5D-5D/-3FSiteurl/-3FWikiid/format%3Dcsv/offset%3D0", csvFile);
	  String csv=FileUtils.readFileToString(csvFile);
	}
}