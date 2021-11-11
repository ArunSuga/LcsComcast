# LcsComcast

Follow the below steps to run the project :

Download the zip or clone the Git repository

Unzip the zip file (if you downloaded one)

Open Command Prompt and Change directory (cd) to folder containing pom.xml

Open Eclipse

File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip

Select the right project

Choose the Spring Boot Application file (search for @SpringBootApplication)

Right Click on the file and Run as Java Application

Application should be running in local host

LcsComcastApplicationTests.java  - Covers all the test scenarios for the project
End Point :
http://localhost:8080/lcs

Sample Request : 
{
	"setOfStrings" : [
		{"value" : "Haiuhury" },
		{"value" : "Haizhurr" },
		{"value" : "Haixihury" }
	]
}

Sample Response :
{
    "lcs": [
        {
            "value": "Hai"
        },
        {
            "value": "hur"
        }
    ]
}


