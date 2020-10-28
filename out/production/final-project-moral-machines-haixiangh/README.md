# COMP90041 - Final Project

This repository contains the skeleton code and test files for the final project submission on GitHub.

Don't know about version control? Click [here](https://www.atlassian.com/git/tutorials/what-is-version-control).

Need a refresher on GitHub? Click [here](https://guides.github.com/activities/hello-world/).

This assignment is due on:
**5pm (AEDT), November 20, 2020 (late-submission penalties apply).**

Also, make sure to fill in the authorship.txt file.

## Moral Machines
The idea of Moral Machines is based on the **Trolley Dilemma**, a fictional scenario presenting a decision-maker with a moral dilemma: choosing ''the lesser of two evils''. The scenario entails an autonomous car whose brakes fail at a pedestrian crossing. As it is too late to relinquish control to the car's passengers, the car needs to make a decision based on the facts available about the situation.

Feel free to familiarize yourself with the problem and explore different scenarios on [MIT's Moral Machine Website](https://www.moralmachine.net/).

In this final course project, you will create an **Ethical Engine**, a program designed to explore different precarious scenarios, build an algorithm to decide between the life of the car's passengers and the life of the pedestrians, audit your decision-making algorithm through simulations, and allow users of your program to judge the outcomes themselves.

## Tests
We have made two types of local tests available: 1) an invocation test with some basic assertions and 2) a program input/output test.

### The TestRunner
The file [TestRunner.java](./TestRunner.java) comprises a range of methods to instantiate and invoke the basic functionality of your code. You can run it as follows:

```
javac TestRunner.java && java TestRunnner
```

While the main functionality of your code is still missing, these tests will lead to a range of compile errors. We recommend you to first finish your project code and then start debugging using these tests. Once you have addressed all compile errors (**don't change the tests, change your code**), the test runner will launch a series of comparisons between your program's and the expected output. These tests will be the basis for the automated grading of your project, so make sure to pass them as well as you can. Passing them, however, does not guarantee the completeness of your program. You will need to thoroughly test your program beyond what these tests provide.

### Scenario Import and Interactive Mode Test

The [tests folder](./tests/) comprises the following files to test your code locally. 

- [config.csv](tests/config.csv) is the example config file depicted the specification document.
- [config_3.csv](tests/config_3.csv): contains 3 example scenarios and is basis for the interactive mode test described below.
- [config_10.csv](tests/config_10.csv): contains 10 example scenarios.
- [config_100.csv](tests/config_100.csv): contains 100 example scenarios.
- [in_interactive_config_3](tests/in_interactive_config_3): is an example user input file.
- [out_help](tests/out_help): contains the expected output of the <i>help</i> command-line flag.
- [out_interactive_config_3](tests/out_interactive_config_3): contains the expected ouput of the interactive mode test described below.

The config files comprise a range of different, valid scenarios. Thus, they do not contain any errors, which is why your program should be able to correctly import and display them.

To test the logic and the expected output of your interactive mode, run your program with the following parameters:

```
java EthicalEngine -i -c tests/config_3.csv < tests/in_interactive_config_3 > output
```

This command will run the EthicalEngine main method in interactive mode. It will import three scenarios from the [config_3.csv](./tests/config_3.csv) file and pipe in the pre-defined user input from the [in_interactive_config_3](./tests/in_interactive_config_3). The output of your program will be written to <i>output</i>. Inspect its contents as it may contain any errors your program execution may have encountered and compare it with the contents of [out_interactive_config_3](./tests/out_interactive_config_3), which holds the expected program output.

These tests are provided out of courtesy to give you the ability to test your program before the final submission. Passing these tests does not mean that your program works as specified, and we will apply more sophisticated tests for marking. It is up to you to make sure your program works as specified.

**Refer to the full final project specifications document for the complete requirements of the program.**
