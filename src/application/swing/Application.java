package application.swing;

import control.CalculateCommand;
import control.Command;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.AttributeDialog;
import view.HistogramDisplay;
import view.PopulationDialog;

public class Application extends JFrame{
    private Map<String, Command> commands = new HashMap<>();
    private AttributeDialog attributeDialog;
    private PopulationDialog populationDialog;
    private HistogramDisplay histogramDisplay;
    
    public static void main(String[] args) {
        new Application().setVisible(true);
    }

    public Application() throws HeadlessException {
        this.deployUI();
        this.createCommands();
    }

    private void deployUI() {
        this.setTitle("Histogram viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);
        this.getContentPane().add(histogramPanel(), BorderLayout.CENTER);
        this.getContentPane().add(toolBar(commands), BorderLayout.NORTH);
    }

    private void createCommands() {
        commands.put("calculate", new CalculateCommand(attributeDialog, populationDialog, histogramDisplay));
    }

    private HistogramPanel histogramPanel() {
        HistogramPanel panel = new HistogramPanel();
        this.histogramDisplay = panel;
        return panel;
    }

    private JPanel toolBar(Map<String, Command> commands) {
        ToolBar toolBar = new ToolBar(commands);
        this.attributeDialog = toolBar;
        this.populationDialog = toolBar;
        return toolBar;
    }
    
    
}
