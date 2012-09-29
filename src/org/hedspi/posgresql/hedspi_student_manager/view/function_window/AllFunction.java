package org.hedspi.posgresql.hedspi_student_manager.view.function_window;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.hedspi.posgresql.hedspi_student_manager.control.Control;
import org.hedspi.posgresql.hedspi_student_manager.view.IView;
import org.hedspi.posgresql.hedspi_student_manager.view.contact.address.AddressPanel;
import org.hedspi.posgresql.hedspi_student_manager.view.help.about.AboutBox;
import org.hedspi.posgresql.hedspi_student_manager.view.student.StudentPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AllFunction extends JFrame implements IView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneMain;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AllFunction frame = new AllFunction();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	private JFrame getFrame(){
		return this;
	}

	/**
	 * Create the frame.
	 */
	public AllFunction() {
		setTitle("Hedspi Student Manager");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
		        if (JOptionPane.showConfirmDialog(arg0.getWindow(), "Are you sure want to quit?", "Quit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		            arg0.getWindow().setVisible(false);
		            arg0.getWindow().dispose();
		        };
			}
			@Override
			public void windowClosed(WindowEvent e) {
				Control.getInstance().fire("exit");
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 774, 478);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				(new AboutBox(getFrame())).setVisible(true);
			}
		});
		mnHelp.add(btnAbout);
		contentPaneMain = new JPanel();
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneMain.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneMain);
		
		JTabbedPane tabbedPaneAll = new JTabbedPane(JTabbedPane.LEFT);
		contentPaneMain.add(tabbedPaneAll, BorderLayout.CENTER);
		
		StudentPanel panel = new StudentPanel();
		tabbedPaneAll.addTab("Student", null, panel, null);
		
		AddressPanel panel_1 = new AddressPanel();
		tabbedPaneAll.addTab("Address", null, panel_1, null);
	}
	
    @Override
    public void fire(String command, Object... data) {
        switch (command) {
            case "set-visible":
                super.setVisible((boolean) data[0]);
                break;
            case "init-database-result":
                String message = (String) data[0];
                if ("".equals(message)) {
                    JOptionPane.showMessageDialog(this, "Init database success", "Init successed", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Init database failed.\nMessage: " + message, "Init failed", JOptionPane.ERROR_MESSAGE);
                }
                break;

            default:
                Control.getInstance().getLogger().log(Level.WARNING, "You have called FunctionWindow an operation that is not supported.\nCommand: {0}", command);
                break;
        }
    }
}
