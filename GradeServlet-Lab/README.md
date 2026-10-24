# Grade Servlet — Ubuntu Lab

This app accepts a student's name and grade, then displays a greeting and grade.

## Requirements
- Ubuntu
- Java JDK (check with `java -version` and `javac -version`)
- Apache Tomcat 9 is already installed at `/home/ssn/` (find the exact folder name below)
- Browser

## 1. Find Tomcat
Open Terminal:
```bash
ls /home/ssn
```
Find the Tomcat folder, for example `apache-tomcat-9.0.xx`, then set:
```bash
export CATALINA_HOME="/home/ssn/apache-tomcat-9.0.xx"
```
Replace the example with the exact folder name. Confirm:
```bash
ls "$CATALINA_HOME/lib/servlet-api.jar"
```

## 2. Copy this project into Tomcat
Extract this ZIP, then in Terminal `cd` into the extracted `GradeServlet-Lab` folder. Run:
```bash
mkdir -p "$CATALINA_HOME/webapps/GradeServlet"
cp index.html "$CATALINA_HOME/webapps/GradeServlet/"
cp -r WEB-INF "$CATALINA_HOME/webapps/GradeServlet/"
```

## 3. Compile inside WEB-INF/classes
```bash
cd "$CATALINA_HOME/webapps/GradeServlet/WEB-INF/classes"
javac -cp "$CATALINA_HOME/lib/servlet-api.jar" GradeServlet.java
```
This creates `GradeServlet.class` beside `GradeServlet.java`.

## 4. Run
```bash
"$CATALINA_HOME/bin/startup.sh"
```
Open: http://localhost:8080/GradeServlet/

Stop Tomcat after the lab:
```bash
"$CATALINA_HOME/bin/shutdown.sh"
```

**Note:** This code uses `javax.servlet`, so use Tomcat 9 (not Tomcat 10+).
