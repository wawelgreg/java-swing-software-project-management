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
    private JButton deleteRiskButton;
    private JTextField riskName;
    private JTextPane riskDescription;
    private JList riskList;
    private JTextField riskScore;
    private JScrollPane Requirements;
    private JList funcList;
    private JButton addButtonFunc;
    private JButton deleteButtonFunc;
    private JButton addButtonNonf;
    private JButton deleteButtonNonf;
    private JTextField funcText;
    private JList nonfList;
    private JTextField nonfText;
    private DefaultListModel namesListModel;
    private DefaultListModel riskListModel;
    private ArrayList<Risk> riskArrayList;

    public TestApplication() {
        setContentPane(mainPanel);
        setTitle("Project Management App 3000");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        fireButton.setEnabled(false);
        deleteRiskButton.setEnabled(false);

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

                riskListModel.insertElementAt(riskName.getText() + ": ("+rScore+")", index);
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
                int listIndex = riskList.getSelectedIndex();
                Risk riskObject = riskArrayList.get(listIndex);
                if (!e.getValueIsAdjusting()) {
                    riskName.setText(riskObject.getRiskName());
                    riskDescription.setText(riskObject.getRiskDescription());
                    riskScore.setText(String.valueOf(riskObject.getRiskProbability()));
                    if (!e.getValueIsAdjusting()) {
                        if (riskList.getSelectedIndex() == -1) {
                            //No selection, disable delete button.
                            deleteRiskButton.setEnabled(false);
                        } else {
                            //Selection, enable the delete button.
                            deleteRiskButton.setEnabled(true);
                        }
                    }
                }
            }
        });
//        updateRiskButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int listIndex = riskList.getSelectedIndex();
//                Risk riskObject = riskArrayList.get(listIndex);
//                riskObject.setRiskDescription(riskName.getText());
//                riskObject.setRiskDescription(riskDescription.getText());
//                riskObject.setRiskProbability(Integer.parseInt(riskScore.getText()));
//                riskListModel.insertElementAt(riskName.getText() + ": ("+riskObject.getRiskProbability()+")", listIndex);
//            }
//        });
        deleteRiskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = riskList.getSelectedIndex();
                riskListModel.removeElementAt(index);
                riskArrayList.remove(index);

                int size = riskListModel.getSize();

                if (size == 0) { //Nobody's left, disable deleting.
                    deleteRiskButton.setEnabled(false);

                } else { //Select an index.
                    if (index == riskListModel.getSize()) {
                        //removed item in last position
                        index--;
                    }

                    riskList.setSelectedIndex(index);
                    riskList.ensureIndexIsVisible(index);
                }
            }
        });
    }
    public static void main(String[] args) {
        TestApplication testApp = new TestApplication();
    }
}
