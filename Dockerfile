FROM openjdk:8
COPY . /usr/apps
WORKDIR /usr/apps
ENTRYPOINT javac RssReader.java && java RssReader