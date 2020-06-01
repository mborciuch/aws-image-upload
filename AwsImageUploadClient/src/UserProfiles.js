import React, {useState, useEffect} from 'react';
import axios from 'axios';

const UserProfiles = () => {
    
    const fetUserProfiles = () => {
        axios.get("http://localhost:8080/api/v1/user-profile").then(response => {
            console.log(response);
            }
        )
    }

    useEffect(() => {
        fetUserProfiles();
    }, [])

    return <h1>Hello</h1>
    
};

export default UserProfiles;