import java.awt.*;
import java.awt.event.*;

public class PatientRegistration extends Frame
        implements ActionListener {

    Label title, nameLabel, ageLabel, genderLabel;
    Label symptomLabel, resultLabel;
    TextField nameField, ageField;
    Choice genderChoice;
    Checkbox symptoms;
    Button submitButton, clearButton;
    TextArea resultArea;
    Panel formPanel, buttonPanel;

    Color navy = new Color(20, 65, 110);
    Color lightBlue = new Color(235, 245, 255);

    PatientRegistration() {
        setTitle("Patient Registration System");
        setSize(520, 520);
        setLayout(new BorderLayout(15, 15));
        setBackground(lightBlue);

        title = new Label("PATIENT REGISTRATION",
                Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBackground(navy);
        add(title, BorderLayout.NORTH);

        formPanel = new Panel(new GridLayout(4, 2, 12, 15));
        formPanel.setBackground(lightBlue);

        nameLabel = new Label("Patient Name:");
        ageLabel = new Label("Patient Age:");
        genderLabel = new Label("Gender:");
        symptomLabel = new Label("Symptoms:");

        nameField = new TextField(20);
        ageField = new TextField(20);

        genderChoice = new Choice();
        genderChoice.add("Male");
        genderChoice.add("Female");
        genderChoice.add("Other");

        symptoms = new Checkbox("Symptoms Present");

        Font font = new Font("Arial", Font.PLAIN, 14);
        nameLabel.setFont(font);
        ageLabel.setFont(font);
        genderLabel.setFont(font);
        symptomLabel.setFont(font);

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(ageLabel);
        formPanel.add(ageField);
        formPanel.add(genderLabel);
        formPanel.add(genderChoice);
        formPanel.add(symptomLabel);
        formPanel.add(symptoms);

        Panel centerPanel = new Panel(new BorderLayout(10, 15));
        centerPanel.setBackground(lightBlue);
        centerPanel.add(formPanel, BorderLayout.NORTH);

        buttonPanel = new Panel(new FlowLayout());
        buttonPanel.setBackground(lightBlue);

        submitButton = new Button("Submit");
        clearButton = new Button("Clear");

        submitButton.setBackground(navy);
        submitButton.setForeground(Color.WHITE);
        clearButton.setBackground(Color.LIGHT_GRAY);

        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);

        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        resultLabel = new Label("REGISTRATION DETAILS");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(navy);

        resultArea = new TextArea("", 6, 40,
                TextArea.SCROLLBARS_VERTICAL_ONLY);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));

        Panel outputPanel = new Panel(new BorderLayout(5, 5));
        outputPanel.setBackground(lightBlue);
        outputPanel.add(resultLabel, BorderLayout.NORTH);
        outputPanel.add(resultArea, BorderLayout.CENTER);

        centerPanel.add(outputPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            nameField.setText("");
            ageField.setText("");
            genderChoice.select(0);
            symptoms.setState(false);
            resultArea.setText("");
            return;
        }

        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String gender = genderChoice.getSelectedItem();
        String symptomStatus =
                symptoms.getState() ? "Yes" : "No";

        if (name.isEmpty() || age.isEmpty()) {
            resultArea.setText(
                    "Please enter patient name and age.");
            return;
        }

        try {
            int patientAge = Integer.parseInt(age);

            if (patientAge < 0 || patientAge > 130) {
                resultArea.setText(
                        "Please enter a valid age (0-130).");
                return;
            }

            resultArea.setText(
                    "Registration Successful!\n\n" +
                    "Patient Name : " + name + "\n" +
                    "Patient Age  : " + patientAge + "\n" +
                    "Gender       : " + gender + "\n" +
                    "Symptoms     : " + symptomStatus
            );

        } catch (NumberFormatException ex) {
            resultArea.setText(
                    "Please enter age as a number.");
        }
    }

    public static void main(String[] args) {
        new PatientRegistration();
    }
}