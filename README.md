# dsd-cohort-2024
The repository for the DSD Cohort 2024 group project

## Software Installs
### NodeJS
- Have Node installed on your local machine:  https://nodejs.org/en
- Can use the terminal to check if you have node installed & what version with `node -v`
### Java
 - Install Java: https://www.java.com/en/
 - Install Java SDK v.21: https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
 - Can also download SDKs through IntelliJ
     - In IntelliJ go to File --> Project Structure
     - This will open up a new window.  Under Project Settings --> Project, you can select an SDK.  If you don't have any installed or don't have v21 (which is what this project uses), this window will give you the option to download the SDK and apply it to the project in this window.   
![image](https://github.com/bethanyann/dsd-cohort-2024/assets/21211634/59c3a780-2c83-410b-b24a-2ad82a4e6fa8)

 - Maven (a build tool for Spring Boot apps): https://maven.apache.org/download.cgi
   
### IntelliJ
- IntelliJ for the backend Java application
    - Community edition DOES NOT SUPPORT SPRING BOOT so don't download this version (ask me how I know this *facepalm*)
    - Download this version: https://www.jetbrains.com/idea/download/?section=windows
    - This version is only free for 30 days, might have to figure something else out after the free window ends

### VSCode
- VSCode for frontend React application
    - This should be pretty straight forward if you've used React and VSCode together before
    - Some Extensions I recommend installing locally in VSCode if you don't have them already:
        -  Prettier
- VSCode for backend Java application
    - IntelliJ is set up to better suppor Java apps, but you can use VSCode as well
    - This article has a list of things to install into VSCode via the Extensions marketplace to get Java & Spring Boot applications to be supported:
      https://code.visualstudio.com/docs/java/java-spring-boot#:~:text=To%20develop%20a%20Spring%20Boot,Spring%20Boot%20Extension%20Pack
        - VSCode extension pack for Java: https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack
        - VSCode extension pack for Spring Boot: https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack
        - You'll need a Java v21 SDK if you don't already have it installed:  https://jdk.java.net/21/

  

## Backend_Application
### Getting Started
- Clone entire project locally 
- The backend application can be opened with IntelliJ or VSCode by right-clicking on the `Backend_Application` folder and selecting which program to use
- If you don't have an SDK downloaded, IntelliJ will propmt you to choose one to download
- Both IntelliJ and VSCode have a green `play` button that you use to run the Java SpringBoot project and a `debug` button
![image](https://github.com/bethanyann/dsd-cohort-2024/assets/21211634/600c0b84-1ed5-40d3-8146-2b115e63fcc0)
- The backend application is set up to run on localhost:8080 and this is set in the dsdcohort.Application --> resources --> application.properties file
- After starting up the project, navigate to localhost:8080 and confirm that you can see a message in your browser! 
- The backend project has been set up to have a `config` folder that contains a `CORSConfig` file.  This file will allow the frontend project to access the backend API

### Dependencies
- You will need to have a Java SDK v.21 installed (link is above in the Java installs list)
### Project Structure
- The `Application` file that contains the `main()` function is the entry point into the application, and this file must remain at the root of the application folder

## Frontend_Application
### Getting Started
- Clone entire project locally and open the Frontend_Application in VSCode
- Run `npm install` to install all project dependencies
- This project was boostrapped with Vite so to run the project use the command `npm run dev`
- Open your browser and navigate to `localhost:3000` to see the React frontend project loaded
### Dependencies
- React Router
- Prettier
- Jest
- ESLint
### Project Structure
- `index.html` is the entrance file into the project



Backend project settings for reference in case I messed this up somehow: 
![image](https://github.com/bethanyann/dsd-cohort-2024/assets/21211634/3bd027c1-9877-401e-876f-bfd8dc3d41a7)
