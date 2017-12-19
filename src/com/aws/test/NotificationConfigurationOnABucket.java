package com.aws.test;

import java.io.IOException;
import java.util.EnumSet;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.BucketNotificationConfiguration;
import com.amazonaws.services.s3.model.QueueConfiguration;
import com.amazonaws.services.s3.model.S3Event;
import com.amazonaws.services.s3.model.SetBucketNotificationConfigurationRequest;
import com.amazonaws.services.s3.model.TopicConfiguration;

public class NotificationConfigurationOnABucket {
    private static String bucketName = "*** bucket name ***";
    private static String snsTopicARN = "*** SNS Topic ARN ***";
    private static String sqsQueueARN = "*** SQS Queue ARN ***";

    public static void main(String[] args) throws IOException {
        AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
        try {
            System.out.println("Setting notification configuration on a bucket.\n");

            BucketNotificationConfiguration notificationConfiguration = new BucketNotificationConfiguration();
            notificationConfiguration.addConfiguration(
                    "snsTopicConfig",
                    new TopicConfiguration(snsTopicARN, EnumSet
                            .of(S3Event.ReducedRedundancyLostObject)));

            notificationConfiguration.addConfiguration(
                    "sqsQueueConfig",
                    new QueueConfiguration(sqsQueueARN, EnumSet
                            .of(S3Event.ObjectCreated)));

            SetBucketNotificationConfigurationRequest request = 
                    new SetBucketNotificationConfigurationRequest(bucketName, notificationConfiguration);

            s3client.setBucketNotificationConfiguration(request);

        } catch (AmazonS3Exception ase) {
            System.out.println("Caught an AmazonServiceException, which "
                    + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response"
                    + " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            System.out.println("Error XML" + ase.getErrorResponseXml());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which "
                    + "means the client encountered "
                    + "an internal error while trying to "
                    + "communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
}