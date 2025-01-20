# Simpler Sorting Tool CLI in Java

A command-line sorting tool built in Java. This project focus on making something out fast.
Trying to follow KISS principle, without caring about project design. This project is part of my project-based learning on HyperSkill.

## Features

- Sort different types of input:
  - Long
  - Words
  - Lines of text
- Read input from console or files
- Display sorted results and basic statistics

## What I Learned

This project helped me understand:
- Command line argument parsing
- Java collections interfaces
- Stream api in Java
- Generics and Data type handling
- Introduction to Design Pattern



## Installation & Running 
### Option 1: Run from JAR
1. Make sure you have Java JRE 8 or higher installed on your system
2. Download the Flashcards.jar file
3. Open terminal/command prompt and run the program using java command 
   ```bash
   java -jar SortingTool.jar -dataType long
   ```


### Option 2: Build from Source
1. Make sure you have Java JDK installed
2. Clone this repository
3. Compile 
   ```bash
   javac -cp . ./sorting/Main.java -d ./compiled
   ```
4. run with args < option > < arg >
   ```bash
   java -cp ./compiled sorting.Main -dataType long
   ```
or Create your executable JAR file

4. create .jar
   ```bash
   jar cfm SortingTool.jar manifest.mf -C compiled sorting
   ```
5. run the program using java command 
   ```bash
   java -jar SortingTool.jar -dataType long
   ```


- To delete all .class files 
   ```bash
   find . -name "*.class" -type f -delete
   ```


## Usage Examples
When completed scan input exit program by oef by use keys sequence like ctrl+z or ctrl + C

```bash
java -jar SortingTool.jar -dataType long
>No sorting type defined! Default to type "natural"
121
1 1 1 1
>^Z
>Total numbers: 5.
>Sorted data: [1, 1, 1, 1, 121]
```


## Future Improvements
- Add self-made custom sorting algorithms
- Implement performance comparisons
