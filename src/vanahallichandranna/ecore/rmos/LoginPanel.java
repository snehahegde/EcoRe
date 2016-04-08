/**
 * 
 */
package vanahallichandranna.ecore.rmos;



import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author Sneha
 *
 */
public class LoginPanel extends JPanel {
	
	private JPanel loginInformationPanel;
	private JLabel usernameJLabel;
	private JTextField usernameTextField;
	private JLabel passwordJLabel;
	private JTextField passwordTextField;
	private JButton loginButton;
	private JPanel loginPanel;

	/**
	 * 
	 */
	public LoginPanel(DynamicPanel dp) {
		setBackground(new Color( 235, 202, 151));
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		this.setLocation(10, 10);
		setLayout(null);
		
		
		this.add(createLoginInformationPanel(dp));
		loginPanel = this;
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics = (Graphics2D)g;
		Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 16);
	    Font newFont = myFont.deriveFont(38F);
	    graphics.setFont(newFont);
		graphics.drawString("Login", Constants.WINDOW_WIDTH/2 - 100, 100);
	}
	
	private JPanel createLoginInformationPanel(DynamicPanel dp) {
		loginInformationPanel = new JPanel();
		loginInformationPanel.setLayout(null);
		loginInformationPanel.setBackground(new Color(235, 202, 155));
		loginInformationPanel.setLocation(150, 150);
		loginInformationPanel.setSize(400, 400);
		//loginInformationPanel.setBackground(Color.CYAN);
		
		usernameJLabel = new JLabel("Username");
		//usernameJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);		
		usernameJLabel.setLocation(100, 10);
		usernameJLabel.setSize(100, 50);
		loginInformationPanel.add(usernameJLabel);
		
		usernameTextField = new JTextField(150);
		usernameTextField.setLocation(200, 10);
		usernameTextField.setSize(150, 50);
		loginInformationPanel.add(usernameTextField);
		
		passwordJLabel = new JLabel("Password");
		//usernameJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);		
		passwordJLabel.setLocation(100, 70);
		passwordJLabel.setSize(100, 50);
		loginInformationPanel.add(passwordJLabel);
		
		passwordTextField = new JPasswordField(150);
		passwordTextField.setLocation(200, 70);
		passwordTextField.setSize(150, 50);
		loginInformationPanel.add(passwordTextField);
		
		loginButton = new JButton("Login");
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.setLocation(150, 150);
		loginButton.setSize(100, 50);
		loginInformationPanel.add(loginButton);
		
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("user: " + usernameTextField.getText() + " " + usernameTextField.getText().equals("admin"));
				System.out.println(("password: " + passwordTextField.getText() + " " + passwordTextField.equals("admin")));
				if( usernameTextField.getText().equals("admin") && passwordTextField.getText().equals("admin")) {
					loginPanel.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect login credentials");
				}
				
			}
		});
	
		
		return loginInformationPanel;
	}
}
