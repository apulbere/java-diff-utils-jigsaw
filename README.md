# Java diff-utils library with Java 9 modules (Jigsaw)

## About
Initial codebase was taken from [this repository](https://github.com/wumpz/java-diff-utils) and then adapted to modules.

## Content
* `difflib-api` - _the general interface for computing diffs_ and classes which hold all differences
 between the original and revised texts
* `myers` - _implementation of Eugene Myers greedy differencing algorithm_
* `histogram` - implementation of the Histogram algorithm
* `difflib-ui` - the main class which loads all diff algorithms implementations using `ServiceLoader`

## To run 
`./gradlew run`