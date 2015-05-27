import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main_Menu extends JFrame {

	private static final long serialVersionUID = 1L;

	public static JPanel pane;
	
	
	//Buttons used to select which game one wishes to play
	private static JButton scorched_Option = new JButton("Tetris");
	private static JButton ttt_Option = new JButton("Tic Tac Toe");
	
	//Label for the titles
	private static ImageIcon arcade = new ImageIcon("arcade.png");
	private static ImageIcon select = new ImageIcon("selectgame.png");
	private static JLabel title = new JLabel(arcade);
	private static JLabel pick = new JLabel(select);
	
	private static JLabel background = new JLabel("");
	
	//Constructor
	public Main_Menu(){
		
	
		pane = new JPanel();
		pane.setPreferredSize(new Dimension(1000,700));
		pane.setBackground(Color.white);
		add(pane);
		setVisible(true);
		
		//Title for Main Menu
		pane.add(title);
		title.setVisible(true);
		title.setBounds(392,14,178,51);
		
		//Label for Pick a Game
		pane.add(pick);
		pick.setVisible(true);
		pick.setBounds(395,75,164,25);
		
		
		BufferedImage bg;
		try {
			bg = ImageIO.read(new File("macedition.png"));
			background = new JLabel(new ImageIcon(bg));
			background.setVisible(true);
			background.setBounds(-3, 0, 1000, 700);
			pane.add(background);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Tic Tac Toe Option for Main Menu
		//Clicking this option takes user(s) to play tic tac toe
		ImageIcon tttImage = new ImageIcon("ttt.gif");
		ttt_Option = new JButton(tttImage);
		ttt_Option.setRolloverIcon(tttImage);
		ttt_Option.setVisible(true);
		ttt_Option.setBackground(Color.black);
		ttt_Option.setBounds(93,174,300,300);
		final TTT game = new TTT();
		ttt_Option.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				pane.setVisible(false);
				add(game);
				game.setVisible(true);
			}
			
		});
		pane.add(ttt_Option);
		
		//Tetris Option for Main Menu
		ImageIcon tetImage = new ImageIcon("scorched.gif");
		scorched_Option = new JButton(tetImage);
		scorched_Option.setRolloverIcon(tetImage);
		scorched_Option.setVisible(true);
		scorched_Option.setBackground(Color.white);
		scorched_Option.setBounds(470, 174, 444, 300);
		final Scorched game2 = new Scorched();
		scorched_Option.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				pane.setVisible(false);
				add(game2);
				game2.setVisible(true);
			}
			
		});
		pane.add(scorched_Option);
		
	}
	
	public JPanel getPane(){
		return pane;
	}


	
}
