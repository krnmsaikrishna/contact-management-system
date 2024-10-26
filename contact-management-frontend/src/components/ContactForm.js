import React, { useState, useEffect } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';

const ContactForm = ({ show, handleClose, handleSubmit, contact }) => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    emailId: '',
    mobile: '',
    address: ''
  });
  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (contact) {
      setFormData(contact);
    }
  }, [contact]);

  const validateForm = () => {
    const newErrors = {};
    if (!/^[a-zA-Z]+$/.test(formData.firstName)) {
      newErrors.firstName = 'First name must contain only alphabets';
    }
    if (!/^[a-zA-Z]+$/.test(formData.lastName)) {
      newErrors.lastName = 'Last name must contain only alphabets';
    }
    if (!/^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/.test(formData.emailId)) {
      newErrors.emailId = 'Invalid email format';
    }
    if (!/^\d{10}$/.test(formData.mobile)) {
      newErrors.mobile = 'Mobile must be 10 digits';
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const onSubmit = (e) => {
    e.preventDefault();
    if (validateForm()) {
      handleSubmit(formData);
      handleClose();
    }
  };

  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{contact ? 'Edit Contact' : 'Add Contact'}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={onSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>First Name*</Form.Label>
            <Form.Control
              type="text"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
              isInvalid={!!errors.firstName}
            />
            <Form.Control.Feedback type="invalid">
              {errors.firstName}
            </Form.Control.Feedback>
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Last Name*</Form.Label>
            <Form.Control
              type="text"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
              isInvalid={!!errors.lastName}
            />
            <Form.Control.Feedback type="invalid">
              {errors.lastName}
            </Form.Control.Feedback>
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Email*</Form.Label>
            <Form.Control
              type="email"
              name="emailId"
              value={formData.emailId}
              onChange={handleChange}
              isInvalid={!!errors.emailId}
            />
            <Form.Control.Feedback type="invalid">
              {errors.emailId}
            </Form.Control.Feedback>
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Mobile*</Form.Label>
            <Form.Control
              type="text"
              name="mobile"
              value={formData.mobile}
              onChange={handleChange}
              isInvalid={!!errors.mobile}
            />
            <Form.Control.Feedback type="invalid">
              {errors.mobile}
            </Form.Control.Feedback>
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Address</Form.Label>
            <Form.Control
              as="textarea"
              name="address"
              value={formData.address}
              onChange={handleChange}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Cancel
        </Button>
        <Button variant="primary" onClick={onSubmit}>
          {contact ? 'Update' : 'Save'}
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ContactForm;
