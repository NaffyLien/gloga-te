## Run and Test the Web App

You can execute your project directly through your IDE or by using the terminal.

Run via Terminal: Open a command prompt inside the root project directory and execute:

```./mvnw spring-boot:run```

Utilisez le code avec précaution.Verify Output: Look for a console log indicating that the embedded server has started: 

```Tomcat started on port(s): 8080 (http).```

Access the App: Open your web browser and test your live application endpoints:

Open **http://localhost:8080/** to see your home message.
Open **http://localhost:8080/greet?name=Developer** to view the parameterized greeting.

## Build for Deployment

When your code is ready, package your web app into a single executable file:
Run 
```./mvnw clean package``` in your terminal.

Find the compiled .jar file generated inside the **target/** directory.

Deploy and launch this jar on any production server with the command: 

```java -jar target/webapp-0.0.1-SNAPSHOT.jar.```