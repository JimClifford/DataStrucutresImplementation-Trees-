// Class representing a contact in a binary tree
public class Contact {
    // Fields to store the contact name, phone number, and references to left and right nodes
    String contactName;
    String phoneNumber;
    Contact right;
    Contact left;

    // Constructor to initialize a contact with a given name and phone number
    public Contact(String contactName, String phoneNumber) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.left = null;
        this.right = null;
    }
}
