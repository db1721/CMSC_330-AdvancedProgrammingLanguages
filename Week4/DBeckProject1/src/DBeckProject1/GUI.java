package DBeckProject1;

/* File: Project 1 - GUI Class
 * Author: Dan Beck
 * Date: November 17, 2020
 * Purpose: Program the builds a GUI from a text file
 */

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI 
{
	/******************************************************************************* 
     * DESCRIPTION: Global variables
     ******************************************************************************/
	private static Scanner scan;
	private static File inputFile;

	/******************************************************************************* 
     * DESCRIPTION: getLabel
     * Creates labels based on characters scanned
     ******************************************************************************/
	private static String getLabel(String lines) 
	{
		int count;
		lines.trim();
		for (count = 0; count < lines.length(); count++) 
		{
			char temp = lines.charAt(count);
			boolean bool2 = Character.isLetter(temp);
			if (!bool2) 
			{
				break;
			}//end if (!bool2)
		}//end for (count = 0; count < lines.length(); count++)
		return lines.substring(0, count);
	}//end String getLabel(String file_lines)
	
	/******************************************************************************* 
     * DESCRIPTION: Main method
     * Allows program to select file named "Input.txt"
     * Scanner that scan text file
     * Builds JFrame
     ******************************************************************************/
	public static void main(String[] args) 
	{
		String createString, createLabel;
		try 
		{
			//Searches for input file
			inputFile = new File("Input.txt");
			
			//creates scanner
			scan = new Scanner(inputFile);
			if (scan.hasNextLine()) 
			{
				//builds string from scanned file
				createString = scan.nextLine().trim();
				
				//looks for window text
				createLabel = getLabel(createString);
				if (!createLabel.equalsIgnoreCase("Window")) 
				{
					JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "First label should be WINDOW");
					return;
				}//end if (!createLabel.equalsIgnoreCase("Window"))
				createString = createString.substring(createLabel.length()).trim();
				
				//Default frame settings
				JFrame frame = (JFrame) addCompntRec(createString, createLabel);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}//end if (scan.hasNextLine())
			else 
			{
				System.out.println("Unknown Error");
			}//end else
		}//end try
		catch (FileNotFoundException exp) 
		{
			System.out.println("File Not Found");
			exp.printStackTrace();
		}//end catch (FileNotFoundException exp) 
		catch (Exception exp2) 
		{
			System.out.println("Unknown Error");
			exp2.printStackTrace();
		}//end catch (Exception exp2) 
	}

	/******************************************************************************* 
     * DESCRIPTION: ArrayList<Integer> getIntAray(String makeString) 
     * Makes an array list from the integers pulled from file
     ******************************************************************************/
	private static ArrayList<Integer> getIntArray(String makeString)
			throws Exception 
	{
		int loop, loop2;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (loop = 0; loop < makeString.length(); loop++) 
		{
			for (loop2 = loop; loop2 < makeString.length() && Character.isDigit(makeString.charAt(loop2)); loop2++)
				;
			if (loop != loop2) 
			{
				result.add(Integer.parseInt(makeString.substring(loop, loop2)));
			}//if (loop != loop2) 
			loop = loop2;
		}//end for (loop = 0; loop < makeString.length(); loop++) 
		return result;
	}//end ArrayList<Integer> getIntAray(String makeString)

	/******************************************************************************* 
     * DESCRIPTION: Component addCompntRec(String createString, String createLabel) 
     * Evaluates the lines pulled from file to create GUI. 
     * While statement allow for recursion until "end" text is reached or an error occurs
     ******************************************************************************/
	private static Component addCompntRec(String createString, String createLabel)
			throws Exception
	{
		String temp;
		
		//Checks for "Window" text and reads past it
		if (createLabel.equalsIgnoreCase("Window")) 
		{
			createString = createString.trim();
			JFrame frame;
			
			//Checks between the quotation for title
			if (createString.charAt(0) == '"') 
			{
				createString = createString.substring(1);
				temp = createString.substring(0, createString.indexOf('\"'));
				frame = new JFrame(temp);
				createString = createString.substring(createString.indexOf('"') + 1).trim();
			}//end if (createString.charAt(0) == '"')
			else 
			{
				//shows if no title
				frame = new JFrame("Default title");
			}//end else
			if (createString.charAt(0) == '(') 
			{
				temp = createString.substring(0, createString.indexOf(')') + 1);
				createString = createString.substring(temp.length()).trim();
				ArrayList<Integer> instr = getIntArray(temp);
				if (instr.size() == 2) 
				{
					frame.setSize(instr.get(0), instr.get(1));
				}//end if (instr.size() == 2) 
			}//end if (createString.charAt(0) == '(') 
			temp = getLabel(createString);
			createString = createString.substring(temp.length()).trim();
			JPanel createPanel = new JPanel();
			
			//runs through file and creates layout, and grid
			if (temp.equalsIgnoreCase("Layout")) 
			{
				temp = getLabel(createString);
				createString = createString.substring(temp.length()).trim();
				
				//checks for flow text
				if (temp.equalsIgnoreCase("flow")) 
				{
					FlowLayout genLayout = new FlowLayout();
					createPanel.setLayout(genLayout);
				}//end if (temp.equalsIgnoreCase("flow"))
				
				//checks for grid text
				if (temp.equalsIgnoreCase("grid")) 
				{
					//reads text after "grid". creates panel layout of those numbers
					if (createString.charAt(0) == '(') 
					{
						temp = createString.substring(0, createString.indexOf(')') + 1);
						createString = createString.substring(temp.length()).trim();
						ArrayList<Integer> intArr = getIntArray(temp);
						GridLayout tmpLayout;
						if (intArr.size() == 2) 
						{
							tmpLayout = new GridLayout(intArr.get(0),
									intArr.get(1));
							createPanel.setLayout(tmpLayout);
						}//end if (intArr.size() == 2)
						else if (intArr.size() == 4) 
						{
							tmpLayout = new GridLayout(intArr.get(0),
									intArr.get(1), intArr.get(2), intArr.get(3));
							createPanel.setLayout(tmpLayout);
						}//end else if (intArr.size() == 4) 
					}//end if (createString.charAt(0) == '(')
				}//end if (temp.equalsIgnoreCase("grid"))
			}//end if (temp.equalsIgnoreCase("Layout"))
			
			/******************************************************************************* 
		     * DESCRIPTION: Recursion that builds the frame 
		     ******************************************************************************/
			while (true) 
			{
				//scans for lines in file until "end is reached
				if (scan.hasNextLine()) 
				{
					createString = scan.nextLine().trim();
					temp = getLabel(createString);
					
					//checks for "end" text before starting 
					if (temp.equalsIgnoreCase("end")) 
					{
						break;
					}//end if (temp.equalsIgnoreCase("end")) 
					else 
					{
						Component tmpCompnt = addCompntRec(createString.substring(temp.length()), temp);
						
						//checks if component is null
						if (tmpCompnt != null) 
						{
							if (tmpCompnt.getClass() == frame.getClass()) 
							{
								JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Window can not be nested inside");
							}//end if (tmpCompnt.getClass() == frame.getClass())
							else
							{
								createPanel.add(tmpCompnt);
							}//end else
						}//end if (tmpCompnt != null)
					}//end else
				}//end if (scan.hasNextLine()) 
				else
				{
					//in case something is wrong with the file
					JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Nesting Error");
					break;
				}//end else
			}//end while (true)
			
			//adds panel to the frame
			frame.add(createPanel);
			return frame;
		}//end if (createLabel.equalsIgnoreCase("Window"))
		
		//creates the panel
		if (createLabel.equalsIgnoreCase("panel")) 
		{
			createString = createString.trim();
			JPanel panel = new JPanel();
			temp = getLabel(createString);
			createString = createString.substring(temp.length()).trim();
			
			//checks for "Layout" text
			if (temp.equalsIgnoreCase("Layout")) 
			{
				temp = getLabel(createString);
				createString = createString.substring(temp.length()).trim();
				
				//checks for "flow" text
				if (temp.equalsIgnoreCase("flow")) 
				{
					FlowLayout flw = new FlowLayout();
					panel.setLayout(flw);
				}
				
				//checks for "grid" text
				if (temp.equalsIgnoreCase("grid")) 
				{
					//looks for open parenthesis
					if (createString.charAt(0) == '(') 
					{
						temp = createString.substring(0, createString.indexOf(')') + 1);
						createString = createString.substring(temp.length()).trim();
						ArrayList<Integer> array = getIntArray(temp);
						GridLayout tempLayout;
						
						
						if (array.size() == 2) 
						{
							tempLayout = new GridLayout(array.get(0),array.get(1));
							panel.setLayout(tempLayout);
						}//end if (instr.size() == 2) 
						else if (array.size() == 4) 
						{
							tempLayout = new GridLayout(array.get(0),array.get(1), array.get(2), array.get(3));
							panel.setLayout(tempLayout);
						}//end else if (instr.size() == 4)
					}//end if (createString.charAt(0) == '(') 
				}//end if (temp.equalsIgnoreCase("grid"))
			}//end if (temp.equalsIgnoreCase("Layout"))
			
			/******************************************************************************* 
		     * DESCRIPTION: Recursion that builds the panel 
		     ******************************************************************************/
			while (true) 
			{
				if (scan.hasNextLine()) 
				{
					createString = scan.nextLine().trim();
					temp = getLabel(createString);
					
					//breaks loops if text is "end"
					if (temp.equalsIgnoreCase("end")) 
					{
						break;
					}//end if (temp.equalsIgnoreCase("end")) 
					else 
					{
						Component tempComponent = addCompntRec(createString.substring(temp.length()), temp);
						if (tempComponent != null) 
						{
							if (tempComponent.getClass() == new JFrame().getClass()) 
							{
								JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Window cant be nested inside");
							}//end if (tempComponent.getClass() == new JFrame().getClass()) 
							else 
							{
								panel.add(tempComponent);
							}//end else
						}//end if (tempComponent != null)
					}//end else
				}//end if (scan.hasNextLine()) 
				else 
				{
					JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Nesting Error");
					break;
				}//end else
			}//end while (true) 
			return panel;
		}//end if (createLabel.equalsIgnoreCase("panel"))
		
		//creates the buttons
		if (createLabel.equalsIgnoreCase("button")) 
		{
			createString = createString.trim();
			JButton button;
			
			//checks for quotes after button text
			if (createString.charAt(0) == '"') 
			{
				createString = createString.substring(1);
				temp = createString.substring(0, createString.indexOf('\"'));
				button = new JButton(temp);
				createString = createString.substring(createString.indexOf('"') + 1).trim();
			}//end if (createString.charAt(0) == '"') 
			else 
			{
				button = new JButton("Default title");
			}//end else
			return button;
		}//end if (createLabel.equalsIgnoreCase("button"))
		
		//checks for "label" text
		if (createLabel.equalsIgnoreCase("label")) 
		{
			createString = createString.trim();
			JLabel Label;
			
			//checks for quotes after label text
			if (createString.charAt(0) == '"') 
			{
				createString = createString.substring(1);
				temp = createString.substring(0, createString.indexOf('\"'));
				Label = new JLabel(temp);
				createString = createString.substring(createString.indexOf('"') + 1).trim();
			}//end if (createString.charAt(0) == '"')
			else 
			{
				Label = new JLabel("Default title");
			}//end else
			return Label;
		}//end if (createLabel.equalsIgnoreCase("label"))
		
		//generates the text field
		if (createLabel.equalsIgnoreCase("textfield")) 
		{
			createString =createString.trim();
			ArrayList< Integer> li = getIntArray(createString);
			JTextField field = new JTextField(li.get(0));
			return field;
		}//end if (createLabel.equalsIgnoreCase("textfield")) 
		return null;
	}//end private static Component addCompntRec(String createString, String createLabel)
}//end public class GUI 
