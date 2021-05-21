package com.learn.datastructures.array;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class RssReader {
	
	/**
	 * 
	 * Sample input https://timesofindia.indiatimes.com/rssfeedstopstories.cms?x=1
	 */

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Provide the url for rss or atom feed");
		String input = scanner.next();
		if (input.trim().isEmpty()) {
			scanner.close();
			throw new RuntimeException("Invalid url");
		}
		try {
			System.out.println(getFeed(input));
		} catch (IOException | XMLStreamException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			scanner.close();
		}
	}

	private static Feed getFeed(String input) throws IOException, XMLStreamException {
		URL url = new URL(input);
		InputStream inputStream = url.openStream();
		XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
		XMLEventReader xmlReader = xmlFactory.createXMLEventReader(inputStream);
		Feed feed = new Feed();
		while (xmlReader.hasNext() && feed.noFeed()) {
			XMLEvent event = xmlReader.nextEvent();
			if (event.isStartElement() && event.asStartElement().getName().getPrefix().isEmpty()) {
				String localPart = event.asStartElement().getName().getLocalPart();
				if (localPart.equalsIgnoreCase("title")) {
					feed.setTitle(getCharacterData(event, xmlReader));
				} else if (localPart.equalsIgnoreCase("link")) {
					feed.setLink(getCharacterData(event, xmlReader));
				}
			}
		}
		return feed;
	}

	private static String getCharacterData(XMLEvent event, XMLEventReader xmlReader) throws XMLStreamException {
		String result = "No Data";
		event = xmlReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	public static class Feed {
		private String title;
		private String link;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public boolean noFeed() {
			return this.title == null || this.link == null || this.title.trim().isEmpty() || this.link.trim().isEmpty();
		}

		@Override
		public String toString() {
			return "Feed [title=" + title + ", link=" + link + "]";
		}
	}
}
