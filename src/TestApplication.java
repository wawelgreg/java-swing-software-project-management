import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TestApplication extends JFrame
{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField projName;
    private JTextPane projDescription;
    private JList namesList;
    private JLabel teamMembersLabel;
    private JButton hireButton;
    private JTextField employeeName;
    private JButton fireButton;
    private JButton addRiskButton;
    private JButton updateRiskButton;
    private JButton deleteRiskButton;
    private JTextField riskName;
    private JTextPane riskDescription;
    private JList riskList;
    private JTextField riskScore;
    private DefaultListModel namesListModel;
    private DefaultListModel riskListModel;
    private ArrayList<Risk> riskArrayList;

    public TestApplication() {
        setContentPane(mainPanel);
        setTitle("Greg Test");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        fireButton.setEnabled(false);

        // General Section //
        // Names list
        namesListModel = new DefaultListModel();
        namesList.setModel(namesListModel);

        // Adding name to names list!
        hireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = employeeName.getText();

                //User did not type in a unique name...
                if (name.equals("")) {
                    Toolkit.getDefaultToolkit().beep();
                    employeeName.requestFocusInWindow();
                    employeeName.selectAll();
                    return;
                }

                int index = namesList.getSelectedIndex(); //get selected index
                if (index == -1) { //no selection, so insert at beginning
                    index = 0;
                } else {           //add after the selected item
                    index++;
                }

                namesListModel.insertElementAt(employeeName.getText(), index);

                //Reset the text field.
                employeeName.requestFocusInWindow();
                employeeName.setText("");

                //Select the new item and make it visible.
                namesList.setSelectedIndex(index);
                namesList.ensureIndexIsVisible(index);
            }
        });
        // Disabling / Enabling fireButton
        namesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (namesList.getSelectedIndex() == -1) {
                        //No selection, disable fire button.
                        fireButton.setEnabled(false);
                    } else {
                        //Selection, enable the fire button.
                        fireButton.setEnabled(true);
                    }
                }
            }
        });
        // Fire button
        fireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = namesList.getSelectedIndex();
                namesListModel.remove(index);

                int size = namesListModel.getSize();

                if (size == 0) { //Nobody's left, disable firing.
                    fireButton.setEnabled(false);

                } else { //Select an index.
                    if (index == namesListModel.getSize()) {
                        //removed item in last position
                        index--;
                    }

                    namesList.setSelectedIndex(index);
                    namesList.ensureIndexIsVisible(index);
                }
            }
        });

        // Risks section
        riskListModel = new DefaultListModel();
        riskList.setModel(riskListModel);
        riskArrayList = new ArrayList<>();

        addRiskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rName = riskName.getText();
                String rDesc = riskDescription.getText();
                int rScore = Integer.parseInt(riskScore.getText());

                //User did not type in a unique name...
                if (rName.equals("") || rDesc.equals("")) {
                    Toolkit.getDefaultToolkit().beep();
                    employeeName.requestFocusInWindow();
                    employeeName.selectAll();
                    return;
                }

                Risk riskObject = new Risk(rName, rDesc, rScore);

                int index = riskList.getSelectedIndex(); //get selected index
                if (index == -1) { //no selection, so insert at beginning
                    index = 0;
                } else {           //add after the selected item
                    index++;
                }

                riskListModel.insertElementAt(riskName.getText(), index);
                riskArrayList.add(index, riskObject);

                //Reset the text field.
                employeeName.requestFocusInWindow();
                employeeName.setText("");

                //Select the new item and make it visible.
                namesList.setSelectedIndex(index);
                namesList.ensureIndexIsVisible(index);
            }
        });

        riskList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int listIndex = riskList.getSelectedIndex();
                    Risk riskObject = riskArrayList.get(listIndex);
                    riskName.setText(riskObject.getRiskName());
                    riskDescription.setText(riskObject.getRiskDescription());
                    riskScore.setText(String.valueOf(riskObject.getRiskProbability()));
                }
            }
        });
    }
    public static void main(String[] args) {
        TestApplication testApp = new TestApplication();
    }
}
