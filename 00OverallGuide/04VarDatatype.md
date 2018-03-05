# Getting To Know Primitive Data Types
## The Byte, Short, Int And Long
```java
public static void main(String[] args) {
    //int has width of 32
    int maxIntValue = 2_147_483_647;
    int minIntValue = -2_147_483_648;
    System.out.println(maxIntValue);

    //byte has width of 8
    byte maxByteValue = 127;
    byte minByteValue = -128;

    //short has width of 16
    short maxSHortValue = 32_767;
    short minShortValue = -32_768;

    //long has width of 64
    long longValue = 100L;   
}
```
## Float And Double
```java
//float has width of 32
float floatValue = 5f/3f;

//double has width of 64
double doubleValue = 5d/3d;

System.out.println("Float value is " + floatValue);
System.out.println("Double value is " + doubleValue);
```
> Float value is 1.6666666  
> Double value is 1.6666666666666667
## Char And Boolean
* More unicode character table: [here](https://unicode-table.com)
```java
char charValue = '\u00AE';
System.out.println("Unicode output is " + charValue);

boolean boolValue = true;
```
> Unicode output is ®
## String
```java
String stringValue = "First String";
stringValue += "\u00AE 2016";
System.out.println(stringValue);

int myInt = 50;
stringValue += myInt;
System.out.println(stringValue);
```
> First String® 2016  
> First String® 201650
# Operator
```java
boolean isCar = false;
boolean wasCar = isCar ? true : false
```
# Method
```java
public static void main(String[] args){
	calculate();
}
public static void calculate() {
    boolean gameOver = false;
    if(gameOver){
        System.out.println("GameOver");
    }
}
```