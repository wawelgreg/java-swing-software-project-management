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
    private JTextField textField1;
    private JButton addButton0;
    private JTextField textField2;
    private JButton addButton1;
    private JTextField textField3;
    private JButton addButton3;
    private JTextField textField4;
    private JButton addButton2;
    private JTextField textField5;
    private JButton addButton4;
    private JLabel out1;
    private JLabel out2;
    private JLabel out3;
    private JLabel out4;
    private JLabel out5;
    private DefaultListModel namesListModel;
    private DefaultListModel riskListModel;
    private ArrayList<Risk> riskArrayList;

    private DefaultListModel functionalListModel;
    private DefaultListModel nonfunctionalListModel;
    private int one = 0;
    private int two = 0;
    private int three = 0;
    private int four = 0;
    private int five = 0;

    public TestApplication() {
        setContentPane(mainPanel);
        setTitle("Project Management App 3000");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        fireButton.setEnabled(false);
        deleteRiskButton.setEnabled(false);
        deleteButtonFunc.setEnabled(false);
        deleteButtonNonf.setEnabled(false);

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

        // Functional and non-functional section
        functionalListModel = new DefaultListModel();
        funcList.setModel(functionalListModel);
        nonfunctionalListModel = new DefaultListModel();
        nonfList.setModel(nonfunctionalListModel);
        addButtonFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = funcText.getText();

                //User did not type in a unique name...
                if (name.equals("")) {
                    Toolkit.getDefaultToolkit().beep();
                    funcText.requestFocusInWindow();
                    funcText.selectAll();
                    return;
                }

                int index = funcList.getSelectedIndex(); //get selected index
                if (index == -1) { //no selection, so insert at beginning
                    index = 0;
                } else {           //add after the selected item
                    index++;
                }

               functionalListModel.insertElementAt(funcText.getText(), index);

                //Reset the text field.
                funcText.requestFocusInWindow();
                funcText.setText("");

                //Select the new item and make it visible.
                funcList.setSelectedIndex(index);
                funcList.ensureIndexIsVisible(index);
            }
        });

        addButtonNonf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nonfText.getText();

                //User did not type in a unique name...
                if (name.equals("")) {
                    Toolkit.getDefaultToolkit().beep();
                    nonfText.requestFocusInWindow();
                    nonfText.selectAll();
                    return;
                }

                int index = nonfList.getSelectedIndex(); //get selected index
                if (index == -1) { //no selection, so insert at beginning
                    index = 0;
                } else {           //add after the selected item
                    index++;
                }

                nonfunctionalListModel.insertElementAt(nonfText.getText(), index);

                //Reset the text field.
                nonfText.requestFocusInWindow();
                nonfText.setText("");

                //Select the new item and make it visible.
                nonfList.setSelectedIndex(index);
                nonfList.ensureIndexIsVisible(index);
            }
        });

        funcList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (funcList.getSelectedIndex() == -1) {
                        //No selection, disable fire button.
                        deleteButtonFunc.setEnabled(false);
                    } else {
                        //Selection, enable the fire button.
                        deleteButtonFunc.setEnabled(true);
                    }
                }
            }
        });
        nonfList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (nonfList.getSelectedIndex() == -1) {
                        //No selection, disable fire button.
                        deleteButtonNonf.setEnabled(false);
                    } else {
                        //Selection, enable the fire button.
                        deleteButtonNonf.setEnabled(true);
                    }
                }
            }
        });

        deleteButtonFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = funcList.getSelectedIndex();
                functionalListModel.remove(index);

                int size = functionalListModel.getSize();

                if (size == 0) { //Nobody's left, disable firing.
                    deleteButtonFunc.setEnabled(false);

                } else { //Select an index.
                    if (index == functionalListModel.getSize()) {
                        //removed item in last position
                        index--;
                    }

                    funcList.setSelectedIndex(index);
                    funcList.ensureIndexIsVisible(index);
                }
            }
        });
        deleteButtonNonf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = nonfList.getSelectedIndex();
                nonfunctionalListModel.remove(index);

                int size = nonfunctionalListModel.getSize();

                if (size == 0) { //Nobody's left, disable firing.
                    deleteButtonNonf.setEnabled(false);

                } else { //Select an index.
                    if (index == nonfunctionalListModel.getSize()) {
                        //removed item in last position
                        index--;
                    }

                    nonfList.setSelectedIndex(index);
                    nonfList.ensureIndexIsVisible(index);
                }
            }
        });

        // Effort Tracking

        addButton0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = Integer.parseInt(textField1.getText());
                one += input;
                out1.setText(one+"");
            }
        });
        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = Integer.parseInt(textField2.getText());
                two += input;
                out2.setText(two+"");
            }
        });
        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = Integer.parseInt(textField3.getText());
                three += input;
                out3.setText(three+"");
            }
        });
        addButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = Integer.parseInt(textField4.getText());
                four += input;
                out4.setText(four+"");
            }
        });
        addButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = Integer.parseInt(textField5.getText());
                five += input;
                out5.setText(five+"");
            }
        });
    }
    public static void main(String[] args) {
        TestApplication testApp = new TestApplication();
    }
}
