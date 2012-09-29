package org.hedspi.posgresql.hedspi_student_manager.view.student.add;

import javax.swing.JPanel;
import org.hedspi.posgresql.hedspi_student_manager.view.contact.ContactPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class AddStudentPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AddStudentPane() {
		setAutoscrolls(true);
		setPreferredSize(new Dimension(491, 700));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("18px"),
				ColumnSpec.decode("257px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("193px:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("fill:454px:grow"),}));
		
		ContactPane panel = new ContactPane();
		add(panel, "2, 2, fill, fill");
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "4, 2, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(89dlu;default)"),
				RowSpec.decode("max(0dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		StudentOtherInfoPanel studentOtherInfoPanel = new StudentOtherInfoPanel();
		panel_1.add(studentOtherInfoPanel, "2, 2, fill, top");
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, "2, 3, fill, top");
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnReset = new JButton("Reset");
		panel_2.add(btnReset);
		
		JButton btnCommit = new JButton("Commit");
		panel_2.add(btnCommit);

	}

}
