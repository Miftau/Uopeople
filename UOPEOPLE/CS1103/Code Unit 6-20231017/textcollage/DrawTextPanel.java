package textcollage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class DrawTextPanel extends JPanel {
	private static final long serialVersionUID = 5891049222728926722L;
	private ArrayList<DrawTextItem> textItems;
    private Color currentTextColor = Color.BLACK;
    private Canvas canvas;
    private JTextField input;
    private JMenuBar menuBar;
    private MenuHandler menuHandler;
    private JMenuItem undoMenuItem;

    public DrawTextPanel() {
        textItems = new ArrayList<>();
        undoMenuItem = new JMenuItem("Remove Item");
        undoMenuItem.setEnabled(true);
        menuHandler = new MenuHandler();

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        canvas = new Canvas();
        add(canvas, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.add(new JLabel("Text to add: "));
        input = new JTextField("Hello World!", 40);
        bottom.add(input);
        add(bottom, BorderLayout.SOUTH);

        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                doMousePress(e);
            }
        });
    }

    public JMenuBar getMenuBar() {
        if (menuBar == null) {
            menuBar = new JMenuBar();

            String commandKey = "control ";
            
            JMenuItem saveItem = new JMenuItem("Save...");
            saveItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "S"));
            saveItem.addActionListener(menuHandler);
            menuBar.add(saveItem);
            // Add "Open" menu item
            JMenuItem openItem = new JMenuItem("Open...");
            openItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "O"));
            openItem.addActionListener(menuHandler);
            menuBar.add(openItem);

            JMenu editMenu = new JMenu("Edit");
            menuBar.add(editMenu);
            undoMenuItem.addActionListener(menuHandler);
            undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "Z"));
            editMenu.add(undoMenuItem);
            editMenu.addSeparator();

            JMenuItem clearItem = new JMenuItem("Clear");
            clearItem.addActionListener(menuHandler);
            editMenu.add(clearItem);

            JMenu optionsMenu = new JMenu("Options");
            menuBar.add(optionsMenu);
            JMenuItem colorItem = new JMenuItem("Set Text Color...");
            colorItem.addActionListener(menuHandler);
            optionsMenu.add(colorItem);
            
        }
        return menuBar;
    }
    
    

    public void doMousePress(MouseEvent e) {
        String text = input.getText().trim();
        if (text.length() == 0) {
            input.setText("Hello World!");
            text = "Hello World!";
        }
        DrawTextItem s = new DrawTextItem(new ArrayList<>(), e.getX(), e.getY());
        s.setTextColor(currentTextColor);
        textItems.add(s);
        undoMenuItem.setEnabled(true);
        canvas.repaint();
    }


    private class Canvas extends JPanel {
        private static final long serialVersionUID = 7318046293998884269L;

		Canvas() {
            setPreferredSize(new Dimension(500, 400));
            setBackground(Color.LIGHT_GRAY);
            setFont(new Font("Serif", Font.BOLD, 24));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (textItems != null) {
                for (DrawTextItem textItem : textItems) {
                    textItem.draw(g);
                }
            }
        }
    }

    private class MenuHandler implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            doMenuCommand(evt.getActionCommand());
        }
    }

    private void doMenuCommand(String command) {
    	if (command.equals("Save...")) {
    		File file = SimpleFileChooser.getOutputFile(this, "Save Drawing", "drawing.txt");
            if (file != null) {
                try (PrintWriter out = new PrintWriter(file)) {
                    // Save the background color
                    Color bgColor = canvas.getBackground();
                    out.println(bgColor.getRed());
                    out.println(bgColor.getGreen());
                    out.println(bgColor.getBlue());

                    // Save the DrawTextItems
                    for (DrawTextItem textItem : textItems) {
                        out.println(textItem.getX());
                        out.println(textItem.getY());
                        out.println(textItem.getString());
                        Color textColor = textItem.getTextColor();
                        out.println(textColor.getRed());
                        out.println(textColor.getGreen());
                        out.println(textColor.getBlue());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage());
                }
            }
        }
    	
    	else if (command.equals("Open...")) {
            File file = SimpleFileChooser.getInputFile(this, "Open Drawing");
            if (file != null) {
                try (Scanner scanner = new Scanner(file)) {
                    // Read the background color
                    int red = Integer.parseInt(scanner.nextLine());
                    int green = Integer.parseInt(scanner.nextLine());
                    int blue = Integer.parseInt(scanner.nextLine());
                    Color bgColor = new Color(red, green, blue);
                    canvas.setBackground(bgColor);

                    // Clear existing text items
                    textItems.clear();

                    // Read and recreate the DrawTextItems
                    while (scanner.hasNextLine()) {
                        int x = Integer.parseInt(scanner.nextLine());
                        int y = Integer.parseInt(scanner.nextLine());
                        String text = scanner.nextLine();
                        red = Integer.parseInt(scanner.nextLine());
                        green = Integer.parseInt(scanner.nextLine());
                        blue = Integer.parseInt(scanner.nextLine());
                        Color textColor = new Color(red, green, blue);

                        DrawTextItem textItem = new DrawTextItem(text, x, y);
                        textItem.setTextColor(textColor);
                        textItems.add(textItem);
                    }
                    canvas.repaint();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage());
                }
            }
        }
    	
    	else if (command.equals("Remove Item")) {
            if (!textItems.isEmpty()) {
                textItems.remove(textItems.size() - 1);
                canvas.repaint();
            }
        } else if (command.equals("Clear")) {
            textItems.clear();
            undoMenuItem.setEnabled(false);
            canvas.repaint();
        } else if (command.equals("Set Text Color...")) {
            Color c = JColorChooser.showDialog(this, "Select Text Color", currentTextColor);
            if (c != null) {
                currentTextColor = c;
            }
        }
    }
}

