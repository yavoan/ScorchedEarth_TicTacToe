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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Scorched extends JPanel {

	//Adds the return and menu button
	private static JButton returnToMenu = new JButton("Main Menu");
	private static JButton restart = new JButton("Restart");
	//Adding player components to the screen, and obstacle
	private static ImageIcon p1UFO = new ImageIcon("plaueUFO.png");
	private static JLabel player1 = new JLabel(p1UFO);
	private static ImageIcon p2UFO = new ImageIcon("barnesUFO.png");
	private static JLabel player2 = new JLabel(p2UFO);
	private static ImageIcon paperImg = new ImageIcon("paper.png");
	private static JLabel paper = new JLabel(paperImg);

	//Ammo image
	private static ImageIcon redAmmo = new ImageIcon("redAmmo.png");
	private static ImageIcon yellowAmmo = new ImageIcon("yellowammo.png");
	private static JLabel ammo = new JLabel(redAmmo);
	private static JLabel ammo2 = new JLabel(yellowAmmo);

	//Sliders to adjust power and angle, labels to display those values to the user
	//player1
	private static JSlider p1SliderPower = new JSlider();
	private static JTextField p1PowerBox = new JTextField("15.0");
	private static JLabel powerLabel1 = new JLabel("Power:");
	private static JSlider p1SliderAngle = new JSlider();
	private static JTextField p1AngleBox = new JTextField("0.0");
	private static JLabel angleLabel1 = new JLabel("Angle:");
	//player2
	private static JSlider p2SliderPower = new JSlider();
	private static JTextField p2PowerBox = new JTextField("15.0");
	private static JLabel powerLabel2 = new JLabel("Power:");
	private static JSlider p2SliderAngle = new JSlider();
	private static JTextField p2AngleBox = new JTextField("0.0");
	private static JLabel angleLabel2 = new JLabel("Angle:");
	//Player score labels
	private static JLabel p1ScoreLabel = new JLabel("Player 1 Score: 0");
	private static JLabel p2ScoreLabel = new JLabel("Player 2 Score: 0");

	//round label
	private static JLabel roundLabel = new JLabel("Round: 1");
	
	//title image
	private static ImageIcon scorchedImg = new ImageIcon("setitle.png");
	private static JLabel scorchedTitle = new JLabel(scorchedImg);
	
	//The values Edited by the sliders
	private static double p1Power = 5;
	private static double p1Angle = 0;
	private static double p2Power = 5;
	private static double p2Angle = 0;

	//Next Round/Play Button
	private static JButton next = new JButton("Next Round");
	private static JButton play = new JButton("Play!");	

	//adding Physics engine
	private static Physics physics = new Physics();
	//adding Round to handle queues
	private static Round round = new Round();
	
	//Instructions button and JLabel to be displayed
	private static JButton instructions = new JButton("Instructions");
	private static JButton instructions2 = new JButton("Hide Instructions");
	private static ImageIcon sepage = new ImageIcon("seInstructions.png");
	private static JLabel page = new JLabel(sepage);

	//Jtimer game loop
	private static Timer time1, time2, time3, time4, time5, time6, time7, time8, time9, time10;

	//power and time
	private static double power1, power2, power3, power4, power5, power6, power7, power8, power9, power10;
	private static double angle1, angle2, angle3, angle4, angle5, angle6, angle7, angle8, angle9, angle10;
	private static double t = 0.1;

	//The round number
	private static int roundNumber = 1;

	//the components used to display a proper win screen
	private static ImageIcon tiedImage = new ImageIcon("tiegame.png");
	private static JLabel youTied = new JLabel(tiedImage);
	private static ImageIcon player2Loses = new ImageIcon("p2loser.png");
	private static ImageIcon player1Loses = new ImageIcon("p1loser.png");
	private static JLabel player1Win = new JLabel(player2Loses);
	private static JLabel player2Win = new JLabel(player1Loses);

	//Constructor
	public Scorched(){
		initialize();
	}

	//Initialize the game
	public void initialize(){
		setUpBoard();
	}

	//Sets the background
	public void paintComponent(Graphics g){
		BufferedImage bg;
		try {
			bg = ImageIO.read(new File("spacebackground1.jpg"));
			g.drawImage(bg,0,0,null);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	//sets up the look of the game board, buttons, etc.
	public void setUpBoard(){

		//sets the layout and panel
		setPreferredSize(new Dimension(1000,700));
		setBackground(Color.white);
		setVisible(true);
		setLayout(null);

		addInstructions();
		
		scorchedTitle.setBounds(295, 25, 401, 51);
		scorchedTitle.setVisible(true);
		add(scorchedTitle);
		
		addRoundLabel();
		addScoreLabels();
		add(player1Win);
		add(player2Win);
		add(youTied);

		add(returnToMenu);
		returnToMenu.setBounds(5,5,95,20);
		returnToMenu.setVisible(true);
		returnToMenu.setFont(new Font("Seriph", Font.PLAIN, 12));
		returnToMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//resets all the values to default
				setVisible(false);
				Main_Menu.pane.setVisible(true);
				roundNumber = 1;
				resetSliders();
				addSliders();
				addRoundLabel();
				round.resetQueue();
				physics.resetScore();
				updateScore();

				//hides win result
				youTied.setVisible(false);
				player1Win.setVisible(false);
				player2Win.setVisible(false);
				
				//makes things visible again
				next.setVisible(true);
				p1SliderPower.setVisible(true);
				p1PowerBox.setVisible(true);
				powerLabel1.setVisible(true);
				p1SliderAngle.setVisible(true);
				p1AngleBox.setVisible(true);
				angleLabel1.setVisible(true);

				p2SliderPower.setVisible(true);
				p2PowerBox.setVisible(true);
				powerLabel2.setVisible(true);
				p2SliderAngle.setVisible(true);
				p2AngleBox.setVisible(true);
				angleLabel2.setVisible(true);
				
				scorchedTitle.setVisible(true);
				add(scorchedTitle);

			}

		});

		//resets all the values in the game
		add(restart);
		restart.setBounds(895, 5, 95, 20);
		restart.setVisible(true);
		restart.setFont(new Font("Seriph", Font.PLAIN,12));
		restart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				play.setVisible(false);
				roundNumber = 1;
				addNextRound();
				addPlayers();
				addRoundLabel();
				resetSliders();
				addSliders();
				round.resetQueue();
				physics.resetScore();
				updateScore();
				
				scorchedTitle.setVisible(true);
				add(scorchedTitle);
				
				player1Win.setVisible(false);
				player2Win.setVisible(false);
				youTied.setVisible(false);

			}

		});

		//adds the button to begin game
		addNextRound();

		//Next round button
		next.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				addValues();

				roundNumber++;
				updateRoundLabel();
				
				if (roundNumber == 5){

					addPlayButton();

				}
			}
		});
		play.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				roundNumber++;

				//Hide everything from sight
				play.setVisible(false);
				p1SliderPower.setVisible(false);
				p1PowerBox.setVisible(false);
				powerLabel1.setVisible(false);
				p1SliderAngle.setVisible(false);
				p1AngleBox.setVisible(false);
				angleLabel1.setVisible(false);
				p2SliderPower.setVisible(false);
				p2PowerBox.setVisible(false);
				powerLabel2.setVisible(false);
				p2SliderAngle.setVisible(false);
				p2AngleBox.setVisible(false);
				angleLabel2.setVisible(false);

				addValues();
				roundLabel.setVisible(false);
				playGame();
				


			}
		});


		//Adds the players to the screen
		addPlayers();

		//Addings paper obstacle
		add(paper);
		paper.setBounds(385,250,250,683);
		paper.setVisible(true);

		//adding sliders
		addSliders();

	}

	//add players
	public void addPlayers(){

		physics.randomInt();

		//Adding players to the screen board
		add(player1);
		player1.setBounds(physics.getRandomX1(),physics.getRandomY1(),100,52);
		player1.setVisible(true);
		add(player2);
		player2.setBounds(physics.getRandomX2(),physics.getRandomY2(),100,52);
		player2.setVisible(true);


	}

	//adds sliders to adjust power and angle, as well as their textfields to display their values
	public void addSliders(){

		//Sliders and such for player 1
		add(p1SliderPower); //adjusts the power
		p1SliderPower.setMaximum(150);
		p1SliderPower.setMinimum(15);
		p1SliderPower.setMinorTickSpacing(5);
		p1SliderPower.setPaintLabels(true);
		p1SliderPower.setPaintTicks(true);
		p1SliderPower.setValue(15);
		p1SliderPower.setBounds(20,50,200,30);
		p1SliderPower.addChangeListener(new ChangeListener() { 
			public void stateChanged(ChangeEvent e) { 
				JSlider source = (JSlider)e.getSource(); 
				double slider = (double)source.getValue(); 
				p1Power = slider; 
				if (source.getValueIsAdjusting()) {  
					p1PowerBox.setText(String.valueOf(slider)); //changes value when slider is adjusted
				} 
			} 
		}); 
		add(p1PowerBox);
		p1PowerBox.setEditable(false);
		p1PowerBox.setVisible(true);
		p1PowerBox.setBounds(220, 50, 50, 30);
		add(powerLabel1);
		powerLabel1.setVisible(true);
		powerLabel1.setBounds(20, 30, 48, 20);
		powerLabel1.setForeground(Color.white);
		add(p1SliderAngle); //adjusts the angle
		p1SliderAngle.setMaximum(90);
		p1SliderAngle.setMinimum(0);
		p1SliderAngle.setMinorTickSpacing(5);
		p1SliderAngle.setPaintLabels(true);
		p1SliderAngle.setPaintTicks(true);
		p1SliderAngle.setValue(0);
		p1SliderAngle.setBounds(20,110,200,30);
		p1SliderAngle.addChangeListener(new ChangeListener() { 
			public void stateChanged(ChangeEvent e) { 
				JSlider source = (JSlider)e.getSource(); 
				double slider = (double)source.getValue(); 
				p1Angle = slider; 
				if (source.getValueIsAdjusting()) {  
					p1AngleBox.setText(String.valueOf(slider)); //changes value when slider is adjusted
				} 
			} 
		}); 
		add(p1AngleBox);
		p1AngleBox.setEditable(false);
		p1AngleBox.setVisible(true);
		p1AngleBox.setBounds(220, 110, 50, 30);
		add(angleLabel1);
		angleLabel1.setVisible(true);
		angleLabel1.setBounds(20, 90, 48, 20);
		angleLabel1.setForeground(Color.white);


		//Sliders and such for Player 2
		add(p2SliderPower); //adjusts the power
		p2SliderPower.setMaximum(150);
		p2SliderPower.setMinimum(15);
		p2SliderPower.setMinorTickSpacing(5);
		p2SliderPower.setPaintLabels(true);
		p2SliderPower.setPaintTicks(true);
		p2SliderPower.setValue(15);
		p2SliderPower.setBounds(720,50,200,30);
		p2SliderPower.addChangeListener(new ChangeListener() { 
			public void stateChanged(ChangeEvent e) { 
				JSlider source = (JSlider)e.getSource(); 
				double slider = (double)source.getValue(); 
				p2Power = slider; 
				if (source.getValueIsAdjusting()) {  
					p2PowerBox.setText(String.valueOf(slider)); //changes value when slider is adjusted
				} 
			} 
		}); 
		add(p2PowerBox);
		p2PowerBox.setEditable(false);
		p2PowerBox.setVisible(true);
		p2PowerBox.setBounds(920, 50, 50, 30);
		add(powerLabel2);
		powerLabel2.setVisible(true);
		powerLabel2.setBounds(720, 30, 48, 20);
		powerLabel2.setForeground(Color.white);
		add(p2SliderAngle); //adjusts the angle
		p2SliderAngle.setMaximum(90);
		p2SliderAngle.setMinimum(0);
		p2SliderAngle.setMinorTickSpacing(5);
		p2SliderAngle.setPaintLabels(true);
		p2SliderAngle.setPaintTicks(true);
		p2SliderAngle.setValue(0);
		p2SliderAngle.setBounds(720,110,200,30);
		p2SliderAngle.addChangeListener(new ChangeListener() { 
			public void stateChanged(ChangeEvent e) { 
				JSlider source = (JSlider)e.getSource(); 
				double slider = (double)source.getValue(); 
				p2Angle = slider; 
				if (source.getValueIsAdjusting()) {  
					p2AngleBox.setText(String.valueOf(slider)); //when slider is adjusted, change value
				} 
			} 
		}); 
		add(p2AngleBox);
		p2AngleBox.setEditable(false);
		p2AngleBox.setVisible(true);
		p2AngleBox.setBounds(920, 110, 50, 30);
		add(angleLabel2);
		angleLabel2.setVisible(true);
		angleLabel2.setBounds(720, 90, 48, 20);
		angleLabel2.setForeground(Color.white);


	}

	//resets the values of the sliders and the values they alter
	public void resetSliders(){

		p1SliderPower.setVisible(true);
		p1SliderAngle.setVisible(true);

		p2SliderPower.setVisible(true);
		p2SliderAngle.setVisible(true);

		p1Power = 15;
		p1Angle = 0;
		p2Power = 15;
		p2Angle = 0;

		p1PowerBox.setText(String.valueOf(15.0));
		p1AngleBox.setText(String.valueOf(0.0));
		p2PowerBox.setText(String.valueOf(15.0));
		p2AngleBox.setText(String.valueOf(0.0));

	}

	//adds the buttons to play the game
	public void addNextRound(){

		add(next);
		next.setBounds(400,100,200,50);
		next.setVisible(true);
		next.setFont(new Font("Seriph", Font.BOLD,15));

	}
	public void addPlayButton(){

		add(play);
		play.setBounds(400,100,200,50);
		play.setVisible(true);
		play.setFont(new Font("Seriph", Font.BOLD,15));
		next.setVisible(false);//hides next button
	}


	//adds the ammo to its correct place
	public void addAmmo1(){

		add(ammo);
		ammo.setBounds(physics.getRandomX1()+50, physics.getRandomY1()-10, 9, 9);
		ammo.setVisible(true);

	}
	public void addAmmo2(){

		add(ammo2);
		ammo2.setBounds(physics.getRandomX2()+41, physics.getRandomY2()-10, 9, 9);
		ammo2.setVisible(true);

	}
	public void updateAmmo(){

		add(ammo);
		ammo.setBounds((int)physics.getNewX(), (int)physics.getNewY()-10, 9, 9);
		ammo.setVisible(true);

	}
	public void updateAmmo2(){

		add(ammo2);
		ammo2.setBounds((int)physics.getNewX(), (int)physics.getNewY()-10, 9, 9);
		ammo2.setVisible(true);

	}


	//adds power and angle to queue
	public void addValues(){

		round.addToPower(p1Power);
		round.addToAngle(p1Angle);
		round.addToPower(p2Power);
		round.addToAngle(p2Angle);

	}

	//plays the game
	public void playGame(){

		restart.setEnabled(false);
		returnToMenu.setEnabled(false);
		
		t = 0.2;
		addAmmo1();
		power1 = round.getFromPower();
		angle1 = round.getFromAngle();
		power2 = round.getFromPower();
		angle2 = round.getFromAngle();
		power3 = round.getFromPower();
		angle3 = round.getFromAngle();
		power4 = round.getFromPower();
		angle4 = round.getFromAngle();
		power5 = round.getFromPower();
		angle5 = round.getFromAngle();
		power6 = round.getFromPower();
		angle6 = round.getFromAngle();
		power7 = round.getFromPower();
		angle7 = round.getFromAngle();
		power8 = round.getFromPower();
		angle8 = round.getFromAngle();
		power9 = round.getFromPower();
		angle9 = round.getFromAngle();
		power10 = round.getFromPower();
		angle10 = round.getFromAngle();

		time10 = new Timer(10, new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				physics.applyGravity2(physics.getRandomX2()+41, physics.getRandomY2()-10, power10, angle10, t);
				t += .2; //increases the time each go
				
				updateAmmo2(); //update for that players ammo
				ammo2.repaint();//repaints the ammo for itself
				
				if(physics.detectHit()){
					time10.stop(); //stops itself
					
					t = .2; //resets the time
					physics.setHitFalse(); //sets hit detection to false
					
					ammo.setVisible(false);
					ammo2.setVisible(false);
					
					updateScore();
					winner();
					restart.setEnabled(true);
					returnToMenu.setEnabled(true);
					
				}
			} 
		}); 
		
		time9 = new Timer(10, new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				physics.applyGravity1(physics.getRandomX1()+50, physics.getRandomY1()-10, power9, angle9, t);
				t += .2; //increases the time each go
				updateAmmo(); //update for that players ammo
				ammo.repaint(); //repaints the ammo for itself
				
				if(physics.detectHit()){
					time9.stop(); //stops itself
					
					ammo.setVisible(false);
					addAmmo2(); //adds other players ammo
					t = .2; //resets the time
					physics.setHitFalse(); //sets hit detection to false
					updateScore();
					
					time10.start(); //starts next timer
				}
			} 
		}); 
		
		time8 = new Timer(10, new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				physics.applyGravity2(physics.getRandomX2()+41, physics.getRandomY2()-10, power8, angle8, t);
				t += .2; //increases the time each go
				
				updateAmmo2(); //update for that players ammo
				ammo2.repaint();//repaints the ammo for itself
				
				if(physics.detectHit()){
					time8.stop(); //stops itself
					
					ammo2.setVisible(false);
					addAmmo1(); //add other plays ammo
					t = .2; //resets the time
					physics.setHitFalse(); //sets hit detection to false
					updateScore();
					
					time9.start(); //starts next timer
				}
			} 
		}); 
		
		time7 = new Timer(10, new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				physics.applyGravity1(physics.getRandomX1()+50, physics.getRandomY1()-10, power7, angle7, t);
				t += .2; //increases the time each go
				updateAmmo(); //update for that players ammo
				ammo.repaint(); //repaints the ammo for itself
				
				if(physics.detectHit()){
					time7.stop(); //stops itself
					
					ammo.setVisible(false);
					addAmmo2(); //adds other players ammo
					t = .2; //resets the time
					physics.setHitFalse(); //sets hit detection to false
					updateScore();
					
					time8.start(); //starts next timer
				}
			} 
		}); 
		
		time6 = new Timer(10, new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				physics.applyGravity2(physics.getRandomX2()+41, physics.getRandomY2()-10, power6, angle6, t);
				t += .2; //increases the time each go
				
				updateAmmo2(); //update for that players ammo
				ammo2.repaint();//repaints the ammo for itself
				
				if(physics.detectHit()){
					time6.stop(); //stops itself
					
					ammo2.setVisible(false);
					addAmmo1(); //add other plays ammo
					t = .2; //resets the time
					physics.setHitFalse(); //sets hit detection to false
					updateScore();
					
					time7.start(); //starts next timer
				}
			} 
		}); 
		
		time5 = new Timer(10, new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				physics.applyGravity1(physics.getRandomX1()+50, physics.getRandomY1()-10, power5, angle5, t);
				t += .2; //increases the time each go
				updateAmmo(); //update for that players ammo
				ammo.repaint(); //repaints the ammo for itself
				
				if(physics.detectHit()){
					time5.stop(); //stops itself
					
					ammo.setVisible(false);
					addAmmo2(); //adds other players ammo
					t = .2; //resets the time
					physics.setHitFalse(); //sets hit detection to false
					updateScore();
					
					time6.start(); //starts next timer
				}
			} 
		}); 
		
		time4 = new Timer(10, new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				physics.applyGravity2(physics.getRandomX2()+41, physics.getRandomY2()-10, power4, angle4, t);
				t += .2; //increases the time each go
				
				updateAmmo2(); //update for that players ammo
				ammo2.repaint();//repaints the ammo for itself
				
				if(physics.detectHit()){
					time4.stop(); //stops itself
					
					ammo2.setVisible(false);
					addAmmo1(); //add other plays ammo
					t = .2; //resets the time
					physics.setHitFalse(); //sets hit detection to false
					updateScore();
					
					time5.start(); //starts next timer
				}
			} 
		}); 
		
		time3 = new Timer(10, new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				physics.applyGravity1(physics.getRandomX1()+50, physics.getRandomY1()-10, power3, angle3, t);
				t += .2; //increases the time each go
				updateAmmo(); //update for that players ammo
				ammo.repaint(); //repaints the ammo for itself
				
				if(physics.detectHit()){
					time3.stop(); //stops itself
					
					ammo.setVisible(false);
					addAmmo2(); //adds other players ammo
					t = .2; //resets the time
					physics.setHitFalse(); //sets hit detection to false
					updateScore();
					
					time4.start(); //starts next timer
				}
			} 
		}); 
		
		time2 = new Timer(10, new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				physics.applyGravity2(physics.getRandomX2()+41, physics.getRandomY2()-10, power2, angle2, t);
				t += .2; //increases the time each go
				
				updateAmmo2(); //update for that players ammo
				ammo2.repaint();//repaints the ammo for itself
				
				if(physics.detectHit()){
					time2.stop(); //stops itself
					
					ammo2.setVisible(false);
					addAmmo1(); //add other plays ammo
					t = .2; //resets the time
					physics.setHitFalse(); //sets hit detection to false
					updateScore();
					
					time3.start(); //starts next timer
				}
			} 
		}); 

		time1 = new Timer(10, new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				physics.applyGravity1(physics.getRandomX1()+50, physics.getRandomY1()-10, power1, angle1, t);
				t += .2; //increases the time each go
				updateAmmo();
				ammo.repaint();
				if(physics.detectHit()){
					time1.stop(); //stops itself
					
					ammo.setVisible(false);
					addAmmo2(); //adds other players ammo
					t = .2; //resets the time
					physics.setHitFalse(); //sets hit detection to false
					updateScore();
					
					time2.start(); //starts the next timer
				}
			} 
		}); 
		time1.start(); //the initial timer to set forth timer chain



	}


	//determines the winner, and displays appropriate results
	public void winner(){
		
		//player1 wins
		if(physics.getP1Score() > physics.getP2Score()){
			player1Win.setBounds(335,90,324,42);
			player1Win.setVisible(true);
			add(player1Win);
		}
		//player2 wins
		else if(physics.getP1Score() < physics.getP2Score()){
			player2Win.setBounds(335,90,324,42);
			player2Win.setVisible(true);
			add(player2Win);
		}
		//tie game
		else if(physics.getP1Score() == physics.getP2Score()){
			youTied.setBounds(388,90,204,41);
			youTied.setVisible(true);
			add(youTied);
		}

		
		
	}
	
	
	//updates the round label
	public void updateRoundLabel(){
		roundLabel.setText("Round: " + String.valueOf(roundNumber));
	}
	
	//adds round label
	public void addRoundLabel(){
		add(roundLabel);
		roundLabel.setBounds(480,150,200,50);
		roundLabel.setText("Round: " + String.valueOf(roundNumber));
		roundLabel.setForeground(Color.white);
		roundLabel.setVisible(true);
	}
	
	//adds score labels to the screen
	public void addScoreLabels(){

		add(p1ScoreLabel);
		p1ScoreLabel.setBounds(60, 600, 200, 50);
		p1ScoreLabel.setForeground(Color.white);
		p1ScoreLabel.setVisible(true);

		add(p2ScoreLabel);
		p2ScoreLabel.setBounds(760, 600, 200, 50);
		p2ScoreLabel.setForeground(Color.white);
		p2ScoreLabel.setVisible(true);

	}

	//adds the instructions button whch will display the JLabel for the instructions
	public void addInstructions(){
		
		add(page);
		page.setBounds(300,100,400,500);
		page.setVisible(false);
		
		add(instructions);
		instructions.setBounds(400, 630, 200, 30);
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
		instructions2.setBounds(400, 630, 200, 30);
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
	
	//update score 
	public void updateScore(){
		p1ScoreLabel.setText("Player 1 Score: " + String.valueOf(physics.getP1Score()));
		p2ScoreLabel.setText("Player 2 Score: " + String.valueOf(physics.getP2Score()));
	}

	//getters for power and angles
	public double getP1Power(){
		double power;
		power = p1Power;
		return power;
	}
	public double getP1Angle(){
		double angle;
		angle = p1Angle;
		return angle;
	}
	public double getP2Power(){
		double power;
		power = p2Power;
		return power;
	}
	public double getP2Angle(){
		double angle;
		angle = p2Angle;
		return angle;
	}



}
