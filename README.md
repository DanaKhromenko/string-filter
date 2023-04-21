# Multi-factor string verification system

## Description
This is a Java implementation of a multi-factor string verification system.
The system consists of a series of checks, the order of which is determined by the client.
If any of them return false, the overall result is false.
The design of the system allows for easy addition of new checks and modification of the order in which the checks are executed.
The filtration process is covered by tests.

### Current filters
* First letter of the input string is in uppercase.
* Length of the input string is no longer than 10 characters.
* Fifth character (index = 4) of the input string is "i".

### Tools
* Java 11
* JUnit 5
* Parameterized test
* Mockito
* Functional interface
* Predicate
* Stream API

### Test results
![unit-tests-result](https://user-images.githubusercontent.com/30585774/233748740-dbcaaa60-8413-4e07-a4e3-c941bdc5aebf.png)
