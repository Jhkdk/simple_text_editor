package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

public class Window {
	JFrame frame = new JFrame();
	String homeDirectory = "C:\\Users\\colen\\Downloads";

	public Window(String name, int xSize, int ySize) {
		frame.setTitle(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon image = new ImageIcon("src/assets/logo.png");
		frame.setIconImage(image.getImage());
		frame.setSize(xSize, ySize);
	}

	public void start() {
		frame.setLayout(new BorderLayout());
		
		
		JScrollPane split = new JScrollPane(splitFrame());
		frame.add(split, BorderLayout.CENTER);
		
		JScrollPane toolBar = new JScrollPane(toolBar());
		frame.add(toolBar, BorderLayout.NORTH);

		frame.setBackground(Color.DARK_GRAY);
		frame.setVisible(true);
	}

	public void fun() {
	}

	public void end() {
		frame.dispose();
	}

	// splitting the frame in two parts for file viewer and text editor
	public JSplitPane splitFrame() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createFileViewerPanel(), createTextEditorPanel());
		
		splitPane.setDividerLocation(0.5);
		splitPane.setDividerSize(5);
		
		return splitPane;
	}

	private JPanel createTextEditorPanel() {
		JPanel textEditor = new JPanel();
		textEditor.setBackground(Color.DARK_GRAY);
		textEditor.setLayout(new BorderLayout());
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(false);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.WHITE);
		textArea.setCaretColor(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		textEditor.add(scrollPane, BorderLayout.CENTER);
		
		return textEditor;
	}

	public JToolBar toolBar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		toolbar.setBackground(Color.BLACK);

		// button stuff
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		buttons.add(new JButton("load"));
		buttons.add(new JButton("save"));
		buttons.add(new JButton("export"));

		for (JButton button : buttons) {
			button.setBackground(Color.DARK_GRAY);
			button.setForeground(Color.WHITE);

			button.setBorderPainted(false);
			button.setFocusPainted(false);

			toolbar.add(button);
		}
		return toolbar;
	}

	private JPanel createFileViewerPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);

		// Create a vertical box layout
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxLayout);
		
		//up icon
		JButton up = new JButton(new ImageIcon(
				new ImageIcon("src/assets/up.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		up.setText("..");

		up.setBackground(Color.DARK_GRAY);
		up.setForeground(Color.WHITE);

		up.setBorderPainted(false);
		up.setFocusPainted(false);

		panel.add(up);
		// Add file names as buttons vertically
		for (String folderName : FolderViewer.viewFolder(homeDirectory)) {
			JButton folderButton = createButtonWithScaledIcon("src/assets/folderIcon.png", 15, 15);

			folderButton.setText(folderName);

			folderButton.setBackground(Color.DARK_GRAY);
			folderButton.setForeground(Color.WHITE);

			folderButton.setBorderPainted(false);
			folderButton.setFocusPainted(false);

			panel.add(folderButton);
		}
		for (String fileName : FolderViewer.viewFiles(homeDirectory)) {
			String extension = "";
			String[] parts = fileName.split("\\.");

			if (parts.length > 1) {
				extension = parts[parts.length - 1];
			} else {
				extension = "folderIcon";
			}
			System.out.println(extension);
			JButton fileButton = createButtonWithScaledIcon("src/assets/" + extension + ".png", 15, 15);

			fileButton.setBackground(Color.DARK_GRAY);
			fileButton.setForeground(Color.WHITE);

			fileButton.setBorderPainted(false);
			fileButton.setFocusPainted(false);

			fileButton.setText(fileName);
			panel.add(fileButton);
		}
		return panel;
	}
	
    private static JButton createButtonWithScaledIcon(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return new JButton(icon);
    }
    
    public String toString() {
    	return frame.getName() + homeDirectory;
    }
}
