import java.io.*;
import java.util.*;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email: " + email;
    }
}

public class ContactManager {
    private static List<Contact> contacts = new ArrayList<>();

    public static void addContact(String name, String phoneNumber, String email) {
        contacts.add(new Contact(name, phoneNumber, email));
        System.out.println("Contact added successfully");
    }

    public static void viewContacts() {
        System.out.println("Contact List:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public static void editContact(String name, String newPhoneNumber, String newEmail) {
        boolean contactFound = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contact.setPhoneNumber(newPhoneNumber);
                contact.setEmail(newEmail);
                System.out.println("Contact updated successfully");
                contactFound = true;
                break;
            }
        }
        if (!contactFound) {
            System.out.println("Contact not found");
        }
    }

    public static void deleteContact(String name) {
        boolean contactFound = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contacts.remove(contact);
                System.out.println("Contact deleted successfully");
                contactFound = true;
                break;
            }
        }
        if (!contactFound) {
            System.out.println("Contact not found");
        }
    }

    public static void saveContactsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("contacts.txt"))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmail());
            }
            System.out.println("Contacts saved to file successfully");
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }

    public static void loadContactsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            while ((line = reader.readLine())!= null) {
                String[] parts = line.split(",");
                contacts.add(new Contact(parts[0], parts[1], parts[2]));
            }
            System.out.println("Contacts loaded from file successfully");
        } catch (IOException e) {
            System.out.println("Error loading contacts from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        loadContactsFromFile();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Add a new contact");
            System.out.println("2. View contact list");
            System.out.println("3. Edit a contact");
            System.out.println("4. Delete a contact");
            System.out.println("5. Save contacts to file");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.next();
                    System.out.print("Enter email: ");
                    String email = scanner.next();
                    addContact(name, phoneNumber, email);
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    System.out.print("Enter the name of the contact to edit: ");
                    String editName = scanner.next();
                    System.out.print("Enter new phone number: ");
                    String newPhoneNumber = scanner.next();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.next();
                    editContact(editName, newPhoneNumber, newEmail);
                    break;
                case 4:
                    System.out.print("Enter the name of the contact to delete: ");
                    String deleteName = scanner.next();
                    deleteContact(deleteName);
                    break;
                case 5:
                    saveContactsToFile();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.print("invalid choice");
            }
            
        }
    }
}