import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class LibraryManagementSystem {
    private JFrame frame;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField isbnField;
    private JTextField searchField;
    private JTable userTable;
    private JTable bookTable;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                LibraryManagementSystem window = new LibraryManagementSystem();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LibraryManagementSystem() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Library Management System");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(30, 30, 80, 25);
        frame.getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setBounds(120, 30, 160, 25);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 70, 80, 25);
        frame.getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(120, 70, 160, 25);
        frame.getContentPane().add(emailField);
        emailField.setColumns(10);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(30, 110, 80, 25);
        frame.getContentPane().add(lblPhone);

        phoneField = new JTextField();
        phoneField.setBounds(120, 110, 160, 25);
        frame.getContentPane().add(phoneField);
        phoneField.setColumns(10);

        JButton btnAddUser = new JButton("Add User");
        btnAddUser.setBounds(30, 150, 120, 25);
        frame.getContentPane().add(btnAddUser);
        btnAddUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User.addUser(nameField.getText(), emailField.getText(), phoneField.getText());
                refreshUserTable();
            }
        });

        JButton btnUpdateUser = new JButton("Update User");
        btnUpdateUser.setBounds(160, 150, 120, 25);
        frame.getContentPane().add(btnUpdateUser);
        btnUpdateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int userId = (int) userTable.getValueAt(selectedRow, 0);
                    User.updateUser(userId, nameField.getText(), emailField.getText(), phoneField.getText());
                    refreshUserTable();
                }
            }
        });

        JButton btnDeleteUser = new JButton("Delete User");
        btnDeleteUser.setBounds(290, 150, 120, 25);
        frame.getContentPane().add(btnDeleteUser);
        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int userId = (int) userTable.getValueAt(selectedRow, 0);
                    User.deleteUser(userId);
                    refreshUserTable();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 190, 380, 150);
        frame.getContentPane().add(scrollPane);

        userTable = new JTable();
        scrollPane.setViewportView(userTable);
        refreshUserTable();

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(420, 30, 80, 25);
        frame.getContentPane().add(lblTitle);

        titleField = new JTextField();
        titleField.setBounds(510, 30, 160, 25);
        frame.getContentPane().add(titleField);
        titleField.setColumns(10);

        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(420, 70, 80, 25);
        frame.getContentPane().add(lblAuthor);

        authorField = new JTextField();
        authorField.setBounds(510, 70, 160, 25);
        frame.getContentPane().add(authorField);
        authorField.setColumns(10);

        JLabel lblIsbn = new JLabel("ISBN:");
        lblIsbn.setBounds(420, 110, 80, 25);
        frame.getContentPane().add(lblIsbn);

        isbnField = new JTextField();
        isbnField.setBounds(510, 110, 160, 25);
        frame.getContentPane().add(isbnField);
        isbnField.setColumns(10);

        JButton btnAddBook = new JButton("Add Book");
        btnAddBook.setBounds(420, 150, 120, 25);
        frame.getContentPane().add(btnAddBook);
        btnAddBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Book.addBook(titleField.getText(), authorField.getText(), isbnField.getText());
                refreshBookTable();
            }
        });

        JButton btnUpdateBook = new JButton("Update Book");
        btnUpdateBook.setBounds(550, 150, 120, 25);
        frame.getContentPane().add(btnUpdateBook);
        btnUpdateBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bookTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int bookId = (int) bookTable.getValueAt(selectedRow, 0);
                    Book.updateBook(bookId, titleField.getText(), authorField.getText(), isbnField.getText());
                    refreshBookTable();
                }
            }
        });

        JButton btnDeleteBook = new JButton("Delete Book");
        btnDeleteBook.setBounds(680, 150, 120, 25);
        frame.getContentPane().add(btnDeleteBook);
        btnDeleteBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bookTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int bookId = (int) bookTable.getValueAt(selectedRow, 0);
                    Book.deleteBook(bookId);
                    refreshBookTable();
                }
            }
        });

        JScrollPane bookScrollPane = new JScrollPane();
        bookScrollPane.setBounds(420, 190, 380, 150);
        frame.getContentPane().add(bookScrollPane);

        bookTable = new JTable();
        bookScrollPane.setViewportView(bookTable);
        refreshBookTable();

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBounds(30, 360, 80, 25);
        frame.getContentPane().add(lblSearch);

        searchField = new JTextField();
        searchField.setBounds(120, 360, 160, 25);
        frame.getContentPane().add(searchField);
        searchField.setColumns(10);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(290, 360, 120, 25);
        frame.getContentPane().add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                ResultSet rs = Book.searchBooks(keyword);
                bookTable.setModel(DbUtils.resultSetToTableModel(rs));
            }
        });
    }

    private void refreshUserTable() {
        ResultSet rs = User.getAllUsers();
        userTable.setModel(DbUtils.resultSetToTableModel(rs));
    }

    private void refreshBookTable() {
        ResultSet rs = Book.getAllBooks();
        bookTable.setModel(DbUtils.resultSetToTableModel(rs));
    }
}



