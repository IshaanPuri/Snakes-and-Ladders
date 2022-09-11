import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon.*;
import java.io.*;

import java.net.URL;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Arrays;//needed to compare char[] returned from password field

import javax.swing.Timer; 
import java.awt.GridLayout; //imports GridLayout library
import javax.swing.JButton; //imports JButton library
import javax.swing.JFrame; //imports JFrame library

public class PROJECT extends JPanel implements ActionListener {
    //Global Variables
    CardLayout cdLayout = new CardLayout ();
    JPasswordField p;
    JTextField u;
    JLabel arrow;
    JLabel arrow2;
    Clip mymusic;
    JLabel backgroundmain;
    JLabel backgroundswitch;
    JLabel gamewins;
    JLabel played;
    JPanel arrray;
    JLabel Rolled;
    JLabel squaretext;
    JButton rolldice;
    JLabel squareon;
    int playeds = 0;
    int won = 0;
    int square = 0;
    int row = 10;
    int dice = (int) (Math.random () * 6) + 1;

    public static void main (String[] args){
        PROJECT content = new PROJECT();

        JFrame window = new JFrame("Snakes And Ladders");
        window.setContentPane( content);
        window.setSize(850,850); //Ideal size for my computer screen at home
        window.setLocation( 100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //so music closes     
        window.setVisible(true);
        window.setResizable (false);

    }//end main
    public PROJECT (){
        setLayout (cdLayout);
        PasswordScreen();
        Gifscreen();
        MainMenu();
        instructions();
        settings();
        statistics();
        actualgame();

    }

    public void PasswordScreen () {
        try{
            URL url = this.getClass().getResource("zeze.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            mymusic = AudioSystem.getClip();
            mymusic.open(audioIn);
            mymusic.loop(Clip.LOOP_CONTINUOUSLY);
            //music plays once the game starts to run

        } 
        catch(Exception g){
            JOptionPane.showMessageDialog(null,"There was an error loading the sound!");
        }

        JPanel Passuser = new JPanel();
        Passuser.setLayout(null); //setting layout to null lets me to custom locate widgets

        JLabel background = new JLabel (createImageIcon ("passwordscreenbackground.jpg")); //Image will be background for my Lock Screen
        background.setBounds(0,0,850,850);

        Passuser.add (background); //Adding my background

        JLabel kobe = new JLabel (createImageIcon ("kobe.gif")); //Image will be background for my Lock Screen
        kobe.setBounds(650,365,150,150);
        background.add (kobe);

        JLabel duo = new JLabel (createImageIcon ("snakeandstatpadder.gif")); //Image will be background for my Lock Screen
        duo.setBounds(25,300,250,250);
        background.add (duo);

        JLabel title = new JLabel (createImageIcon ("logintitle.gif"));
        title.setBounds (137,-210,600,600);

        background.add (title);
        JLabel name = new JLabel ("Username:");
        name.setBounds (290,180,200,200);
        //Setting custom colour
        name.setForeground(Color.white);        
        //using custom location
        name.setFont (new Font ("Arial", Font.BOLD, 25)); //cutomizing label
        background.add (name);

        JLabel pass = new JLabel ("Password:");
        pass.setBounds (290,230,200,200);
        //Setting custom colour
        pass.setForeground(Color.white);        
        pass.setFont (new Font ("Arial", Font.BOLD, 25));        
        background.add (pass); 

        u = new JTextField (5); //Username will be typed here
        u.setBounds(435,267,200,25);
        p = new JPasswordField (5); //Password will be typed here
        p.setLocation(435,317); //Coded the custom location differently as the same method used for the JTextfield can't be used with a JPasswordfield
        p.setSize(200,25);
        p.setText ("");
        u.setText ("");

        background.add (u);
        background.add (p); //adding the variables to the background

        JButton Login = new JButton ("LOGIN!"); //Button will move player to gifscreen
        Login.setSize(150,250);
        Login.setBounds(305,550,250,150);
        Login.setActionCommand("started");//Setting command name which will be used 
        Login.addActionListener(this);
        Login.setBackground(Color.white);
        Login.setFont (new Font ("Courier New", Font.BOLD, 25));
        Login.setForeground(Color.black);
        background.add (Login);

        add ("1", Passuser);
        //Adding panel so that it can be called back if need be

    }  //Login Screen ends
    public void Gifscreen () {                
        JPanel Gif = new JPanel();
        Gif.setLayout(null);
        JButton Gifimage = new JButton();
        Gifimage.setIcon(new ImageIcon("gifscreen.gif")); //my gif
        Gifimage.setBounds(0,0,850,850);
        Gifimage.setActionCommand("screen2");//Setting command name which will be used 
        Gifimage.addActionListener(this);
        Gifimage.setBorderPainted(false); //makes the button have no border
        Gifimage.setContentAreaFilled(false); //makes the button have no border or grey space

        Gif.add (Gifimage);

        add ("2",Gif);

    }

    public void MainMenu () {
        JPanel Main = new JPanel();
        Main.setLayout(null);
        JLabel backgroundmain = new JLabel (createImageIcon ("backgroundmain.jpg")); //Image will be background for my Lock Screen
        backgroundmain.setBounds(0,0,850,850);                      
        Main.add (backgroundmain);

        JLabel titlemain = new JLabel (createImageIcon ("TITLE.gif"));
        titlemain.setBounds (160,-210,600,600); //setting location for the title

        backgroundmain.add (titlemain);
        JLabel closegif = new JLabel (createImageIcon ("close.gif"));
        closegif.setBounds (450,350,480,415);
        JLabel copywrite = new JLabel ("Unpublished work (c) 2019 Ishaan  Puri."); //gotta copywrite this great game
        copywrite.setBounds (507,590,400,400);
        copywrite.setForeground(Color.BLACK);
        copywrite.setFont (new Font ("Jokerman", Font.BOLD, 15));

        JButton instructions = new JButton();
        instructions.setIcon(new ImageIcon("instructions.png")); //Button will take the player to the instructions menu
        instructions.setBounds(310,350,250,100);
        instructions.setActionCommand("instruct");
        instructions.addActionListener(this);
        instructions.setBorderPainted(false); //makes the button have no border
        instructions.setContentAreaFilled(false); //makes the button have no border or grey space
        backgroundmain.add (instructions);

        JButton Play = new JButton();
        Play.setIcon(new ImageIcon("play.png"));
        Play.setBounds(310,225,250,100);
        Play.setActionCommand("getstarted");//Button will start the game 
        Play.addActionListener(this);
        Play.setBorderPainted(false); //makes the button have no border
        Play.setContentAreaFilled(false); //makes the button have no border or grey space
        backgroundmain.add (Play);

        JButton settingss = new JButton();
        settingss.setIcon(new ImageIcon("settings.png"));
        settingss.setBounds(310,475,250,100);
        settingss.setActionCommand("change");//Button will open the settings screen 
        settingss.addActionListener(this);
        settingss.setBorderPainted(false); //makes the button have no border
        settingss.setContentAreaFilled(false); //makes the button have no border or grey space
        backgroundmain.add (settingss);

        JButton statss = new JButton();
        statss.setIcon(new ImageIcon("stats.png"));
        statss.setBounds(310,600,250,100);
        statss.setActionCommand("scores");//Button will open up the stats screen 
        statss.addActionListener(this);
        statss.setBorderPainted(false); //makes the button have no border
        statss.setContentAreaFilled(false); //makes the button have no border or grey space
        backgroundmain.add (statss);

        JLabel adgif = new JLabel (createImageIcon ("ad.gif"));
        adgif.setBounds (100,370,250,250); //for esthetic purposes

        backgroundmain.add (adgif);

        JLabel lbjgif = new JLabel (createImageIcon ("lbj.gif"));
        lbjgif.setBounds (550,370,250,250); //for esthetic purposes

        backgroundmain.add (lbjgif);

        backgroundmain.add (copywrite);
        add ("3",Main); //naming the screen so it can be called back in the action command
    }

    public void instructions () {
        JPanel instruction = new JPanel();
        instruction.setLayout(null);

        JLabel background2 = new JLabel (createImageIcon ("background2.jpg"));
        background2.setBounds(0,0,850,850);
        instruction.add (background2);

        JButton Back = new JButton();
        Back.setIcon(new ImageIcon("back.png"));
        Back.setBounds(670,10,150,150);
        Back.setActionCommand("goingback");//Will bring player back to the main menu
        Back.addActionListener(this);
        Back.setBorderPainted(false); //makes the button have no border
        Back.setContentAreaFilled(false); //makes the button have no border or grey space
        background2.add (Back);

        JLabel closegif = new JLabel (createImageIcon ("close.gif"));
        closegif.setBounds(410,300,480,415);
        background2.add(closegif);

        JLabel howto = new JLabel (createImageIcon ("howto.jpg"));
        howto.setBounds(50,25,350,800);
        background2.add(howto);

        instruction.add (background2);
        add ("4",instruction); //naming it so it can be brought up in the action performed
    }

    public void settings () {
        JPanel settingscreen = new JPanel();
        settingscreen.setLayout(null);

        backgroundmain = new JLabel (createImageIcon ("backgroundmain.jpg"));
        backgroundmain.setBounds(0,0,850,850);

        settingscreen.add (backgroundmain);

        JButton Back2 = new JButton (); 
        Back2.setIcon(new ImageIcon("back.png"));
        Back2.setBounds(670,10,150,150);
        Back2.setActionCommand("goingback2");//When pressed it will bring player back to the main menu 
        Back2.addActionListener(this);
        Back2.setBorderPainted(false); //makes the button have no border
        Back2.setContentAreaFilled(false); //makes the button have no border or grey space
        backgroundmain.add (Back2);

        JButton mute = new JButton ();
        mute.setIcon(new ImageIcon("mute.png"));
        mute.setBounds(50,150,250,250);
        mute.setActionCommand("off");//When pressed the music will mute 
        mute.addActionListener(this);
        mute.setBorderPainted(false); //makes the button have no border
        mute.setContentAreaFilled(false); //makes the button have no border or grey space
        backgroundmain.add (mute);

        JButton unmute = new JButton ();
        unmute.setIcon(new ImageIcon("unmute.png"));
        unmute.setBounds(50,500,250,250);
        unmute.setActionCommand("backon");//When pressed the music will start to play again
        unmute.addActionListener(this);
        unmute.setBorderPainted(false); //makes the button have no border
        unmute.setContentAreaFilled(false); //makes the button have no border or grey space
        backgroundmain.add (unmute);

        arrow = new JLabel (createImageIcon ("arrow.png"));
        arrow.setBounds(325,540,199,150);
        arrow.setVisible(false); //will be made visible in the action performed when a the unmute button is pressed and the song changes so the player knows
                                 //the music is on

        arrow2 = new JLabel (createImageIcon ("arrow.png"));//will be made visible in the action performed when a the mute button is pressed so the player knows the music is mute                                                 
        arrow2.setBounds(325,190,199,150);
        arrow2.setVisible(false);
        backgroundmain.add (arrow2);

        JButton blueface = new JButton ();
        blueface.setIcon(new ImageIcon("blueface.jpg"));
        blueface.setBounds(600,350,250,250);
        blueface.setActionCommand("songswitch");//When pressed the song will change to the thotiana instrumental
        blueface.addActionListener(this);
        blueface.setBorderPainted(false); //makes the button have no border
        blueface.setContentAreaFilled(false); //makes the button have no border or grey space
        backgroundmain.add (blueface);

        JButton zeze = new JButton ();
        zeze.setIcon(new ImageIcon("zezecover.jpg"));
        zeze.setBounds(600,550,250,250);
        zeze.setActionCommand("backagain");//When pressed the song will change back to the zeze instrumental 
        zeze.addActionListener(this);
        zeze.setBorderPainted(false); //makes the button have no border
        zeze.setContentAreaFilled(false); //makes the button have no border or grey space
        backgroundmain.add (zeze);

        JLabel settitle = new JLabel (createImageIcon ("settingss.gif"));
        settitle.setBounds (250,50,378,111);

        backgroundmain.add (settitle);

        add ("5",settingscreen); //adding screen so the name can be used in the action performed
    }

    public void statistics () {
        JPanel stats = new JPanel();
        stats.setLayout(null);
        JLabel backgroundscore = new JLabel (createImageIcon ("backgroundscores.jpg"));
        backgroundscore.setBounds(0,0,850,850);


        stats.add (backgroundscore);

        JButton Back3 = new JButton ();
        Back3.setIcon(new ImageIcon("back.png"));
        Back3.setBounds(670,10,150,150);
        Back3.setActionCommand("goingback3");//Will bring player back to the main menu
        Back3.addActionListener(this);
        Back3.setBorderPainted(false); //makes the button have no border
        Back3.setContentAreaFilled(false); //makes the button have no border or grey space
        backgroundscore.add (Back3);

        played = new JLabel ("Games Played:"); //text will be updated in the action command since setText command will be used in an if statement there
        played.setFont (new Font ("Arial", Font.BOLD, 30));        
        played.setBounds(50,200,300,300);
        played.setForeground (Color.white);
        backgroundscore.add (played);

        gamewins = new JLabel ("Games Won: 000 "); //text will be updated in the action command since setText command will be used in an if statement there
        gamewins.setFont (new Font ("Arial", Font.BOLD, 30));
        gamewins.setForeground (Color.white);
        gamewins.setBounds(50,500,300,300);

        backgroundscore.add (gamewins);
        add ("6",stats); //adding the screen and naming it so it can be used in the action performed
    }

    JButton a[] = new JButton [row * row]; //setting my array up, 10 x 10 array 
    public void actualgame () {
        JPanel games = new JPanel();
        games.setBackground(Color.black);

        JButton rolldice = new JButton ("Roll The Dice");
        rolldice.setActionCommand("rolldat");
        rolldice.addActionListener(this);
        rolldice.setBorderPainted(true); 
        rolldice.setContentAreaFilled(true); 
        games.add (rolldice);

        Rolled = new JLabel ("You Rolled a : "); //text will be updated in the action performed
        Rolled.setFont (new Font ("Arial", Font.BOLD, 20));
        Rolled.setForeground(Color.white);
        games.add (Rolled);

        squareon = new JLabel ("You're On Square : ");  //text will be updated in the action performed
        squareon.setFont (new Font ("Arial", Font.BOLD, 20));
        squareon.setForeground(Color.white);
        games.add (squareon);

        arrray = new JPanel(); //this panel will contain my array

        arrray.setLayout(new GridLayout (row, row)); //set the panel to grind layout so my button array will be organized in a 10 x 10 /row x row square

        for (int i = 0 ; i < a.length ; i++) //initilize the array
        {
            a [i] = new JButton (""+ i);
            a [i].setPreferredSize(new Dimension(60,75));
            a [i].setFont (new Font ("Arial", Font.BOLD, 15));
            a [i].addActionListener (this);
            a [i].setActionCommand("goat");
            a [i].setVisible(true);
            arrray.add (a [i]); //adding it to my grid layout panel
        }
        
        //What i'm doing below here is adding each square of the board as the icon for my buttons on my array. I split the board into 100 squares and each button
        // will represent on of those squares using the setIcon(new ImageIcon) command

        a [90].setIcon(new ImageIcon("1.jpg"));
        a [91].setIcon(new ImageIcon("2.jpg"));
        a [92].setIcon(new ImageIcon("3.jpg"));
        a [93].setIcon(new ImageIcon("4.jpg"));
        a [94].setIcon(new ImageIcon("5.jpg"));
        a [95].setIcon(new ImageIcon("6.jpg"));
        a [96].setIcon(new ImageIcon("7.jpg"));
        a [97].setIcon(new ImageIcon("8.jpg"));
        a [98].setIcon(new ImageIcon("9.jpg"));
        a [99].setIcon(new ImageIcon("10.jpg"));

        a [80].setIcon(new ImageIcon("11.jpg"));
        a [81].setIcon(new ImageIcon("12.jpg"));
        a [82].setIcon(new ImageIcon("13.jpg"));
        a [83].setIcon(new ImageIcon("14.jpg"));
        a [84].setIcon(new ImageIcon("15.jpg"));
        a [85].setIcon(new ImageIcon("16.jpg"));
        a [86].setIcon(new ImageIcon("17.jpg"));
        a [87].setIcon(new ImageIcon("18.jpg"));
        a [88].setIcon(new ImageIcon("19.jpg"));
        a [89].setIcon(new ImageIcon("20.jpg"));

        a [70].setIcon(new ImageIcon("21.jpg"));
        a [71].setIcon(new ImageIcon("22.jpg"));
        a [72].setIcon(new ImageIcon("23.jpg"));
        a [73].setIcon(new ImageIcon("24.jpg"));
        a [74].setIcon(new ImageIcon("25.jpg"));
        a [75].setIcon(new ImageIcon("26.jpg"));
        a [76].setIcon(new ImageIcon("27.jpg"));
        a [77].setIcon(new ImageIcon("28.jpg"));
        a [78].setIcon(new ImageIcon("29.jpg"));
        a [79].setIcon(new ImageIcon("30.jpg"));

        a [60].setIcon(new ImageIcon("31.jpg"));
        a [61].setIcon(new ImageIcon("32.jpg"));
        a [62].setIcon(new ImageIcon("33.jpg"));
        a [63].setIcon(new ImageIcon("34.jpg"));
        a [64].setIcon(new ImageIcon("35.jpg"));
        a [65].setIcon(new ImageIcon("36.jpg"));
        a [66].setIcon(new ImageIcon("37.jpg"));
        a [67].setIcon(new ImageIcon("38.jpg"));
        a [68].setIcon(new ImageIcon("39.jpg"));
        a [69].setIcon(new ImageIcon("40.jpg"));

        a [50].setIcon(new ImageIcon("41.jpg"));
        a [51].setIcon(new ImageIcon("42.jpg"));
        a [52].setIcon(new ImageIcon("43.jpg"));
        a [53].setIcon(new ImageIcon("44.jpg"));
        a [54].setIcon(new ImageIcon("45.jpg"));
        a [55].setIcon(new ImageIcon("46.jpg"));
        a [56].setIcon(new ImageIcon("47.jpg"));
        a [57].setIcon(new ImageIcon("48.jpg"));
        a [58].setIcon(new ImageIcon("49.jpg"));
        a [59].setIcon(new ImageIcon("50.jpg"));

        a [40].setIcon(new ImageIcon("51.jpg"));
        a [41].setIcon(new ImageIcon("52.jpg"));
        a [42].setIcon(new ImageIcon("53.jpg"));
        a [43].setIcon(new ImageIcon("54.jpg"));
        a [44].setIcon(new ImageIcon("55.jpg"));
        a [45].setIcon(new ImageIcon("56.jpg"));
        a [46].setIcon(new ImageIcon("57.jpg"));
        a [47].setIcon(new ImageIcon("58.jpg"));
        a [48].setIcon(new ImageIcon("59.jpg"));
        a [49].setIcon(new ImageIcon("60.jpg"));

        a [30].setIcon(new ImageIcon("61.jpg"));
        a [31].setIcon(new ImageIcon("62.jpg"));
        a [32].setIcon(new ImageIcon("63.jpg"));
        a [33].setIcon(new ImageIcon("64.jpg"));
        a [34].setIcon(new ImageIcon("65.jpg"));
        a [35].setIcon(new ImageIcon("66.jpg"));
        a [36].setIcon(new ImageIcon("67.jpg"));
        a [37].setIcon(new ImageIcon("68.jpg"));
        a [38].setIcon(new ImageIcon("69.jpg"));
        a [39].setIcon(new ImageIcon("70.jpg"));

        a [20].setIcon(new ImageIcon("71.jpg"));
        a [21].setIcon(new ImageIcon("72.jpg"));
        a [22].setIcon(new ImageIcon("73.jpg"));
        a [23].setIcon(new ImageIcon("74.jpg"));
        a [24].setIcon(new ImageIcon("75.jpg"));
        a [25].setIcon(new ImageIcon("76.jpg"));
        a [26].setIcon(new ImageIcon("77.jpg"));
        a [27].setIcon(new ImageIcon("78.jpg"));
        a [28].setIcon(new ImageIcon("79.jpg"));
        a [29].setIcon(new ImageIcon("80.jpg"));

        a [10].setIcon(new ImageIcon("81.jpg"));
        a [11].setIcon(new ImageIcon("82.jpg"));
        a [12].setIcon(new ImageIcon("83.jpg"));
        a [13].setIcon(new ImageIcon("84.jpg"));
        a [14].setIcon(new ImageIcon("85.jpg"));
        a [15].setIcon(new ImageIcon("86.jpg"));
        a [16].setIcon(new ImageIcon("87.jpg"));
        a [17].setIcon(new ImageIcon("88.jpg"));
        a [18].setIcon(new ImageIcon("89.jpg"));
        a [19].setIcon(new ImageIcon("90.jpg"));

        a [0].setIcon(new ImageIcon("91.jpg"));
        a [1].setIcon(new ImageIcon("92.jpg"));
        a [2].setIcon(new ImageIcon("93.jpg"));
        a [3].setIcon(new ImageIcon("94.jpg"));
        a [4].setIcon(new ImageIcon("95.jpg"));
        a [5].setIcon(new ImageIcon("96.jpg"));
        a [6].setIcon(new ImageIcon("97.jpg"));
        a [7].setIcon(new ImageIcon("98.jpg"));
        a [8].setIcon(new ImageIcon("99.jpg"));
        a [9].setIcon(new ImageIcon("100.jpg"));

        games.add (arrray);

        add ("8",games); //adding the screen so it can be mentioned in the action performed
    }

    public void actionPerformed (ActionEvent e){
        if (u.getText().equals ("password") //if the username is correct
        && Arrays.equals("password".toCharArray(), p.getPassword())) { //if the password is correct
            cdLayout.show (this, "2"); //the next screen will show
        }
        else{
            JOptionPane.showMessageDialog (null, "Incorrect Password or Username",
                "Try Again",
                JOptionPane.ERROR_MESSAGE);//if the usermane or password entered is incorrect this JOptionPane will show
        }
        if (e.getActionCommand().equals ("screen2")) {
            cdLayout.show (this, "3"); //the 3rd screen will show if the image/button is pressed

        }
        if (e.getActionCommand().equals ("instruct")) {
            cdLayout.show (this, "4"); //the 4th screen will show if the image/button is pressed

        }
        if (e.getActionCommand().equals ("goingback")) {
            cdLayout.show (this, "3"); //Will go back to main menu
        }
        if (e.getActionCommand().equals ("goingback2")) {
            cdLayout.show (this, "3"); //Will go back to main menu
        }
        if (e.getActionCommand().equals ("change")) {
            cdLayout.show (this, "5"); //Will go to settings screen
        }
        if (e.getActionCommand().equals ("goingback3")) {
            cdLayout.show (this, "3"); //Will go back to main menu
        }
        if (e.getActionCommand().equals ("scores")) {
            cdLayout.show (this, "6"); //Will go to the stats screen
        }
        if (e.getActionCommand().equals ("goat")) {
            cdLayout.show (this, "8"); //if the board which is buttons it pressed the screen won't change it will stay on screen 8 the game screen
        }
        if (e.getActionCommand().equals ("backon")) {        
            mymusic.start(); //when the unmute button is pressed the song starts

            arrow.setVisible(true); //arrow beside the volume on button appears to indicate the volumes on
            arrow2.setVisible(false); //the arrow that indicates the volume is mute disapears
            cdLayout.show (this, "5"); //makes it so button doesnt change screen to gifscreen

        }
        if (e.getActionCommand().equals ("off")) {        
            mymusic.stop(); //when the mute button is pressed the song stops

            arrow2.setVisible(true); //arrow beside the volume off appears to make sure the player knows the volume is mute
            arrow.setVisible(false); //Arrow beside the volume on button dispears
            cdLayout.show (this, "5"); //makes it so button doesnt change screen to gifscreen

        }
        if (e.getActionCommand().equals ("songswitch")) {   
            mymusic.stop(); //When the song changes the music stops
            arrow.setVisible(true); //arrow beside the volume on button appears to indicate the volumes on despite the song going quiet to a split second
            arrow2.setVisible(false);

            cdLayout.show (this, "5");//makes it so button doesnt change screen to gifscreen
            try{
                URL url = this.getClass().getResource("blueface.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                mymusic = AudioSystem.getClip();
                mymusic.open(audioIn);
                mymusic.loop(Clip.LOOP_CONTINUOUSLY);

            } 
            catch(Exception g){
                JOptionPane.showMessageDialog(null,"There was an error loading the sound!");
            }
            mymusic.start();//new song starts
        }
        if (e.getActionCommand().equals ("backagain")) { 
            cdLayout.show (this, "5");
            mymusic.stop(); //the changed song stops
            arrow.setVisible(true);
            arrow2.setVisible(false);

            try{
                URL url = this.getClass().getResource("zeze.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                mymusic = AudioSystem.getClip();
                mymusic.open(audioIn);
                mymusic.loop(Clip.LOOP_CONTINUOUSLY);

            } 
            catch(Exception g){
                JOptionPane.showMessageDialog(null,"There was an error loading the sound!");
            }
            mymusic.start();//old song starts to play again
        } 
        if (e.getActionCommand().equals ("getstarted")) {
            cdLayout.show (this, "8"); //actual game screen with board is displayed
            playeds = playeds + 1;
            int square = 0;
            played.setText ("Games Played: " + playeds); //when the play button is pressed the games played label updates and the #increases by 1

        }   

        if (e.getActionCommand().equals ("rolldat")) {
            cdLayout.show (this, "8");
            for(int i=0;i<=99;i++) //made it so that everytime the button is pressed all the buttons are visible
                a[i].setVisible(true); //this is important as before this the false visibility that was indicating where you were on the board was not updating
                                      // If i rolled a 4 then a 5 square 4 and 9 would both be transparent but this loop fixes that
            int dice = ((int) (Math.random() * 6)) + 1;
            square = (dice) + (square);
            Rolled.setText ("You Rolled a : " + dice);

            
            if (square == 1) //if statements will make a button transparent which would indicate to the player that they were on that square/button
            {  //squareon text gets updated and the square the player is on is added at the end
                squareon.setText ("You're On Square : " + square);

                a [90].setVisible(false);
            }
            if (square == 2)
            { 
                squareon.setText ("You're On Square : " + square);

                a [91].setVisible(false);
            }
            if (square == 3)
            { 
                square = 51;
                squareon.setText ("You're On Square : " + square);

                a [40].setVisible(false);
            }
            if (square == 4)
            { 

                a [93].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 5)
            { 

                a [94].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 6)
            { 
                square = 27;

                a [76].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }        
            if (square == 7)
            { 

                a [96].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 8)
            { 

                a [97].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 9)
            { 

                a [98].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 10)
            { 

                a [99].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 11)
            { 

                a [80].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 12)
            { 

                a [81].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 13)
            { 

                a [82].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 14)
            { 

                a [83].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 15)
            { 

                a [84].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 16)
            { 

                a [85].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 17)
            { 

                a [86].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 18)
            { 

                a [87].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 19)
            { 

                a [88].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 20)
            { 
                square = 70;

                a [39].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 21)
            { 

                a [70].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 22)
            { 

                a [71].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 23)
            { 

                a [72].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 24)
            { 

                a [73].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 25)
            { 
                square = 5;

                a [94].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 26)
            { 

                a [75].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 27)
            { 

                a [76].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 28)
            { 

                a [77].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 29)
            { 

                a [78].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 30)
            { 

                a [79].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 31)
            { 

                a [60].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 32)
            { 

                a [61].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 33)
            { 

                a [62].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 34)
            { 
                square = 1;

                a [90].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 35)
            { 

                a [64].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 36)
            { 
                square = 55;

                a [44].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 37)
            { 

                a [66].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 38)
            { 

                a [67].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 39)
            { 

                a [68].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 40)
            { 

                a [69].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 41)
            { 

                a [50].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 42)
            { 

                a [51].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 43)
            { 

                a [52].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 44)
            { 

                a [53].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 45)
            { 

                a [54].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 46)
            { 

                a [55].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 47)
            { 
                square = 19;

                a [88].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 48)
            { 

                a [57].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 49)
            { 

                a [58].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 50)
            { 

                a [59].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 51)
            { 

                a [40].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 52)
            { 

                a [41].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 53)
            { 

                a [42].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 54)
            { 

                a [43].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 55)
            { 

                a [44].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 56)
            { 

                a [45].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 57)
            { 

                a [46].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 58)
            { 

                a [47].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 59)
            { 

                a [48].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 60)
            { 

                a [49].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 61)
            { 

                a [30].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 62)
            { 

                a [31].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 63)
            { 
                square = 95;

                a [4].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 64)
            { 

                a [33].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 65)
            { 
                square = 52;

                a [41].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 66)
            { 

                a [35].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 67)
            { 

                a [36].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 68)
            { 
                square = 98;

                a [7].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 69)
            { 

                a [38].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 70)
            { 

                a [39].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 71)
            { 

                a [20].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 72)
            { 

                a [21].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 73)
            { 

                a [22].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 74)
            { 

                a [23].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 75)
            { 

                a [24].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 76)
            { 

                a [25].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 77)
            { 

                a [26].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 78)
            { 

                a [27].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 79)
            { 

                a [28].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 80)
            { 

                a [29].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 81)
            { 

                a [10].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 82)
            { 

                a [11].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 83)
            { 

                a [12].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 84)
            { 

                a [13].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 85)
            { 

                a [14].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 86)
            { 

                a [15].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 87)
            { 
                square = 57;

                a [46].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 88)
            { 

                a [17].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 89)
            { 

                a [18].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 90)
            { 

                a [19].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 91)
            { 
                square = 61;

                a [30].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 92)
            { 

                a [1].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 93)
            { 

                a [2].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 94)
            { 

                a [3].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 95)
            { 

                a [4].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 96)
            { 

                a [5].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 97)
            { 

                a [6].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 98)
            { 

                a [7].setVisible(false);
                squareon.setText ("You're On Square : " + square);
            }
            if (square == 99)
            { 
                square = 69;

                a [38].setVisible(false);
                squareon.setText ("You're On Square : " + square);

            }
            if (square == 100)
            { 
                cdLayout.show (this, "3");
                won = won + 1;
                gamewins.setText("Games Won: 000" + won); //games won is increased by 1
                squareon.setText ("You're On Square : " + square);

                a [8].setVisible(false);
                
                JOptionPane.showMessageDialog (null, "CONGRATULATIONS YOU WON!", "Good Job!" , JOptionPane.INFORMATION_MESSAGE);
                //congratulations JOptionPane that shows if u win
                
            }
            if (square>100)
            { 

                square = 0;
                //makes sure that when the player plays again after winning the board resets since after the min number on the dice is 1 which would
                // add to make square 101 which is higher than 100, resulting in square becoming equal to 0

            }

            
            
        }
    }

    protected static ImageIcon createImageIcon (String path) { //COPIED 
        java.net.URL imgURL = PROJECT.class.getResource (path);
        if (imgURL != null) {
            return new ImageIcon (imgURL);
        } else {
            System.err.println ("Couldn't find file: " + path);
            return null;
        }
    }
}

