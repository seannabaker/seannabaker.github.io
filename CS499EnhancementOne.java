// CS499 Enhancement One
// Developer: Seanna Baker
// Last Updated: September 20th, 2026

//import statements
import java.util.InputMismatchException;
import java.util.Scanner;

public class CS499EnhancementOne {

    //initialized variables
    //updated to specify username and password for the investment portal
    public static String portalUsername = "";
    public static String portalPassword = "";

    //for the time being a stored password is need due to the lack of a user database
    public static String storedPassword = "123";

    //customer names used by the DisplayInfo method
    //updated to specify the names are for the customers
    public static String customerName1 = "Bob Jones";
    public static String customerName2 = "Sarah Davis";
    public static String customerName3 = "Amy Friendly";
    public static String customerName4 = "Johnny Smith";
    public static String customerName5 = "Carol Spears";

    //customer account choice used by the ChangeCustomerChoice method
    //updated to specify that the number respresents the customers account choice
    //number coresponds with customer number so custAcctChoice1 is for customerName1, custAcctChoice2 is for customerName2, etc.
    public static int custAcctChoice1 = 1;
    public static int custAcctChoice2 = 2;
    public static int custAcctChoice3 = 1;
    public static int custAcctChoice4 = 1;
    public static int custAcctChoice5 = 2;

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
        System.out.println("CS499 Enhancement One - Seanna Baker");
        System.out.println("--------------------────୨ৎ────--------------------\n\n\n");

        System.out.println("Hello! Welcome to our Investment Company!");

        //method to check the users permission and either grants or denies access to the portal
        CheckUserPermissionAccess(scanner);

        //as long as the option selected from the menu is not 3, the menu will continue prompting the user
        while(menuChoice != 3){
                
            System.out.println("What would you like to do?\n" +
                            "DISPLAY the client list (Enter 1)\n" +
                            "CHANGE a client's choice (Enter 2)\n" +
                            "EXIT the program... (Enter 3)");

            menuChoice = checkValidInt(scanner, 1, 3, "You selected an invalid option. Please enter a number between 1 and 3:");

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
        System.out.println("1. " + customerName1 + " selected option " + custAcctChoice1);
        System.out.println("2. " + customerName2 + " selected option " + custAcctChoice2);
        System.out.println("3. " + customerName3 + " selected option " + custAcctChoice3);
        System.out.println("4. " + customerName4 + " selected option " + custAcctChoice4);
        System.out.println("5. " + customerName5 + " selected option " + custAcctChoice5);
        System.out.println("\n\n\n");
    }

    //method used to change a customers account choice
    //the portal user is prompted to select a customer and then enter the new choice for that customer
    public static void ChangeCustomerChoice(Scanner custChoiceScanner){

        System.out.println("Enter the number of the client you wish to change (1-5): ");
        clientSelected = checkValidInt(custChoiceScanner, 1, 5, "You selected an invalid option. Please enter a number between 1 and 5:");

        System.out.println("Please enter the client's new service choice (1 = Brokerage, 2 = Retirement)");
        clientNewChoice = checkValidInt(custChoiceScanner, 1, 2, "You selected an invalid option. Please enter a choice 1 = Brokerage or 2 = Retirement:");
    

        if (clientSelected == 1) {
            custAcctChoice1 = clientNewChoice;
        } else if (clientSelected == 2) {
            custAcctChoice2 = clientNewChoice;
        } else if (clientSelected == 3) {
            custAcctChoice3 = clientNewChoice;
        } else if (clientSelected == 4) {
            custAcctChoice4 = clientNewChoice;
        } else if (clientSelected == 5) {
            custAcctChoice5 = clientNewChoice;
        } else {
            System.out.println("Invalid client selection.");
        }

        System.out.println("\n\n\n--------- Client's Choice Updated ---------\n" +
                            "Client " + clientSelected + "'s new account choice is now: " + clientNewChoice + "\n\n\n");
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