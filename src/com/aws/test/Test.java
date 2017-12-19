package com.aws.test;

import java.io.File;
import java.io.InputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class Test {
  public static void main(String[] args) throws Exception {
	  
	  // presigned url upload
	  
	  File fileToUpload = new File("E:\\SampleVideo.mp4");
	  HttpPut putreq = new HttpPut(URI.create("https://chalapathisvssr.s3.amazonaws.com/sample/samplevideo?AWSAccessKeyId=AKIAI2EBDM4A7M7G6RXQ&Expires=1512740338&Signature=Y61f%2FA2Do801TYZRVqGMUDKOQCg%3D"));
	  // AES256 is currently the only supported algorithm for SSE-S3
	 /* putreq.addHeader(new BasicHeader(Headers.SERVER_SIDE_ENCRYPTION,
	      SSEAlgorithm.AES256.getAlgorithm()));*/
	  putreq.setEntity(new FileEntity(fileToUpload));
	  CloseableHttpClient httpclient = HttpClients.createDefault();
	  httpclient.execute(putreq);
	  
	  // presigned url Get
	  
	  HttpGet getreq = new HttpGet(URI.create("https://chalapathisvssr.s3.amazonaws.com/sample/s3.txt?AWSAccessKeyId=AKIAI2EBDM4A7M7G6RXQ&Expires=1512736039&Signature=2%2FyHDC4l0Ueo93yrGxfsKEDAEI4%3D"));
	  /*CloseableHttpClient*/  httpclient = HttpClients.createDefault();
	  CloseableHttpResponse res = httpclient.execute(getreq);
	  InputStream is = res.getEntity().getContent();
	  String actual = IOUtils.toString(is);
	  System.out.println(actual);
  }
}