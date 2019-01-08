package windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import main.DBConnector;
import main.Kolokwium;
import main.Message;
import models.User;
import models.Task;

public class StudentWindow {

	private JFrame frame;
	private JTextField daneUzytkownika;
	private JPanel infoPanel;
	private JPanel kartaWyslijZadanie,kartaPodgladOcen,kartaZadania,kartaKontakt;
	private JButton przyciskWylogowania;
	JTabbedPane tabbedPane = new JTabbedPane();
	private JTextArea poleIdTestu;
	private JLabel tekstWyslij;
	private JButton przyciskPrzegladaj;
	private JTextArea pole_Temat;
	private JLabel textTemat;
	private JTextArea poleNumerAlbumu;
	private JLabel tekstIDProwadzacy;
	private JLabel tekst_tresc;
	private JTextArea poleTresc;
	private JButton przyciskWyslij;
	private JList listaOcenionych;
	private JList listaZadan;
	private JSpinner answerSpinner;
	private User currentUser;
	private JLabel answerLabel;
	private JButton refreshButton;
	private JButton selectButton;
	
	private DBConnector db;
	private JLabel gradesLabel;
	private JLabel titleLabel;
	private JButton refreshGrades;
	private JTextArea poleOdpowiedzi;
	private JButton wyslijOdpButton;
	private String currentTask;
	private JLabel tekstTestPytanie;
	private JTextArea poleOdpNaPytanie;
	private JButton przyciskWyslijOdp;
	private JLabel tekstOdpowiedz;
	private ArrayList<Kolokwium> pytania = new ArrayList<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					StudentWindow window = new StudentWindow(new User(0, "Testowy", "User", "Student", "33i", null));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentWindow(User logged) {
		currentUser = logged;
		db = new DBConnector();
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 483, 416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//----- górny panel, info o u¿ytkowniku
		
		infoPanel = new JPanel();
		infoPanel.setBackground(SystemColor.control);
		infoPanel.setBounds(0, 0, 477, 39);
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		
		JLabel Uzytkownik = new JLabel(currentUser.toString());
		Uzytkownik.setBounds(10, 9, 79, 14);
		infoPanel.add(Uzytkownik);
		
		daneUzytkownika = new JTextField();
		daneUzytkownika.setBounds(99, 6, 199, 20);
		infoPanel.add(daneUzytkownika);
		daneUzytkownika.setBackground(SystemColor.control);
		daneUzytkownika.setForeground(SystemColor.control);
		daneUzytkownika.setEditable(false);
		daneUzytkownika.setBorder(null);
		daneUzytkownika.setColumns(10);
		
		przyciskWylogowania = new JButton("Wyloguj si\u0119");
		przyciskWylogowania.setForeground(SystemColor.desktop);
		przyciskWylogowania.setBounds(358, 7, 109, 19);
		infoPanel.add(przyciskWylogowania);
		przyciskWylogowania.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			
			}
        });
		
		
		
		//------ stworzenie poszczególnych wygl¹dów kazdej zak³adki
        
        kartaWyslijZadanie = new JPanel();
        kartaWyslijZadanie.setBackground(SystemColor.control);
        kartaWyslijZadanie.setBounds(0, 36, 550, 398);
        kartaWyslijZadanie.setLayout(null);
        
        kartaPodgladOcen = new JPanel();
        kartaPodgladOcen.setBackground(SystemColor.control);
        kartaPodgladOcen.setBounds(0, 36, 550, 398);
        kartaPodgladOcen.setLayout(null);
        
        kartaZadania = new JPanel();
        kartaZadania.setBackground(SystemColor.control);
        kartaZadania.setBounds(0, 36, 550, 398);
        kartaZadania.setLayout(null);
        
        kartaKontakt = new JPanel();
        kartaKontakt.setBackground(SystemColor.control);
        kartaKontakt.setBounds(0, 36, 550, 398);
        kartaKontakt.setLayout(null);
        
        
       //--------------- stworzenie zak³adek i dodanie do nich kart
        
        tabbedPane.setBounds(0, 36, 478, 354);
        frame.getContentPane().add(tabbedPane);
        tabbedPane.setBorder(null);
        tabbedPane.setBackground(SystemColor.control);
        tabbedPane.addTab("Rozwiaz test", kartaWyslijZadanie);
        tabbedPane.addTab("Sprawdz zadania", kartaZadania);
        tabbedPane.addTab("Podglad Ocen", kartaPodgladOcen);
        tabbedPane.addTab("Kontakt z prowadzacym", kartaKontakt);
        
        
        //------------ komponenty do 1 zak³adki
        
        poleIdTestu = new JTextArea();
        poleIdTestu.setBounds(160, 55, 50, 24);
        poleIdTestu.setBorder(new LineBorder(new Color(0, 0, 0)));
        poleIdTestu.setColumns(10);
        kartaWyslijZadanie.add(poleIdTestu);
        
        
        
        tekstWyslij = new JLabel("Pobierz pytanie z testu nr.:");
        tekstWyslij.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tekstWyslij.setHorizontalAlignment(SwingConstants.CENTER);
        tekstWyslij.setBounds(102, 11, 288, 24);
        kartaWyslijZadanie.add(tekstWyslij);
        
        tekstTestPytanie = new JLabel("Brak pobranego pytania");
        tekstTestPytanie.setVerticalAlignment(SwingConstants.TOP);
        tekstTestPytanie.setHorizontalAlignment(SwingConstants.CENTER);
        poleIdTestu.setBorder(new LineBorder(new Color(0, 0, 0)));
        tekstTestPytanie.setBounds(10, 90, 400, 250);
        kartaWyslijZadanie.add(tekstTestPytanie);
        
        
        przyciskPrzegladaj = new JButton("Pobierz");
        przyciskPrzegladaj.setBounds(220, 50, 110, 35);
        kartaWyslijZadanie.add(przyciskPrzegladaj);
        przyciskPrzegladaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pytania = LoginWindow.getClient().getTest(poleIdTestu.getText());
				
				tekstTestPytanie.setText("<html>"+pytania.get(0).pyt+"<br/>1)"+
				pytania.get(0).odp1+"<br/>2)"+pytania.get(0).odp2+"<br/>3)"+pytania.get(0).odp3+"<br/>4)"+
						pytania.get(0).odp4+"<br/>"+"</html>");
				
			}
		});
        
       
        
        tekstOdpowiedz = new JLabel("Odp:");
        tekstOdpowiedz.setBounds(120, 270, 50, 24);
        kartaWyslijZadanie.add(tekstOdpowiedz);
        
        poleOdpNaPytanie = new JTextArea();
        poleOdpNaPytanie.setBounds(160, 270, 50, 24);
        poleOdpNaPytanie.setBorder(new LineBorder(new Color(0, 0, 0)));
        poleOdpNaPytanie.setColumns(10);
        kartaWyslijZadanie.add(poleOdpNaPytanie);
        
        przyciskWyslijOdp = new JButton("Nastepne");
        przyciskWyslijOdp.setBounds(225, 265, 100, 35);
        kartaWyslijZadanie.add(przyciskWyslijOdp);
        przyciskWyslijOdp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Your answer has been saved!", "Answer saved" , JOptionPane.INFORMATION_MESSAGE);
				poleOdpNaPytanie.setText("");
			}
		});
        
       //---------- komponenty do 2 zak³adki
        
        List<Task> taskList = db.getTasks(currentUser.group.toString());
        if (taskList == null || taskList.isEmpty()) {
        	taskList = new ArrayList<Task>();
        	taskList.add(new Task("Brak zadan", 999, "00"));
        }
              
        SpinnerListModel model = new SpinnerListModel(taskList);
	
		answerSpinner = new JSpinner(model);
		answerSpinner.setBounds(120, 11, 200, 26);
