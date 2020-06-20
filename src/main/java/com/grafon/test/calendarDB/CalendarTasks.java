package com.grafon.test.calendarDB;

import com.grafon.test.MouseListener;
import com.grafon.test.eventsDB.EventsDB;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class CalendarTasks extends JFrame {
    String username;
    Connection connection;
    static Date date = new Date();
    static CalendarDB calendarDB = new CalendarDB();
    static JTable table;


    public CalendarTasks() {

    }

    public CalendarTasks(String username, Connection connection) throws HeadlessException {
        table = new JTable(getData(),calendarDB.getColumnNames(date));
        this.username = username;
        this.connection = connection;
        JFrame frame = new JFrame("Meeting room");
        JButton button = new JButton();
        JTextField jTextField = new JTextField("Выбрать дату");
        jTextField.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(table);
        table.addMouseListener(new MouseListener(frame, table, username));
        table.getTableHeader().setReorderingAllowed(false);
        table.setPreferredSize(new Dimension(1500, 400));

        DateTextField dateTextField = new DateTextField();
        dateTextField.setPreferredSize(new Dimension(80, 30));
        button.add(dateTextField);
        button.setPreferredSize(new Dimension(125, 20));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.PAGE_START);
        frame.setPreferredSize(new Dimension(1500, 500));
        frame.add(jTextField, "West");
        frame.add(button, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }


    public void changeTableHeader(Date dat) {
        String[] columnNames = calendarDB.getColumnNames(dat);

        JTableHeader header = getTable().getTableHeader();
        TableColumnModel colMod = header.getColumnModel();
        for (int i = 0; i < 8; i++) {
            TableColumn tabCol = colMod.getColumn(i);
            tabCol.setHeaderValue(columnNames[i]);
            header.repaint();
        }
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(columnNames);
        table.setModel(defaultTableModel);
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(0);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.CENTER );
        column.setCellRenderer(rightRenderer);
        for (int i = 0; i < 24; i++) {       //Set Hours Column
            defaultTableModel.addRow(getData()[i]);
        }
        setNewValue();
    }

    public JTable getTable() {
        return table;
    }

    public Object[][] getData() {
        Object[][] data = new Object[24][8];
        for (int i = 0; i < 24; i++) {       //Set Hours Column
            data[i][0] = i + 1;
        }
        return data;
    }

    public void setNewValue() {
        EventsDB eventsDB = new EventsDB();
        eventsDB.ReadSQL();
        Map<String[], String[]> events = eventsDB.getEventList();
        String[] columnHeader;


        for (Map.Entry<String[], String[]> pair : events.entrySet()) {
            for (int x = 0; x < 8; x++) {
                for (int i = 0; i < 24; i++) {
                    columnHeader = calendarDB.getColumnHeaders();
                    if (columnHeader != null) {
                        String day = columnHeader[x];

                        if (!day.isEmpty()) {

                            day = day.substring(day.length() - 10);

                            if (Arrays.equals(pair.getKey(), new String[]{day, i + ""})) {

                                String[] name_message = pair.getValue();

                            table.setValueAt(name_message[0] + ": " + name_message[1],i-1,x);


                            }
                        }
                    }
                }
            }
        }
    }

}