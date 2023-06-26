# Exercise for Concepts of Programming Languages

## Syntax 

Your task is to create a parser that interprets a scripting language more or
less similar to Excel. Your parser shall recognize the following
syntax:

- A decimal literal is defined by the regular expression
  `(+|-)?[1-9][0-9]*`
- A floating point literal is defined by one of the regular expressions
  - `(+|-)?[0-9]+E(+|-)?[0-9]+`
  - `(+|-)?\.[0-9]+(E(+|-)?[0-9]+)?`
  - `(+|-)?[0-9]+\.[0-9]*(E(+|-)?[0-9]+)?`
- A string literal is defined by the regular expression 
  `'([^']|'')*'`
- A timestamp literal is defined by the regular expression
  `\{\d\d\d\d-\d\d-\d\d \d\d:\d\d:\d\d\}`
- A variable is defined by the regular expression
  `[ijklmn]`
- A number expression is defined as in the sample grammar or as a result of
  a function returning a number or as the difference of two timestamp values.
- A timestamp expression is defined as a timestamp value plus or minus a number 
  expression giving the offset in seconds.
- A string expression is either a string value, optionally concatenated to 
  another string value with the operator `&`.
- A boolean expression is defined as the comparison of either two number,
  string, or timestamp expressions with one of the comparison operators `<`, 
  `<=`, `=`, `>=`, `>`, and `<>`.
- A cell reference is defined as 
  `C <column> R <row>` where `<column>` and `<row>` are number expressions.
- An assigment is defined as 
  `<cell reference> = <expression> [ FORMATTED <string expression>]`
  where `<string expression>` obeys the rules of the Java formatter
- A for loop is defined as
  ```for <variable> from <expression> to <expression> do <assigment> end```

## Remarks

- A cell expression must not refer to its own content, neither direct nor 
  indirect
- Type checking can be done at runtime.
## A Sample 

```
C14R7 = 'Hello'
for j from 1 to 9 do C14+jR7 = C14+j-1R7 & ' World' end 
```

## Regulations

You may work on the exercise in groups of two or three persons. Please upload 
your solution into the moodle course.