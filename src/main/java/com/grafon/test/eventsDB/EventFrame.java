package com.grafon.test.eventsDB;

import com.grafon.test.calendarDB.CalendarTasks;


import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;


public class  EventFrame extends JFrame {
    String username;
    int selectedColumn,selectedRow;
    JFrame frame = new JFrame();
    JTextArea textArea = new JTextArea();
    JButton buttonAdd = new JButton("Add Event");
    JButton buttonRemove = new JButton("Remove Event");
    CalendarTasks calendarTasks = new CalendarTasks();
  JTable table = calendarTasks.getTable();
    public EventFrame(String username, int selectedColumn, int selectedRow){
        this.username = username;
        this.selectedColumn = selectedColumn;
        this.selectedRow = selectedRow+1;

        frameView();
    }
    public void frameView(){
        JTableHeader header= table.getTableHeader();
        TableColumnModel colMod = header.getColumnModel();
        TableColumn tabCol = colMod.getColumn(selectedColumn);
        String day = (String) tabCol.getHeaderValue();
        day = day.substring(day.length()-10);

        String finalDay = day;

        buttonAdd.addActionListener(actionEvent -> {
            CreateEvent createEvent = new CreateEvent(finalDay,selectedRow+"",username,table);
            createEvent.addEvent(selectedRow,selectedColumn,textArea.getText());
            frame.setVisible(false);


        });
       buttonRemove.addActionListener(actionEvent -> {
           CreateEvent createEvent = new CreateEvent(finalDay,selectedRow+"",username,table);
           createEvent.removeEvent(selectedRow,selectedColumn);
           frame.setVisible(false);
       });


        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0) );
        frame.setPreferredSize(new Dimension(600, 400));
        textArea.setPreferredSize(new Dimension(500, 200));
        frame.add(textArea, BorderLayout.PAGE_START);
        grid.add(buttonAdd);
        grid.add(buttonRemove);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        frame.setLayout(layout);
        flow.add(grid);
        frame.add(flow, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }




}
