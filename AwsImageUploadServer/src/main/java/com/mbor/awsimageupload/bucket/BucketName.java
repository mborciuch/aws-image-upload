package com.mbor.awsimageupload.bucket;

import org.springframework.beans.factory.annotation.Value;

public enum BucketName {

    PROFILE_IMAGE("mbor-image-upload");

    private String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
