import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button, Alert, Navbar, Nav  } from 'react-bootstrap';
import { FaPlusCircle, FaSignOutAlt, FaUserCircle  } from 'react-icons/fa';
import ContactCard from './ContactCard';
import ContactForm from './ContactForm';
import contactService from '../services/contactService';
import { MdOutlineMergeType } from 'react-icons/md';



const Dashboard = () => {
  const [contacts, setContacts] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [selectedContact, setSelectedContact] = useState(null);
  const [alert, setAlert] = useState(null);
  const [username, setUsername] = useState("");

      const fetchContacts = async () => {
      try {
        const response = await contactService.getAllContacts();
        setContacts(response.data);
      } catch (error) {
        showAlert('Error fetching contacts! Maybe you dont have permission to perform this action', 'danger');
      }
    };

  useEffect(() => {
    const username = localStorage.getItem("username");
    setUsername(username);
  fetchContacts();
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    window.location.href = "/login";
  };



  useEffect(() => {



  
  }, []);

  const showAlert = (message, type = 'success') => {
    setAlert({ message, type });
    setTimeout(() => setAlert(null), 3000);
  };

  const handleSubmit = async (contactData) => {
    try {
      if (selectedContact) {
        await contactService.updateContact(selectedContact.id, contactData);
        showAlert('Contact updated successfully');
      } else {
        await contactService.createContact(contactData);
        showAlert('Contact created successfully');
      }
      fetchContacts();
      handleCloseForm();
    } catch (error) {
      showAlert(error.response?.data?.message || 'Error processing request', 'danger');
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this contact?')) {
      try {
        await contactService.deleteContact(id);
        showAlert('Contact deleted successfully');
        fetchContacts();
      } catch (error) {
        showAlert('Error deleting contact! Maybe you dont have permission to perform this action', 'danger');
      }
    }
  };

  const handleEdit = (contact) => {
    setSelectedContact(contact);
    setShowForm(true);
  };

  const handleCloseForm = () => {
    setShowForm(false);
    setSelectedContact(null);
  };

  const handleMergeDuplicates = async (mergeBy) => {
    try {
      await contactService.mergeDuplicates(mergeBy);
      showAlert('Duplicate contacts merged successfully');
      fetchContacts();
    } catch (error) {
      showAlert('Error merging duplicate contacts! Maybe you dont have permission to perform this action', 'danger');
    }
  };

  return (

    <>
   <div>
   <Navbar bg="light" expand="lg">
        <Container>
          <Navbar.Brand href="#home">MyDashboard</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav" className="d-flex justify-content-between">
            <Nav className="d-flex align-items-center">
              {username && (
                <Navbar.Text className="d-flex align-items-center mr-3">
                  <FaUserCircle className="mr-2" style={{ verticalAlign: 'middle' }} />
                  <span style={{ verticalAlign: 'middle' }}>{username}</span>
                </Navbar.Text>
              )}
            </Nav>
            <Nav className="d-flex align-items-center">
              <FaSignOutAlt className="cursor-pointer" size={18} onClick={handleLogout} style={{ verticalAlign: 'middle' }} />
<span>Logout</span>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      
    </div>

    
    <Container className="py-4">
      {alert && (
        <Alert variant={alert.type} className="mb-4">
          {alert.message}
        </Alert>
      )}
      
      <div className="d-flex justify-content-between align-items-center mb-4">
        <div>
          <Button variant="primary" onClick={() => setShowForm(true)} className="me-2">
           <FaPlusCircle /> Add Contact
          </Button>
          <Button variant="secondary" onClick={() => handleMergeDuplicates('email')}>
            <MdOutlineMergeType /> Merge Duplicates (By Email)
          </Button>
        </div>
      </div>

      <Row>
        {contacts.map(contact => (
          <Col key={contact.id} xs={12} md={6} lg={4}>
            <ContactCard
              contact={contact}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          </Col>
        ))}
      </Row>

      <ContactForm
        show={showForm}
        handleClose={handleCloseForm}
        handleSubmit={handleSubmit}
        contact={selectedContact}
      />
    </Container>

    </>
  );
};

export default Dashboard;
