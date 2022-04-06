# Spring commandfast Sample Application 

This is a fork of https://github.com/spring-projects/spring-commandfast to be used for the PSG2 course. The main changes that have been performed were:
- Trimming several parts of the application to keep the example low
- Reorganize some parts of the code according to best practices introduced in the DP1 course

## Understanding the Spring commandfast application with a few diagrams
<a href="https://speakerdeck.com/michaelisvy/spring-commandfast-sample-application">See the presentation here</a>

## Running commandfast locally
commandfast is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:


```
git clone https://github.com/gii-is-PSG2/spring-commandfast.git
cd spring-commandfast
./mvnw package
java -jar target/*.jar
```

You can then access commandfast here: http://localhost:8080/

<img width="1042" alt="commandfast-screenshot" src="https://cloud.githubusercontent.com/assets/838318/19727082/2aee6d6c-9b8e-11e6-81fe-e889a5ddfded.png">

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

## In case you find a bug/suggested improvement for Spring commandfast
Our issue tracker is available here: https://github.com/gii-is-PSG2/spring-commandfast/issues


## Database configuration

In its default configuration, commandfast uses an in-memory database (H2) which
gets populated at startup with data. 

## Working with commandfast in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
  not there, just follow the install process here: https://www.eclipse.org/m2e/
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA
  * [VS Code](https://code.visualstudio.com)

### Steps:

1) On the command line
```
git clone https://github.com/gii-is-PSG2/spring-commandfast.git
```
2) Inside Eclipse or STS
```
File -> Import -> Maven -> Existing Maven project
```

Then either build on the command line `./mvnw generate-resources` or using the Eclipse launcher (right click on project and `Run As -> Maven install`) to generate the css. Run the application main method by right clicking on it and choosing `Run As -> Java Application`.

3) Inside IntelliJ IDEA

In the main menu, choose `File -> Open` and select the commandfast [pom.xml](pom.xml). Click on the `Open` button.

CSS files are generated from the Maven build. You can either build them on the command line `./mvnw generate-resources`
or right click on the `spring-commandfast` project then `Maven -> Generates sources and Update Folders`.

A run configuration named `commandfastApplication` should have been created for you if you're using a recent Ultimate
version. Otherwise, run the application by right clicking on the `commandfastApplication` main class and choosing
`Run 'commandfastApplication'`.

4) Navigate to commandfast

Visit [http://localhost:8080](http://localhost:8080) in your browser.


## Looking for something in particular?

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|The Main Class | [commandfastApplication](https://github.com/gii-is-PSG2/spring-commandfast/blob/master/src/main/java/org/springframework/samples/commandfast/commandfastApplication.java) |
|Properties Files | [application.properties](https://github.com/gii-is-PSG2/spring-commandfast/blob/master/src/main/resources) |
|Caching | [CacheConfiguration](https://github.com/gii-is-PSG2/spring-commandfast/blob/master/src/main/java/org/springframework/samples/commandfast/system/CacheConfiguration.java) |

## Interesting Spring commandfast branches and forks

The Spring commandfast master branch in the main [spring-projects](https://github.com/spring-projects/spring-commandfast)
GitHub org is the "canonical" implementation, currently based on Spring Boot and Thymeleaf. There are
[quite a few forks](https://spring-commandfast.github.io/docs/forks.html) in a special GitHub org
[spring-commandfast](https://github.com/spring-commandfast). If you have a special interest in a different technology stack
that could be used to implement the Pet Clinic then please join the community there.

# Contributing

The [issue tracker](https://github.com/gii-is-PSG2/spring-commandfast/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](.editorconfig) for easy use in common text editors. Read more and download plugins at <https://editorconfig.org>. If you have not previously done so, please fill out and submit the [Contributor License Agreement](https://cla.pivotal.io/sign/spring).

# License

The Spring commandfast sample application is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).

[spring-commandfast]: https://github.com/spring-projects/spring-commandfast
[spring-framework-commandfast]: https://github.com/spring-commandfast/spring-framework-commandfast
[spring-commandfast-angularjs]: https://github.com/spring-commandfast/spring-commandfast-angularjs 
[javaconfig branch]: https://github.com/spring-commandfast/spring-framework-commandfast/tree/javaconfig
[spring-commandfast-angular]: https://github.com/spring-commandfast/spring-commandfast-angular
[spring-commandfast-microservices]: https://github.com/spring-commandfast/spring-commandfast-microservices
[spring-commandfast-reactjs]: https://github.com/spring-commandfast/spring-commandfast-reactjs
[spring-commandfast-graphql]: https://github.com/spring-commandfast/spring-commandfast-graphql
[spring-commandfast-kotlin]: https://github.com/spring-commandfast/spring-commandfast-kotlin
[spring-commandfast-rest]: https://github.com/spring-commandfast/spring-commandfast-rest
