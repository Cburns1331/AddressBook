import java.util.*;

public class ImplementContact {

    private Map<String, Contact> contacts = new HashMap<>();
    //lists all contacts in the address book
    public List<Contact> getContacts(){
        return new ArrayList<>(contacts.values());
    }

    //finds contact by name and returns it
  //  public Contact viewContact(String name) { .. }

    //creates new contact
    public Contact addContact(String name, String address, String email, String phoneNumber) {
        entryError(name, address, email, phoneNumber);
        Contact contact = new Contact(name, address, email, phoneNumber);
        return contact;
    }
/*
    //updates existing contact
    public Contact updateContact(String name, String address, String email, String phoneNumber) { .. }

    //deletes existing contact
    public Contact deleteContact(String name, String address, String email, String phoneNumber) { ..}
    */

    public void entryError(String name, String address, String email, String phoneNumber) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty...");
        }
        if(address == null || address.isEmpty()){
            throw new IllegalArgumentException("Address Cannot be empty...");
        }
        if(email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty...");
        }
        if(phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty...");
        }
    }

}
