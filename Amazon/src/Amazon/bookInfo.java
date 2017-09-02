package Amazon;

import java.util.ArrayList;
import java.util.List;

public class bookInfo {
	//"id"	            "catName"	            "title"	            "descr"	"rating"	"nRatings"	"positionInList"	"size"	"pageNum"	"paperPrice"	"kindlePrice"	"hardcPrice"	"r1"	"r2"	"r3"	"r4"	"r5"	"daysSinseRelease"
	//"-an-Edition-"	"humor & entertainment"	"Аристократка ()"	"цитя."	 4.8	4	1	"592 pages"	19	NA	0.64	NA	0	0	0	25	75	756.041666666667
	//"-UCH-MORE---"	"humor & entertainment"	"★★Stick War:★★"	"★★ld units, mine gold, learn the way of the Sword, Spear, Archer, Mage, and even Giant. Destroy the enemy statue, and capture all Territories.In a world called Inamorta, you're surrounded by discriminate nations devoted to their individual nations technology and struggle for dominance. Each nation has developed its own unique way to defend and attack. Proud of their unique craft they have become obsessed to the point of worship, turning weapons to religion. Each believe that their way of life is the only way, and are dedicated to teaching their polices to all other nations through what there leaders claim as divine intervention, or as you will know it... war.The others are known as: \"Archidons\" the way of the archer, \"Swordwrath\" the way of the sword, \"Magikill\" the way of the mage, and \"Speartons\" the way of the Spear.You are the leader of the nation called \"Order\", your way is of peace and knowledge, your people do not worship their weapons as gods. This makes you a mark for infiltration by the surrounding nations. Your only chance to defend is to attack first, and obtain the technology's from each nation along the way.Now, with all of this you will need guidance, a wizard perhaps. Luckily for you, I am here to help you. I am one of the beta testers for this app and I know everything there is to know. I have been playing it for months now and I was one of the first to ever download the beta version of it. What that means is that I know a lot about it before anybody else did.Download this guide and you will know what I am talking about."	4.8	5	11	"922 pages"	59	NA	2.64	NA	0	0	0	20	80	247

	 public String n_title;
	 
	 public String n_catName;
	 public String descr;
	 public double rating;
	 public double nRatings;

	 public String lang;
	 public double langConf;
	 public String langReliable;

	 
	 public int title_non_words = -1;
	 public List<String> non_words = new ArrayList<String>();
	 public int title_amount_words = -1; 
	 public String n_title_meaninfull ="";
	 public int title_amount_meaninful_words = -1; 


	 
	 

}
