package general;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FenetreTableau extends JFrame {

	public FenetreTableau(double tab[][]){

	    this.setLocationRelativeTo(null);

	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    this.setTitle("Methodes");

	    this.setSize(300, 240);


	    //Les données du tableau
	    Object[][] data = new Object[10][5];
	    for(int i = 0; i < 10; i++) {
			data[i][0] = tab[0][i];
			data[i][1] = tab[1][i];
			data[i][2] = tab[2][i];
			data[i][3] = tab[3][i];
			data[i][4] = tab[4][i];
		}
	    

	    //Les titres des colonnes

	    String  title[] = {"Fractional", "FirstFit", "BestFit", "FirstFitDecreasing", "BestFitDecreasing"};

	    JTable tableau = new JTable(data, title);

	    //Nous ajoutons notre tableau à notre contentPane dans un scroll

	    //Sinon les titres des colonnes ne s'afficheront pas !

	    this.getContentPane().add(new JScrollPane(tableau));
	    this.setVisible(true);

	  }

}
