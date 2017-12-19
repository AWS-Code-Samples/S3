package com.infotree.qliktest;

import java.io.File;
import java.io.IOException;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

public class MultipartFileUpload {

	public static void main(String[] args) {
		
		
		String bucketName = "qliktestvideostreaming";
		  String keyName = "sample1.mp4";
		  
		  String filePath = "D:/video/sample1.mp4";
		  File f=new File(filePath);
		  Upload myUpload=null;
		  TransferManager tx1=null;
			try {
				tx1 = new TransferManager(new PropertiesCredentials(MultipartFileUpload.class.getClassLoader()
						  										   .getResourceAsStream("aws.properties")));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
				
				PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, keyName, f);
			  
				myUpload= tx1.upload(putObjectRequest);
				
					if (myUpload.isDone() == false) {
						  System.out.println("Transfer: " + myUpload.getDescription());
						  System.out.println("  - State: " + myUpload.getState());
						  System.out.println("  - Progress: "+ myUpload.getProgress().getBytesTransferred());
						  }

	}

}
