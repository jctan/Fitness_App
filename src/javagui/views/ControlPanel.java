package javagui.views;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ControlPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textSets;
	private JTextField textReps;
	private JTextField textWeight;
	private JLabel lblSets;
	private JLabel lblReps; 
	private JLabel lblWeight;
	private JComboBox comboBox;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private DefaultTableModel model;
	
	//my variables 
	private String SetItems; 
	private String RepItems; 
	private String WeightItems;
	private String ExerciseItems; 

	private buttonFunctions buttonFunctions; 
	private ArrayList<DataBean> tableList;
	
	private static String [] muscleGroup = {"Abdominals", "Arms", "Back", "Chest", "Legs", "Shoulders"};
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblMessage;

	
	
	public ControlPanel(DataBean dataBean) {
		setPanel();
		btnAddActionPerformed(dataBean);
		btnUpdateActionPerformed(dataBean);
		btnDeleteActionPerformed(dataBean);

	}
	
	
	private void setPanel(){
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ControlPanel.class.getResource("/javagui/resources/workout-icon.png")));
		
		
		setBounds(600, 550, 561, 556);
		
		textSets = new JTextField();
		textSets.setColumns(10);
		
		textReps = new JTextField();
		textReps.setColumns(10);
		
		textWeight = new JTextField();
		textWeight.setColumns(10);
		
		lblSets = new JLabel("Sets");
		
		lblReps = new JLabel("Reps");
		
		lblWeight = new JLabel("Weight");
		
		//ComboBox 
	
		comboBox = new JComboBox(muscleGroup);
	
		
		btnAdd = new JButton("Add");
		
		btnUpdate = new JButton("Update");
		
		btnDelete = new JButton("Delete");
		
		lblMessage = new JLabel("");
		lblMessage.setForeground(Color.RED);
		
		
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("MuscleGroup"); 
		model.addColumn("Sets");
		model.addColumn("Reps");
		model.addColumn("Weight");
		buttonFunctions = new buttonFunctions();
		tableList = buttonFunctions.show();
		for(int i = 0; i < tableList.size(); i++) {
			model.addRow(new Object[] {tableList.get(i).getMuscleGroup() , tableList.get(i).getSets(), tableList.get(i).getReps(), tableList.get(i).getWeight()});
		}
		
		table.setBounds(2, 18, 450, 16);
		table.setPreferredScrollableViewportSize(new Dimension(300,400));
		table.setFillsViewportHeight(true);
		
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(54, 204, 443, 296);
		add(scrollPane);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSets)
							.addGap(130)
							.addComponent(lblReps)
							.addGap(111)
							.addComponent(lblWeight))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textSets, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textReps, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(textWeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdd)
							.addGap(77)
							.addComponent(btnUpdate)
							.addGap(55)
							.addComponent(btnDelete))
						.addComponent(lblMessage, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSets)
						.addComponent(lblReps)
						.addComponent(lblWeight))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textSets, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textReps, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textWeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdd)
						.addComponent(btnUpdate)
						.addComponent(btnDelete))
					.addGap(12)
					.addComponent(lblMessage, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
		
		

	}
	
	
	private void setIconImage(Image image) {
		// TODO Auto-generated method stub
		
	}


	// add button 
	private void btnAddActionPerformed(final DataBean dataBean){
		btnAdd.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent arg0) {
			
			ExerciseItems = comboBox.getSelectedItem().toString();
			SetItems = textSets.getText();
			RepItems = textReps.getText();
			WeightItems = textWeight.getText();

			if(!(SetItems.trim().equals("") || RepItems.trim().equals("") || WeightItems.trim().equals(""))){
				
				buttonFunctions = new buttonFunctions();
				buttonFunctions.addButton(ExerciseItems, SetItems, RepItems, WeightItems);
				tableList = new ArrayList<DataBean>(); 
				tableList = buttonFunctions.show();
				
				
				model.setRowCount(0);
				for(int i = 0; i < tableList.size(); i++) {
					model.addRow(new Object[] {tableList.get(i).getMuscleGroup() , tableList.get(i).getSets(), tableList.get(i).getReps(), tableList.get(i).getWeight()});
				}
			}
			else{
				lblMessage.setText("Please enter Sets, Reps, and Weight");
			}
			
			}
			});		
	}
	
	
	private void tableClicked(final DataBean dataBean) {

		model = (DefaultTableModel) table.getModel();

		comboBox.setSelectedItem(model.getValueAt(table.getSelectedRow(),0).toString());
		textSets.setText(model.getValueAt(table.getSelectedRow(),1).toString());
		textReps.setText(model.getValueAt(table.getSelectedRow(),2).toString());
		textWeight.setText(model.getValueAt(table.getSelectedRow(),3).toString());
	}

	
	//update button
	private void btnUpdateActionPerformed(final DataBean dataBean){
		btnUpdate.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent arg0) {
			
			ExerciseItems = comboBox.getSelectedItem().toString();
			SetItems = textSets.getText();
			RepItems = textReps.getText();
			WeightItems = textWeight.getText();
			
			//lblMessage.setText("");
			
			buttonFunctions = new buttonFunctions();
			ArrayList<DataBean> result = new ArrayList<DataBean>(); 
			result = buttonFunctions.show();
			buttonFunctions.updateButton(result.get(table.getSelectedRow()).getEx_Sets_ID(),ExerciseItems, SetItems, RepItems, WeightItems);
			result = buttonFunctions.show();
			
			
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			if(table.getSelectedRow()==-1){
				if(table.getRowCount()==0){
					lblMessage.setText("Table is empty");
				}
				else{
					lblMessage.setText("You must select an exercise");
				}
			}else{
				model.setRowCount(0);
				for(int i = 0; i < result.size(); i++) {
					model.addRow(new Object[] {result.get(i).getMuscleGroup(), result.get(i).getSets(), result.get(i).getReps(), result.get(i).getWeight()});
				}
			}
		}
		});		
	}
	
	//delete button
	private void btnDeleteActionPerformed(final DataBean dataBean){
		btnDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				buttonFunctions = new buttonFunctions();
				ArrayList<DataBean> result = new ArrayList<DataBean>(); 
				result = buttonFunctions.show();
				buttonFunctions.deleteButton(result.get(table.getSelectedRow()).getEx_Sets_ID());
				result = buttonFunctions.show();
				
				model = (DefaultTableModel) table.getModel();
				if(table.getSelectedRow()==-1){
					if(table.getRowCount()==0){
						lblMessage.setText("Table is empty");
					}
					else{
						lblMessage.setText("You must select an exercise");
					}
				}else{
					model.setRowCount(0);
					for(int i = 0; i < result.size(); i++) {
						model.addRow(new Object[] {result.get(i).getMuscleGroup(), result.get(i).getSets(), result.get(i).getReps(), result.get(i).getWeight()});
					}
				}
			}
			});		
	}
	

	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
