package com.ticketbooking_chatbot.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.ChatBot.Actions.ValidationsUtil;

public class ChatBotView extends JFrame {
	JTextArea gptDisplay = new JTextArea();
	JTextField questionsField = new JTextField();
	JButton enter_button = new JButton("send");
	int i = 0;
	String[] questions = { "enter your name:", "enter phone number:", "enter mail-id:", "leaving from ?",
			"travelling to ?", "departure_time(hh:mm) ?", "departure_date(yyyy-MM-DD) ?", "number of tickets ?", "payment mode ?",
			"thankyou for booking!🙏 \n ticket booked!." };

	String[] answers = {};

	String[] columnNames = { "User-Name", "Phone_number", "Mail-id", "From_city", "Destination", "dept_time",
			"dept_date", "ticket-count", "Payment-mode", "ticket-number", "Service_no", "Driver_no" };

	String Mobile_Number, city_from, city_to, dept_date, arr_date, ticket_number, driver_Mobile, service_number,
			arr_time, dept_time;

	JLabel departure_place, arrival_place, departure_time, arrival_time, departure_date, arrival_date, Mobile,
			Driver_mobile, ticket, service;
	DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	JTable table = new JTable(tableModel);
	JScrollPane scrollPane = new JScrollPane(table);

	Random random = new Random();

	public ChatBotView() {

		table.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());

		SwingUtilities.invokeLater(ValidationsUtil.LookandFeel());
		setTitle("TicketBooking-ChatBot");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setLayout(new GridLayout(1, 2));
		JPanel botpanel = new JPanel();
		JPanel displaypanel = new JPanel();

		add(botpanel);
		add(displaypanel);
		displaypanel.setLayout(new GridLayout(2, 1));
		JPanel tablepanel = new JPanel();
		JPanel ticketpanel = new JPanel();
		displaypanel.add(tablepanel);
		displaypanel.add(ticketpanel);

		botpanel.setBackground(Color.black);
		displaypanel.setBackground(Color.black);

		String imagePath = "C:\\Users\\srini\\Downloads\\images.png";
		ImageIcon imageIcon = new ImageIcon(imagePath);
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setPreferredSize(new Dimension(140, 130));

		GridBagConstraints gridbagconstraint = new GridBagConstraints();
		gridbagconstraint.gridx = 0;
		gridbagconstraint.gridy = 0;
		botpanel.add(imageLabel, gridbagconstraint);

		JLabel topname = new JLabel("Ticket-Gpt");

		topname.setFont(new Font("verdana", Font.BOLD, 30));
		topname.setForeground(Color.CYAN);
		topname.setSize(20, 10);
		gridbagconstraint.gridy = 1;
		botpanel.add(topname, gridbagconstraint);

		gridbagconstraint.gridy = 2;
		gridbagconstraint.weighty = 1;
		botpanel.add(gptDisplay, gridbagconstraint);

		gridbagconstraint.gridy = 3;
		botpanel.add(questionsField, gridbagconstraint);

		gridbagconstraint.gridy = 4;
		botpanel.add(enter_button, gridbagconstraint);

		gptDisplay.setBackground(Color.LIGHT_GRAY);
		gptDisplay.setPreferredSize(new Dimension(550, 500));

		questionsField.setPreferredSize(new Dimension(475, 50));
		enter_button.setPreferredSize(new Dimension(75, 50));
		enter_button.setBackground(Color.CYAN);

