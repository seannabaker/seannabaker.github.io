// CS499 Enhancement Two
// Developer: Seanna Baker
// Last Updated: September 27th, 2026

//import statements
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CS499EnhancementTwo {

    //initialized variables
    //updated to specify username and password for the investment portal
    public static String portalUsername = "";
    public static String portalPassword = "";

    //for the time being a stored password is need due to the lack of a user database
    public static String storedPassword = "123";

    //customer names used by the DisplayInfo method
    //enhancement one: updated to specify the names are for the customers
    //enhancement two: converted from hardcoded variables to an ArrayList to allow for easier management of the customer names
    public static ArrayList<String> customerNames = new ArrayList<>(List.of("Bob Jones", "Sarah Davis", "Amy Friendly", "Johnny Smith", "Carol Spears"));

    //customer account choice used by the ChangeCustomerChoice method
    //enhancement one: updated to specify that the number respresents the customers account choice
    //number coresponds with customer number so custAcctChoice1 is for customerName1, custAcctChoice2 is for customerName2, etc.
    //enhancement two: converted from hardcoded variables to an ArrayList to allow for easier management of the customer account choices
    //the index matches with the index of the customerNames ArrayList so customerAccountChoices.get(0) is for customerNames.get(0), etc.

    public static ArrayList<Integer> custAcctChoice = new ArrayList<>(List.of(1, 2, 1, 1, 2));

    //the menu option selected by the portal user
    //updated to specify that the choice is for the portal menu
    public static int menuChoice = 0;

    //the varibles to hold the value of the customer being edited with the ChangeCustomerChoice method
    public static int clientSelected = -1;
    public static int clientNewChoice = -1;

    public static void main(String[] args) {
        //scanner to handle the input from the portal user
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n\n\n\n\n\n--------------------────୨ৎ────--------------------");
        System.out.println("CS499 Enhancement Two - Seanna Baker");
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

        //close the scanner to prevent memory leaks
        scanner.close();
    }

    //method used to check if the user enters a username and password that matches a valid entry set
    //currently no user database so the username doesn't matter and the password has to equal the storedPassword
    public static void CheckUserPermissionAccess(Scanner permissionScanner) {

        while(true){
            System.out.println("\n\nPlease enter your username: ");
            portalUsername = permissionScanner.nextLine();

            System.out.println("Please enter your password: ");
            portalPassword = permissionScanner.nextLine();

            if (portalPassword.equals(storedPassword)) {
                //provides access and welcomes the user
                System.out.println("\n\n\nACCESS GRANTED\n Welcome, " + portalUsername + "!\n\n\n");
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
        //enhancement two: updated to use a for loop to display the customer names and their account choices from the ArrayLists
        for (int i = 0; i < customerNames.size(); i++) {
            System.out.println((i + 1) + ". " + customerNames.get(i) + " selected option " + custAcctChoice.get(i));
        }
        System.out.println("\n\n\n");
    }

    //method used to change a customers account choice
    //the portal user is prompted to select a customer and then enter the new choice for that customer
    public static void ChangeCustomerChoice(Scanner custChoiceScanner){

        System.out.println("Enter the number of the client you wish to change (1-5): ");
        clientSelected = checkValidInt(custChoiceScanner, 1, 5, "You selected an invalid option. Please enter a number between 1 and 5:");

        System.out.println("Please enter the client's new service choice (1 = Brokerage, 2 = Retirement)");
        clientNewChoice = checkValidInt(custChoiceScanner, 1, 2, "You selected an invalid option. Please enter a choice 1 = Brokerage or 2 = Retirement:");
    
        //enhancement two: updated to use the set method of the ArrayList to change the value of the customer's account choice in the custAcctChoice ArrayList
        //eliminated the need for the if statements that were used to check which customer was selected and then change the corresponding variable
        custAcctChoice.set((clientSelected - 1), clientNewChoice);

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

        customerNames.add(newClientName);
        custAcctChoice.add(newClientChoice);

        System.out.println("\n\n\n--------- New Client Added ---------\n" +
                            "Client " + newClientName + " has been added with account choice: " + newClientChoice + "\n\n\n");
    }

    //enhancement two: method to delete a client from the customerNames and custAcctChoice ArrayLists
    public static void DeleteClient(Scanner deleteClientScanner) {
        System.out.println("Enter the number of the client you wish to delete (1-" + customerNames.size() + "): ");
        int clientToDelete = checkValidInt(deleteClientScanner, 1, customerNames.size(), "You selected an invalid option. Please enter a valid client number:");

        String deletedClientName = customerNames.get(clientToDelete - 1);

        //getting confirmation before removing the client account
        System.out.print("Are you sure you want to delete client " + deletedClientName + "? (yes/no): ");
        String confirmation = deleteClientScanner.next().trim().toLowerCase();

        if(confirmation.equals("yes")){
            customerNames.remove(clientToDelete - 1);
            custAcctChoice.remove(clientToDelete - 1);

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
}