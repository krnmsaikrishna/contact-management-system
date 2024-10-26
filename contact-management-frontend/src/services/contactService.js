// import authService from 'authService';
import authService from './authService';

const API_URL = '';

const contactService = {
  
  getAllContacts: () => {
    return authService.get(`${API_URL}/getAllContacts`);
  },

  getContactById: (id) => {
    return authService.get(`${API_URL}/getContact/${id}`);
  },

  mergeDuplicates: (mergeBy) => {
    return authService.get(`${API_URL}/mergeContacts/${mergeBy}`);
  },

  createContact: (contact) => {
    return authService.post(`${API_URL}/createContact`, contact);
  },

  updateContact: (id, contact) => {
    return authService.put(`${API_URL}/updateContact/${id}`, contact);
  },

  deleteContact: (id) => {
    return authService.delete(`${API_URL}/deleteContact/${id}`);
  },
};

export default contactService;
