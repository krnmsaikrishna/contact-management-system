import React from 'react';
import { Card, Button } from 'react-bootstrap';
import { CgProfile } from 'react-icons/cg';
import { FaEdit, FaPhoneAlt, FaTrash } from 'react-icons/fa';
import { FaLocationDot } from 'react-icons/fa6';
import { IoMdMail } from 'react-icons/io';

const ContactCard = ({ contact, onEdit, onDelete }) => {
  return (
    <Card className="mb-3">
      <Card.Body>
        <div className="d-flex justify-content-between align-items-start text-start">
          <div>
            <Card.Title> <CgProfile /> {contact.firstName} {contact.lastName}</Card.Title>
            <Card.Text>
              <strong> <IoMdMail /> Email:</strong> {contact.emailId}<br />
              <strong> <FaPhoneAlt /> Mobile:</strong> {contact.mobile}<br />
              {contact.address && (
                <>
                  <strong> <FaLocationDot /> Address:</strong> {contact.address}
                </>
              )}
            </Card.Text>
          </div>
          <div>
            <Button variant="outline-primary" className="me-2" onClick={() => onEdit(contact)}>
              <FaEdit />
            </Button>
            <Button variant="outline-danger" onClick={() => onDelete(contact.id)}>
              <FaTrash />
            </Button>
          </div>
        </div>
      </Card.Body>
    </Card>
  );
};

export default ContactCard;