		tablepanel.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(600, 300));
		ticketpanel.setPreferredSize(new Dimension(600, 400));
		tablepanel.setBackground(Color.black);
		ticketpanel.setBackground(Color.black);

		topname.setForeground(Color.CYAN);
		ticketpanel.setLayout(new GridLayout(6, 2));

		departure_place = new JLabel();
		arrival_place = new JLabel();
		departure_time = new JLabel();
		arrival_time = new JLabel();
		ticket = new JLabel();
		service = new JLabel();
		departure_date = new JLabel();
		arrival_date = new JLabel();
		Mobile = new JLabel();
		Driver_mobile = new JLabel();

		JLabel head = new JLabel("Ticket-Details:");
		ticketpanel.add(head);
		head.setForeground(Color.CYAN);
		Font font = new Font("Courier", Font.BOLD, 20);
		head.setFont(font);
		ticketpanel.add(new JLabel(""));
		ticketpanel.add(new JLabel(""));
		ticketpanel.add(new JLabel(""));

		JLabel fromLabel = new JLabel("From:");
		fromLabel.setForeground(Color.CYAN);
		ticketpanel.add(fromLabel);
		ticketpanel.add(departure_place);
		departure_place.setForeground(Color.GRAY);

		JLabel toLabel = new JLabel("To:");
		toLabel.setForeground(Color.CYAN);
		ticketpanel.add(toLabel);
		ticketpanel.add((arrival_place));
		arrival_place.setForeground(Color.GRAY);


		JLabel ticketLabel = new JLabel("Ticket_no:");
		ticketLabel.setForeground(Color.CYAN);
		ticketpanel.add(ticketLabel);
		ticketpanel.add(ticket);
		ticket.setForeground(Color.GRAY);

		JLabel serviceLabel = new JLabel("Service_no:");
		serviceLabel.setForeground(Color.CYAN);
		ticketpanel.add(serviceLabel);
		ticketpanel.add(service);
		service.setForeground(Color.GRAY);

		JLabel dept_timeLabel = new JLabel("Departure_time:");
		dept_timeLabel.setForeground(Color.CYAN);
		ticketpanel.add(dept_timeLabel);
		ticketpanel.add(departure_time);
		departure_time.setForeground(Color.GRAY);

		JLabel arrival_timeLabel = new JLabel("Arrival_time:");
		arrival_timeLabel.setForeground(Color.CYAN);
		ticketpanel.add(arrival_timeLabel);
		ticketpanel.add(arrival_time);
		arrival_time.setForeground(Color.GRAY);
		
		
		JLabel dept_dateLabel = new JLabel("Departure_date:");
		dept_dateLabel.setForeground(Color.CYAN);
		ticketpanel.add(dept_dateLabel);
		ticketpanel.add(departure_date);
		departure_date.setForeground(Color.GRAY);

		JLabel arrival_dateLabel = new JLabel("Arrival_date:");
		arrival_dateLabel.setForeground(Color.CYAN);
		ticketpanel.add(arrival_dateLabel);
		ticketpanel.add(arrival_date);
		arrival_date.setForeground(Color.GRAY);

		JLabel mobileLabel = new JLabel("Mobile_no:");
		mobileLabel.setForeground(Color.CYAN);
		ticketpanel.add(mobileLabel);
		ticketpanel.add(Mobile);
		Mobile.setForeground(Color.GRAY);

		JLabel driver_mobileLabel = new JLabel("Driver-Mobile:");
		driver_mobileLabel.setForeground(Color.CYAN);
		ticketpanel.add(driver_mobileLabel);
		ticketpanel.add(Driver_mobile);
		Driver_mobile.setForeground(Color.GRAY);

		gptDisplay.setText("Hello, Welcome to Ticket GPT. \n");

		questionsField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					action_perform();
				}
			}
		});

		enter_button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == enter_button) {
					action_perform();
				}

			}

		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();

				Mobile_Number = (String) tableModel.getValueAt(rowIndex, 1);
				city_from = (String) tableModel.getValueAt(rowIndex, 3);
				city_to = (String) tableModel.getValueAt(rowIndex, 4);
				dept_time = (String) tableModel.getValueAt(rowIndex, 5);
				dept_date = (String) tableModel.getValueAt(rowIndex, 6);
				ticket_number = (String) tableModel.getValueAt(rowIndex, 9);
				service_number = (String) tableModel.getValueAt(rowIndex, 10);
				driver_Mobile = (String) tableModel.getValueAt(rowIndex, 11);
				
				LocalTime time = LocalTime.parse(dept_time);
			    LocalTime newTime = time.plusHours(8);
			    arr_time = newTime.toString();
			    
			    String date_time = dept_date +" "+ dept_time;
			    DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			    LocalDateTime ndate = LocalDateTime.parse(date_time, date);
			    LocalDate newdate = ndate.plusHours(8).toLocalDate();
			    arr_date = newdate.toString();
			    

				departure_place.setText(city_from);
				arrival_place.setText(city_to);
				departure_time.setText(dept_time);
				departure_date.setText(dept_date);
				arrival_time.setText(arr_time);
				arrival_date.setText(arr_date);
				service.setText(service_number);
				Driver_mobile.setText(driver_Mobile);
				Mobile.setText(Mobile_Number);
				ticket.setText(ticket_number);

			}
		});

		setVisible(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	public void action_perform() {

		String text_entered = questionsField.getText().toLowerCase();
		if (i == 1 && !text_entered.matches("[a-zA-Z]+")) {
			JOptionPane.showMessageDialog(null, "Name should contain only alphabets");
			return;
		}

		else if (i == 2 && !text_entered.matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Phone number should contain only numbers.");
			return;
		}

		else if (i == 2 && !(text_entered.length() == 10)) {
			JOptionPane.showMessageDialog(null, "Phone number must have 10 digits.");
			return;
		}

		else if (i == 4 && !text_entered.matches("[a-zA-Z]+")) {
			JOptionPane.showMessageDialog(null, "departure place must have only alphabets");
			return;
		}

		else if (i == 5 && !text_entered.matches("[a-zA-Z]+")) {
			JOptionPane.showMessageDialog(null, "destination place must have only alphabets");
			return;
		}

		else if (i == 6 && !text_entered.matches("[0-9][0-9]:[0-9][0-9]+")) {
			JOptionPane.showMessageDialog(null, "Please enter the departure time in HH:mm format");
			return;
		} else if (i == 7 && !text_entered.matches("[0-2][0][0-2][3-5]-[0-1][0-9]-[0-3][0-9]")) {
			JOptionPane.showMessageDialog(null,
					" Please enter the departure date in yyyy-MM-DD format");
			return;
		} else if (i == 8 && !text_entered.matches("[0-5]")) {
			JOptionPane.showMessageDialog(null, " maximum ticket count is 5");
			return;
		}

		gptDisplay.append("you-->" + text_entered + "\n");

		answers = java.util.Arrays.copyOf(answers, answers.length + 1);
		answers[answers.length - 1] = text_entered;

		questionsField.setText("");

		if (i < questions.length) {
			bot_reply();

			i++;
		} else {
			String[] rowData = new String[12];
			for (int j = 0; j < 9; j++) {
				rowData[j] = answers[j + 1];
			}
			rowData[9] = generateRandomNumber();
			rowData[10] = generateRandomserviceNumber();
			rowData[11] = generateRandomNumber();
			tableModel.addRow(rowData);
			gptDisplay.setText("");
			i = 0;
			answers = new String[0];

			gptDisplay.setText("Hello, Welcome to Ticket GPT. \n");

		}

	}

	private String generateRandomNumber() {
		int randomNumber = 100000000 + random.nextInt(900000000);
		return String.valueOf(randomNumber);

	}

	private String generateRandomserviceNumber() {
		int randomNumber = 1000 + random.nextInt(9000);
		return String.valueOf(randomNumber);
	}

	private class CustomHeaderRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);
			label.setBorder(new LineBorder(Color.BLACK, 1)); // Add border
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(new Font("Arial", Font.BOLD, 12));
			return label;
		}
	}

	public void bot_reply() {
		gptDisplay.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		String str = questions[i];
		gptDisplay.append("bot-->" + str + "\n");

	}

	public static void main(String[] args) {
		new ChatBotView();

	}
}
