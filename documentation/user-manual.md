# MoneyTracking

## Description
Money Tracking is an application which allows a user to keep track of their's expenses and incomes. The user interact with a text-based user interface via the command-line. Features of this applications are:
+ Display the items
+ Add the items
+ Edit or remove items
+ Save and quit

### Display items
>Displays the items that can be sorted by month, amount or title.
>But it displays only expenses or only incomes.

### Add items
>Allow a user to enter expense(s) and income(s).
>While adding, it asks the user to assign a month, title and amount to every expense or income.

### Edit or remove items
>Support the ability to allow user to edit and remove items. 

### Save and quit
>User can also quit and save the current items list and then on restart the application with the former state restored.
>Load and save items list to a file.

## Steps
![Flow chart diagram](documentation/flowChart.png)
![Class diagram](documentation/classDiagram.png)

## File Structure
+ *Main.java*
>Contains the main function to kick the program. 
>Call the `MainConsole` function. 

+ *MainConsole.java*
>Contains the `display()` function.
>It display the features of the application.
>Takes user i/p and runs the `switch` case.
>Based on the `switch` case,`MoneyTracking` functions are called.

+ *MoneyTracking.java*
>Implements the 4 features of the application.

+ *FileHandler.java*
>Handle file related methods.


