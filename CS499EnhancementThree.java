// CS499 Enhancement Three
// Developer: Seanna Baker
// Last Updated: October 4th, 2026

//import statements
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//mongoDB Imports
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mongodb.client.MongoDatabase;

public class CS499EnhancementThree {

    //initialized variables
    //updated to specify username and password for the investment portal
    public static String attemptedUsername = "";
    public static String attemptedPassword = "";

    //the menu option selected by the portal user
    //updated to specify that the choice is for the portal menu
    public static int menuChoice = 0;

    //the varibles to hold the value of the customer being edited with the ChangeCustomerChoice method
    public static int clientSelected = -1;
    public static int clientNewChoice = -1;

    //enhancement three: added a database for the users and the clients
    //mongo variables
    public static MongoCollection<Document> clients;
    public static MongoCollection<Document> portalUsers;
    public static Logger mongoLogger = Logger.getLogger("org.mongodb.driver");

    public static void main(String[] args) {

        //connecting to mongoDB
        //hiding the logs so it doesn't interrupt the programs output
        mongoLogger.setLevel(Level.WARNING);
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("InvestmentCompanyPortal");
        clients = db.getCollection("clients");
        portalUsers = db.getCollection("portalUsers");

        //scanner to handle the input from the portal user
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n\n\n\n\n\n--------------------────୨ৎ────--------------------");
        System.out.println("CS499 Enhancement Three - Seanna Baker");
        System.out.println("--------------------────୨ৎ────--------------------\n\n\n");

        System.out.println("Hello! Welcome to our Investment Company!");

        //method to check the users permission and either grants or denies access to the portal
        CheckUserPermissionAccess(scanner);

        //as long as the option selected from the menu is not 5, the menu will continue prompting the user
        while(menuChoice != 5){
                
            //enhancement two: added the functionality to add or delete a client from the customerNames and custAcctChoice ArrayLists
            System.out.println("What would you like to do?\n" +
                            "DISPLAY the client list (Enter 1)\n" +
                            "CHANGE a client's choice (Enter 2)\n" +
                            "ADD a new client (Enter 3)\n" +
                            "DELETE a client (Enter 4)\n" +
                            "EXIT the program... (Enter 5)");

            menuChoice = checkValidInt(scanner, 1, 5, "You selected an invalid option. Please enter a number between 1 and 5:");

            switch (menuChoice) {
                case 1:
                    //if option one is selected the info is DISPLAYED
                    DisplayInfo();
                    break;
                case 2:
                    //if option two is selected the customer choice can be changed
                    ChangeCustomerChoice(scanner);
                    break;
                case 3:
                    //if option three is selected the user can add a new client
                    //enhancement two: added the ability to add a new client to the customerNames and custAcctChoice ArrayLists
                    AddNewClient(scanner);
                    break;
                case 4:
                    //if option four is selected the user can delete a client
                    //enhancement two: added the ability to delete a client from the customerNames and cust
                    DeleteClient(scanner);
                    break;
                case 5:
                    //if option three is selected the program exits
                    System.out.println("Exiting Program");
                    break;
                default:
                    //default to ensure no issues arise from an incorrect number being entered
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        //close the mongo connection
        mongoClient.close();

        //close the scanner to prevent memory leaks
        scanner.close();
    }

    //method used to check if the user enters a username and password that matches a valid entry set
    //currently no user database so the username doesn't matter and the password has to equal the storedPassword
    public static void CheckUserPermissionAccess(Scanner permissionScanner) {

        while(true){
            System.out.println("\n\nPlease enter your username: ");
            attemptedUsername = permissionScanner.nextLine();

            System.out.println("Please enter your password: ");
            attemptedPassword = permissionScanner.nextLine();

            //find the document that matches the attempted username and password in the portalUsers collection
            Document user = portalUsers.find(
                Filters.and(Filters.eq("username", attemptedUsername),
                Filters.eq("password", attemptedPassword))).first();

            //the document will be null if the pair don't exist, so if the document isn't null then
            //access is granted
            if (user != null) {
                //provides access and welcomes the user
                System.out.println("\n\n\nACCESS GRANTED\n Welcome, " + attemptedUsername + "!\n\n\n");
                break;
            }
            else {
                //denies access and prompts the user to try again
                System.out.println("\n\n\nACCESS DENIED\n Invalid username or password. Please try again.");
            }
        }
    }

    //method used to display the info of the users saved
    //displays their name and their account type
    public static void DisplayInfo() {
        System.out.println("\nClient Name\tService Selected (1 = Brokerage, 2 = Retirement)");
        //enhancement three: goes through each entry in the clients database and displays their name and option
        int i = 1;
        for (Document d : clients.find()) {
            System.out.println((i++) + ". " + d.getString("name") + " selected option " + d.getInteger("choice"));
        }
        System.out.println("\n\n\n");
    }

    //method used to change a customers account choice
    //the portal user is prompted to select a customer and then enter the new choice for that customer
    public static void ChangeCustomerChoice(Scanner custChoiceScanner){

        List<Document> clientList = getClientList();

        int maxClients = clientList.size();

        System.out.println("Enter the number of the client you wish to change (1-" + maxClients + "): ");
        clientSelected = checkValidInt(custChoiceScanner, 1, maxClients, "You selected an invalid option. Please enter a number between 1 and 5:");

        System.out.println("Please enter the client's new service choice (1 = Brokerage, 2 = Retirement)");
        clientNewChoice = checkValidInt(custChoiceScanner, 1, 2, "You selected an invalid option. Please enter a choice 1 = Brokerage or 2 = Retirement:");

        //enhancement three: updated to use the updateOne method of the MongoCollection to update the document in the clients collection
        String clientName = clientList.get(clientSelected - 1).getString("name");

        clients.updateOne(Filters.eq("name", clientName), Updates.set("choice", clientNewChoice));

        System.out.println("\n\n\n--------- Client's Choice Updated ---------\n" +
                            "Client " + clientSelected + "'s new account choice is now: " + clientNewChoice + "\n\n\n");
    }

    //enhancement two: method to add a new client to the customerNames and custAcctChoice ArrayLists
    public static void AddNewClient(Scanner addClientScanner) {
        addClientScanner.nextLine(); // Clear the buffer before reading the next line
        System.out.println("Enter the name of the new client: ");
        String newClientName = addClientScanner.nextLine();

        System.out.println("Please enter the new client's service choice (1 = Brokerage, 2 = Retirement)");
        int newClientChoice = checkValidInt(addClientScanner, 1, 2, "You selected an invalid option. Please enter a choice 1 = Brokerage or 2 = Retirement:");

        //enhancement three: updated to use the insertOne method of the MongoCollection to add a new document to the clients collection
        clients.insertOne(new Document("name", newClientName).append("choice", newClientChoice));

        System.out.println("\n\n\n--------- New Client Added ---------\n" +
                            "Client " + newClientName + " has been added with account choice: " + newClientChoice + "\n\n\n");
    }

    //enhancement two: method to delete a client from the customerNames and custAcctChoice ArrayLists
    public static void DeleteClient(Scanner deleteClientScanner) {

        List<Document> clientList = getClientList();

        System.out.println("Enter the number of the client you wish to delete (1-" + clientList.size() + "): ");
        int clientToDelete = checkValidInt(deleteClientScanner, 1, clientList.size(), "You selected an invalid option. Please enter a valid client number:");

        String deletedClientName = clientList.get(clientToDelete - 1).getString("name");

        //getting confirmation before removing the client account
        System.out.print("Are you sure you want to delete client " + deletedClientName + "? (yes/no): ");
        String confirmation = deleteClientScanner.next().trim().toLowerCase();

        if(confirmation.equals("yes")){
            //enhancement three: updated to use the deleteOne method of the MongoCollection to remove the document from the clients collection
            clients.deleteOne(Filters.eq("name", deletedClientName));

            System.out.println("\n\n\n--------- Client Deleted ---------\n" +
                            "Client " + deletedClientName + " has been removed from the system.\n\n\n");
        }
        else {
        System.out.println("\n\n\n--------- Deletion Cancelled ---------\n" +
                            "Client " + deletedClientName + " has NOT been removed from the system.\n\n\n");
        }
    }

    //method to check if the input is valid based on the min and max values provided
    //this method is only able to check for menus using int values as options
    public static boolean checkValidInput(int input, int min, int max) {
        if (input < min || input > max) {
            return false;
        }
        else {
            return true;
        }
    }

    //validates that the input from the user is an integer and not a type that will cause the 'InputMismatchException' to be thrown
    public static int checkValidInt(Scanner scanner, int min, int max, String errorMessage){
        while (true){
                try {
                    int uncheckedValue = scanner.nextInt();
                    //check if the input is valid, if it is then it returns the value to the variable that called the method
                    if(checkValidInput(uncheckedValue, min, max)) {
                        return uncheckedValue;
                    }
                }
                catch (InputMismatchException e) {
                    //catches the mismatch exception that may arise from the input
                    scanner.nextLine(); // clear the buffer
                }
                //displays the error message to guide the user to enter the correct expected value
                System.out.println(errorMessage);
            }
    }

    //enhancement three: method to get the list of clients from the MongoDB collection for easy access in other methods
    public static List<Document> getClientList() {
        return clients.find().into(new ArrayList<Document>());
    }
}