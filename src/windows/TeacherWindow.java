package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Font;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import main.DBConnector;
import main.Kolokwium;
import main.Message;
import models.Task;
import models.User;
import models.TaskAnswer;

public class TeacherWindow {

	private JFrame frame;
	private JTextField daneUzytkownika;
	private JPanel infoPanel;
	private JPanel kartaDodajZadanie,kartaPodgladPrac,kartaKolos,kartaKontakt;
	private JButton przyciskWylogowania;
	JTabbedPane tabbedPane = new JTabbedPane();
	private JTextArea poleTrescZadania;
	private JLabel tekstTresc;
	private JButton przyciskWyslijZadanie;
	private JTextArea pole_Temat;
	private JLabel textTemat;
	private JTextArea poleNumerAlbumu;
	private JLabel tekstAlbum;
	private JLabel tekstOdp;
	private JLabel tekst_tresc;
	private JTextArea poleTresc;
	private JTextArea poleGrupa, poleGrupaTask;
	private JButton przyciskWyslij;
	private JTextArea poleIDKolokwium;
	private JLabel tekstGrupa, tekstGrupaTask;
	private JLabel tekstIDKol;
	private JTextArea poleTrescPytania;
	private JTextArea poleOdpowiedz;
	private JLabel tekstPytanie;
	private JTextArea odpowiedzNumer1;
	private JTextArea odpowiedzNumer2;
	private JTextArea odpowiedzNumer3;
	private JTextArea odpowiedzNumer4;
	private JLabel tekst_odp1;
	private JLabel tekst_odp2;
	private JLabel tekst_odp3;
	private JLabel tekst_odp4;
	private JButton przyciskNastepne;
	private JButton przyciskKoniec;
	private JList list;
	private JButton przyciskPobierzWszystkie;
	private JSpinner answerSpinner;
	private JSpinner contentSpinner;
	private JLabel answerLabel;
	private JLabel select1Label;
	private JLabel select2Label;
	private JButton selectAnsButton;
	private JButton selectButton;
	
	private JLabel gradeLabel;
	private JSpinner gradeSelect;
	private JButton sendGrade;

	private User currentUser;
	private DBConnector db;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TeacherWindow window = new TeacherWindow((new User(0,"Testowy", "User", "Student", null, "Majca")));
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

