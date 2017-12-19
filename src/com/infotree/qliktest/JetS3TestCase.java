package com.infotree.qliktest;

import org.jets3t.service.CloudFrontService;
import org.jets3t.service.CloudFrontServiceException;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.model.cloudfront.Distribution;
import org.jets3t.service.security.AWSCredentials;


public class JetS3TestCase {

	
	public static void main(String[] args) {
	
		String awsAccessKey = "AKIAIBJXJWKB2EMS6ZVA";
		String awsSecretKey = "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";
		AWSCredentials awsCredentials = 
			    new AWSCredentials(awsAccessKey, awsSecretKey);
		S3Service s3Service = null;
		try {
			s3Service = new RestS3Service(awsCredentials);
			S3Bucket[] buckets=s3Service.listAllBuckets();
			for(S3Bucket bucket:buckets){
				//System.out.println(bucket.getName()+" created on "+bucket.getCreationDate()+" at "+bucket.getLocation()+" and its created by "+bucket.getOwner().getDisplayName());
				S3Object[] objects=s3Service.listObjects(bucket);
				for(S3Object object:objects){
					System.out.println(object.getKey());
				}
			}
		} catch (S3ServiceException e) {
			e.printStackTrace();
		}
		
	}

}
