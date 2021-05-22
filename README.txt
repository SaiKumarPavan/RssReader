This is a simple JAVA application which takes RSSFeed as an input and gives out the Title and URL.

Steps to run the application in a dockerised environment.
1. GIT clone the project
2. Build the image using : docker build -t rssfeed/latest .
3. Create a container using : docker run -it rssfeed/latest
4. Provide the RSSFeed URL when it prompts. Sample URL : https://timesofindia.indiatimes.com/rssfeedstopstories.cms?x=1

We can also use the Jenkinsfile for automating the above procedure.



