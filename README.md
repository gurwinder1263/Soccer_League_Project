# My Fantasy Soccer League 

## Made for Soccer Fans

This application has everything users need to have a virtual experience with **European Soccer League** such as:
- one can add one of the teams to their favourites and manage it.
- sign new players from different teams, sell their own players and swap players to strengthen their own squad.
- add a youth players to their respective teams.
- select your playing eleven, the captain and substitutes for upcoming games.
- improve statistics of a player by training their individual attributes.
- become the **best team** in your league by winning trophies.

It is made available to *EVERYONE* worldwide; however, it is perfect for users who are already familiar with
soccer. New users with little to no knowledge of soccer league system can also learn to win trophies in the league
after using the app for while.

The creator of this application is big time soccer fan too. He enjoys all aspects of the soccer and uses this
app in leisure time. His favorite player is Cristiano Ronaldo, and his favorite team is Juventus FC (*Fino alla Fine*).
He always had this idea of designing something like this for **free** for all users of all ages around the world.

## User Stories


As a user,

- one want to be able to create a new team and add it to a list of teams.
- one want to be able to select a team and add a new player to the team.
- one want to be able to select a team and view a list of the players on that team.
- one want to be able to select a player on a team and train player to increase the overall soccer rating.
  for example, a training session would enhance skills which would increase overall rating for that player.
- one want to be able to save league (all teams including its players) to file.
- one want to be able to optionally load league from file when the app  restarts.

Instructions for Grader
- You can generate the first required event by entering the name of a new player and then,  clicking (add new Player)
  button to add a player to the team. 
- You can generate the second required event by selecting the player fom the player list and then, clicking 
  train button to increase hi soccer rating. 
- You can locate my visual component by training a player(i.e when you press train player button, you will see
  image of a player training). 
- You can save the state of my application by clicking on (Save and Quit) button which appears on the second screen.
- You can reload the state of my application by clicking on (Load Save Teams) button which appears at the start
of the app.

Phase 4:Task 2 *Test and design a robust class* 
- In model package, class Player has a method trainPlayer() that throws NotTrainableException and, it has two distinct
test methods in corresponding test class.

Phase 4:Task 3
Problems:
- In ui package, class SoccerApp completely lacks cohesion. It is displaying menus, processing user commands and,
saving and loading data to/from file.
- In ui package, two different types of ui (console and gui) both have their own functionality to save and load data
to/from file, and create default data (or league). Thus, it is undesired duplication.
- There is a miniature amount of duplication in all classes of ui.gui package in terms of implementation
of aspects of Java Swing library.
- In persistence, class reader lacks a bit of cohesion. It reads data from the file in some format and, it also 
parses that format to correct format.


Solutions to 2 important corresponding problems.
- New classes DataManager and DisplayManager have been created to ensure single responsibility principle.
 DataManager deals with saving, loading, and introducing data. And, DisplayManager deals with display
 of all menus and print outs to users in ui.console package.
 
- The creation of new Class DataManager ensures that its methods are shared by both ui.console and ui.gui packages to
avoid duplication of code.