//		answerSpinner.addChangeListener(new ChangeListener() {
//
//	        @Override
//	        public void stateChanged(ChangeEvent e) {
//	            LOG.info("value changed: " + spinner.getValue());
//	        }
//	    });
		
		
		kartaZadania.add(answerSpinner);
		
		refreshButton = new JButton("Refresh");
		refreshButton.setBounds(10, 11, 100, 26);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				answerLabel.setText(((Task)answerSpinner.getValue()).getContent());
				model.setList(db.getTasks(currentUser.group.toString()));
			}
		});
		kartaZadania.add(refreshButton);
		
		selectButton = new JButton("Select");
		selectButton.setBounds(330, 11, 100, 26);
		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Task> taskList = db.getTasks(currentUser.group.toString());
				if(taskList != null && !taskList.isEmpty())
				{
					answerLabel.setText(((Task)answerSpinner.getValue()).getContent());
					currentTask = answerLabel.getText();
					answerLabel.setVisible(true);
					poleOdpowiedzi.setVisible(true);
				}
				else
				{
					answerLabel.setText("Brak zadan");
					answerLabel.setVisible(false);
					poleOdpowiedzi.setVisible(false);
				}
			}
		});
		kartaZadania.add(selectButton);
		
		answerLabel = new JLabel(((Task)answerSpinner.getValue()).getContent());
		answerLabel.setVerticalAlignment(JLabel.TOP);
		//answerLabel.setMinimumSize()
		answerLabel.setBounds(10, 40, 500, 300);
		kartaZadania.add(answerLabel);
		
		poleOdpowiedzi = new JTextArea();
		poleOdpowiedzi.setBounds(10, 90, 410, 50);
		poleOdpowiedzi.setBorder(new LineBorder(new Color(0, 0, 0)));
		poleOdpowiedzi.setColumns(10);
		kartaZadania.add(poleOdpowiedzi);
		
		wyslijOdpButton = new JButton("Wyslij");
		wyslijOdpButton.setBounds(170, 160, 100, 26);
		wyslijOdpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnector.sendTaskAns(currentUser.UID, currentTask, poleOdpowiedzi.getText());
				JOptionPane.showMessageDialog(frame, "Odpowiedz zostala wyslana!","Dodano odpowiedz", JOptionPane.INFORMATION_MESSAGE);
				poleOdpowiedzi.setText("");
			}
		});
		kartaZadania.add(wyslijOdpButton);
        
        //---------- komponenty do 3 zak³adki
       
       
       
        titleLabel = new JLabel("Twoje oceny:");
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setBounds(10, 10, 500, 300);
		kartaPodgladOcen.add(titleLabel);
		
		gradesLabel = new JLabel("Brak ocen");
        gradesLabel.setVerticalAlignment(JLabel.TOP);
        gradesLabel.setBounds(10, 40, 500, 300);
		kartaPodgladOcen.add(gradesLabel);
		
		refreshGrades = new JButton("Odswiez");
		refreshGrades.setBounds(350, 10, 90, 20);
        kartaPodgladOcen.add(refreshGrades);
        refreshGrades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> results = DBConnector.getGrades(currentUser.UID);
				gradesLabel.setText("<html> ");
				for(int i=0; i<results.size(); i++)
					gradesLabel.setText(gradesLabel.getText()+" <br> "+results.get(i));
				gradesLabel.setText( gradesLabel.getText() + " </html>");
		}
        });
        
        
        //---------- komponenty do 4 zak³adki
        
        pole_Temat = new JTextArea();
        pole_Temat.setBounds(10, 69, 453, 20);
        pole_Temat.setBorder(new LineBorder(new Color(0, 0, 0)));
        pole_Temat.setColumns(10);
        kartaKontakt.add(pole_Temat);
      
        textTemat = new JLabel("Temat:");
        textTemat.setBounds(10, 50, 46, 14);
        kartaKontakt.add(textTemat);
        
        poleNumerAlbumu = new JTextArea();
        poleNumerAlbumu.setBounds(10, 25, 131, 20);
        poleNumerAlbumu.setBorder(new LineBorder(new Color(0, 0, 0)));
        poleNumerAlbumu.setColumns(10);
        kartaKontakt.add(poleNumerAlbumu);
       
        tekstIDProwadzacy = new JLabel("ID_Prowadz\u0105cego");
        tekstIDProwadzacy.setBounds(10, 8, 117, 14);
        kartaKontakt.add(tekstIDProwadzacy);
        
        tekst_tresc = new JLabel("Tre\u015B\u0107:");
        tekst_tresc.setBounds(10, 100, 46, 14);
        kartaKontakt.add(tekst_tresc);
        
        poleTresc = new JTextArea();
        poleTresc.setBorder(new LineBorder(new Color(0, 0, 0)));
        poleTresc.setBounds(10, 117, 453, 160);
        poleTresc.setColumns(10);
        kartaKontakt.add(poleTresc);
       
        
        przyciskWyslij = new JButton("Wyslij");
        przyciskWyslij.setBounds(178, 288, 112, 27);
        kartaKontakt.add(przyciskWyslij);
        przyciskWyslij.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Message newmsg = new Message(Integer.parseInt(poleNumerAlbumu.getText()), currentUser.UID, pole_Temat.getText(), poleTresc.getText());
				LoginWindow.getClient().sendMessage(newmsg);

				JOptionPane.showMessageDialog(frame, "Wiadomosc wyslana","Sukces", JOptionPane.INFORMATION_MESSAGE);
				poleNumerAlbumu.setText("");
				pole_Temat.setText("");
				poleTresc.setText("");
			
			}
        });
	}
}
