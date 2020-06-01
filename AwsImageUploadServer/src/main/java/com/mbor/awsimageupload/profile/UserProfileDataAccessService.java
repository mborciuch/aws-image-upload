package com.mbor.awsimageupload.profile;

import com.mbor.awsimageupload.datastore.FakeUserProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Repository
public class UserProfileDataAccessService {

    private final FakeUserProfileDataStore fakeUserProfileDataStore;

    @Autowired
    public UserProfileDataAccessService(FakeUserProfileDataStore fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
    }

    List<UserProfile> getUserProfiles(){
        return fakeUserProfileDataStore.getUserProfiles();
    }

    UserProfile getUser(UUID uuid){
        List<UserProfile> userProfiles = fakeUserProfileDataStore.getUserProfiles();
        return userProfiles.stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", uuid)));
    }

}
