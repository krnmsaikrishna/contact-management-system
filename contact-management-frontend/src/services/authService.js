import axios from 'axios';

const authService = axios.create({
  baseURL: 'http://localhost:8080/api/contacts',
});

authService.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  error => Promise.reject(error)
);

export default authService;
