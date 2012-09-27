package org.hedspi.posgresql.hedspi_student_manager.view.login;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.hedspi.posgresql.hedspi_student_manager.control.Control;
import org.hedspi.posgresql.hedspi_student_manager.model.LoginInfo;
import org.hedspi.posgresql.hedspi_student_manager.view.IView;
import org.hedspi.posgresql.hedspi_student_manager.view.util.CancelButton;
import org.hedspi.posgresql.hedspi_student_manager.view.util.InputField;
import org.hedspi.posgresql.hedspi_student_manager.view.util.IFrameAskToClose;


public class LoginWindow extends IFrameAskToClose implements IView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension DEFAULT_LOGIN_SIZE = new Dimension(300, 210);
	private InputField username;
	private InputField password;
	private InputField host;
	private FlowLayout fl;
	private InputField port;
	private static final String DEFAULT_HOST = "localhost";
	private static final String DEFAULT_PORT = "5432";
	
	public LoginWindow(){
		setUIBase();
	}


	private void setUIBase() {
		//base info
		super.setTitle("Hedspi student manager - login");
		super.setSize(DEFAULT_LOGIN_SIZE);
		super.setResizable(false);
		
		//add components
		fl = new FlowLayout(FlowLayout.CENTER);
		super.getContentPane().setLayout(fl);
		
		//input field
		addInputField();
		addOkCancel();
	}

	private void addOkCancel() {
		JButton loginButton = new LoginButton(this);
		super.getContentPane().add(loginButton);
		super.getRootPane().setDefaultButton(loginButton);

		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Control.getInstance().fireByView(((LoginButton)e.getSource()).getLoginWindow(), "try-login", new LoginInfo(host.getValue(), port.getValue(), username.getValue(), password.getValue()));
			}
		});

		super.getContentPane().add(new CancelButton(this));
	}


	private void addInputField() {
		//host
		host = new InputField("Host address: ", DEFAULT_HOST);
		host.setMnemonic('h');
		super.getContentPane().add(host);
		
		//port
		port = new InputField("Port", DEFAULT_PORT);
		port.setMnemonic('t');
		super.getContentPane().add(port);
		
		//username
		username = new InputField("User name");
		username.setMnemonic('u');
		super.getContentPane().add(username);
		
		//password
		password = new InputField("Password", true);
		password.setMnemonic('p');
		super.getContentPane().add(password);

	}

	@Override
	public void fire(String command, Object... data) {
		switch (command){
		case "set-visible":
			super.setVisible((boolean)data[0]);
			break;
		case "login-fail":
			JOptionPane.showMessageDialog(this, "Login failed", "Login failed", JOptionPane.WARNING_MESSAGE);
			break;
			
			default:
				Control.getInstance().getLogger().log(Level.WARNING, "You have called LoginWindow an operation that is not supported.\nCommand: " + command);
		}
	}
	
	public void close() {
		if (JOptionPane.showConfirmDialog(this, "Are you sure want to quit?", "Quit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			super.setVisible(false);
			super.dispose();
		};
	}

}