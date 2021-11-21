# Functional Java

Lambdas , streams and method reference

### Lambda

Lambdas enable functions to be treated as objects . Lambda can be used 
in places a SAM(Single Abstract Method) Interface is required.

```java
//Block Lambda
new Thread(()->{
   System.out.println("Hello world" + Thread.currentThread().getName());
}).start();


// Expression Lambda
new Thread(()->System.out.println("Hello world" + Thread.currentThread().getName()))
.start();
```

The javac first look for a method prototype which accepts an interface .
And the checks if the prototype of lambda matches the SAM of the Interface , If so
the lambda is replaced with an implementation of the interface.

If more than one interfaces match the specification then we can either use cast or
assignment to specify the type

### Method references

If the body of the lambda is just a method invocation , we can use method referecnes 
to replace the body

```java
List<String> studentNames = List.of("ken","thompson","ben","evans");

studentNames
        .stream()
        .map(student -> student.toUpperCase())
        .map((studentName)->new Student(studentName))
        .forEach(student -> System.out.println(student));
```
can be written as 

```java
studentNames
        .stream()
        .map(String::toUpperCase)
        .map(Student::new)
        .forEach(System.out::println);
```
method references are a design choice . if the lambda calls a method in the 
body , we can call method as `this::methodName` .

### Common Functional Interfaces

Important methods in ``java.util.function``

* **Consumer\<T>** accepts T and returns void ``void accept(T t);``
* **Supplier\<T>** accepts nothing and returns T ``T get();`` 
* **Function\<T, R>** accepts T and return R ``R apply(T t);`` . Transforms a T to R
* **Predicate\<T>** accepts T and return boolean ``boolean test(T t);``

There are other variations like **BiConsumer\<T,U>** and **Function\<T, R>** . 
An operator is a variation of function where T and U are the same Type .
``
BinaryOperator<T> extends BiFunction<T,T,T>
`` ,
``
UnaryOperator<T> extends Function<T, T> 
``