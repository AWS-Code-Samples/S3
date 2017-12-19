package com.infotree.qliktest;

import java.io.IOException;
import java.util.List;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class NumberOfFiles {

	
	public static void main(String[] args) {
		
		String bucketName = "1-infotree";
		try {
			
			AmazonS3 s3Client = new AmazonS3Client(new PropertiesCredentials(FileUploadToAmazonS3.class.getClassLoader()
					   .getResourceAsStream("aws.properties")));
			String mystr="import-test-cases/5/1/qliktest";
		    ObjectListing lists=s3Client.listObjects(bucketName,mystr);
		     System.out.println("mystr:"+mystr);
		     List<S3ObjectSummary> list=lists.getObjectSummaries();
		     System.out.println("list size: "+list.size());
		     boolean flag=false;
		     for(S3ObjectSummary object:list){
		    	 String folderName=object.getKey();
		    	 System.out.println("Folder Name : "+folderName);
		    	 String str[]=folderName.split("/");
		    	
		    	 if(str.length>3){
		    		 if(str[3].equals(mystr.substring(mystr.lastIndexOf("/")+1))){
		    			 flag=true;
		    		 }
		    	 }
		    	 
		    	 //System.out.println(object.getBucketName()+"\t"+object.getKey());
		     }
		     System.out.println("flag:"+flag);
			
			/*
			
			System.out.println("started...");
			ListMultipartUploadsRequest allMultpartUploadsRequest = 
				     new ListMultipartUploadsRequest(bucketName);
				MultipartUploadListing multipartUploadListing = 
				     s3Client.listMultipartUploads(allMultpartUploadsRequest);
				List<MultipartUpload> lists=multipartUploadListing.getMultipartUploads();
				System.out.println("List size  :  "+lists.size());
				for(MultipartUpload list:lists){
					System.out.println(list.getOwner().getDisplayName());
				}*/	
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

}
