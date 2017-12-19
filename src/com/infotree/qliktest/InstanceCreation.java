package com.infotree.qliktest;

import java.io.IOException;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.InstanceStatus;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;


public class InstanceCreation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		AWSCredentials credentials=null;
		try {
			credentials = new PropertiesCredentials(
			         InstanceCreation.class.getClassLoader().getResourceAsStream("aws.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		AmazonEC2 amazonEC2Client = new AmazonEC2Client(credentials);
		amazonEC2Client.setEndpoint("ec2.us-west-2.amazonaws.com");
		System.out.println("AmazonEC2Client object creation started....");
		
		//Instance creation from AMI
		RunInstancesRequest runInstancesRequest = 
				  new RunInstancesRequest();
			        	
			  runInstancesRequest.withImageId("ami-e35377d3")
			                     .withInstanceType("t1.micro")
			                     .withMinCount(1)
			                     .withMaxCount(1)
			                     .withKeyName("smishra")
			                     .withSecurityGroups("launch-wizard-1")
			                     ;
		RunInstancesResult runInstancesResult = 
					  amazonEC2Client.runInstances(runInstancesRequest);
		
		Reservation reservation=runInstancesResult.getReservation();
		
		List<Instance> instances=reservation.getInstances();
		String instanceId=null;
		for(Instance instance:instances){
			InstanceState instanceState=instance.getState();
			instanceId=instance.getInstanceId();
			System.out.println("Instance ID : "+instance.getInstanceId()+" , State : "+instanceState.getName());
			System.out.println("Private IP : "+instance.getPrivateIpAddress()+" Public IP : "+instance.getPublicIpAddress());
		}
		
		// client.setEndpoint("ec2." + region + ".amazonaws.com");
		  /*DescribeInstancesRequest desc=new DescribeInstancesRequest().withInstanceIds(instanceId);
		  DescribeInstancesResult res=amazonEC2Client.describeInstances(desc);
		  */
		  DescribeInstanceStatusRequest describeInstanceRequest = new DescribeInstanceStatusRequest().withInstanceIds(instanceId);
		  DescribeInstanceStatusResult describeInstanceResult = amazonEC2Client.describeInstanceStatus(describeInstanceRequest);
		  List<InstanceStatus> state = describeInstanceResult.getInstanceStatuses();
		  System.out.println("List size: "+state.size());
		  while (state.size() < 1) { 
		      // Do nothing, just wait, have thread sleep if needed
		      describeInstanceResult = amazonEC2Client.describeInstanceStatus(describeInstanceRequest);
		      
		      state = describeInstanceResult.getInstanceStatuses();
		      System.out.print("..");
		      
		  }
		  
		  System.out.println();
		  System.out.println("InstanceId: "+state.get(0).getInstanceId()+" , State : "+state.get(0).getInstanceState().getName());
		  System.out.println("\nState size : "+state.size());
		 
		  while(state.get(0).getInstanceStatus().getStatus().equalsIgnoreCase("initializing")){
			  
			  describeInstanceResult = amazonEC2Client.describeInstanceStatus(describeInstanceRequest);
			  state = describeInstanceResult.getInstanceStatuses();
			  System.out.println("Inside loop");
			  try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }
		  if(state.get(0).getInstanceStatus().getStatus().equalsIgnoreCase("OK")){
			  System.out.println("Now You can Connect to your Instance."+state.get(0).getInstanceStatus().getStatus()+" , ID:"+state.get(0).getInstanceId());
		  }
		  else
			  System.out.println("Again headache");
	/*	  String status = state.get(0).getInstanceState().getName();
		  //System.out.println(state.get(0).getInstanceStatus().getStatus());
		  String instanceId1=state.get(0).getInstanceId();
		  System.out.println("InstanceId : "+instanceId1+" , Status : "+status);
		  String statusValue=state.get(0).getInstanceStatus().getStatus();
		  System.out.println("Status Value:"+statusValue);
		  
		  while(state.get(0).getInstanceStatus().getStatus().equalsIgnoreCase("initializing")){
			 describeInstanceRequest = new DescribeInstanceStatusRequest().withInstanceIds(instanceId);
			 describeInstanceResult = amazonEC2Client.describeInstanceStatus(describeInstanceRequest);
			 state = describeInstanceResult.getInstanceStatuses();
			  while (state.size() < 1) { 
			      // Do nothing, just wait, have thread sleep if needed
			      describeInstanceResult = amazonEC2Client.describeInstanceStatus(describeInstanceRequest);
			      state = describeInstanceResult.getInstanceStatuses();
			      System.out.print("...");
			  }
			  System.out.println();
			 status = state.get(0).getInstanceState().getName();
			  
			  try {
				Thread.sleep(10000);
				System.out.print("..");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  System.out.println(state.get(0).getInstanceStatus().getStatus());
	*/	  
		  /*for (  Reservation resr : res.getReservations()) {
		    for (    Instance ins : resr.getInstances()) {
		    	
		    	for (com.amazonaws.services.ec2.model.Tag tag : ins.getTags()) {
		        //if (tag.getKey().equals("aws:autoscaling:groupName"))
		      }
		    }
		  }
		*/
		/*
		Reservation reservation=runInstancesResult.getReservation();
		List<Instance> instances=reservation.getInstances();
		
		for(Instance instance:instances)
		{
				System.out.println("ImageID: "+instance.getImageId()+" , InstanceID: "+instance.getInstanceId());
				com.amazonaws.services.ec2.model.InstanceState state=instance.getState();
				System.out.println(instance.getPublicDnsName()+","+instance.getPublicIpAddress()+","+instance.getSubnetId());
				System.out.println("after pending state becomes: "+state.getName());
		}
*/		
		  System.out.println("instance created");
		//starting and stopping Ec2 Instances
		//StartInstancesRequest startRequest = new StartInstancesRequest().withInstanceIds("i-e47b3be8");
		//StopInstancesRequest stopRequest = new StopInstancesRequest().withInstanceIds("i-17e8ab1b");
	    //StartInstancesResult startResult = ec2.startInstances(startRequest);
		//TerminateInstancesRequest terminateRequest= new TerminateInstancesRequest().withInstanceIds("i-be0240b2");
	    //StartInstancesResult startResult = amazonEC2Client.startInstances(startRequest);
	    // amazonEC2Client.startInstances(startRequest);
		//amazonEC2Client.terminateInstances(terminateRequest);
		//amazonEC2Client.stopInstances(stopRequest);
		//System.out.println("Instance Terminated . . . ");
	}

}
