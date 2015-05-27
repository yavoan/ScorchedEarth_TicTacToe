import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

public class TTT extends JPanel {

	private static final long serialVersionUID = 1L;

	//Buttons used to select placement of move
	private static JButton box1 = new JButton();
	private static JButton box2 = new JButton();
	private static JButton box3 = new JButton();
	private static JButton box4 = new JButton();
	private static JButton box5 = new JButton();
	private static JButton box6 = new JButton();
	private static JButton box7 = new JButton();
	private static JButton box8 = new JButton();
	private static JButton box9 = new JButton();

	//Image icons to be used to show the X and O's
	private static ImageIcon oImage = new ImageIcon("second.png");
	private static ImageIcon xImage = new ImageIcon("first.png");

	
	private static JButton instructions = new JButton("Instructions");
	private static JButton instructions2 = new JButton("Hide Instructions");
	private static ImageIcon tttpage = new ImageIcon("tttInstructions.png");
	private static JLabel page = new JLabel(tttpage);
	//Labels/images to display for the board
	private static JButton oPic1 = new JButton(oImage);
	private static JButton xPic1 = new JButton(xImage);
	private static JButton oPic2 = new JButton(oImage);
	private static JButton xPic2 = new JButton(xImage);
	private static JButton oPic3 = new JButton(oImage);
	private static JButton xPic3 = new JButton(xImage);
	private static JButton oPic4 = new JButton(oImage);
	private static JButton xPic4 = new JButton(xImage);
	private static JButton oPic5 = new JButton(oImage);
	private static JButton xPic5 = new JButton(xImage);
	private static JButton oPic6 = new JButton(oImage);
	private static JButton xPic6 = new JButton(xImage);
	private static JButton oPic7 = new JButton(oImage);
	private static JButton xPic7 = new JButton(xImage);
	private static JButton oPic8 = new JButton(oImage);
	private static JButton xPic8 = new JButton(xImage);
	private static JButton oPic9 = new JButton(oImage);
	private static JButton xPic9 = new JButton(xImage);

	//images to display for the player/win conditions
	private static JLabel tied = new JLabel();
	private static ImageIcon tiedImage = new ImageIcon("tiegame.png");
	private static JLabel youTied = new JLabel(tiedImage);
	private static JLabel tried = new JLabel();
	private static ImageIcon player2Loses = new ImageIcon("p2loser.png");
	private static ImageIcon player1Loses = new ImageIcon("p1loser.png");
	private static JLabel player1Win = new JLabel(player2Loses);
	private static JLabel player2Win = new JLabel(player1Loses);

	//ReturnToMenu, Undo, and Restart Buttons
	private static JButton returnToMenu = new JButton("Main Menu");
	private static JButton undo = new JButton("Undo");
	private static JButton restart = new JButton("Next Game");

	//Title Image and extra side image
	private static JLabel ttt = new JLabel();
	private static ImageIcon paperImg = new ImageIcon("paper.png");
	private static JLabel papers = new JLabel(paperImg);

	//Label for whose turn it is
	private static ImageIcon imageTurn1 = new ImageIcon("player1.png");
	private static ImageIcon imageTurn2 = new ImageIcon("player2.png");
	private static JLabel playerTurn1 = new JLabel(imageTurn1);
	private static JLabel playerTurn2 = new JLabel(imageTurn2);

	//Stacks on stacks
	private static Stack<String> staticStack = new Stack<String>();

	//Key to Board integer meanings, Player 1 is O's, Player 2 is X's
	// -1 = empty
	// 0 = X's
	// 1 = O's
	private static int[][] board = new int[3][3];

	//counter to keep track of who's turn it is, player 1 = odd number, player 2 = even
	private static int count = 1;

	//Scores
	private static int p1Score = 0;
	private static JLabel p1ScoreLabel = new JLabel("Player 1: " + p1Score);
	private static int p2Score = 0;
	private static JLabel p2ScoreLabel = new JLabel("Player 2: " + p2Score);

