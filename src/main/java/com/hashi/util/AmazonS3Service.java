package com.hashi.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import org.jclouds.ContextBuilder;
import org.jclouds.aws.s3.blobstore.AWSS3BlobStoreContext;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.blobstore.domain.Blob;
import org.jclouds.blobstore.domain.BlobAccess;
import org.jclouds.blobstore.options.ListContainerOptions;
import org.jclouds.blobstore.options.PutOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hashi.config.ApplicationConfig;




import com.google.common.io.ByteSource;
import com.google.common.io.Files;

@Service
public class AmazonS3Service {

	private final static Logger logger = Logger
			.getLogger(AmazonS3Service.class.getName());

	private final static String FOLDER_SUFFIX = "/";

	@Autowired
	private ApplicationConfig config;

    
	@Async
	public void asyncUploadS3Files(List<File> files, Long userId, String bucketName) throws IOException {
 	   	
	    	BlobStoreContext context = 
	        ContextBuilder.newBuilder("aws-s3").credentials(config.getS3Accesskey(), config.getS3Secretkey()).
	        buildView(AWSS3BlobStoreContext.class);
	    		 
	    	// Access the BlobStore
	    	BlobStore  blobStore = context.getBlobStore();
	    	
	    	// Create a Container
	        blobStore.createContainerInLocation(null, bucketName);
	        //check if the folder [userId] exist, if does then delete the content 
	        if(blobStore.directoryExists(bucketName, userId.toString())){
	        	blobStore.clearContainer(bucketName, new ListContainerOptions().inDirectory(userId.toString()));
	        }else{
	        	blobStore.createDirectory(bucketName, userId.toString());
	        }
	        
	        for (File file : files) {
	        	ByteSource payload = Files.asByteSource(file);
	        	Blob blob = blobStore.blobBuilder(userId.toString() + FOLDER_SUFFIX +  file.getName())
	        			.payload(payload)
	        			.contentLength(payload.size())
	        			.build();
	        	blobStore.putBlob(bucketName, blob, PutOptions.Builder.multipart()); 
	   	        blobStore.setBlobAccess(bucketName, userId.toString() + FOLDER_SUFFIX +  file.getName() , BlobAccess.PUBLIC_READ);
	        	
	        }	     
	        // Don't forget to close the context when yourt('re done!
	        context.close();
	        System.out.println("Created Folder and File");
	   }
	
    
    
    @Async
    public void asyncDeleteS3Directory(Long userId, String bucketName) {
    	   	
    	BlobStoreContext context = 
        ContextBuilder.newBuilder("aws-s3").credentials(config.getS3Accesskey(), config.getS3Secretkey()).
        buildView(AWSS3BlobStoreContext.class);
    		 
    	// Access the BlobStore
    	BlobStore  blobStore = context.getBlobStore();
        // Upload the Blob
    	
        blobStore.createContainerInLocation(null, bucketName);        
        blobStore.clearContainer(bucketName, new ListContainerOptions().inDirectory(userId.toString()));
        blobStore.deleteDirectory(bucketName, userId.toString());
        // Don't forget to close the context when you're done!
        context.close();
        System.out.println("Deleted Folder");
   }
    
    
    @Async
    public void asyncUploadS3File(File profileFile, Long userId, String bucketName) throws IOException {
    	   	
    	BlobStoreContext context = 
        ContextBuilder.newBuilder("aws-s3").credentials(config.getS3Accesskey(), config.getS3Secretkey()).
        buildView(AWSS3BlobStoreContext.class);
    		 
    	// Access the BlobStore
    	BlobStore  blobStore = context.getBlobStore();
    	
    	// Create a Container
        blobStore.createContainerInLocation(null, bucketName);
         
        ByteSource payload = Files.asByteSource(profileFile);
        Blob blob = blobStore.blobBuilder(userId.toString() + FOLDER_SUFFIX +  profileFile.getName())
            .payload(payload)
            .contentLength(payload.size())
            .build();
        
        //check if the folder [userId] exist, if does then delete the content 
        if(blobStore.directoryExists(bucketName, userId.toString())){
        	blobStore.clearContainer(bucketName, new ListContainerOptions().inDirectory(userId.toString()));
        }else{
        	blobStore.createDirectory(bucketName, userId.toString());
        }
  
        // Upload the Blob
        blobStore.putBlob(bucketName, blob, PutOptions.Builder.multipart()); 
        blobStore.setBlobAccess(bucketName, userId.toString() + FOLDER_SUFFIX +  profileFile.getName() , BlobAccess.PUBLIC_READ);

        // Don't forget to close the context when yourt('re done!
        context.close();
        System.out.println("Created Folder and File");
   }
    
    
    public void syncUploadS3File(File profileFile, Long userId, String bucketName) throws IOException {   	
    	BlobStoreContext context = 
        ContextBuilder.newBuilder("aws-s3").credentials(config.getS3Accesskey(), config.getS3Secretkey()).
        buildView(AWSS3BlobStoreContext.class);
    		 
    	// Access the BlobStore
    	BlobStore  blobStore = context.getBlobStore();

    	// Create a Container
        blobStore.createContainerInLocation(null, bucketName);
      
        ByteSource payload = Files.asByteSource(profileFile);
        Blob blob = blobStore.blobBuilder(userId.toString() + FOLDER_SUFFIX +  profileFile.getName())
            .payload(payload)
            .contentLength(payload.size())
            .build();
        
        //check if the folder [userId] exist, if does then delete the content 
        if(blobStore.directoryExists(bucketName, userId.toString())){
        	blobStore.clearContainer(bucketName, new ListContainerOptions().inDirectory(userId.toString()));
        }else{
        	blobStore.createDirectory(bucketName, userId.toString());
        }
        // Upload the Blob
        blobStore.putBlob(bucketName, blob, PutOptions.Builder.multipart()); 
        blobStore.setBlobAccess(bucketName, userId.toString() + FOLDER_SUFFIX +  profileFile.getName() , BlobAccess.PUBLIC_READ);

        // Don't forget to close the context when yourt('re done!
        context.close();
        System.out.println("Created Folder and File");
   }

}