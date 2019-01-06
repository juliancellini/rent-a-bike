# rent-a-bike project

Context
```	
A company rents bikes under following options:
	
	1. Rental by hour, charging $5 per hour
	2. Rental by day, charging $20 a day
	3. Rental by week, changing $60 a week
	4. Family Rental, is a promotion that can include from 3 to 5 Rentals 
	    (of any type) with a discount of 30% of the total price
	
	# Assigment:
	1. Implement a set of classes to model this domain and logic
	2. Add automated tests to ensure a coverage over 85%
	3. Use GitHub to store and version your code
	4. Apply all the recommended practices you would use in a real project
	5. Add a README.md file to the root of your repository to explain: your design, 
	    the development practices you applied and how run the tests.
	
	Note: we don't expect any kind of application, just a set of classes with 
	    its automated tests.
	
	# Deliverables:
	The link to your repository 
```

This project contains the classes representing a bike, its schedule an a single rental. I put enphasis 
in checking the superposition in the schedule of a bike and modeling the pricing, promotions, etc. 

###### Bike

   Only for the purpose of the exercise, to be able to rent and verify the rents.  
  
###### Schedule

   Keep track of rentals and their schedules, to avoid overlaps and check free gaps.

###### Rental

   Model a single Rental. Uses:
   
   * RentalType
      
      Models the three types of rentals (by hour, by day and by week). Given a period, determines the correct 
      type (for example, if spans 4 or more hours uses a day rental)
      
      Calculates the total price (without Promotions)
  
   * RentalPeriod
   
      Models a period of time, calculates overlaps between other period. 
      I implement `equals` to be able to remove from Schedule collection.
       
   * Promotion
    
       Gives two constants: `FAMILY` and a null object `NULL` promotions as Singletons


All the test are in RentalTest.java. Use JUnit. 


   
 
	
