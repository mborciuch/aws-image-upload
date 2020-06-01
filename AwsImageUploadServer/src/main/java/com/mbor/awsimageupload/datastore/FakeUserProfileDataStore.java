package com.mbor.awsimageupload.datastore;

import com.mbor.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
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
                        .setUserProfileId(UUID.randomUUID())
                        .setUserName("janetjones")
                        .setUserProfileImageLink(null)
                        .build(),
                UserProfile.userProfileBuilder()
                        .setUserProfileId(UUID.randomUUID())
                        .setUserName("antoniojunior")
                        .setUserProfileImageLink(null)
                        .build())
        );
    }
}