	public TeacherWindow(User logged) {
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
		
		//----- górny panel, info o użytkowniku
		
		infoPanel = new JPanel();
		infoPanel.setBackground(SystemColor.control);
		infoPanel.setBounds(0, 0, 477, 39);
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		
		JLabel Uzytkownik = new JLabel("User: "+currentUser.toString());
		Uzytkownik.setBounds(10, 9, 200, 14);
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
		przyciskWylogowania.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		przyciskWylogowania.setForeground(SystemColor.desktop);
		przyciskWylogowania.setBounds(358, 7, 109, 19);
		infoPanel.add(przyciskWylogowania);
		przyciskWylogowania.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			
			}
        });
        
		//------ stworzenie poszczególnych wyglšdów kazdej zakładki
		
        kartaDodajZadanie = new JPanel();
        kartaDodajZadanie.setBackground(SystemColor.control);
        kartaDodajZadanie.setBounds(0, 36, 550, 398);
        kartaDodajZadanie.setLayout(null);
        
        kartaPodgladPrac = new JPanel();
        kartaPodgladPrac.setBackground(SystemColor.control);
        kartaPodgladPrac.setBounds(0, 36, 550, 398);
        kartaPodgladPrac.setLayout(null);
        
        kartaKolos = new JPanel();
        kartaKolos.setBackground(SystemColor.control);
        kartaKolos.setBounds(0, 36, 550, 398);
        kartaKolos.setLayout(null);
        
        kartaKontakt = new JPanel();
        kartaKontakt.setBackground(SystemColor.control);
        kartaKontakt.setBounds(0, 36, 550, 398);
        kartaKontakt.setLayout(null);
        
        
        //--------------- stworzenie zakładek i dodanie do nich kart
        
        tabbedPane.setBounds(0, 36, 478, 354);
        frame.getContentPane().add(tabbedPane);
        tabbedPane.setBorder(null);
        tabbedPane.setBackground(SystemColor.control);
        tabbedPane.addTab("Dodaj Zadanie", kartaDodajZadanie);
        tabbedPane.addTab("Dodaj Kolokwium", kartaKolos);
        tabbedPane.addTab("Podglšd Prac", kartaPodgladPrac);
        tabbedPane.addTab("Kontakt ze studentem", kartaKontakt);
        
        
        
        //------------ komponenty do 1 zakładki
        
        poleTrescZadania = new JTextArea();
        poleTrescZadania.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        poleTrescZadania.setBounds(10, 35, 453, 200);
        poleTrescZadania.setBorder(new LineBorder(new Color(0, 0, 0)));
        poleTrescZadania.setColumns(10);
        kartaDodajZadanie.add(poleTrescZadania);
        
        tekstGrupaTask = new JLabel("Grupa:");
        tekstGrupaTask.setBounds(180, 250, 86, 20);
        kartaDodajZadanie.add(tekstGrupaTask);
        
        poleGrupaTask = new JTextArea();
        poleGrupaTask.setBounds(230, 250, 50, 20);
        poleGrupaTask.setBorder(new LineBorder(new Color(0, 0, 0)));
        poleGrupaTask.setColumns(10);
        kartaDodajZadanie.add(poleGrupaTask);
 
        tekstTresc = new JLabel("Tresc zadania");
        tekstTresc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tekstTresc.setHorizontalAlignment(SwingConstants.CENTER);
        tekstTresc.setBounds(135, 7, 196, 24);
        kartaDodajZadanie.add(tekstTresc);
        
        przyciskWyslijZadanie = new JButton("Wyslij zadanie");
        przyciskWyslijZadanie.setBounds(177, 283, 126, 32);
        kartaDodajZadanie.add(przyciskWyslijZadanie);
        przyciskWyslijZadanie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Task task = new Task(poleTrescZadania.getText(), currentUser.UID, poleGrupaTask.getText());
				LoginWindow.getClient().sendTask(task);
				JOptionPane.showMessageDialog(frame, "Zadanie zostalo wyslane do studentow.","Dodano zadanie", JOptionPane.INFORMATION_MESSAGE);
				poleTrescZadania.setText("");
				poleGrupaTask.setText("");
			}
		});
       
        
        //---------- komponenty do 2 zakładki
        
        tekstIDKol = new JLabel("ID_Kolokwium:");
        tekstIDKol.setBounds(10, 7, 100, 14);
        kartaKolos.add(tekstIDKol);
        
        tekstGrupa = new JLabel("Grupa:");
        tekstGrupa.setBounds(110, 7, 86, 14);
        kartaKolos.add(tekstGrupa);
        
        tekstOdp = new JLabel("Poprawna odp:");
        tekstOdp.setBounds(210, 7, 100, 14);
        kartaKolos.add(tekstOdp);
        
        poleIDKolokwium = new JTextArea();
        poleIDKolokwium.setBounds(10, 23, 86, 20);
        poleIDKolokwium.setBorder(new LineBorder(new Color(0, 0, 0)));
        poleIDKolokwium.setColumns(10);
        kartaKolos.add(poleIDKolokwium);
        
        poleGrupa = new JTextArea();
        poleGrupa.setBounds(110, 23, 86, 20);
        poleGrupa.setBorder(new LineBorder(new Color(0, 0, 0)));
        poleGrupa.setColumns(10);
        kartaKolos.add(poleGrupa);
        
        poleOdpowiedz = new JTextArea();
        poleOdpowiedz.setBounds(210, 23, 86, 20);
        poleOdpowiedz.setBorder(new LineBorder(new Color(0, 0, 0)));
        poleOdpowiedz.setColumns(10);
        kartaKolos.add(poleOdpowiedz);
        
        poleTrescPytania = new JTextArea();
        poleTrescPytania.setBounds(10, 66, 453, 44);
        poleTrescPytania.setBorder(new LineBorder(new Color(0, 0, 0)));
        poleTrescPytania.setColumns(10);
        kartaKolos.add(poleTrescPytania);
     
        
        tekstPytanie = new JLabel("Tre\u015B\u0107 Pytania");
        tekstPytanie.setBounds(10, 48, 86, 14);
        kartaKolos.add(tekstPytanie);
        
        odpowiedzNumer1 = new JTextArea();
        odpowiedzNumer1.setBounds(10, 130, 453, 20);
        odpowiedzNumer1.setBorder(new LineBorder(new Color(0, 0, 0)));
        odpowiedzNumer1.setColumns(10);
        kartaKolos.add(odpowiedzNumer1);
      
        
        odpowiedzNumer2 = new JTextArea();
        odpowiedzNumer2.setBounds(10, 170, 453, 20);
        odpowiedzNumer2.setBorder(new LineBorder(new Color(0, 0, 0)));
        odpowiedzNumer2.setColumns(10);
        kartaKolos.add(odpowiedzNumer2);
        
        
        odpowiedzNumer3 = new JTextArea();
        odpowiedzNumer3.setBounds(10, 210, 453, 20);
        odpowiedzNumer3.setBorder(new LineBorder(new Color(0, 0, 0)));
        odpowiedzNumer3.setColumns(10);
        kartaKolos.add(odpowiedzNumer3);
     
        
        odpowiedzNumer4 = new JTextArea();
        odpowiedzNumer4.setBounds(10, 250, 453, 20);
        odpowiedzNumer4.setBorder(new LineBorder(new Color(0, 0, 0)));
        odpowiedzNumer4.setColumns(10);
        kartaKolos.add(odpowiedzNumer4);

        
        tekst_odp1 = new JLabel("Odpowied\u017A 1");
        tekst_odp1.setBounds(10, 115, 86, 14);
        kartaKolos.add(tekst_odp1);
        
        tekst_odp2 = new JLabel("Odpowied\u017A 2");
        tekst_odp2.setBounds(10, 155, 86, 14);
        kartaKolos.add(tekst_odp2);
        
        tekst_odp3 = new JLabel("Odpowied\u017A 3");
        tekst_odp3.setBounds(10, 195, 110, 14);
        kartaKolos.add(tekst_odp3);
        
        tekst_odp4 = new JLabel("Odpowied\u017A 4");
        tekst_odp4.setBounds(10, 235, 133, 14);
        kartaKolos.add(tekst_odp4);
        
        przyciskNastepne = new JButton("Nast\u0119pne");
        przyciskNastepne.setBounds(200, 281, 110, 34);
        kartaKolos.add(przyciskNastepne);
        przyciskNastepne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Kolokwium newKolokwium = new Kolokwium(poleIDKolokwium.getText(), poleGrupa.getText(), poleTrescPytania.getText(),
						odpowiedzNumer1.getText(),odpowiedzNumer2.getText(),odpowiedzNumer3.getText(),odpowiedzNumer4.getText(), poleOdpowiedz.getText());
				
				LoginWindow.getClient().addTest(newKolokwium);

				JOptionPane.showMessageDialog(frame, "Pytanie do kolokwium zostalo zapisanie w bazie","Dodano pytanie", JOptionPane.INFORMATION_MESSAGE);
				poleTrescPytania.setText("");
				odpowiedzNumer1.setText("");
				odpowiedzNumer2.setText("");
				odpowiedzNumer3.setText("");
				odpowiedzNumer4.setText("");
			}
		});
        
       
        
        //------ komponenty do 3 zakładki
        
        /*list = new JList();
        list.setBounds(10, 11, 453, 262);
        kartaPodgladPrac.add(list);*/

        List<String> task_id = db.getTeacherTasks(String.valueOf(currentUser.UID));
        List<TaskAnswer> answers = new ArrayList<TaskAnswer>();
        if(task_id == null) {
        	task_id = new ArrayList<String>();
        	task_id.add(new String("Brak"));
        }
