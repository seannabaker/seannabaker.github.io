//Code Review for CS499
//Artifact for Category 1, 2, and 3
//Developer: Seanna Baker
//Last Updated: August 2026


//import statements
#include <iostream>
#include <string>
using namespace std;

//username and password for login
// VULNERABILITY FOUND - uninitalized variables
// Vulnerabitity fixed by initialzing the varaibles to an empty string
string username = "";
string password = "";
string storedPassword = "123";

//function declarations
void DisplayInfo();
void ChangeCustomerChoice();
void CheckUserPermissionAccess();

//function that checks if the type entered for the input is valid
// uses a template so it can be used both for strings, booleans, ints, etc.
template <typename T>
bool getValidInput(T& value){
    cin >> value;
    
    if(cin.fail()){
        cin.clear();
        cin.ignore(10000, '\n');
        cout << "Invalid Input. Please Try Again." << endl;
        return false;
    }

    return true;
}

//client names and their service choices
string name1 = "Bob Jones";
string name2 = "Sarah Davis";
string name3 = "Amy Friendly";
string name4 = "Johnny Smith";
string name5 = "Carol Spears";

int num1 = 1;
int num2 = 2;
int num3 = 1;
int num4 = 1;
int num5 = 2;

//variables need for user services
int choice = 0;
// VULNERABILITY FOUND - uninitalized variables
// vulnerabilites fixed by initialziing the varaibles to -1
int clientSelected = -1;
int clientNewChoice = -1;

//main function
int main() {

    //added output statement
    cout << "\n\n\n\n\n\n\n--------------------────୨ৎ────--------------------" << endl;
    cout << "CS 410 Project 1 - Created by Seanna Baker" << endl;
    cout << "-------------------------------------------------\n\n\n\n\n" << endl;


    cout << "Hello! Welcome to our Investment Company!" << endl;

    CheckUserPermissionAccess();

    while(choice != 3) {

        //prints the menu options
        cout << "What would you like to do?" << endl;
        cout << "DISPLAY the client list (enter 1)" << endl;
        cout << "CHANGE a client's choice (enter 2)" << endl;
        cout << "Exit the program.. (enter 3)" << endl;

        // VULNERABILITY FOUND - input type not checked
        // vulnerabiity fixed by checking the input type and repromting the user if incorrect
        // VULNERABILITY FOUND - doesn't check if choice selected by user exists
        // vulnerability fixed by checking if the option selected is 1-3
        while(!getValidInput(choice) || choice > 3 || choice < 1){
            cout << "Invalid Choice - Please enter a number between 1 and 3" << endl;
        }

        cout << "You chose " << choice << endl;

        //calls the appropriate function based on user input
        if (choice == 1) {
            DisplayInfo();
            continue;
        }
        if (choice == 2) {
            ChangeCustomerChoice();
            continue;
        }
    }

    return 0;
}

//displays the client names and their service choices
void DisplayInfo() {
    cout << "\nClient's Name     Service Selected (1 = Brokerage, 2 = Retirement)" << endl;
    cout << "1. " << name1 << " selected option " << num1 << endl;
    cout << "2. " << name2 << " selected option " << num2 << endl;
    cout << "3. " << name3 << " selected option " << num3 << endl;
    cout << "4. " << name4 << " selected option " << num4 << endl;
    cout << "5. " << name5 << " selected option " << num5 << endl;
    cout << "\n\n\n" << endl;
}

//changes the clients choice if the user requests it
void ChangeCustomerChoice() {
    cout << "Enter the number of the client you wish to change" << endl;
    // VULNERABILITY FOUND - input type not checked
    // vulnerabiity fixed by checking the input type and repromting the user if incorrect
    // VULNERABILITY FOUND - doesn't check if the user enters a number for an existing client
    // vulnerability fixed by checking if the client selected exists
    while(!getValidInput(clientSelected) || clientSelected < 1 || clientSelected > 5){
        cout << "Invalid Input - Please enter a number between 1 and 5" << endl;
    }

    cout << "Please enter the client's new service choice (1 = Brokerage, 2 = Retirement)" << endl;
    // VULNERABILITY FOUND - input type not checked
    // vulnerabiity fixed by checking the input type and repromting the user if incorrect
    // VULNERABILITY FOUND - doesn't check if the user enters a number for an existing service
    // vulnerability fixed by checking if the client choice selected exists
    while(!getValidInput(clientNewChoice) || clientNewChoice < 1 || clientNewChoice > 2){
        cout << "Invalid Input - Please enter either 1 for Brokerage or 2 for Retirement"  << endl;
    }

    if (clientSelected == 1) {
        num1 = clientNewChoice;
    }
    else if (clientSelected == 2) {
        num2 = clientNewChoice;
    }
    else if (clientSelected == 3) {
        num3 = clientNewChoice;
    }
    else if (clientSelected == 4) {
        num4 = clientNewChoice;
    }
    else if (clientSelected == 5) {
        num5 = clientNewChoice;
    }

    cout << " ---- Client's Choice Updated ---- \n\n" << endl;

}

//checks if the user has permission to access the program
void CheckUserPermissionAccess() {
    while (true){
        cout << "\n\nEnter your username:" << endl;
        // VULNERABILITY FOUND - input type not checked
        // vulnerabiity fixed by checking the input type and repromting the user if incorrect
        while(!getValidInput(username)){
            cout << "Enter your username:" << endl;
        }
        cout << "Enter your password:" << endl;
        // VULNERABILITY FOUND - input type not checked
        // vulnerabiity fixed by checking the input type and repromting the user if incorrect
        while(!getValidInput(password)){
            cout << "Enter your password:" << endl;
        }


        //checks if the password is the same as the storedPassword and if it isn't then the while loop is not broke out of
        // if the password is entered correctly then the loop is exited
        if(password == storedPassword){
            break;
        }

        cout << "Incorrect password. Please try login again." << endl;

    }
}
