package com.grafon.test;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;


import java.io.*;
import java.security.GeneralSecurityException;
import java.util.*;

public class CalendarQuickstart {
    static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    static final String TOKENS_DIRECTORY_PATH = "tokens";

    static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    static final String CREDENTIALS_FILE_PATH = "/credentials.json";


    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }


    public void createEvent(String message, String dateOfEvent, String hourOfevent, String userName) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        Event event = new Event()
                .setSummary(userName)
                .setDescription(message);

        if (hourOfevent.length() == 1) {
            hourOfevent = "0" + hourOfevent;
        }

        DateTime startDateTime = new DateTime(String.format("%sT%s:00:00", dateOfEvent, hourOfevent));
        long utc = startDateTime.getValue() + (startDateTime.getTimeZoneShift() * 60000);
        DateTime newDate = new DateTime(utc + (3600000 * -8));
        EventDateTime start = new EventDateTime().setDateTime(newDate);
        event.setStart(start);
        event.setEnd(start);


        String calendarId = "primary";
        event = service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());

    }

    public void deleteEvent(String dateOfEvent, String hourOfevent, String userName) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        String user;
        String eventID;
        if (hourOfevent.length() == 1) {
            hourOfevent = "0" + hourOfevent;
        }
        Events events = service.events().list("primary").execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");


            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();

                if (start == null) {
                    start = event.getStart().getDate();

                }
                eventID = event.getId();
                user = event.getCreator().getDisplayName();
                String startDay = start+"";
                String checkDay = String.format("%sT%s:00:00.000+04:00", dateOfEvent,hourOfevent);
                if(startDay.equals(checkDay) || userName.equals(user)){
                    System.out.println("Event deleted: " +start);
                    service.events().delete("primary", eventID).execute();
                }

            }

        }
    }
}