// import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.sql.ResultSet;
// import net.proteanit.sql.DbUtils;

// public class LibraryManagementSystem {
//     private JFrame frame;
//     private JTextField nameField;
//     private JTextField emailField;
//     private JTextField phoneField;
//     private JTextField titleField;
//     private JTextField authorField;
//     private JTextField isbnField;
//     private JTextField searchField;
//     private JTable userTable;
//     private JTable bookTable;

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             try {
//                 LibraryManagementSystem window = new LibraryManagementSystem();
//                 window.frame.setVisible(true);
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });
//     }

//     public LibraryManagementSystem() {
//         initialize();
//     }

//     private void initialize() {
//         frame = new JFrame("Library Management System");
//         frame.setBounds(100, 100, 800, 600);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.getContentPane().setLayout(null);

//         JLabel lblName = new JLabel("Name:");
//         lblName.setBounds(30, 30, 80, 25);
//         frame.getContentPane().add(lblName);

//         nameField = new JTextField();
//         nameField.setBounds(120, 30, 160, 25);
//         frame.getContentPane().add(nameField);
//         nameField.setColumns(10);

//         JLabel lblEmail = new JLabel("Email:");
//         lblEmail.setBounds(30, 70, 80, 25);
//         frame.getContentPane().add(lblEmail);

//         emailField = new JTextField();
//         emailField.setBounds(120, 70, 160, 25);
//         frame.getContentPane().add(emailField);
//         emailField.setColumns(10);

//         JLabel lblPhone = new JLabel("Phone:");
//         lblPhone.setBounds(30, 110, 80, 25);
//         frame.getContentPane().add(lblPhone);

//         phoneField = new JTextField();
//         phoneField.setBounds(120, 110, 160, 25);
//         frame.getContentPane().add(phoneField);
//         phoneField.setColumns(10);

//         JButton btnAddUser = new JButton("Add User");
//         btnAddUser.setBounds(30, 150, 120, 25);
//         frame.getContentPane().add(btnAddUser);
//         btnAddUser.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 User.addUser(nameField.getText(), emailField.getText(), phoneField.getText());
//                 refreshUserTable();
//             }
//         });

//         JButton btnUpdateUser = new JButton("Update User");
//         btnUpdateUser.setBounds(160, 150, 120, 25);
//         frame.getContentPane().add(btnUpdateUser);
//         btnUpdateUser.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 int selectedRow = userTable.getSelectedRow();
//                 if (selectedRow >= 0) {
//                     int userId = (int) userTable.getValueAt(selectedRow, 0);
//                     User.updateUser(userId, nameField.getText(), emailField.getText(), phoneField.getText());
//                     refreshUserTable();
//                 }
//             }
//         });

//         JButton btnDeleteUser = new JButton("Delete User");
//         btnDeleteUser.setBounds(290, 150, 120, 25);
//         frame.getContentPane().add(btnDeleteUser);
//         btnDeleteUser.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 int selectedRow = userTable.getSelectedRow();
//                 if (selectedRow >= 0) {
//                     int userId = (int) userTable.getValueAt(selectedRow, 0);
//                     User.deleteUser(userId);
//                     refreshUserTable();
//                 }
//             }
//         });