//        else
//        {
//        	answers = db.getTaskAnswers(task_id.get(0));
//        }
        	
		SpinnerListModel model = new SpinnerListModel(task_id);
		answerSpinner = new JSpinner(model);
		answerSpinner.setBounds(200, 11, 180, 26); // top row - right
		
		SpinnerListModel contentModel = new SpinnerListModel();
		contentSpinner = new JSpinner(contentModel);
		contentSpinner.setBounds(200, 40, 180, 26); // bottom row- right
		kartaPodgladPrac.add(contentSpinner);
		
		kartaPodgladPrac.add(answerSpinner);
		
		
		select2Label = new JLabel("Nr. Ucznia:");
		select2Label.setVerticalAlignment(JLabel.TOP);
		select2Label.setHorizontalAlignment(JLabel.LEFT);
		select2Label.setMinimumSize(new Dimension(0,0));
		select2Label.setBounds(0, 40, 100, 26);
		kartaPodgladPrac.add(select2Label);
		
		selectAnsButton = new JButton("Select");
		selectAnsButton.setBounds(100, 40, 100, 26);		// bottom row - left
		selectAnsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String answer_value = contentSpinner.getValue().toString();
				String selectedValue = answerSpinner.getValue().toString();
				List<TaskAnswer> temp_copy = new ArrayList<TaskAnswer>();
				if(!selectedValue.equals("empty"))
				{									
					for(TaskAnswer ts : DBConnector.getTaskAnswers(selectedValue))
					{
						temp_copy.add(ts);	
					}
				}
				if(!temp_copy.isEmpty()) {
					for(TaskAnswer ans : temp_copy) {
						if(ans.sender_id == Integer.parseInt(answer_value))
						{
							answerLabel.setText(ans.getContent());
						}
					}
				}
				
				
				
			}
		});
		kartaPodgladPrac.add(selectAnsButton);
		
		
		select1Label = new JLabel("Nr.Zadania:");
		select1Label.setVerticalAlignment(JLabel.TOP);
		select1Label.setHorizontalAlignment(JLabel.LEFT);
		select1Label.setMinimumSize(new Dimension(0,0));
		select1Label.setBounds(0, 11, 100, 26);
		kartaPodgladPrac.add(select1Label);
		
		selectButton = new JButton("Select");
		selectButton.setBounds(100, 11, 100, 26); // top row - right
		
		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedValue = answerSpinner.getValue().toString();
				List<TaskAnswer> temp_copy = new ArrayList<TaskAnswer>();
				if(!selectedValue.equals("Brak"))
				{									
					for(TaskAnswer ts : DBConnector.getTaskAnswers(selectedValue))
					{
						temp_copy.add(ts);						
					}

				}
				if(!temp_copy.isEmpty())
				{
					contentModel.setList(temp_copy);
//					answerLabel.setText(temp_copy.get(0).getContent());
				}
					
			}
		});
		kartaPodgladPrac.add(selectButton);
	
		answerLabel = new JLabel("Wybierz zadanie do oceny");
		answerLabel.setVerticalAlignment(JLabel.TOP);
		answerLabel.setHorizontalAlignment(JLabel.LEFT);
		answerLabel.setMinimumSize(new Dimension(0,0));
		answerLabel.setBounds(200, 80, 200, 26);
		kartaPodgladPrac.add(answerLabel);
        // Ocena zadan
		
		
