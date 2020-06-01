package com.mbor.awsimageupload.datastore;

import com.mbor.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private  final List<UserProfile> USER_PROFILES = new ArrayList<>();

    public  List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }

    @PostConstruct
    void bootstrap() {
        USER_PROFILES.addAll(Arrays.asList(
                UserProfile.userProfileBuilder()
                        .setUserProfileId(UUID.fromString("60115c62-bfd9-4b69-9f00-147c5ad6815e"))
                        .setUserName("janetjones")
                        .setUserProfileImageLink(null)
                        .build(),
                UserProfile.userProfileBuilder()
                        .setUserProfileId(UUID.fromString("1287e0b9-3d0f-4243-91d8-ca4202a902d0"))
                        .setUserName("antoniojunior")
                        .setUserProfileImageLink(null)
                        .build())
        );
    }
}
