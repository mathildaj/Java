/*
 * Program Description: This is the User Interface of the Beverage Dispenser
  */

package ui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import beverage.*;

public class BeverageUI extends JFrame {

	//variables for passing values through different actions
	private String bev;
	private String coffee="", coffee1="", coffee2="";
	//these are the default values if user does not select 
	private int size=8, sugarCount=0, creamCount=0, beansPerOunce=10;
	
	
	//some common elements
	private JButton btnBackToMain;
	private JButton btnStart;
	
	//variables for main Panel
	private JPanel pMain;
	private JList listBev;
	private JLabel l2;
	private String [] bevs = {"COFFEE", "HOT CHOCOLATE", "HOT WATER"};
	
	//variables for coffee Panel
	private JPanel pCoffee;
	private JList listCoffee;
	private JLabel lblCoffee;
	//when user chooses mix two, it will be half / half for each type
	private String [] coffees = {"VERONA", "PIKE PLACE", "DECAL", "MIX TWO"};
	
	
	//variables for selected coffee
	private JPanel pCoffeeSelect;
	private JLabel lblCoffeeSelect;
	private JLabel lblSize;
	private JRadioButton s1, s2;
	private ButtonGroup gSize;
	
	//variables for mix two coffee
	private JPanel pMix;
	private JLabel lblMix;
	private JRadioButton m1, m2, m3;
	private ButtonGroup gMix;
	//private String [] mix = {"VERONA", "PIKE PLACE", "DECAL"};
	private JButton btnToCoffeeStart; 
	
	//variables for condiments
	private JLabel lblSugar, lblCream;
	private JRadioButton su1, su2;
	private JRadioButton c1, c2;
	private ButtonGroup gSugar, gCream;
	
	//variables for successful dispense
	private JPanel pSuccess;
	private JLabel lblSuccess;
	private JButton btnExit;
	
	
	//variables for hot CHOCOLATE
	private JPanel pCho;
	private JLabel lblCho;
	private JButton btnCho;
	private JRadioButton ch1, ch2;
	private ButtonGroup gCho;
	
	//variables for hot water
	private JPanel pWater;
	private JLabel lblWater;
	private JButton btnWater;
	private JRadioButton w1, w2;
	private ButtonGroup gWater;
	
	
	
	
	
	public BeverageUI()
	{
		setTitle("Beverage Dispenser");
		setPreferredSize(new Dimension(300, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//set the frame to visible
		this.setVisible(true);
		
		//main menu
		buildMainPanel();
		pMain.setVisible(true);
		
		buildCoffeePanel();
		pCoffee.setVisible(false);
		
		//coffee selected menu
		buildCoffeeSelectPanel();
		pCoffeeSelect.setVisible(false);
		
		//coffee mix two menu
		buildMixPanel();
		pMix.setVisible(false);
		
		//sucess panel
		buildSuccessPanel();
		pSuccess.setVisible(false);
		
		//chocolate panel
		buildChoPanel();
		pCho.setVisible(false);
		
		//hot water panel
		buildWaterPanel();
		pWater.setVisible(false);
		
	}
	
				
	//the main menu Panel
	void buildMainPanel()
	{
		pMain = new JPanel();
		//add the what would you like today
		l2 = new JLabel("What would you like today?");
		pMain.add(l2);
		listBev = new JList(bevs);
		listBev.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listBev.addListSelectionListener(new MainListListener());
		pMain.add(listBev);	
		
		//set to vertical layout
		pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));
		this.add(pMain);
		
		pack();
	}	
	
	
	//the success menu Panel
	void buildSuccessPanel()
	{
		pSuccess = new JPanel();
		lblSuccess = new JLabel("You've got your drink!");
		pSuccess.add(lblSuccess);
		
		pSuccess.add(Box.createHorizontalStrut(2)); 
		
		btnBackToMain = new JButton("Back To Main Menu");
		btnBackToMain.addActionListener(new btnBackToMainListener());
		pSuccess.add(btnBackToMain);
		
		pSuccess.add(Box.createHorizontalStrut(2)); 
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new btnExitListener());
		pSuccess.add(btnExit);
		
		//set to vertical layout
		pSuccess.setLayout(new BoxLayout(pSuccess, BoxLayout.Y_AXIS));
		this.add(pSuccess);
		