//		private JLabel gradeLabel;
//		private JSpinner gradeSelect;
//		private JButton sendGrade;
		
		// Label
		gradeLabel = new JLabel("Ocena:");
		gradeLabel.setVerticalAlignment(JLabel.TOP);
		gradeLabel.setHorizontalAlignment(JLabel.LEFT);
		gradeLabel.setMinimumSize(new Dimension(0,0));
		gradeLabel.setBounds(100, 250, 50, 26);
		kartaPodgladPrac.add(gradeLabel);
		
		
		// Spiner
		String[] grades = {"2.0", "2.5", "3.0", "3.5", "4.0", "4.5", "5.0"};
		
		SpinnerModel gradeModel = new SpinnerListModel(grades);
		gradeSelect = new JSpinner(gradeModel);
		gradeSelect.setBounds(150, 250, 180, 26); // bottom row- right
		kartaPodgladPrac.add(gradeSelect);
		
		
		// Button
		sendGrade = new JButton("Send grade");
		sendGrade.setBounds(330, 250, 100, 26); // top row - right
		
		sendGrade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String gradeValue = gradeSelect.getValue().toString();
				String answer_value = contentSpinner.getValue().toString();
				String selectedValue = answerSpinner.getValue().toString();
				if(DBConnector.setTaskGrade(selectedValue, answer_value, gradeValue))
				{
					answerLabel.setText("Wyslano ocene");
				}
				
			}
			
		});
		
		kartaPodgladPrac.add(sendGrade);
		
		
       
      //------ komponenty do 4 zakładki
        
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

        tekstAlbum = new JLabel("Numer Albumu");
        tekstAlbum.setBounds(10, 8, 91, 14);
        kartaKontakt.add(tekstAlbum);
        
        tekst_tresc = new JLabel("Tre\u015B\u0107:");
        tekst_tresc.setBounds(10, 100, 46, 14);
        kartaKontakt.add(tekst_tresc);
        
        poleTresc = new JTextArea();
        poleTresc.setBorder(new LineBorder(new Color(0, 0, 0)));
        poleTresc.setBounds(10, 117, 453, 160);
     
        kartaKontakt.add(poleTresc);
        poleTresc.setColumns(10);
        
        przyciskWyslij = new JButton("Wy\u015Blij");
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
