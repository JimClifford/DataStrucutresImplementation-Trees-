// Class representing a Phone Directory using a binary tree of contacts
public class PhoneDirectory {
    // Reference to the root of the binary tree
    Contact root;

    // Constructor to initialize an empty phone directory
    public PhoneDirectory() {
        root = null;
    }

    // Method to search for a contact by name
    public boolean search(String contactName) {
        Contact temp = root;
        while (temp != null) {
            int comparison = temp.contactName.toLowerCase().compareTo(contactName.toLowerCase());
            if (comparison == 0) {
                // Names match, return true
                return true;
            } else if (comparison < 0) {
                // The target name is smaller, go right
                temp = temp.right;
            } else {
                // The target name is greater, go left
                temp = temp.left;
            }
        }
        // Name not found in the tree
        return false;
    }

    // Method to insert a new contact into the directory
    public void insert(Contact contact) {
        if (root == null) {
            root = contact;
        } else {
            Contact parent = null;
            Contact temp = root;
            while (temp != null) {
                parent = temp;
                if (contact.contactName.compareTo(temp.contactName) > 0) {
                    temp = temp.right;
                } else if (contact.contactName.compareTo(temp.contactName) < 0) {
                    temp = temp.left;
                } else {
                    // Handle duplicate: update the phone number or ignore, depending on your requirement
                    temp.phoneNumber = contact.phoneNumber;
                    return; // Exit the method after handling duplicate
                }
            }

            if (parent.contactName.compareTo(contact.contactName) < 0) {
                parent.right = contact;
            } else {
                parent.left = contact;
            }
        }
    }

    // Method to delete a contact from the directory
    public boolean delete(String contactName) {
        Contact parent = null;
        Contact temp = root;

        while (temp != null) {
            int compareResult = contactName.compareTo(temp.contactName);

            if (compareResult < 0) {
                parent = temp;
                temp = temp.left;
            } else if (compareResult > 0) {
                parent = temp;
                temp = temp.right;
            } else {
                break;
            }
        }

        if (temp == null) {
            return false;
        }

        if (temp.left == null && temp.right == null) {
            if (parent != null) {
                if (parent.left == temp) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else {
                root = null;
            }
        } else if (temp.left == null) {
            if (parent != null) {
                if (parent.left == temp) {
                    parent.left = temp.right;
                } else {
                    parent.right = temp.right;
                }
            } else {
                root = temp.right;
            }
        } else if (temp.right == null) {
            if (parent != null) {
                if (parent.left == temp) {
                    parent.left = temp.left;
                } else {
                    parent.right = temp.left;
                }
            } else {
                root = temp.left;
            }
        } else {
            String min = findMin(temp.right);
            temp.contactName = min;
            delete(temp.right, min);
        }

        return true;
    }

    // Helper method to find the minimum contact name in a subtree
    private String findMin(Contact temp) {
        String minName = temp.contactName;
        while (temp.left != null) {
            minName = temp.left.contactName;
            temp = temp.left;
        }
        return minName;
    }

    // Helper method to delete a contact with a specific name from a subtree
    private void delete(Contact node, String contactName) {
        if (node == null) {
            return;
        }

        if (node.left != null && node.left.contactName.equals(contactName)) {
            node.left = null;
        } else if (node.right != null && node.right.contactName.equals(contactName)) {
            node.right = null;
        } else {
            delete(node.left, contactName);
            delete(node.right, contactName);
        }
    }

    // Method to perform an inorder traversal of the binary tree and print contact names
    public void inorder(Contact temp) {
        if (temp != null) {
            inorder(temp.left);
            System.out.print(temp.contactName + " ");
            inorder(temp.right);
        }
    }

    // Method to print the contact names in sorted order
    public void print() {
        Contact temp = this.root;
        inorder(temp);
    }

    // Main method for testing the PhoneDirectory
    public static void main(String[] args) {
        PhoneDirectory phoneDirectory1 = new PhoneDirectory();

        Contact contact1 = new Contact("John Doe", "123-456-7890");
        Contact contact2 = new Contact("Alice Smith", "987-654-3210");
        Contact contact3 = new Contact("Bob Johnson", "555-123-4567");
        Contact contact4 = new Contact("Emily Davis", "444-567-8901");
        Contact contact5 = new Contact("Michael Lee", "777-888-9999");
        Contact contact6 = new Contact("Sara Brown", "111-222-3333");
        Contact contact7 = new Contact("Daniel White", "666-333-2222");
        Contact contact8 = new Contact("Olivia Miller", "333-111-4444");
        Contact contact9 = new Contact("Ethan Wilson", "222-444-5555");
        Contact contact10 = new Contact("Ava Martinez", "888-999-0000");

        phoneDirectory1.insert(contact10);
        phoneDirectory1.insert(contact9);
        phoneDirectory1.insert(contact8);
        phoneDirectory1.insert(contact7);
        phoneDirectory1.insert(contact6);
        phoneDirectory1.insert(contact5);
        phoneDirectory1.insert(contact4);
        phoneDirectory1.insert(contact3);
        phoneDirectory1.insert(contact2);
        phoneDirectory1.insert(contact1);

        phoneDirectory1.print();

        boolean search_Ava = phoneDirectory1.search("Ava Martinez");
        System.out.println("Was the contact found?: " + search_Ava);

        phoneDirectory1.delete("Daniel White");

        phoneDirectory1.print();
        System.out.println("------------------------------------------------------");

        phoneDirectory1.delete(contact1, "Olivia Miller");

        phoneDirectory1.print();
    }
}
