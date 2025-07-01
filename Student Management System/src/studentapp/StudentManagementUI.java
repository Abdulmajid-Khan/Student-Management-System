//package studentapp;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.List;
//
//public class StudentManagementUI extends JFrame {
//    private StudentDAO dao = new StudentDAO();
//    private JTextField nameField, emailField, courseField, idField;
//    private JTextArea resultArea;
//
//    public StudentManagementUI() {
//        setTitle("Student Management System");
//        setSize(450, 500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new FlowLayout());
//
//        // Form Inputs
//        idField = new JTextField(20);
//        nameField = new JTextField(20);
//        emailField = new JTextField(20);
//        courseField = new JTextField(20);
//
//        resultArea = new JTextArea(15, 35);
//        resultArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(resultArea);
//
//        // Buttons
//        JButton addBtn = new JButton("Add Student");
//        JButton viewBtn = new JButton("View Students");
//        JButton updateBtn = new JButton("Update by ID");
//        JButton deleteBtn = new JButton("Delete by ID");
//
//        // Adding components
//        add(new JLabel("ID (for update/delete):")); add(idField);
//        add(new JLabel("Name:")); add(nameField);
//        add(new JLabel("Email:")); add(emailField);
//        add(new JLabel("Course:")); add(courseField);
//
//        add(addBtn); add(updateBtn); add(deleteBtn); add(viewBtn);
//        add(scrollPane);
//
//        // Button Actions
//
//        // ADD
//        addBtn.addActionListener(e -> {
//            String name = nameField.getText();
//            String email = emailField.getText();
//            String course = courseField.getText();
//
//            if (name.isEmpty() || email.isEmpty() || course.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Please fill all fields.");
//                return;
//            }
//
//            dao.insertStudent(new Student(name, email, course));
//            resultArea.setText("✅ Student added successfully!");
//        });
//
//        // VIEW
//        viewBtn.addActionListener(e -> {
//            List<Student> list = dao.getAllStudents();
//            resultArea.setText("");
//            for (Student s : list) {
//                resultArea.append("ID: " + s.getId() + " | Name: " + s.getName() +
//                        " | Email: " + s.getEmail() + " | Course: " + s.getCourse() + "\n");
//            }
//        });
//
//        // UPDATE
//        updateBtn.addActionListener(e -> {
//            try {
//                int id = Integer.parseInt(idField.getText());
//                String name = nameField.getText();
//                String email = emailField.getText();
//                String course = courseField.getText();
//
//                if (name.isEmpty() || email.isEmpty() || course.isEmpty()) {
//                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
//                    return;
//                }
//
//                dao.updateStudent(new Student(id, name, email, course));
//                resultArea.setText("✅ Student updated successfully!");
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(this, "Invalid ID.");
//            }
//        });
//
//        // DELETE
//        deleteBtn.addActionListener(e -> {
//            try {
//                int id = Integer.parseInt(idField.getText());
//                dao.deleteStudent(id);
//                resultArea.setText("❌ Student deleted (if existed).");
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(this, "Invalid ID.");
//            }
//        });
//
//        setVisible(true);
//    }
//}
package studentapp;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentManagementUI extends JFrame {
    private StudentDAO dao = new StudentDAO();
    private JTextField idField, nameField, emailField, courseField;
    private JTextArea resultArea;

    public StudentManagementUI() {
        setTitle("Student Management System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header Label
        JLabel titleLabel = new JLabel("Student Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204)); // Deep Blue
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;

        // Fields
        idField = new JTextField(20);
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        courseField = new JTextField(20);
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Row 1 - ID
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("ID (for update/delete):"), gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        // Row 2 - Name
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Row 3 - Email
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        // Row 4 - Course
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Course:"), gbc);
        gbc.gridx = 1;
        add(courseField, gbc);

        // Row 5 - Buttons (Add, Update, Delete)
        JPanel buttonPanelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanelTop.setBackground(new Color(240, 248, 255));
        JButton addBtn = new JButton("Add Student");
        JButton updateBtn = new JButton("Update by ID");
        JButton deleteBtn = new JButton("Delete by ID");

        buttonPanelTop.add(addBtn);
        buttonPanelTop.add(updateBtn);
        buttonPanelTop.add(deleteBtn);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        add(buttonPanelTop, gbc);

        // Row 6 - View Button
        JPanel buttonPanelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanelBottom.setBackground(new Color(240, 248, 255));
        JButton viewBtn = new JButton("View Students");
        buttonPanelBottom.add(viewBtn);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        add(buttonPanelBottom, gbc);

        // Row 7 - Result Area
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Button Actions

        // ADD
        addBtn.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String course = courseField.getText();

            if (name.isEmpty() || email.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            dao.insertStudent(new Student(name, email, course));
            resultArea.setText("\u2705 Student added successfully!");
        });

        // VIEW
        viewBtn.addActionListener(e -> {
            List<Student> list = dao.getAllStudents();
            resultArea.setText("");
            for (Student s : list) {
                resultArea.append("ID: " + s.getId() + " | Name: " + s.getName() +
                        " | Email: " + s.getEmail() + " | Course: " + s.getCourse() + "\n");
            }
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                String course = courseField.getText();

                if (name.isEmpty() || email.isEmpty() || course.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
                    return;
                }

                dao.updateStudent(new Student(id, name, email, course));
                resultArea.setText("\u2705 Student updated successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.");
            }
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                dao.deleteStudent(id);
                resultArea.setText("\u274C Student deleted (if existed).");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementUI::new);
    }
}