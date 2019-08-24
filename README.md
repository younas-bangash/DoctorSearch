# Android MVVM Architecture using Architectural Components

# Highlights

1. MVVM Architectural pattern
2. Unit test demonstration using JUnit and Mockito
3. UI unit test demonstration using Espresso
4. Gradle scripts for running sonarqube static code analysis, code coverage, etc.
5. Use AndroidX for Android Support Libraries
6. Use of The Navigation component library

The whole application is built based on the MVVM architectural pattern.

The main advantage of using MVVM, there is no two way dependency between ViewModel and Model unlike MVP. Here the view can observe the datachanges in the viewmodel as we are using LiveData which is lifecycle aware. The viewmodel to view communication is achieved through observer pattern (basically observing the state changes of the data in the viewmodel).

# Sonar Qube Report 
<img src="/screenshots/sonar_qube.png" width="1050" height="500" alt="Home"/> 

# Programming Practices Followed
a) Android Architectural Components <br/>
b) Dagger 2 for Dependency Injection <br/>
c) MVVM <br/>
d) Retrofit with Okhttp <br/>
e) JUnit and Mockito for Unit testing <br/>
f) Repository pattern <br/>

# How to build ?

Open terminal and type the below command to generate debug build <br/>

``` ./gradlew assembleDebug ```

Open terminal and type the below command to generate release build <br/>

``` ./gradlew assembleRelease ```

# How to generate Sonarqube report ?

Open gradle.properties and update the below line with the sonarqube server url

```systemProp.sonar.host.url=http://localhost:9000```

Before running the sonarqube job, make sure the project version has been updated in the build.gradle. On every run, increment the version by 1.<br/>

```
            property "sonar.sources", "src/main"
            property "sonar.projectName", "ProjectName" // Name of your project
            property "sonar.projectVersion", "1.0.0" // Version of your project
            property "sonar.projectDescription", "Description"
```

For running the sonarqube job, type the below command in the terminal. <br/>

```./gradlew sonarqube assembleDebug```

<br/>

# How to generate code coverage report ?

Open terminal and type the following command

```./gradlew clean jacocoTestReport```

The coverage report will be generated on the following path.

``` app/build/reports ```

# Improvements 
1. Code Coverage need to increase
2. UI testing has to be written
3. Pagination can be improved by using Paging library
