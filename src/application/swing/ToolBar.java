package application.swing;

import control.Command;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import kata7.model.Attribute;
import view.AttributeDialog;
import view.PopulationDialog;

public class ToolBar extends JPanel implements PopulationDialog, AttributeDialog{
    private final Map<String, Command> commands;
    private final List <Attribute> attributes = new ArrayList<>();
    private JComboBox comboBox;

    public ToolBar(Map<String, Command> commands) {
        super(new FlowLayout());
        this.commands = commands;
        this.add(mailDomainsAttribute());
        this.add(firstCharAttribute());
        this.add(comboBox());
        this.add(calculateButton());
    }
    

    
    private Attribute mailDomainsAttribute(){
        return new Attribute<Person, String>() {
            @Override
            public String get(Person item) {
                return item.getMail().split("@")[1];
            }
        };
    }
    
    private Attribute firstCharAttribute(){
        return new Attribute<Person, Character>() {
            @Override
            public Character get(Person item) {
                return item.getMail().charAt(0);
            }
        };
    }
    
    private void add (Attribute attribute){
        attributes.add(attribute);
    }
    
    @Override
    public Attribute attribute() {
        return attributes.get(comboBox.getSelectedIndex());
    }

    private JComboBox comboBox() {
        comboBox = new JComboBox(options("Mail Domains", "First Char"));
        return comboBox;
    }

    private String[] options(String... options) {
        return options;
    }
    
    private JButton calculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("calculate").execute();
            }
        });
        return button;
    }

    @Override
    public List population() {
        try {
            return MailReader.read("emails.txt");
        } catch (IOException ex) {
            return new ArrayList();
        }
        
    }
}

    

