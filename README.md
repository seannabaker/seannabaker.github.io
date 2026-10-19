<h1>Welcome to my ePortfolio!</h1>

<p>{<br>
      &emsp;&emsp;"name" : "Seanna Baker",<br>
      &emsp;&emsp;"profession" : "IT Helpdesk Technician",<br>
      &emsp;&emsp;"degree" : "Bachelors in Computer Science",<br>
      &emsp;&emsp;"languages" : [ "Python", "Java", "C++" ]<br>
}<br><br></p>

<h2>Skills and Outcomes</h2>
<p>This ePortfolio demonstrates my ability to meet the following requirements of CS499:<br>
  
<ul>
<li>1. Employ strategies for building collaborative environments that enable diverse audiences to support organizational decision making in the field of computer science.</li>

<li>2. Design, develop, and deliver professional-quality oral, written, and visual communications that are coherent, technically sound, and appropriately adapted to specific audiences and contexts.</li>

<li>3. Design and evaluate computing solutions that solve a given problem using algorithmic principles and computer science practices and standards appropriate to its solution, while managing the trade-offs involved in design choices.</li>

<li>4.Demonstrate an ability to use well-founded and innovative techniques, skills, and tools in computing practices for the purpose of implementing computer solutions that deliver value and accomplish industry-specific goals.</li>

<li>5. Develop a security mindset that anticipates adversarial exploits in software architecture and designs to expose potential vulnerabilities, mitigate design flaws, and ensure privacy and enhanced security of data and resources.</li>
</ul>

<h2>Project Overview</h2>

<img width="290" height="382.5" align="left" alt="Screenshot 2026-10-18 at 3 02 38 PM" src="https://github.com/user-attachments/assets/00e42594-6fd4-4b2e-8037-ff23e2e47013" />

<br><br>This project started out with a piece of code I worked on in a previous course. The original code was C++ and it had very basic functions to accept input from the user and to provide output based on what the user selected. The image to the left is an example of the programs output.<br>

There were a decent amount of issues with the code, not logically, but structurally. I thought that for my capstone this would be the perfect piece to enhance so that I could show my abilities and skills with computer science, algorithms, data structures, databases, and software engineering.<br><br>

[Original Project from CS410 - Software Reverse Engineering](https://github.com/seannabaker/seannabaker.github.io/blob/main/cs410project1.cpp)

<br>

[Code Review of the Original Project that Details the Enhancement Plan](https://youtu.be/qGohitRhW5E?si=23Ha3zIZL73fS85r)

<br><br>

<h2>Enhancement One - Software Design and Engineering </h2>

<img width="454.5" height="442.5" align="right" alt="Screenshot 2026-10-18 at 3 57 13 PM" src="https://github.com/user-attachments/assets/5f28fc98-e95a-416a-8740-f8e3ff65ba5c" />

[Enhancement One Narrative](https://github.com/seannabaker/seannabaker.github.io/blob/main/3-2%20Milestone%20Two_%20Enhancement%20One%20-%20SeannaBaker2026.docx)&emsp;--->&emsp; 
[Enhancement One Java File](https://github.com/seannabaker/seannabaker.github.io/blob/main/CS499EnhancementOne.java)

The first enhancement made to the code to demonstrate my abilities to design and engineer software was to convert the C++ program into a Java program with the exact same functionality and expected input/output.<br><br>
The image on the right is an example of what the menu while loop looked like within enhancement one. It used an in-program function to verify that the input received was viable for the section of the code and then it used that input to determine which branch of the switch case would be executed. <br><br>
Aside from converting the language, other updates were made to the program to make sure it was cleaner, easier to read and had exits should an error occur. This means that variable names were updated to be more specific while also following the same naming convention. Error handling was also added due to the complexities that can be encountered when asking the user to provide input.
<br>

<br><br><br>
<h2>Enhancement Two - Algorithms and Data Structures</h2>

<img width="632" height="268" align="left" alt="Screenshot 2026-10-18 at 4 06 05 PM" src="https://github.com/user-attachments/assets/5d5458ad-1462-48cc-91f5-8f5b0883d19d" />

<br>

[Enhancement Two Narrative](https://github.com/seannabaker/seannabaker.github.io/blob/main/3-2%20Milestone%20Two_%20Enhancement%20One%20-%20SeannaBaker2026.docx)&emsp;--->&emsp; 
[Enhancement Two Java File](https://github.com/seannabaker/seannabaker.github.io/blob/main/CS499EnhancementTwo.java)

The second enhancement made to the code to demonstrate my abilities to work with and implement data structures was to transition the previously hard-coded variables into ArrayLists. This conversion allowed for easier access and management. When a new name needed to be added it can simply be added to the end of the ArrayList instead of the need for a new variable to be declared and initialized. 

<img width="592" height="113" align="left" alt="Screenshot 2026-10-18 at 4 06 21 PM" src="https://github.com/user-attachments/assets/a5f845cd-2ee4-4fbc-a214-ca619c05ddec" />

The images to the left show the implementation of the new data structure as well as the updates to the functions that access those variables. Now that an arrayList is being used the values can quickly be indexed through and accessed with a for loop.

<br>

<h2>Enhancement Three - Databases </h2>

<img width="1429" height="818" alt="Screenshot 2026-10-18 at 4 52 49 PM" src="https://github.com/user-attachments/assets/2e5ef421-7ee8-45b7-88da-8fafae0505dd" />
<br>

[Enhancement Three Narrative](https://github.com/seannabaker/seannabaker.github.io/blob/main/4-2%20Milestone%20Three_%20Enhancement%20Two%20-%20SeannaBaker2026.docx)&emsp;--->&emsp; 
[Enhancement Three Java File](https://github.com/seannabaker/seannabaker.github.io/blob/main/CS499EnhancementThree.java)

The third enhancement made to the code to demonstrate my abilities to work with and implement databases was to create and connect a database to the code. I decided for this project to work with MongoDB. I used MongoDB Compass as my database GUI for easy of use and to have the ability to quickly see which documents I had in each of the two databases.<br><br>
The image above shows an example of what the MongoDB Compass application looked like when within one of the databases and viewing the objects.<br><br>
The first database that I created was for the clients, it simply holds their names and their account choice but could easily be expanded to hold more information should the application be expanded. The second database is for the users, this database holds the username and password pairs that are allowed to access the program, this provides an introductory layer of security for the program.
</p>




