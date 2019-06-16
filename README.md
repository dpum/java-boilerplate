# Project Boilerplate for Java

Sometimes there are benefits on writing unit tests for your solutions both
while training and in competition setups. One of the goals of this repository
is to simplify that process.

In order to do so, hereby is provided a class that enables the automatic
execution and debugging of your code using test cases provided in text files.

<p align="center">
  <img width="800" src="https://user-images.githubusercontent.com/19409687/59626798-b4d9a480-9134-11e9-97e3-858aa4a8491f.gif">
</p>

## Quick Start

1. Make sure that you have Java JDK 9 or above installed.
2. Setup your JDK in your IntelliJ. (<kbd>Ctrl</kbd>+<kbd>Alt</kbd>+<kbd>Shift</kbd>+<kbd>S</kbd>)
3. Create Test Cases:
    1. Test cases start at 0 because we are real programmers.
    2. Each test case should have an input file (with `.in` extension) and the
       expected output in another file (with `.out` extension) in the same
       folder as the main class.
    3. Then update the `config.properties` file with the number of test cases
       and the main class settings.
4. Run the `TestExecutor.main` function using the IDE. If it fails it will
output where it did fail.

## Debugging

If it fails in the tests you can put breakpoints in your main class and run
the tests again.

