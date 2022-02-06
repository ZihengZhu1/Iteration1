# SYSC 3303 PROJECT

Date: Feb 5th 2022.  
**Version 1.0**

Team Members
- Han Jiatong
- Iyamu Ese
- Man Qiushuo
- Zhang Boshen
- Zhu Ziheng

# ITERATION 1 
For this project we have the following java classes:
- Elevator.java     
 This class represents the Elevator Subsystem which emulates an elevator car. It sends
 calls out to the scheduler when there is a request from the elevator and receives instructions
 from the scheduler when theres a message from the floor.

- Floor.java      
 This classs represents the Floor Subsystem which emulates a floor in a building (with 
 up and down buttons to call the elevator). The floor Subsystem exchanges messages with
 the scheduler.

- Main.java     
  This class
 
- Scheduler.java      
  This class recieives instructions form the floor and elevator class. It is used to schedule the elevator 
  cars and the order they respond to requests.
  
- RequestMessage     
  This class serves as a buffer between the floor, elevator and scheduler. It send the messages recived 
  from the floor and elevator to the scheduler. 
  
- ArrivalMessage     
  This class

### Breakdown of responibilites:
- Han Jiatong:- Elevator.java, UML diagram, test case
- Zhang Boshen:- Floor.java, Scheduler.java, coding fixed
- Qiushuo Man:- Scheduler.java, data_structure
- Iyamu Ese:- README, UML Class diagram

### Set up and test instructions:
#### Before running project:
- Create a file that contains input data to be read by the floor class
- Copy file pathname and...

#### To run project:
- Import project from git on eclipese
- Run...