	//Paints the background
	public void paintComponent(Graphics g){
		BufferedImage bg;
		try {
			bg = ImageIO.read(new File("spacebackground1.jpg"));
			g.drawImage(bg,0,0,null);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	//Initialize board to empty
	public void startBoard(){

		for(int i = 0; i < 3; i++){

			for(int j = 0; j < 3; j++){

				board[i][j] = -1;
			}
		}
	}

	//Constructor, sets up the board and initializes all the buttons
	public TTT(){

		startBoard();
		initialize();

	}

	//Displays the proper screen depending on which player won
	public void winScreen(){

		//removes all buttons and labels
		box1.setVisible(false);
		box2.setVisible(false);
		box3.setVisible(false);
		box4.setVisible(false);
		box5.setVisible(false);
		box6.setVisible(false);
		box7.setVisible(false);
		box8.setVisible(false);
		box9.setVisible(false);

		//removes pictures
		oPic1.setVisible(false);
		xPic1.setVisible(false);
		oPic2.setVisible(false);
		xPic2.setVisible(false);
		oPic3.setVisible(false);
		xPic3.setVisible(false);
		oPic4.setVisible(false);
		xPic4.setVisible(false);
		oPic5.setVisible(false);
		xPic5.setVisible(false);
		oPic6.setVisible(false);
		xPic6.setVisible(false);
		oPic7.setVisible(false);
		xPic7.setVisible(false);
		oPic8.setVisible(false);
		xPic8.setVisible(false);
		oPic9.setVisible(false);
		xPic9.setVisible(false);

		playerTurn1.setVisible(false);
		playerTurn2.setVisible(false);

		//determines who has won
		if (count%2==0) 
		{
			//Player 2 Wins the Game

			//Image to mock the other player
			ImageIcon gif = new ImageIcon("tried.gif");
			tried = new JLabel(gif);
			tried.setBounds(120, 174, 245, 180);
			tried.setVisible(true);
			add(tried);

			//JLabel to mock the other player
			add(player2Win);
			player2Win.setVisible(true);
			player2Win.setForeground(Color.white);
			player2Win.setBounds(95,325,324,200);
			player2Win.setFont(new Font("Seriph", Font.BOLD, 30));

			p2Score++;
			p2ScoreLabel.setText("Player 2: " + String.valueOf(p2Score));


		}
		else{
			//Player 1 wins the game

			//Image to mock the other player
			ImageIcon gif = new ImageIcon("tried.gif");
			tried = new JLabel(gif);
			tried.setBounds(120, 174, 245, 180);
			tried.setVisible(true);
			add(tried);

			//JLabel to mock the other player
			add(player1Win);
			player1Win.setVisible(true);
			player1Win.setForeground(Color.white);
			player1Win.setBounds(95,325,320,200);
			player1Win.setFont(new Font("Seriph", Font.BOLD, 30));

			p1Score++;
			p1ScoreLabel.setText("Player 1: " + String.valueOf(p1Score));

		}

		//Undo is no longer possible after game ends
		undo.setEnabled(false);
		count = 0;

	}

	//Checks to see if a player has won, returns true if a player has won, false if not
	//Also displays proper screen if the game ties
	public Boolean checkForWin(){

		Boolean win = false;
		int countX =0;
		int countO =0;

		//Horizontal check
		for (int i = 0; i < 3; i++){
			countX=0;
			countO=0;
			for(int j = 0; j < 3; j++){
				if (board[i][j]==1)
					countX++;
				if (board[i][j]==0)
					countO++;

				if (countX == 3 || countO ==3){
					return true;
				}
			}
		}

		//vertical check
		for (int i = 0; i < 3; i++){
			countX=0;
			countO=0;
			for(int j = 0; j < 3; j++){
				if (board[j][i]==1)
					countX++;
				if (board[j][i]==0)
					countO++;

				if (countX == 3 || countO ==3){
					return true;
				}
			}
		}

		//diagonal checks
		//player1 wins - X's
		if (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1){
			return true;
		}
		else if(board[2][0] == 1 && board[1][1] == 1 && board[0][2] == 1){
			return true;
		}
		//player2 wins - O's
		else if(board[0][0] == 0 && board[1][1] == 0 && board[2][2] == 0){
			return true;
		}
		else if(board[2][0] == 0 && board[1][1] == 0 && board[0][2] == 0){
			return true;
		}

		//Displays proper screen if the game is a tie
		if(count >= 9 && win == false){
			ImageIcon gif = new ImageIcon("thumb.gif");
			tied = new JLabel(gif);
			tied.setBounds(90, 174, 320, 240);
			tied.setVisible(true);
			add(tied);

			add(youTied);
			youTied.setVisible(true);
			youTied.setBounds(95,355,300,200);
			youTied.setFont(new Font("Seriph", Font.BOLD, 30));

			//removes all buttons and labels
			box1.setVisible(false);
			box2.setVisible(false);
			box3.setVisible(false);
			box4.setVisible(false);
			box5.setVisible(false);
			box6.setVisible(false);
			box7.setVisible(false);
			box8.setVisible(false);
			box9.setVisible(false);

			//removes pictures
			oPic1.setVisible(false);
			xPic1.setVisible(false);
			oPic2.setVisible(false);
			xPic2.setVisible(false);
			oPic3.setVisible(false);
			xPic3.setVisible(false);
			oPic4.setVisible(false);
			xPic4.setVisible(false);
			oPic5.setVisible(false);
			xPic5.setVisible(false);
			oPic6.setVisible(false);
			xPic6.setVisible(false);
			oPic7.setVisible(false);
			xPic7.setVisible(false);
			oPic8.setVisible(false);
			xPic8.setVisible(false);
			oPic9.setVisible(false);
			xPic9.setVisible(false);

			undo.setEnabled(false);
			playerTurn1.setVisible(false);
			playerTurn2.setVisible(false);
		}

		return win;
	}

	public void displayTurn(){

		//displays player turn based on which turn it is
		if(count%2 == 1){
			playerTurn1.setVisible(false);
			playerTurn2.setBounds(92,580,335,54);
			playerTurn2.setVisible(true);
			add(playerTurn2);

		}
		else{
			playerTurn2.setVisible(false);
			playerTurn1.setBounds(95,580,320,54);
			playerTurn1.setVisible(true);
			add(playerTurn1);
		}
	}

	//Restarts the game - sets the game board to default values
	public void restart(){

		//clears the stack
		while(!staticStack.isEmpty()){
			staticStack.pop();
		}

		//sets board to default values
		startBoard();

		//Sets all components to proper visibility
		oPic1.setVisible(false);
		xPic1.setVisible(false);
		oPic2.setVisible(false);
		xPic2.setVisible(false);
		oPic3.setVisible(false);
		xPic3.setVisible(false);
		oPic4.setVisible(false);
		xPic4.setVisible(false);
		oPic5.setVisible(false);
		xPic5.setVisible(false);
		oPic6.setVisible(false);
		xPic6.setVisible(false);
		oPic7.setVisible(false);
		xPic7.setVisible(false);
		oPic8.setVisible(false);
		xPic8.setVisible(false);
		oPic9.setVisible(false);
		xPic9.setVisible(false);

		tried.setVisible(false);
		tied.setVisible(false);
		youTied.setVisible(false);

		player2Win.setVisible(false);
		player1Win.setVisible(false);
		playerTurn2.setVisible(false);
		playerTurn1.setVisible(true);

		box1.setVisible(true);
		box2.setVisible(true);
		box3.setVisible(true);
		box4.setVisible(true);
		box5.setVisible(true);
		box6.setVisible(true);
		box7.setVisible(true);
		box8.setVisible(true);
		box9.setVisible(true);
		//default value for counter
		count = 1;

		//sets to false
		undo.setEnabled(false);

	}

	//Undo method to be called by undo button and keybinding
	public void undoMove(){

		displayTurn();

		//Converts the integer value into a string to be interpreted to perform undo
		String x = (String) staticStack.pop();
		String arrayX = x.substring(0,1);
		String arrayY = x.substring(1,2);
		board[Integer.parseInt(arrayX)-1][Integer.parseInt(arrayY)-1] = -1; 
		//System.out.println(arrayX+"  "+arrayY);
		count--;
		if (count == 1){
			undo.setEnabled(false);
		}
		if (Integer.parseInt(arrayX) == 1 && Integer.parseInt(arrayY) == 1){
			xPic1.setVisible(false);
			oPic1.setVisible(false);
			box1.setVisible(true);
		}
		else if (Integer.parseInt(arrayX) == 1 && Integer.parseInt(arrayY) == 2){
			xPic2.setVisible(false);
			oPic2.setVisible(false);
			box2.setVisible(true);
		}
		else if (Integer.parseInt(arrayX) == 1 && Integer.parseInt(arrayY) == 3){
			xPic3.setVisible(false);
			oPic3.setVisible(false);
			box3.setVisible(true);
		}
		else if (Integer.parseInt(arrayX) == 2 && Integer.parseInt(arrayY) == 1){
			xPic4.setVisible(false);
			oPic4.setVisible(false);
			box4.setVisible(true);
		}
		else if (Integer.parseInt(arrayX) == 2 && Integer.parseInt(arrayY) == 2){
			xPic5.setVisible(false);
			oPic5.setVisible(false);
			box5.setVisible(true);
		}
		else if (Integer.parseInt(arrayX) == 2 && Integer.parseInt(arrayY) == 3){
			xPic6.setVisible(false);
			oPic6.setVisible(false);
			box6.setVisible(true);
		}
		else if (Integer.parseInt(arrayX) == 3 && Integer.parseInt(arrayY) == 1){
			xPic7.setVisible(false);
			oPic7.setVisible(false);
			box7.setVisible(true);
		}
		else if (Integer.parseInt(arrayX) == 3 && Integer.parseInt(arrayY) == 2){
			xPic8.setVisible(false);
			oPic8.setVisible(false);
			box8.setVisible(true);
		}
		else if (Integer.parseInt(arrayX) == 3 && Integer.parseInt(arrayY) == 3){
			xPic9.setVisible(false);
			oPic9.setVisible(false);
			box9.setVisible(true);
		}

	}


	//sets up the hotkeys for undoing moves
	private void setupUndo() {
		String UNDO = "Undo move";

		Action undoAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if(count>1 && count < 10){
					undoMove();
				}
			}
		};

		getActionMap().put(UNDO, undoAction);

		InputMap[] keymap = new InputMap[] {
				getInputMap(JComponent.WHEN_FOCUSED),
				getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT),
				getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW),
		};
		for(InputMap i : keymap) {
			i.put(KeyStroke.getKeyStroke("control Z"), UNDO);
			i.put(KeyStroke.getKeyStroke("command Z"), UNDO);
		}
	}



	//Initializes the entire game board
	public void initialize(){
		addInstructions();
		//sets up the size and background of everything to be displayed
		setPreferredSize(new Dimension(1000,700));
		setBackground(Color.white);
		setVisible(true);
		setLayout(null);

		setupUndo();

		//Adds the score labels
		add(p1ScoreLabel);
		p1ScoreLabel.setVisible(true);
		p1ScoreLabel.setBounds(415, 230, 80, 40);
		p1ScoreLabel.setFont(new Font("Seriph", Font.PLAIN,13));
		p1ScoreLabel.setForeground(Color.white);
		add(p2ScoreLabel);
		p2ScoreLabel.setVisible(true);
		p2ScoreLabel.setBounds(415, 280, 80, 40);
		p2ScoreLabel.setFont(new Font("Seriph", Font.PLAIN,13));
		p2ScoreLabel.setForeground(Color.white);

		//RETURN TO MENU BUTTON - Located in upper left corner
		//Returns the user to the main menu
		add(returnToMenu);
		returnToMenu.setBounds(5,5,95,20);
		returnToMenu.setVisible(true);
		returnToMenu.setFont(new Font("Seriph", Font.PLAIN, 12));
		returnToMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				restart();
				p2ScoreLabel.setText("Player 2: " + String.valueOf(0));
				p1ScoreLabel.setText("Player 1: " + String.valueOf(0));
				setVisible(false);
				Main_Menu.pane.setVisible(true);	
			}

		});

		playerTurn1 = new JLabel(imageTurn1);
		playerTurn1.setBounds(95,580,320,54);
		playerTurn1.setVisible(true);
		add(playerTurn1);


		//Image for the title of the game
		ImageIcon tttImage = new ImageIcon("textTTT.png");
		ttt = new JLabel(tttImage);
		add(ttt);
		ttt.setBounds(85,70,318,51);
		ttt.setVisible(true);
		
		//image of paperstack
		add(papers);
		papers.setBounds(720,200,250,683);
		papers.setVisible(true);

		//RESTART BUTTON - Located beneath the board next to undo
		//Restarts the game of tic tac toe from the beginning
		add(restart);
		restart.setBounds(292, 513, 100, 40);
		restart.setVisible(true);
		restart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				restart();
			}
		});
		undo.setBounds(115, 513, 80, 40);

		//UNDO BUTTON - Located beneath the game board next to restart
		//Can be selected after first move is made, undoes a single move
		add(undo);
		undo.setVisible(true);
		undo.setEnabled(false);
		undo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				undoMove();
			}

		});



		//All X's and O's are put in, just hidden
		add(oPic1);
		oPic1.setBounds(93, 174, 100, 100);
		oPic1.setBorder(new LineBorder(Color.black,2));
		oPic1.setVisible(false);
		add(xPic1);
		xPic1.setBorder(new LineBorder(Color.black,2));
		xPic1.setBounds(93, 174, 100, 100);
		xPic1.setVisible(false);

		add(oPic2);
		oPic2.setBounds(193, 174, 100, 100);
		oPic2.setBorder(new LineBorder(Color.black,2));
		oPic2.setVisible(false);
		add(xPic2);
		xPic2.setBorder(new LineBorder(Color.black,2));
		xPic2.setBounds(193, 174, 100, 100);
		xPic2.setVisible(false);

		add(oPic3);
		oPic3.setBounds(293, 174, 100, 100);
		oPic3.setBorder(new LineBorder(Color.black,2));
		oPic3.setVisible(false);
		add(xPic3);
		xPic3.setBorder(new LineBorder(Color.black,2));
		xPic3.setBounds(293, 174, 100, 100);
		xPic3.setVisible(false);

		add(oPic4);
		oPic4.setBounds(93, 274, 100, 100);
		oPic4.setBorder(new LineBorder(Color.black,2));
		oPic4.setVisible(false);
		add(xPic4);
		xPic4.setBorder(new LineBorder(Color.black,2));
		xPic4.setBounds(93, 274, 100, 100);
		xPic4.setVisible(false);

		add(oPic5);
		oPic5.setBounds(193, 274, 100, 100);
		oPic5.setBorder(new LineBorder(Color.black,2));
		oPic5.setVisible(false);
		add(xPic5);
		xPic5.setBorder(new LineBorder(Color.black,2));
		xPic5.setBounds(193, 274, 100, 100);
		xPic5.setVisible(false);

		add(oPic6);
		oPic6.setBounds(293, 274, 100, 100);
		oPic6.setBorder(new LineBorder(Color.black,2));
		oPic6.setVisible(false);
		add(xPic6);
		xPic6.setBorder(new LineBorder(Color.black,2));
		xPic6.setBounds(293, 274, 100, 100);
		xPic6.setVisible(false);

		add(oPic7);
		oPic7.setBounds(93, 374, 100, 100);
		oPic7.setBorder(new LineBorder(Color.black,2));
		oPic7.setVisible(false);
		add(xPic7);
		xPic7.setBorder(new LineBorder(Color.black,2));
		xPic7.setBounds(93, 374, 100, 100);
		xPic7.setVisible(false);

		add(oPic8);
		oPic8.setBounds(193, 374, 100, 100);
		oPic8.setBorder(new LineBorder(Color.black,2));
		oPic8.setVisible(false);
		add(xPic8);
		xPic8.setBorder(new LineBorder(Color.black,2));
		xPic8.setBounds(193, 374, 100, 100);
		xPic8.setVisible(false);

		add(oPic9);
		oPic9.setBounds(293, 374, 100, 100);
		oPic9.setBorder(new LineBorder(Color.black,2));
		oPic9.setVisible(false);
		add(xPic9);
		xPic9.setBorder(new LineBorder(Color.black,2));
		xPic9.setBounds(293, 374, 100, 100);
		xPic9.setVisible(false);


		//BOX BUTTONS - ARE ALL THE SAME EXCEPT FOR POSITION
		//User presses button to select their move
		box1.setBounds(93, 174, 100, 100);
		add(box1);
		box1.setBorder(new LineBorder(Color.black,2));
		box1.setBackground(Color.white);
		box1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				displayTurn();

				if (count%2 == 0){
					board [0][0] = 0;

					box1.setVisible(false);
					oPic1.setVisible(true);

					staticStack.push(new String("110")); //adds this buttons commands to the stack

				}
				else{
					board [0][0]= 1;
					box1.setVisible(false);
					xPic1.setVisible(true);

					staticStack.push(new String("111")); //adds this buttons commands to the stack, all other buttons have same feature
				}

				if (count >= 5){

					if(checkForWin()){
						//displays the win screen
						winScreen();
					}
				}

				count++;

				//System.out.println(count);

				if(count > 1 && count < 10){
					undo.setEnabled(true);
				}

			}

		});
		box1.setVisible(true);

		box2.setBounds(193, 174, 100, 100);
		add(box2);
		box2.setBorder(new LineBorder(Color.black,2));
		box2.setBackground(Color.white);
		box2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {

				displayTurn();

				if (count%2 == 0){
					board [0][1] = 0;
					box2.setVisible(false);
					oPic2.setVisible(true);

					staticStack.push(new String("120"));
				}
				else{
					board [0][1]= 1;
					box2.setVisible(false);
					xPic2.setVisible(true);

					staticStack.push(new String("121"));
				}

				if (count >= 5){

					if(checkForWin()){
						//displays the win screen
						winScreen();
					}
				}
				count++;

				if(count > 1 && count < 10){
					undo.setEnabled(true);
				}

			}

		});
		box2.setVisible(true);

		box3.setBounds(293, 174, 100, 100);
		add(box3);
		box3.setBorder(new LineBorder(Color.black,2));
		box3.setBackground(Color.white);
		box3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {

				displayTurn();

				if (count%2 == 0){
					board [0][2] = 0;
					box3.setVisible(false);
					oPic3.setVisible(true);
					staticStack.push(new String("130"));
				}
				else{
					board [0][2]= 1;
					box3.setVisible(false);
					xPic3.setVisible(true);

					staticStack.push(new String("131"));
				}

				if (count >= 5){

					if(checkForWin()){
						//displays the win screen
						winScreen();
					}
				}

				count++;

				if(count > 1 && count < 10){
					undo.setEnabled(true);
				}

			}

		});
		box3.setVisible(true);

		box4.setBounds(93, 274, 100, 100);
		add(box4);
		box4.setBorder(new LineBorder(Color.black,2));
		box4.setBackground(Color.white);
		box4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				displayTurn();
				if (count%2 == 0){
					board [1][0] = 0;
					box4.setVisible(false);
					oPic4.setVisible(true);

					staticStack.push(new String("210"));
				}
				else{
					board [1][0]= 1;
					box4.setVisible(false);
					xPic4.setVisible(true);

					staticStack.push(new String("211"));
				}

				if (count >= 5){

					if(checkForWin()){
						//displays the win screen
						winScreen();
					}
				}
				count++;

				if(count > 1 && count < 10){
					undo.setEnabled(true);
				}

			}

		});
		box4.setVisible(true);

		box5.setBounds(193, 274, 100, 100);
		add(box5);
		box5.setBorder(new LineBorder(Color.black,2));
		box5.setBackground(Color.white);
		box5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				displayTurn();
				if (count%2 == 0){
					board [1][1] = 0;
					box5.setVisible(false);
					oPic5.setVisible(true);

					staticStack.push(new String("220"));
				}
				else{
					board [1][1]= 1;
					box5.setVisible(false);
					xPic5.setVisible(true);

					staticStack.push(new String("221"));
				}
				if (count >= 5){

					if(checkForWin()){
						//displays the win screen
						winScreen();
					}
				}
				count++;

				if(count > 1 && count < 10){
					undo.setEnabled(true);
				}

			}

		});
		box5.setVisible(true);

		box6.setBounds(293, 274, 100, 100);
		add(box6);
		box6.setBorder(new LineBorder(Color.black,2));
		box6.setBackground(Color.white);
		box6.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				displayTurn();
				if (count%2 == 0){
					board [1][2] = 0;
					box6.setVisible(false);
					oPic6.setVisible(true);

					staticStack.push(new String("230"));
				}
				else{
					board [1][2]= 1;
					box6.setVisible(false);
					xPic6.setVisible(true);

					staticStack.push(new String("231"));
				}

				if (count >= 5){

					if(checkForWin()){
						//displays the win screen
						winScreen();
					}
				}
				count++;

				if(count > 1 && count < 10){
					undo.setEnabled(true);
				}

			}

		});
		box6.setVisible(true);

		box7.setBounds(93, 374, 100, 100);
		add(box7);
		box7.setBorder(new LineBorder(Color.black,2));
		box7.setBackground(Color.white);
		box7.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				displayTurn();
				if (count%2 == 0){
					board [2][0] = 0;
					box7.setVisible(false);
					oPic7.setVisible(true);
					staticStack.push(new String("310"));
				}
				else{
					board [2][0]= 1;
					box7.setVisible(false);
					xPic7.setVisible(true);

					staticStack.push(new String("311"));
				}

				if (count >= 5){

					if(checkForWin()){
						//displays the win screen
						winScreen();
					}
				}
				count++;

				if(count > 1 && count < 10){
					undo.setEnabled(true);
				}
			}

		});
		box7.setVisible(true);

		box8.setBounds(193, 374, 100, 100);
		add(box8);
		box8.setBorder(new LineBorder(Color.black,2));
		box8.setBackground(Color.white);
		box8.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				displayTurn();
				if (count%2 == 0){
					board [2][1] = 0;
					box8.setVisible(false);
					oPic8.setVisible(true);

					staticStack.push(new String("320"));
				}
				else{
					board [2][1]= 1;
					box8.setVisible(false);
					xPic8.setVisible(true);

					staticStack.push(new String("321"));
				}

				if (count >= 5){

					if(checkForWin()){
						//displays the win screen
						winScreen();
					}
				}
				count++;

				if(count > 1 && count < 10){
					undo.setEnabled(true);
				}
			}
		});
		box8.setVisible(true);

		box9.setBounds(293, 374, 100, 100);
		add(box9);
		box9.setBorder(new LineBorder(Color.black,2));
		box9.setBackground(Color.white);
		box9.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				displayTurn();
				if (count%2 == 0){
					board [2][2] = 0;
					box9.setVisible(false);
					oPic9.setVisible(true);

					staticStack.push(new String("330"));
				}
				else{
					board [2][2]= 1;
					box9.setVisible(false);
					xPic9.setVisible(true);

					staticStack.push(new String("331"));
				}

				if (count >= 5){

					if(checkForWin()){
						//displays the win screen
						winScreen();
					}
				}
				count++;

				if(count > 1 && count < 10){
					undo.setEnabled(true);
				}
			}

		});
		box9.setVisible(true);

		//IF ALREADY SELECTED BUTTONS ARE PRESSED SENDS ERROR TO USER
		oPic1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		xPic1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		oPic2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		xPic2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		oPic3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		xPic3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		oPic4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		xPic4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		oPic5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		xPic5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		oPic6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		xPic6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		oPic7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		xPic7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		oPic8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		xPic8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		oPic9.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});
		xPic9.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(getParent(), "Oops! You can't go there. Pick another spot.");

			}
		});


	}
public void addInstructions(){
		
		add(page);
		page.setBounds(500,100,400,500);
		page.setVisible(false);
		
		add(instructions);
		instructions.setBounds(600, 50, 200, 30);
		instructions.setVisible(true);
		instructions.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//hides proper button
				instructions2.setVisible(true);
				instructions.setVisible(false);
				
				//adds the instructions page
				page.setVisible(true);
				
			}
			
			
		});
		
		add(instructions2);
		instructions2.setBounds(600, 50, 200, 30);
		instructions2.setVisible(false);
		instructions2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//hides proper button
				instructions.setVisible(true);
				instructions2.setVisible(false);
				
				//hides the instructions page
				page.setVisible(false);
			}
			
			
		});
		
		
		
		
		
	}


}
