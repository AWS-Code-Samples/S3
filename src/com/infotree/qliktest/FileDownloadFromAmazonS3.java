package com.infotree.qliktest;

import java.util.List;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class FileDownloadFromAmazonS3 {

	public static void main(String[] args) {
		
		String bucketName = "qliktest1";
		String keyName 	  = "test/satya/myimage123.jpg";
		AmazonS3 s3Client = null;
		try {
			s3Client = new AmazonS3Client(new PropertiesCredentials(
					  FileDownloadFromAmazonS3.class.getClassLoader()
					  .getResourceAsStream("aws.properties")));
			GetObjectRequest request = new GetObjectRequest(bucketName,keyName);
			String mystr=keyName.substring(0,keyName.lastIndexOf("/"));
			
			ObjectMetadata object=s3Client.getObjectMetadata(bucketName, keyName);
			System.out.println(object.getCacheControl());
			/*System.out.println("FileLocation: "+mystr);
		     ObjectListing lists=s3Client.listObjects(bucketName,mystr);
		     
		     List<S3ObjectSummary> list=lists.getObjectSummaries();
		     
		     for(S3ObjectSummary l:list){
		    	 System.out.println(l.getKey());
		     }
		     
		     
		     System.out.println("list size: "+list.size());*/
			/*
			
			Date d1=new Date();
			d1.setDate(d1.getDate()+1);
			String url=s3Client.generatePresignedUrl(bucketName, keyName, d1).toString();
			System.out.println("completed : "+url);
			
			S3Object object = s3Client.getObject(request);
			IOUtils.copy(object.getObjectContent(), new FileOutputStream("D://upload//image123.jpg"));
			System.out.println("File downloaded from Amazon S3.Check upload folder in D drive");
		*/} catch (com.amazonaws.services.s3.model.AmazonS3Exception e) {
			System.out.println("Hey your key not there inside bucket");
			//e.printStackTrace();
		} 
		catch(Exception e){
			System.out.println("Other exception");
		}
	}

}
