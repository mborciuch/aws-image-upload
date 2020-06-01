package com.mbor.awsimageupload.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {

    private UUID userProfileId;
    private String userName;
    private String userProfileImageLink;


    public UserProfile(UUID userProfileId, String userName, String userProfileImageLink) {
        this.userProfileId = userProfileId;
        this.userName = userName;
        this.userProfileImageLink = userProfileImageLink;
    }

    public UUID getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(UUID userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(userProfileImageLink);
    }

    public void setUserProfileImageLink(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    public  static UserProfileBuilder userProfileBuilder(){
        return new UserProfileBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileId, that.userProfileId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userProfileImageLink, that.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, userName, userProfileImageLink);
    }

    public static class UserProfileBuilder {

        private UUID userProfileId;
        private String userName;
        private String userProfileImageLink;

        public UserProfileBuilder() {
        }


        public UserProfileBuilder setUserProfileId(UUID uuid) {
            this.userProfileId = uuid;
            return this;
        }

        public UserProfileBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserProfileBuilder setUserProfileImageLink(String userProfileImageLink) {
            this.userProfileImageLink = userProfileImageLink;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(userProfileId, userName, userProfileImageLink);
        }
    }
}


