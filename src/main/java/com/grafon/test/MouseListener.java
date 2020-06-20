package com.grafon.test;



import com.grafon.test.eventsDB.EventFrame;
import com.grafon.test.eventsDB.EventsDB;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {
    JFrame frame;
    JTable table;
    String username;
    EventsDB eventsDB = new EventsDB();



    public MouseListener(JFrame frame, JTable table, String username) {
        this.frame=frame;
        this.table=table;
        this.username=username;

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(table.getSelectedColumn() != 0){
            eventsDB.ReadSQL();
            EventFrame eventFrame = new EventFrame(username, table.getSelectedColumn(),table.getSelectedRow() );
              }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
