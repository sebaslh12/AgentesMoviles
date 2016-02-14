# Question 4 Solution

4. Implementing the Strategy patterns. The requirement presented
is to do an application which needs to order a list of Students
based on differences rules, for example alphabetically by name,
by age and order alphabetically by career.

	+ Create the Student class (create attributes) (Student.java)
	+ Create an Interface called IComparisonStretegy (IComparisonStretegy.java)
	+ Create 3 comparison Strategies that you design. (SortId.java , SortAge.java and SortLastName.java)
	+ Create a class sorter that receives a Strategy and then filters the list based on the given rule. (Sorter.java)
	+ Provide unit test example for each designed strategy. (AllTests.java)

The Student attributes are:
``` java 
	int id
	String name
	String lastName
	int age
	String career
``` 

The Strategies designed were Sort by Id, sort by Name and sort by Last Name.
