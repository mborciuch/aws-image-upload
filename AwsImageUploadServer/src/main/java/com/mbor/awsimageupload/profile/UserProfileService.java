package com.mbor.awsimageupload.profile;

import com.mbor.awsimageupload.bucket.BucketName;
import com.mbor.awsimageupload.filestore.FileStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;

    private final FileStore fileStore;

    private final List<String> AVAILABLE_IMAGE_FORMATS = new ArrayList<>();

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    @PostConstruct
    public void bootstrap(){
        AVAILABLE_IMAGE_FORMATS.addAll(Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType()));
    }

    public List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID uuid, MultipartFile file) {
        isFileEmpty(file);
        isFileAnImage(file);
        UserProfile user = userProfileDataAccessService.getUser(uuid);
        Map<String, String> metaData = prepareMetaData(file);
        InputStream inputStream = getInputStream(file);
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        user.setUserProfileImageLink(filename);
        fileStore.save(path, filename, inputStream, Optional.of(metaData) );

    }

    private InputStream getInputStream(MultipartFile file) {
        InputStream inputStream;
        try  {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new IllegalStateException("Unexpected error during getting data", e);
        }
        return inputStream;
    }

    private Map<String, String> prepareMetaData(MultipartFile file) {
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));
        return metaData;
    }

    private void isFileAnImage(MultipartFile file) {
        if(!AVAILABLE_IMAGE_FORMATS.contains(file.getContentType())){
            throw new IllegalStateException("File is not an image");
         }
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("File is empty");
        }
    }
}
