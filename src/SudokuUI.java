import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class SudokuUI {
    private JFrame sudokuFrame;
    private JPanel textBoxPanel;
    private SudokuBoard board;

    public SudokuUI(SudokuBoard board) {
        this.board = board;
        sudokuFrame = new JFrame("Sudoku");
        sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textBoxPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        GridLayout glayout = new GridLayout(9, 9);
        glayout.setHgap(2);
        glayout.setVgap(2);
        textBoxPanel.setLayout(glayout);
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                JTextField textField = new JTextField();
                
                Vector2 pos = new Vector2(j, i);
                textField.setText( String.valueOf(board.GetCell(pos) ) );
                textField.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) { 
                        if (textField.getText().length() >= 1 ) // limit textfield to 3 characters
                            e.consume(); 
                    }  
                });
                textField.setBorder(null);
                textField.setBackground(Color.LIGHT_GRAY);
                textField.setHorizontalAlignment(SwingConstants.CENTER);
                textField.setForeground(Color.BLACK);
                textField.setFont(new Font("Arial", Font.PLAIN, 30));
                textBoxPanel.add(textField);
            }
        }
        
        textBoxPanel.setPreferredSize(new Dimension(60 * 9, 60 * 9));
        containerPanel.add(textBoxPanel);

        sudokuFrame.getContentPane().add(containerPanel);
        sudokuFrame.pack();
        sudokuFrame.setVisible(true);
    }
}
