import javax.swing.*;

public class TestApplication extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField projName;
    private JTextPane projDescription;
    private JList list1;
    private JLabel teamMembersLabel;
    private JButton hireButton;
    private JTextField textField1;
    private JButton fireButton;

    public TestApplication()
    {
        setContentPane(mainPanel);
        setTitle("Greg Test");
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        TestApplication testApp = new TestApplication();
    }
}
