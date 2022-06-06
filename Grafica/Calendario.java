package Grafica;

import com.formdev.flatlaf.FlatDarkLaf;
import mainclasses.Utente;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calendario extends JFrame {

    static JLabel label;
    static JButton preButton, nextButton;
    static JTable table;
    static JComboBox combo;
    static JFrame frame;
    static Container pane;
    static DefaultTableModel mtblCalendar;
    static JScrollPane scroll;
    static JPanel panel;
    static int realYear, realMonth, realDay, currentYear, currentMonth;

    static JComboBox hour, minute;


    public Calendario usaCalendario(Utente utente){
        //Look and feel
        try{
            UIManager.setLookAndFeel(new FlatDarkLaf() );
        }
        catch (Exception e){
            e.printStackTrace ();
        }


        //Prepare frame
        frame = new JFrame();
        frame.setSize(330, 375);           //330, 375 -It's possible to change the size of the frame to fit the hour of the task
        pane = frame.getContentPane();
        pane.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);     //This only close itself because it's a sub-frame

        //Icon
        ImageIcon image = new ImageIcon("Grafica\\Immagini\\Calendar_Icon.png");
        frame.setIconImage(image.getImage());

        //Create controls
        label = new JLabel("gennaio");
        combo = new JComboBox();
        preButton = new JButton("<---");
        nextButton = new JButton("--->");
        mtblCalendar = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        table = new JTable(mtblCalendar);
        scroll = new JScrollPane(table);
        panel = new JPanel(null);
        hour = new JComboBox();
        minute = new JComboBox();

        //Set border
        panel.setBorder(BorderFactory.createTitledBorder("Calendario") );

        //Register action listeners
        preButton.addActionListener(new btnPrev_Action() );
        nextButton.addActionListener(new btnNext_Action() );
        combo.addActionListener(new cmbYear_Action() );

        //Add controls to pane
        pane.add(panel);
        panel.add(label);
        panel.add(combo);
        panel.add(preButton);
        panel.add(nextButton);
        panel.add(scroll);
        panel.add(hour);
        panel.add(minute);


        //Set bounds                                                                                                    //Change the size only if we won to have a better look/feel
        panel.setBounds(0, 0, 320, 335);
        label.setBounds(160 - label.getPreferredSize().width / 2, 25, 100, 25);
        combo.setBounds(230, 309, 80, 20);      //(230, 305, 80, 20)                                  //(y = 309) because i want to have the year-box 2 pixels below the scroll-pane
        preButton.setBounds(10, 25, 60, 25);
        nextButton.setBounds(250, 25, 60, 25);
        scroll.setBounds(10, 52, 300, 255);                                                           //(height 255) only because I don't want to have a scroll bar + (y = 52) because i like it more
        //Hour/Minute of a task
        hour.setBounds(90, 309, 80, 20);
        minute.setBounds(10, 309, 80, 20);



        //Make frame visible/resizable
        frame.setResizable(false);
        frame.setVisible(true);

        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar();
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
        realMonth = cal.get(GregorianCalendar.MONTH);
        realYear = cal.get(GregorianCalendar.YEAR);
        currentMonth = realMonth;
        currentYear = realYear;

        //Add headers
        String[] headers = {"Dom", "Lun", "Mar", "Mer", "Gio", "Ven", "Sab"};
        for (int i = 0; i < 7; i++) {
            mtblCalendar.addColumn(headers[i]);
        }

        table.getParent().setBackground(table.getBackground());

        //No resize/reorder
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        //Single cell selection
        table.setColumnSelectionAllowed(true);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Set row/column count
        table.setRowHeight(38);
        mtblCalendar.setColumnCount(7);
        mtblCalendar.setRowCount(6);

        //Set slide year + set all the year usable(+10 -5 current year)
        for (int i = realYear - 5; i <= realYear + 10; i++) {
            combo.addItem(String.valueOf(i));
        }

        //Set slide hour to current year
        for(int i = 0; i <= 24; i++ ){
            hour.addItem(String.valueOf(i));
        }

        //Set slide year to current year
        for(int i = 0; i <= 60; i++ ){
            minute.addItem(String.valueOf(i));
        }

        //Refresh calendar
        refreshCalendar(realMonth, realYear);

        return  this;
    }

    public static void refreshCalendar(int month, int year) {
        //Variables
        String[] months = {"Gennaio", "Febbrario", "Marzo", "Aprile", "Maggio ", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
        int nod, som;

        //Allow/disallow buttons
        preButton.setEnabled(true);
        nextButton.setEnabled(true);
        if (month == 0 && year <= realYear - 10) {
            preButton.setEnabled(false);
        } //Too early
        if (month == 11 && year >= realYear + 100) {
            nextButton.setEnabled(false);
        } //Too late
        label.setText(months[month]);
        label.setBounds(160 - label.getPreferredSize().width / 2, 25, 180, 25);
        combo.setSelectedItem(String.valueOf(year));



        //Clear table
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mtblCalendar.setValueAt(null, i, j);
            }
        }

        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);

        //Draw calendar (graphical representation of the month)                                                         //----------------------------------------------------------
        for (int i = 1; i <= nod; i++) {
            int row = (i + som - 2) / 7;
            int column = (i + som - 2) % 7;

            mtblCalendar.setValueAt(i, row, column);
        }

        table.setDefaultRenderer(table.getColumnClass(0), new tblCalendarRenderer());
    }

    static class tblCalendarRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6) {
                setBackground(new Color(238,148,121));                                                          //Set background color for weekends
            } else {
                setBackground(new Color(255, 255, 255));                                                        //Set background color for weekdays
            }
            if (value != null) {
                if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear) {
                    setBackground(new Color(15,124,145));
                }
            }
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }

    static class btnPrev_Action implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (currentMonth == 0) {
                currentMonth = 11;
                currentYear -= 1;
            } else {
                currentMonth -= 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }

    static class btnNext_Action implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (currentMonth == 11) {
                currentMonth = 0;
                currentYear += 1;
            } else {
                currentMonth += 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }

    static class cmbYear_Action implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (combo.getSelectedItem() != null) {
                String b = combo.getSelectedItem().toString();
                currentYear = Integer.parseInt(b);
                refreshCalendar(currentMonth, currentYear);
            }
        }
    }
}



