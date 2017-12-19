package com.infotree.qliktest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.glacier.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;
import com.amazonaws.services.s3.model.ListMultipartUploadsRequest;
import com.amazonaws.services.s3.model.MultipartUpload;
import com.amazonaws.services.s3.model.MultipartUploadListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.Region;


public class FileUploadToAmazonS3 {

	
	public static void main(String[] args) {
		
		  String bucketName = "1-infotree";
		  String keyName = "videos/5/1/testscenario/102.pdf";
		  
		  String filePath = "D:/image/aaa.jpg";
		  String uploadLocation=bucketName;

		  try {
				  	AmazonS3 s3Client = new AmazonS3Client(new PropertiesCredentials(FileUploadToAmazonS3.class.getClassLoader()
				  										   .getResourceAsStream("aws.properties")));
					FileInputStream stream = new FileInputStream(filePath);
					ObjectMetadata objectMetadata = new ObjectMetadata();
					Date d=new Date();
					d.setMinutes(d.getMinutes()+5);
					System.out.println(s3Client.generatePresignedUrl(bucketName, keyName, d));
					/*File f=new File(filePath);
					PutObjectRequest putObjectRequest = new PutObjectRequest(uploadLocation, keyName, f);
					
					//PutObjectRequest putObjectRequest = new PutObjectRequest(uploadLocation, keyName, stream,objectMetadata);
					s3Client.putObject(putObjectRequest);
					System.out.println("check inside bucket.");*/
					//PutObjectResult result = s3Client.putObject(putObjectRequest);
					/*Date date=new Date();
					date.setMinutes(date.getMinutes()+5);
					System.out.println("Time in mili seconds : "+date.getTime());
					String url=s3Client.generatePresignedUrl(bucketName, keyName,date).toString();
					System.out.println("URL 1 : "+url);
					GeneratePresignedUrlRequest request=new GeneratePresignedUrlRequest(bucketName, keyName);
					request.setExpiration(date);
					String url2=s3Client.generatePresignedUrl(request).toString();
					System.out.println("URL 2  : "+url2);
		
					String url3=s3Client.generatePresignedUrl(bucketName, keyName, date, HttpMethod.GET).toString();
					System.out.println("URL 3  : "+url3);
					*/
					//s3Client.generate
					
					//System.out.println("our URL is : "+url);
					
					/*
					GeneratePresignedUrlRequest grequest=new GeneratePresignedUrlRequest(bucketName, keyName);
					//System.out.println(s3Client.generatePresignedUrl(grequest));
					URL url=s3Client.generatePresignedUrl(grequest);
					
					String urlOfBucket="http://s3-";
					urlOfBucket+=s3Client.getBucketLocation("qliktestvideostreaming");
					urlOfBucket+=".amazon.com/";
					
					System.out.println(s3Client.getBucketLocation("qliktestvideostreaming"));
					
					
					System.out.println("File: "+url.getFile());
					System.out.println("Host  : "+url.getHost());
					System.out.println("Authority : "+url.getAuthority());
					System.out.println("Path : "+url.getPath());
					
					System.out.println(result.getETag());
					*/
				  	/*
				  	//Displaying all buckets
				  	List<Bucket> list=s3Client.listBuckets();
				  	for(Bucket b:list){
				  		System.out.println(b.getName()+"  ,"+b.getCreationDate()+" ,  "+b.getOwner());
				  	}*/
					/*s3Client.createBucket("infotree-crossbrowser-testing",Region.AP_Singapore);	  	
					System.out.println("Bucket created .....");*/
						  	
				  	/*s3Client.createBucket("qliktestbucket", Region.AP_Singapore);
				  	System.out.println("bucket created....");*/
					
					//Listing the files 
				  	
					
					
		  } 
		  catch (IOException e) {
			e.printStackTrace();
		}

		  
	}

}
