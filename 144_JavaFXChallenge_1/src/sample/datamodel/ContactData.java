package sample.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by M4800 on 03-May-17.
 */
public class ContactData {
    private static final String CONTACTS_FILE = "contacts.xml";
    private static final String CONTACT = "contact";
    private static final String LAST_NAME = "last_name";
    private static final String FIRST_NAME = "first_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String NOTES = "notes";

    private ObservableList<Contact> contacts;

    public ContactData(){
        contacts = FXCollections.observableArrayList();
    }

    public void addContactData(Contact contact){
        contacts.add(contact);
    }

    public void deleteContactData(Contact contact){
        contacts.remove(contact);
    }

    public ObservableList<Contact> getContacts() {
        return contacts;
    }

    public void loadContacts() {
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();//First, create a new XMLInputFactory

            XMLEventReader eventReader = inputFactory.createXMLEventReader(new FileInputStream(CONTACTS_FILE));
            Contact contact = null;//read the XML document

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    if (startElement.getName().getLocalPart().equals(CONTACT)) {
                        contact = new Contact();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(FIRST_NAME)) {
                            event = eventReader.nextEvent();
                            contact.setFirstName(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(LAST_NAME)) {
                        event = eventReader.nextEvent();
                        contact.setLastName(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(PHONE_NUMBER)) {
                        event = eventReader.nextEvent();
                        contact.setPhoneNumber(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(NOTES)) {
                        event = eventReader.nextEvent();
                        contact.setNotes(event.asCharacters().getData());
                        continue;
                    }
                }

                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(CONTACT)) {
                        contacts.add(contact);
                    }
                }
            }
        } catch (FileNotFoundException e) {

        } catch (XMLStreamException e) {

        }
    }

    public void saveContacts(){
        try {
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(CONTACTS_FILE));

            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");

            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement contactsStartElement = eventFactory.createStartElement("", "", "contacts");
            eventWriter.add(contactsStartElement);
            eventWriter.add(end);

            for(Contact contact: contacts){
                saveContact(eventWriter, eventFactory, contact);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "contacts"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }catch (FileNotFoundException e){
            System.out.println("Problem with Contacts file: " + e.getMessage());
            e.printStackTrace();
        }catch (XMLStreamException e){
            System.out.println("Problem writing contact: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveContact(XMLEventWriter eventWriter, XMLEventFactory eventFactory, Contact contact) throws FileNotFoundException, XMLStreamException{
        XMLEvent end = eventFactory.createDTD("\n");

        StartElement configStartElement = eventFactory.createStartElement("", "", CONTACT);
        eventWriter.add(configStartElement);
        eventWriter.add(end);

        createNode(eventWriter, FIRST_NAME, contact.getFirstName());
        createNode(eventWriter, LAST_NAME, contact.getLastName());
        createNode(eventWriter, PHONE_NUMBER, contact.getPhoneNumber());
        createNode(eventWriter, NOTES, contact.getNotes());

        eventWriter.add(eventFactory.createEndElement("", "", CONTACT));
        eventWriter.add(end);
    }

    private void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException{
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");

        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);

        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);

        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
}
