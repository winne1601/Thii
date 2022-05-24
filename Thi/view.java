package Thi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class view extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNh;
	private JTextField txtStudent;
	private JTextField txtBirthday;
	private JTextField txtGender;
	private JTextField txtStudentid;
	private JTextField txtMajor;
	private JTextField txtEnrolldate;
	private JTextField tfgender;
	private JTextField tfstudent;
	private JTextField tfstudentid;
	private JTextField tfmajor;
	private JDateChooser datebirthday;
	private JDateChooser dateenrolldate;
	private ArrayList<Student> liststudent = new ArrayList<>();
	DefaultTableModel model = new DefaultTableModel();
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	String[] modelcolumn = {"Student", "Birthday", "Gender", "StudentID", "Major", "EnrollDate"};
	
	private void adddatatable() {
		String student = tfstudent.getText();
		String birthday = dateFormat.format(datebirthday.getDate());
		String gender = tfgender.getText();
		String studentid = tfstudentid.getText();
		String major = tfmajor.getText();
		String enrolldate = dateFormat.format(dateenrolldate.getDate());
		String[] data = {student, birthday, gender, studentid, major, enrolldate};
		model.addRow(data);
		
	}
	private void CreateandShowtable() {
		model.addColumn("Student");
		model.addColumn("Birthday");
		model.addColumn("Gender");
		model.addColumn("StudentID");
		model.addColumn("Major");
		model.addColumn("EnrollDate");
	    table.setModel(model);
	}
	private void saveasfile(File file) {
		String student;
		String birthday;
		String gender;
		String studentid;
		String major;
		String enrolldate;
		int index = model.getRowCount();
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
			for(int i=0; i<index; i++) {
				student = (String)model.getValueAt(i, 0);
				birthday = (String)model.getValueAt(i, 1);
				gender = (String)model.getValueAt(i, 2);
				studentid = (String)model.getValueAt(i, 3);
				major = (String)model.getValueAt(i, 4);
				enrolldate = (String)model.getValueAt(i, 5);
				String[] data = {student, birthday, gender, studentid, major, enrolldate};
				System.out.println(data);
				os.writeObject(data);
			}
			
			os.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void openfile(File file) {
		String[] data;
		String[] datarow;
		// xóa dữ liệu trong jtable
		model.getDataVector().removeAllElements();
	    revalidate();
	    //lấy dữ liệu từ file đưa vào jtable
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			do {
				data = (String[])input.readObject();
				if(!file.canRead()) break;
				model.addRow(data);
			}while(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view frame = new view();
					frame.CreateandShowtable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(57, 217, 523, 266);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Information", null, scrollPane, null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			modelcolumn
		));
		scrollPane.setViewportView(table);
		
		txtNh = new JTextField();
		txtNh.setEditable(false);
		txtNh.setText("INFORMATION");
		txtNh.setBounds(236, 11, 158, 20);
		contentPane.add(txtNh);
		txtNh.setColumns(10);
		
		txtStudent = new JTextField();
		txtStudent.setText("Student");
		txtStudent.setEditable(false);
		txtStudent.setColumns(10);
		txtStudent.setBounds(10, 39, 80, 20);
		contentPane.add(txtStudent);
		
		txtBirthday = new JTextField();
		txtBirthday.setText("Birthday");
		txtBirthday.setEditable(false);
		txtBirthday.setColumns(10);
		txtBirthday.setBounds(10, 70, 80, 20);
		contentPane.add(txtBirthday);
		
		txtGender = new JTextField();
		txtGender.setText("Gender");
		txtGender.setEditable(false);
		txtGender.setColumns(10);
		txtGender.setBounds(10, 101, 80, 20);
		contentPane.add(txtGender);
		
		txtStudentid = new JTextField();
		txtStudentid.setText("StudentID");
		txtStudentid.setEditable(false);
		txtStudentid.setColumns(10);
		txtStudentid.setBounds(282, 39, 80, 20);
		contentPane.add(txtStudentid);
		
		txtMajor = new JTextField();
		txtMajor.setText("Major");
		txtMajor.setEditable(false);
		txtMajor.setColumns(10);
		txtMajor.setBounds(282, 70, 80, 20);
		contentPane.add(txtMajor);
		
		txtEnrolldate = new JTextField();
		txtEnrolldate.setText("EnrollDate");
		txtEnrolldate.setEditable(false);
		txtEnrolldate.setColumns(10);
		txtEnrolldate.setBounds(282, 101, 80, 20);
		contentPane.add(txtEnrolldate);
		
		tfgender = new JTextField();
		tfgender.setColumns(10);
		tfgender.setBounds(100, 101, 158, 20);
		contentPane.add(tfgender);
		
		tfstudent = new JTextField();
		tfstudent.setColumns(10);
		tfstudent.setBounds(100, 39, 158, 20);
		contentPane.add(tfstudent);
		
		tfstudentid = new JTextField();
		tfstudentid.setColumns(10);
		tfstudentid.setBounds(377, 39, 158, 20);
		contentPane.add(tfstudentid);
		
		tfmajor = new JTextField();
		tfmajor.setColumns(10);
		tfmajor.setBounds(377, 70, 158, 20);
		contentPane.add(tfmajor);
		
		datebirthday = new JDateChooser();
		datebirthday.setBounds(100, 70, 158, 20);
		contentPane.add(datebirthday);
		
		dateenrolldate = new JDateChooser();
		dateenrolldate.setBounds(377, 101, 158, 20);
		contentPane.add(dateenrolldate);
		
		JButton addbutton = new JButton("Add");
		addbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adddatatable();
			}
		});
		addbutton.setBounds(236, 156, 105, 23);
		contentPane.add(addbutton);
		
		JButton btnSaveAs = new JButton("Save as ...");
		btnSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSaveAs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==btnSaveAs) {
					JFileChooser fileChoser = new JFileChooser();
					int response = fileChoser.showSaveDialog(null);
					if(response == JFileChooser.APPROVE_OPTION) {
						File file = new File(fileChoser.getSelectedFile().getAbsolutePath());
						saveasfile(file);
					}
			}
				}
		});
		btnSaveAs.setBounds(173, 494, 105, 23);
		contentPane.add(btnSaveAs);
		
		JButton btnOpenFile = new JButton("Open File ...");
		btnOpenFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==btnOpenFile) {
					JFileChooser fileChoser = new JFileChooser();
					int response = fileChoser.showOpenDialog(null);
					if(response == JFileChooser.APPROVE_OPTION) {
						File file = new File(fileChoser.getSelectedFile().getAbsolutePath());
						openfile(file);
					}
			}
			}
		});
		btnOpenFile.setBounds(57, 494, 105, 23);
		contentPane.add(btnOpenFile);
	}
}
