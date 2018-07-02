# Mutli Threading in Java

## Sources 
- Cave of Programming Youtube tutorial - Multi Threading

### How to create threads?
1. A class extends Thread class and override the run method. 
2. A class implements Runnable interface and override run method.
    - An anonymous class can able be used to implement the Runnable interface methods.
    - Runnable instance should be passed as contrustor arguement in Thread class. 

- Note: Do not invoke the "run" method, call the start method to invoke the run method in a new thread. 