		pack();
	}
	
	//build the coffee menu Panel
	void buildCoffeePanel()
	{
		pCoffee = new JPanel();
		
		lblCoffee = new JLabel("COFFEE");
		pCoffee.add(lblCoffee);
		
		listCoffee = new JList(coffees);
		
		listCoffee.clearSelection();
		
		listCoffee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCoffee.addListSelectionListener(new CoffeeListListener());
		pCoffee.add(listCoffee);
				
		btnBackToMain = new JButton("Back To Main Menu");
		btnBackToMain.addActionListener(new btnBackToMainListener());
		pCoffee.add(btnBackToMain);
				
		//set to vertical layout
		pCoffee.setLayout(new BoxLayout(pCoffee, BoxLayout.Y_AXIS));
		this.add(pCoffee);
		
		pack();
	}
	
	//build the mix coffee menu Panel
	void buildMixPanel()
	{
		pMix = new JPanel();
		
		lblMix = new JLabel("Select two type: ");
		pMix.add(lblMix);
		
		m1 = new JRadioButton("VERONA AND PIKE PLACE");
		m2 = new JRadioButton("VERONA AND DECAL");
		m3 = new JRadioButton("PIKE PLACE AND DECAL");
		
		gMix = new ButtonGroup();
		gMix.add(m1);
		gMix.add(m2);
		gMix.add(m3);
		
		m1.addActionListener(new mixRadioListener());
		m2.addActionListener(new mixRadioListener());
		m3.addActionListener(new mixRadioListener());	
		
		
		pMix.add(m1);
		pMix.add(m2);
		pMix.add(m3);
				
		btnToCoffeeStart = new JButton("Next");
		btnToCoffeeStart.addActionListener(new btnToCoffeeStartListener());
		pMix.add(btnToCoffeeStart);
		
		//set to vertical layout
		pMix.setLayout(new BoxLayout(pMix, BoxLayout.Y_AXIS));
				
		this.add(pMix);
		
		pack();
	}
	
	//build the coffee Select Panel
	void buildCoffeeSelectPanel()
	{
		pCoffeeSelect = new JPanel();
		
		lblCoffee = new JLabel("COFFEE");
		pCoffeeSelect.add(lblCoffee);
		
		lblCoffeeSelect = new JLabel(coffee);
		pCoffeeSelect.add(lblCoffeeSelect);
		
		//size of the coffee
		s1 = new JRadioButton("8 oz");
		s2 = new JRadioButton("10 oz");
		
		gSize = new ButtonGroup();
		gSize.add(s1);
		gSize.add(s2);
			
		s1.addActionListener(new sizeRadioListener());
		s2.addActionListener(new sizeRadioListener());
		pCoffeeSelect.add(s1);
		pCoffeeSelect.add(s2);
		
		//condiments for the coffee
		lblSugar = new JLabel("Sugar: ");
		pCoffeeSelect.add(lblSugar);
		
		su1 = new JRadioButton("1 pack");
		su2 = new JRadioButton("2 packs");
		
		gSugar = new ButtonGroup();
		gSugar.add(su1);
		gSugar.add(su2);
		
		su1.addActionListener(new sugarRadioListener());
		su2.addActionListener(new sugarRadioListener());
		pCoffeeSelect.add(su1);
		pCoffeeSelect.add(su2);
		
		lblCream = new JLabel("Cream: ");
		pCoffeeSelect.add(lblCream);
		
		c1 = new JRadioButton("1 pack");
		c2 = new JRadioButton("2 packs");
		
		gCream = new ButtonGroup();
		gCream.add(c1);
		gCream.add(c2);
		
		c1.addActionListener(new creamRadioListener());
		c2.addActionListener(new creamRadioListener());
		pCoffeeSelect.add(c1);
		pCoffeeSelect.add(c2);
		
		
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new btnCoffeeStartListener());
		pCoffeeSelect.add(btnStart);
		
		// Add some space between the two itmes.
		//Fixed width invisible separator.
		pCoffeeSelect.add(Box.createHorizontalStrut(2)); 
		
		btnBackToMain = new JButton("Back To Main Menu");
		btnBackToMain.addActionListener(new btnBackToMainListener());
		pCoffeeSelect.add(btnBackToMain);
		
		//set to vertical layout
		pCoffeeSelect.setLayout(new BoxLayout(pCoffeeSelect, BoxLayout.Y_AXIS));
		this.add(pCoffeeSelect);
		
		pack();
	}
	
	//Chocolate panel
	void buildChoPanel()
	{
		pCho = new JPanel();
		
		lblCho = new JLabel("CHOCOLATE");
		pCho.add(lblCho);
		//size of the cho
		ch1 = new JRadioButton("8 oz");
		ch2 = new JRadioButton("10 oz");
		
		gCho = new ButtonGroup();
		gCho.add(ch1);
		gCho.add(ch2);
		
		ch1.addActionListener(new sizeRadioListener());
		ch2.addActionListener(new sizeRadioListener());
		pCho.add(ch1);
		pCho.add(ch2);
		
		btnCho = new JButton("Start");
		btnCho.addActionListener(new btnChoStartListener());
		pCho.add(btnCho);
		
		// Add some space between the two itmes.
		//Fixed width invisible separator.
		pCho.add(Box.createHorizontalStrut(2)); 
		
		btnBackToMain = new JButton("Back To Main Menu");
		btnBackToMain.addActionListener(new btnBackToMainListener());
		pCho.add(btnBackToMain);
		
		//set to vertical layout
		pCho.setLayout(new BoxLayout(pCho, BoxLayout.Y_AXIS));
		this.add(pCho);
		
		pack();
	}
	
	
	//Water panel
	void buildWaterPanel()
	{
		pWater = new JPanel();
		
		lblWater = new JLabel("WATER");
		pWater.add(lblWater);
		//size of the water
		w1 = new JRadioButton("8 oz");
		w2 = new JRadioButton("10 oz");
		
		gWater = new ButtonGroup();
		gWater.add(w1);
		gWater.add(w2);
		
		w1.addActionListener(new sizeRadioListener());
		w2.addActionListener(new sizeRadioListener());
		pWater.add(w1);
		pWater.add(w2);
		
		btnWater = new JButton("Start");
		btnWater.addActionListener(new btnWaterStartListener());
		pWater.add(btnWater);
		
		// Add some space between the two itmes.
		//Fixed width invisible separator.
		pWater.add(Box.createHorizontalStrut(2)); 
		
		btnBackToMain = new JButton("Back To Main Menu");
		btnBackToMain.addActionListener(new btnBackToMainListener());
		pWater.add(btnBackToMain);
		
		//set to vertical layout
		pWater.setLayout(new BoxLayout(pWater, BoxLayout.Y_AXIS));
		this.add(pWater);
		
		pack();
	}


	//Main menu list event handler
	private class MainListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e)
		{
			bev = (String)listBev.getSelectedValue();
			
			if (bev.equals("COFFEE"))
			{
				//coffee menu
				pCoffee.setVisible(true);
				
				pMain.setVisible(false);
				pMix.setVisible(false);
				pCoffeeSelect.setVisible(false);
				pSuccess.setVisible(false);
				pCho.setVisible(false);
				pWater.setVisible(false);
				
			}
			else if (bev.equals("HOT CHOCOLATE"))
			{
				
				pCho.setVisible(true);
				
				pMain.setVisible(false);
				pMix.setVisible(false);
				pCoffeeSelect.setVisible(false);
				pSuccess.setVisible(false);
				pCoffee.setVisible(false);
				pWater.setVisible(false);
			}
			else if (bev.equals("HOT WATER"))
			{
				
				pWater.setVisible(true);
				
				pMain.setVisible(false);
				pMix.setVisible(false);
				pCoffeeSelect.setVisible(false);
				pSuccess.setVisible(false);
				pCoffee.setVisible(false);
				pCho.setVisible(false);
				
			}
			
		}
	}
	
	//Coffee menu list event handler
	private class CoffeeListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e)
		{
			coffee = (String)listCoffee.getSelectedValue();
			
			if (coffee.equals("MIX TWO"))
			{
				pMix.setVisible(true);
				//System.out.println("Mix!!");
				pCoffeeSelect.setVisible(false);
			}
			else
			{
				pCoffeeSelect.setVisible(true);
				pMix.setVisible(false);
			}
			
			pMain.setVisible(false);
			pCoffee.setVisible(false);
			pSuccess.setVisible(false);
			pCho.setVisible(false);
			pWater.setVisible(false);
									
		}
	}
	

	//back to main button event handler
	private class btnBackToMainListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("back to amin");
			
			//clear out all the previous selections
			clearSelection();
			
			pMain.setVisible(true);
			
			pCoffee.setVisible(false);
			pCoffeeSelect.setVisible(false);
			pMix.setVisible(false);
			pSuccess.setVisible(false);
			pCho.setVisible(false);
			pWater.setVisible(false);
		 
		}
	} 
	
	//exit event handler
	private class btnExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	} 
	
	//back to coffee start button event handler
	private class btnToCoffeeStartListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			//System.out.println("back to coffee start");
			pCoffeeSelect.setVisible(true);
			
			pMain.setVisible(false);
			pCoffee.setVisible(false);
			pMix.setVisible(false);
			pSuccess.setVisible(false);
			pCho.setVisible(false);
			pWater.setVisible(false);
					 
		}
	} 
	
	
	//mix two coffee radio button listerner
	private class mixRadioListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("VERONA AND PIKE PLACE"))
			{
				coffee1 = "VERONA";
				coffee2 = "PIKE PLACE";
			}
			else if (e.getActionCommand().equals("VERONA AND DECAL"))
			{
				coffee1 = "VERONA";
				coffee2 = "DECAL";
			}
			else if (e.getActionCommand().equals("PIKE PLACE AND DECAL"))
			{
				coffee1 = "PIKE PLACE";
				coffee2 = "DECAL";
			}
		}
		
	}
	
	
	//mix two coffee radio button listerner
	private class sizeRadioListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("8 oz"))
			{
				size = 8;
			}
			else if (e.getActionCommand().equals("10 oz"))
			{
				size = 10;
			}
		}
		
	}
	

	//sugar radio button listerner
	private class sugarRadioListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("1 pack"))
			{
				sugarCount = 1;
			}
			else if (e.getActionCommand().equals("2 packs"))
			{
				sugarCount = 2;
			}
		}
		
	}
	
	//cream radio button listerner
	private class creamRadioListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("1 pack"))
			{
				creamCount = 1;
			}
			else if (e.getActionCommand().equals("2 packs"))
			{
				creamCount = 2;
			}
		}
		
	}
	
	
	
	//hot chocolate start button event handler
	private class btnChoStartListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			Beveragable b1;
			
			b1 = new Chocolate(size);
							
			//dispense
			b1.dispenseBev();
			pSuccess.setVisible(true);
			
			pMain.setVisible(false);
			pCoffee.setVisible(false);
			pCoffeeSelect.setVisible(false);
			pMix.setVisible(false);
			pCho.setVisible(false);
			pWater.setVisible(false);
		}
	
	}
	
	//hot water start button event handler
	private class btnWaterStartListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			Beveragable b1;
			
			b1 = new Water(size);
							
			//dispense
			b1.dispenseBev();
			pSuccess.setVisible(true);
			
			pMain.setVisible(false);
			pCoffee.setVisible(false);
			pCoffeeSelect.setVisible(false);
			pMix.setVisible(false);
			pCho.setVisible(false);
			pWater.setVisible(false);
		}
	
	}
	
	
	//coffee start button event handler
	//it's where the dispense happens
	private class btnCoffeeStartListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			Beveragable b1;
			
			ArrayList<CoffeeBean> beans = new ArrayList<CoffeeBean>();
			ArrayList<CoffeeBean> beansOne = new ArrayList<CoffeeBean>();
			ArrayList<CoffeeBean> beansTwo = new ArrayList<CoffeeBean>();
			
			ArrayList<Condiment> cds = new ArrayList<Condiment>();
			
			//start processing
			System.out.println("Coffee dispensed");
			
			
			if (coffee1.length() > 0 && coffee2.length() > 0)
			{
				System.out.println("mixed coffee start");
				//get the beans
				//mix half/half, 
				//assume for each ounce of coffee, needs 10 coffee beans
				for (int i = 0; i < size / 2 * beansPerOunce; i++)
				{
					beansOne.add(new CoffeeBean(coffee1));
				}
				
				for (int i = 0; i < size / 2 * beansPerOunce; i++)
				{
					beansTwo.add(new CoffeeBean(coffee2));
				}
				
				beans.addAll(beansOne);
				beans.addAll(beansTwo);
			}
			else
			{
				System.out.println("not coffee start");
				for (int i = 0; i < size * beansPerOunce; i++)
				{
					beans.add(new CoffeeBean(coffee));
				}
			}
			
			//get the condiments
			if (sugarCount > 0)
			{
				cds.add(new Condiment("sugar", sugarCount));
			}
			if (creamCount > 0)
			{
				cds.add(new Condiment("cream", creamCount));
			}
			
			//create drink
			if (coffee1.length() > 0 && coffee2.length() > 0)
			{
				b1 = new Coffee(beans, coffee1, coffee2, size, cds);
			}
			else
			{
				b1 = new Coffee(beans, coffee, size, cds);
			}
						
			//dispense
			b1.dispenseBev();
			pSuccess.setVisible(true);
			
			pMain.setVisible(false);
			pCoffee.setVisible(false);
			pCoffeeSelect.setVisible(false);
			pMix.setVisible(false);
			pCho.setVisible(false);
			pWater.setVisible(false);
						
		}
	} 
	
	//clear out all the selections for radio groups
	void clearSelection()
	{
		//listCoffee.clearSelection();
		gSize.clearSelection();
		gMix.clearSelection();
		gSugar.clearSelection();
		gCream.clearSelection();
		gCho.clearSelection();
		gWater.clearSelection();
		
	}
	
	
	//main
	//public static void main(String [] a1)
	//{
	//	BeverageUI b1 = new BeverageUI();
	//}	

	
}
