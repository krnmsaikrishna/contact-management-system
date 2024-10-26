import React, { useState } from "react";
import axios from "axios";
import { Form, Button, Container, Row, Col, Toast } from 'react-bootstrap';
import { FaUserCircle } from 'react-icons/fa';
import { Link, useNavigate } from 'react-router-dom';

const Register = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [roles, setRoles] = useState([]);
  const [showToast, setShowToast] = useState(false);
  const [toastMessage, setToastMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/api/auth/register", { username, password, roles });
    setToastMessage("Registration successful! Redirecting to login...");
    setShowToast(true);
    setTimeout(() => {
      navigate("/login");
    }, 3000);
  
    } catch (error) {
        setToastMessage("Registration failed. Please try again.");
        setShowToast(true);
    }
  };

  const handleRoleChange = (e) => {
    const { value, checked } = e.target;
    setRoles((prev) => (checked ? [...prev, value] : prev.filter((role) => role !== value)));
  };

  return (
    <div className="d-flex align-items-center justify-content-center vh-100">
      <Container>
        <Row className="justify-content-md-center">
          <Col md="6" lg="4">
            <div className="p-4 rounded shadow" style={{ borderRadius: '15px' }}>
              <div className="text-center mb-4">
                <FaUserCircle size={70} />
              </div>
              <h2 className="text-center">Register</h2>
              <Form onSubmit={handleSubmit} className="mt-4">
                <Form.Group controlId="formUsername">
                  <Form.Label>Username</Form.Label>
                  <Form.Control
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                  />
                </Form.Group>
                <Form.Group controlId="formPassword">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                  />
                </Form.Group>
                <Form.Group controlId="formRoles">
                  <Form.Label>Roles</Form.Label>
                  <Form.Check
                    type="checkbox"
                    label="User"
                    value="ROLE_USER"
                    onChange={handleRoleChange}
                  />
                  <Form.Check
                    type="checkbox"
                    label="Admin"
                    value="ROLE_ADMIN"
                    onChange={handleRoleChange}
                  />
                </Form.Group>
                <Button variant="primary" type="submit" block>Register</Button>
              </Form>
              <div className="text-center mt-3">
                <Link to="/login">Already have an account? Login here</Link>
              </div>
            </div>
          </Col>
        </Row>
      </Container>

      <Toast
        onClose={() => setShowToast(false)}
        show={showToast}
        delay={3000}
        autohide
        style={{ position: 'fixed', top: 20, right: 20 }}
      >
        <Toast.Body>{toastMessage}</Toast.Body>
      </Toast>
    
    </div>
  );
};

export default Register;
