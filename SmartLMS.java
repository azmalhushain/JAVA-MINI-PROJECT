
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class SmartLMS extends JFrame {

    Color navy = new Color(8, 65, 78);
    Color teal = new Color(24, 167, 166);
    Color background = new Color(239, 245, 246);
    Color textColor = new Color(40, 57, 65);

    ArrayList<String[]> courses = new ArrayList<>();
    ArrayList<String[]> students = new ArrayList<>();

    JPanel contentPanel;
    CardLayout cardLayout;

    JLabel courseCount, studentCount;

    public SmartLMS() {
        setTitle("Smart Learning Management System");
        setSize(1200, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        loadSampleData();
        createSidebar();
        createMainContent();

        showDashboard();
        setVisible(true);
    }

    void loadSampleData() {
        courses.add(new String[]{"Java Programming", "Learn Java and OOP", "Programming"});
        courses.add(new String[]{"Data Structures", "Learn algorithms and structures", "Computer Science"});
        courses.add(new String[]{"Database Management", "Learn SQL and databases", "Database"});
        courses.add(new String[]{"Web Development", "HTML, CSS and JavaScript", "Web Development"});
        courses.add(new String[]{"Python Programming", "Learn Python fundamentals", "Programming"});

        students.add(new String[]{"AJMAL BIN AJMAL", "ajmalbinajmal@gmail.com"});
        students.add(new String[]{"AMAN RAJ AZAD", "aman@gmail.com"});
        students.add(new String[]{"NIHAR BARMON", "nihar@gmail.com"});
    }

    void createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(navy);
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(25, 15, 20, 15));

        JLabel logo = new JLabel("  ✦ Smart LMS");
        logo.setFont(new Font("Arial", Font.BOLD, 23));
        logo.setForeground(Color.WHITE);
        logo.setBorder(new EmptyBorder(5, 0, 45, 0));
        sidebar.add(logo);

        addNavButton(sidebar, "▦  Dashboard", "dashboard");
        addNavButton(sidebar, "▣  Classes", "classes");
        addNavButton(sidebar, "♙  Students", "students");
        addNavButton(sidebar, "▤  Study Materials", "materials");
        addNavButton(sidebar, "◷  Assignments", "assignments");
        addNavButton(sidebar, "▧  Examinations", "exams");
        addNavButton(sidebar, "◉  Attendance", "attendance");

        sidebar.add(Box.createVerticalGlue());

        JLabel footer = new JLabel("  Learn. Grow. Achieve.");
        footer.setForeground(new Color(170, 215, 218));
        sidebar.add(footer);

        add(sidebar, BorderLayout.WEST);
    }

    void addNavButton(JPanel sidebar, String title, String page) {
        JButton button = new JButton(title);
        button.setMaximumSize(new Dimension(190, 48));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setForeground(Color.WHITE);
        button.setBackground(navy);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(12, 12, 12, 5));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(teal);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(navy);
            }
        });

        button.addActionListener(e -> {
            switch (page) {
                case "dashboard": showDashboard(); break;
                case "classes": showClasses(); break;
                case "students": showStudents(); break;
                default: showComingSoon(title);
            }
        });

        sidebar.add(button);
        sidebar.add(Box.createVerticalStrut(8));
    }

    void createMainContent() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(background);
        add(contentPanel, BorderLayout.CENTER);
    }

    JPanel createPage(String title, String subtitle) {
        JPanel panel = new JPanel(new BorderLayout(15, 20));
        panel.setBackground(background);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel titles = new JPanel();
        titles.setOpaque(false);
        titles.setLayout(new BoxLayout(titles, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel(title);
        heading.setFont(new Font("Arial", Font.BOLD, 30));
        heading.setForeground(textColor);

        JLabel subheading = new JLabel(subtitle);
        subheading.setFont(new Font("Arial", Font.PLAIN, 13));
        subheading.setForeground(Color.GRAY);

        titles.add(heading);
        titles.add(Box.createVerticalStrut(7));
        titles.add(subheading);

        JLabel profile = new JLabel("  ● Admin  ");
        profile.setFont(new Font("Arial", Font.BOLD, 13));
        profile.setForeground(navy);

        header.add(titles, BorderLayout.WEST);
        header.add(profile, BorderLayout.EAST);

        panel.add(header, BorderLayout.NORTH);
        return panel;
    }

    void showDashboard() {
        JPanel page = createPage(
            "Dashboard",
            "Welcome to your Smart Learning Management System"
        );

        JPanel body = new JPanel(new BorderLayout(15, 25));
        body.setOpaque(false);

        JPanel stats = new JPanel(new GridLayout(1, 3, 15, 15));
        stats.setOpaque(false);

        courseCount = new JLabel();
        studentCount = new JLabel();

        stats.add(statCard("TOTAL CLASSES",
                String.valueOf(courses.size()), new Color(236, 190, 221)));
        stats.add(statCard("TOTAL STUDENTS",
                String.valueOf(students.size()), new Color(163, 224, 205)));
        stats.add(statCard("LEARNING STATUS",
                "ACTIVE", new Color(194, 190, 239)));

        JPanel welcome = new JPanel(new BorderLayout());
        welcome.setBackground(navy);
        welcome.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel welcomeText = new JLabel(
                "<html><font color='white' size='6'><b>Welcome to Smart LMS!</b></font><br><br>"
                + "<font color='#C7E9E9' size='4'>Manage your learning journey in one place.</font></html>"
        );

        JButton explore = new JButton("Explore Classes →");
        explore.setBackground(Color.WHITE);
        explore.setForeground(navy);
        explore.setFocusPainted(false);
        explore.addActionListener(e -> showClasses());

        welcome.add(welcomeText, BorderLayout.CENTER);
        welcome.add(explore, BorderLayout.EAST);

        JPanel recent = new JPanel(new BorderLayout());
        recent.setOpaque(false);

        JLabel recentTitle = new JLabel("Available Classes");
        recentTitle.setFont(new Font("Arial", Font.BOLD, 21));

        JPanel courseGrid = new JPanel(new GridLayout(0, 3, 12, 12));
        courseGrid.setOpaque(false);

        for (String[] course : courses) {
            courseGrid.add(courseCard(course));
        }

        recent.add(recentTitle, BorderLayout.NORTH);
        recent.add(courseGrid, BorderLayout.CENTER);

        body.add(stats, BorderLayout.NORTH);
        body.add(welcome, BorderLayout.CENTER);
        body.add(recent, BorderLayout.SOUTH);

        page.add(body, BorderLayout.CENTER);

        contentPanel.removeAll();
        contentPanel.add(page, "dashboard");
        cardLayout.show(contentPanel, "dashboard");
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    JPanel statCard(String title, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
            new LineBorder(new Color(225, 235, 235), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel heading = new JLabel(title);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel number = new JLabel(value);
        number.setForeground(navy);
        number.setFont(new Font("Arial", Font.BOLD, 28));

        JPanel colorLine = new JPanel();
        colorLine.setBackground(color);
        colorLine.setMaximumSize(new Dimension(60, 5));

        card.add(heading);
        card.add(Box.createVerticalStrut(15));
        card.add(number);
        card.add(Box.createVerticalStrut(10));
        card.add(colorLine);

        return card;
    }

    JPanel courseCard(String[] course) {
        JPanel card = new JPanel(new BorderLayout(5, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 232, 234), 1, true),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel category = new JLabel("✦ " + course[2]);
        category.setForeground(teal);
        category.setFont(new Font("Arial", Font.BOLD, 11));

        JLabel name = new JLabel(course[0]);
        name.setForeground(navy);
        name.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel description = new JLabel(
            "<html><body width='180'>" + course[1] + "</body></html>"
        );
        description.setForeground(Color.GRAY);
        description.setFont(new Font("Arial", Font.PLAIN, 11));

        card.add(category, BorderLayout.NORTH);

        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.add(name);
        details.add(Box.createVerticalStrut(8));
        details.add(description);

        card.add(details, BorderLayout.CENTER);
        return card;
    }

    void showClasses() {
        JPanel page = createPage(
            "Classes",
            "Manage and explore all available courses"
        );

        JPanel body = new JPanel(new BorderLayout(15, 20));
        body.setOpaque(false);

        JPanel top = new JPanel(new BorderLayout(10, 10));
        top.setOpaque(false);

        JTextField search = new JTextField();
        search.setPreferredSize(new Dimension(250, 38));
        search.setToolTipText("Search courses");

        JButton add = new JButton("+ Create Class");
        add.setBackground(navy);
        add.setForeground(Color.WHITE);
        add.setFocusPainted(false);

        top.add(search, BorderLayout.WEST);
        top.add(add, BorderLayout.EAST);

        JPanel grid = new JPanel(new GridLayout(0, 3, 15, 15));
        grid.setOpaque(false);

        Runnable refresh = () -> {
            grid.removeAll();
            String query = search.getText().toLowerCase();

            for (String[] course : courses) {
                if (course[0].toLowerCase().contains(query)
                    || course[2].toLowerCase().contains(query)) {

                    JPanel card = courseCard(course);
                    JButton delete = new JButton("Remove");
                    delete.setForeground(new Color(190, 70, 85));
                    delete.addActionListener(e -> {
                        int choice = JOptionPane.showConfirmDialog(
                            this, "Remove " + course[0] + "?",
                            "Confirm", JOptionPane.YES_NO_OPTION
                        );
                        if (choice == JOptionPane.YES_OPTION) {
                            courses.remove(course);
                            refreshClasses();
                            showClasses();
                        }
                    });

                    card.add(delete, BorderLayout.SOUTH);
                    grid.add(card);
                }
            }

            grid.revalidate();
            grid.repaint();
        };

        add.addActionListener(e -> {
            JTextField name = new JTextField();
            JTextField description = new JTextField();
            String[] categories = {
                "Programming", "Computer Science",
                "Database", "Web Development"
            };
            JComboBox<String> category = new JComboBox<>(categories);

            JPanel form = new JPanel(new GridLayout(0, 1, 5, 5));
            form.add(new JLabel("Class Name"));
            form.add(name);
            form.add(new JLabel("Description"));
            form.add(description);
            form.add(new JLabel("Category"));
            form.add(category);

            int result = JOptionPane.showConfirmDialog(
                this, form, "Create New Class",
                JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION
                && !name.getText().trim().isEmpty()) {
                courses.add(new String[]{
                    name.getText().trim(),
                    description.getText().trim(),
                    category.getSelectedItem().toString()
                });
                showClasses();
            }
        });

        search.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {
                public void insertUpdate(javax.swing.event.DocumentEvent e) { refresh.run(); }
                public void removeUpdate(javax.swing.event.DocumentEvent e) { refresh.run(); }
                public void changedUpdate(javax.swing.event.DocumentEvent e) { refresh.run(); }
            }
        );

        refresh.run();

        body.add(top, BorderLayout.NORTH);
        body.add(new JScrollPane(grid), BorderLayout.CENTER);

        page.add(body, BorderLayout.CENTER);

        contentPanel.removeAll();
        contentPanel.add(page, "classes");
        cardLayout.show(contentPanel, "classes");
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    void refreshClasses() {
        if (courseCount != null) {
            courseCount.setText(String.valueOf(courses.size()));
        }
        if (studentCount != null) {
            studentCount.setText(String.valueOf(students.size()));
        }
    }

    void showStudents() {
        JPanel page = createPage(
            "Students",
            "Manage registered learners"
        );

        JPanel body = new JPanel(new BorderLayout(15, 15));
        body.setOpaque(false);

        JButton add = new JButton("+ Add Student");
        add.setBackground(navy);
        add.setForeground(Color.WHITE);

        String[] columns = {"Student Name", "Email Address"};
        String[][] data = students.toArray(new String[0][]);

        JTable table = new JTable(data, columns);
        table.setRowHeight(35);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        add.addActionListener(e -> {
            JTextField name = new JTextField();
            JTextField email = new JTextField();

            Object[] form = {
                "Student Name:", name,
                "Email Address:", email
            };

            int result = JOptionPane.showConfirmDialog(
                this, form, "Register Student",
                JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION
                && !name.getText().trim().isEmpty()
                && !email.getText().trim().isEmpty()) {
                students.add(new String[]{
                    name.getText().trim(),
                    email.getText().trim()
                });
                showStudents();
            }
        });

        body.add(add, BorderLayout.NORTH);
        body.add(new JScrollPane(table), BorderLayout.CENTER);
        page.add(body, BorderLayout.CENTER);

        contentPanel.removeAll();
        contentPanel.add(page, "students");
        cardLayout.show(contentPanel, "students");
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    void showComingSoon(String title) {
        JPanel page = createPage(title, "Learning management module");
        JLabel message = new JLabel(
            "<html><center><h1>Coming Soon!</h1>"
            + "This module will be available in the next development phase."
            + "</center></html>",
            SwingConstants.CENTER
        );
        message.setFont(new Font("Arial", Font.PLAIN, 20));
        page.add(message, BorderLayout.CENTER);

        contentPanel.removeAll();
        contentPanel.add(page);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SmartLMS());
    }
}
