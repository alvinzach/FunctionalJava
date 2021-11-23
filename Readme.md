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

* System.out::println
``x → System.out.println(x)``
* Math::max
``(x,y) → Math.max(x,y)``
* String::length
``x → x.length()``
* String::compareToIgnoreCase
``(x,y) → x.compareToIgnoreCase(y)``

### Common Functional Interfaces

Important classes in ``java.util.function``

* **Consumer\<T>** accepts T and returns void ``void accept(T t);``
* **Supplier\<T>** accepts nothing and returns T ``T get();`` 
* **Function\<T, R>** accepts T and return R ``R apply(T t);`` . Transforms a T to R
* **Predicate\<T>** accepts T and return boolean ``boolean test(T t);``

```java
Predicate<String> predicate = (s) -> s.startsWith("k");
Consumer<String> consumer = System.out::println;

List.of("ken", "karen", "ben").stream()
        .filter(predicate)
        .forEach(consumer);
```

There are other variations like **BiConsumer\<T,U>** and **Function\<T, R>** . 
An operator is a variation of function where T and U are the same Type .
``
BinaryOperator<T> extends BiFunction<T,T,T>
`` ,
``
UnaryOperator<T> extends Function<T, T> 
``

**Intconsumer , LongConsumer , DoubleConsumers** are consumers that work on 
primitve types .

```java
BiPredicate<String,String> reduplicatedName = String::equalsIgnoreCase;
// (a,b) - > a.equalsIgnoreCase(b);
        
BinaryOperator<String> concatNames = String::concat;
// BiFunction<String,String,String> concatNames = (s1,s2)->s1.concat(s2);
        
IntConsumer intConsumer = (i) -> System.out.println(Math.sqrt(i));
intConsumer.accept(10);
```

## Streams

Streams are a sequence of elements , that doesnt alter the source ,
operations are lazy and closed on reaching the terminal operation

Streams are said to create an operation pipeline , that has a source ,
multiple intermediate operations and a terminal operation . All intermediate
operations result in a stream , and terminal operators result in a value/object

To create a list of students from an array of names :
Code before java 8
```java
String[] names = {"Abi","Adi","Ben","Celine"};
//before java 8
List<Student> students = new ArrayList<>();
for(String name : names){
    students.add(new Student(name));
}
System.out.println(students);
```
The for each loop shares a  single mutable state . 

```java
students = Arrays.stream(names)
                .map(Student::new)
                .collect(Collectors.toList());

System.out.println(students);
```

The above code does not directly manipulate the values inside the array,
It creates a stream of elements of array , which gets transformed to a stream of students ,
and gets collected to a list of Students

Instead of the ``.collect`` method use ``.toArray(Student[]::new)`` to 
collect the stream to an array .

`Collectors.toSet()` , `Collectors.toUnmodifiableList()` , `Collectors.toUnmodifiableSet()` , `Collectors.toMap` , `Collectors.toUnmodifiableMap` , `Collectors.toConcurrentMap`

`Collectors.toCollection` accepts a supplier of preffered collection type
. To create a linkedList of students :

```java
students = Arrays.stream(names)
                .map(Student::new)
                .collect(Collectors.toCollection(LinkedList::new));
```

`.collect` method also has an overloaded 3 argument version
`.collect(supplier,Accumulator,Collector)` . This version is mostly used 
with paraller streams . `Supplier` creates new collection for each of
the forkjoin thread . Accumulater is a BiConsumer that receives the collection
of a particular forkjoin thread and an element . Collector is a Biconsumer that 
receives the final collection and collection from each fork join thread . 
The accumulator will be called only in case of parallel stream .

```java
 students = Arrays.stream(names)
                .map(Student::new)
                .collect(LinkedList::new,
                        (studentList,student)->studentList.add(student),
                        (finalStudentList,studentList)->finalStudentList.addAll(studentList));
```

A less readable alternative with method reference :

```java
students = Arrays.stream(names)
        .map(Student::new)
        .collect(LinkedList::new,
            List::add,
            List::addAll);
```

To collect the list of students into a map :

```java
Map<String,Student> studentMap = students.stream()
                .collect(Collectors.toMap(Student::getName,student->student));
```
`toMap(ValueMapper,keyMapper)`