//         JScrollPane scrollPane = new JScrollPane();
//         scrollPane.setBounds(30, 190, 380, 150);
//         frame.getContentPane().add(scrollPane);

//         userTable = new JTable();
//         scrollPane.setViewportView(userTable);
//         refreshUserTable();

//         JLabel lblTitle = new JLabel("Title:");
//         lblTitle.setBounds(420, 30, 80, 25);
//         frame.getContentPane().add(lblTitle);

//         titleField = new JTextField();
//         titleField.setBounds(510, 30, 160, 25);
//         frame.getContentPane().add(titleField);
//         titleField.setColumns(10);

//         JLabel lblAuthor = new JLabel("Author:");
//         lblAuthor.setBounds(420, 70, 80, 25);
//         frame.getContentPane().add(lblAuthor);

//         authorField = new JTextField();
//         authorField.setBounds(510, 70, 160, 25);
//         frame.getContentPane().add(authorField);
//         authorField.setColumns(10);

//         JLabel lblIsbn = new JLabel("ISBN:");
//         lblIsbn.setBounds(420, 110, 80, 25);
//         frame.getContentPane().add(lblIsbn);

//         isbnField = new JTextField();
//         isbnField.setBounds(510, 110, 160, 25);
//         frame.getContentPane().add(isbnField);
//         isbnField.setColumns(10);

//         JButton btnAddBook = new JButton("Add Book");
//         btnAddBook.setBounds(420, 150, 120, 25);
//         frame.getContentPane().add(btnAddBook);
//         btnAddBook.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 Book.addBook(titleField.getText(), authorField.getText(), isbnField.getText());
//                 refreshBookTable();
//             }
//         });

//         JButton btnUpdateBook = new JButton("Update Book");
//         btnUpdateBook.setBounds(550, 150, 120, 25);
//         frame.getContentPane().add(btnUpdateBook);
//         btnUpdateBook.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 int selectedRow = bookTable.getSelectedRow();
//                 if (selectedRow >= 0) {
//                     int bookId = (int) bookTable.getValueAt(selectedRow, 0);
//                     Book.updateBook(bookId, titleField.getText(), authorField.getText(), isbnField.getText());
//                     refreshBookTable();
//                 }
//             }
//         });

//         JButton btnDeleteBook = new JButton("Delete Book");
//         btnDeleteBook.setBounds(680, 150, 120, 25);
//         frame.getContentPane().add(btnDeleteBook);
//         btnDeleteBook.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 int selectedRow = bookTable.getSelectedRow();
//                 if (selectedRow >= 0) {
//                     int bookId = (int) bookTable.getValueAt(selectedRow, 0);
//                     Book.deleteBook(bookId);
//                     refreshBookTable();
//                 }
//             }
//         });

//         JScrollPane bookScrollPane = new JScrollPane();
//         bookScrollPane.setBounds(420, 190, 380, 150);
//         frame.getContentPane().add(bookScrollPane);

//         bookTable = new JTable();
//         bookScrollPane.setViewportView(bookTable);
//         refreshBookTable();

//         JLabel lblSearch = new JLabel("Search:");
//         lblSearch.setBounds(30, 360, 80, 25);
//         frame.getContentPane().add(lblSearch);

//         searchField = new JTextField();
//         searchField.setBounds(120, 360, 160, 25);
//         frame.getContentPane().add(searchField);
//         searchField.setColumns(10);

//         JButton btnSearch = new JButton("Search");
//         btnSearch.setBounds(290, 360, 120, 25);
//         frame.getContentPane().add(btnSearch);
//         btnSearch.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 String keyword = searchField.getText();
//                 ResultSet rs = Book.searchBooks(keyword);
//                 bookTable.setModel(DbUtils.resultSetToTableModel(rs));
//             }
//         });
//     }

//     private void refreshUserTable() {
//         ResultSet rs = User.getAllUsers();
//         userTable.setModel(DbUtils.resultSetToTableModel(rs));
//     }

//     private void refreshBookTable() {
//         ResultSet rs = Book.getAllBooks();
//         bookTable.setModel(DbUtils.resultSetToTableModel(rs));
//     }
// }