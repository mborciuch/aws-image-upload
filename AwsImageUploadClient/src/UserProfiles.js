import React, {useState, useEffect} from 'react';
import axios from 'axios';
import MyDropzone from "./MyDropzone";

const UserProfiles = () => {

    const [userProfiles, setUserProfiles] = useState([]);

    const fetchUserProfiles = () => {
        axios.get("http://localhost:8080/api/v1/user-profile").then(response => {
            setUserProfiles(response.data);
            }
        )
    };

    useEffect(() => {
        fetchUserProfiles();
    }, []);

    return userProfiles.map((userProfile, index) => {
        return (
            <div key={index}>
                <h1>{userProfile.userName}</h1>
                <p>{userProfile.userProfileId}</p>
                <MyDropzone userProfileId={userProfile.userProfileId}/>
            </div>
        )
    })
    
};

export default UserProfiles;