import axios from 'axios';

export const login = (username, password) => {
    const requestOptions = {
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    };
    return axios.post('http://localhost:8070/loginController',requestOptions)
        .then((res) => {
            console.log(res.data);
            
        })
        .catch(err => {
            console.error(err)
        });
}


export const logout = () => {
    // remove user from local storage to log user out
    sessionStorage.removeItem('user');
}

export const register = (user) => {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    };
    return fetch('/users/register', requestOptions).then(handleResponse);
}


export const handleResponse = (response) => {
    if (!response.ok) { 
        return Promise.reject(response.statusText);
    }
    return response.json();
}