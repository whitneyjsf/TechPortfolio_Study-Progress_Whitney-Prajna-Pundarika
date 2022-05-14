package finalproject;

import java.io.IOException;
import java.util.*;
import java.lang.*;

public class finalProject {
	
	static ArrayList<String>SemuaData = new ArrayList<>();
	static ArrayList<String>gapulang = new ArrayList<>();
	static ArrayList<String>pakat = new ArrayList<>();
	static ArrayList<String>Waktu = new ArrayList<>();
	static ArrayList<String>Harga= new ArrayList<>();  
	static ArrayList<String>Gmail= new ArrayList<>();
	static ArrayList<String>Password= new ArrayList<>();
	static ArrayList<String>Username= new ArrayList<>();
	
	public finalProject() {
	
	Scanner ss =new Scanner(System.in);
		
	int chs = 0;
		
		do {
			System.out.println("                                ");
			System.out.println("================================");
			System.out.println("|      GAMER WORLD iCAFE       |");
			System.out.println("================================");
			System.out.println("| 1. Register Account          |");
			System.out.println("| 2. Login                     |");
			System.out.println("| 3. Exit                      |");
			System.out.println("================================");
			System.out.print("= Choose >> ");
			chs = ss.nextInt();
			
			switch(chs) {
				case 1:
					register();
				break;
			
				case 2:
					Scanner sc = new Scanner (System.in);
					if (Username.isEmpty() || Password.isEmpty()) {
						System.out.println("Go to Register first");
						System.out.println("Press Enter to Continue");
						sc.nextLine();
				continue;
				}	
					Login();
				break;
				
				case 3:
					System.out.println("\nByeeee <3 We hope to see you soon again!");
					System.out.println("\n\nGAMER WORLD iCAFE @ 2020");
					System.exit(0);
				break;
		
			}
		}while(chs !=3 );
	}
	
	public static void register() {
		Scanner sc = new Scanner(System.in);
		Scanner abc = new Scanner(System.in);
		Scanner zigizaga =new Scanner(System.in);
		
		int umur = 0;
		String username;
		String password= null;
		String email;
		
        boolean condition = true;
        while (condition) {

        System.out.print("\nUsername: ");
        username = sc.nextLine();
        if (username.length() < 3) {
            condition = true;
            System.out.print("Input username Up to 3 words");
            username = sc.nextLine();
        }else {
            condition = false;
        }
          Username.add(username);
        }
        boolean condition1 = true;
        while (condition1) {
            System.out.print("\nPassword: ");
            password = sc.nextLine();
            char ch = password.charAt(0);
            
            if (password.length()<5) {
                condition1 = true;
                System.out.print("Password must up to 5 characters");
                password=sc.nextLine();
            } else if(Character.isLowerCase(ch)) {
                condition1 = true; 
                System.out.print("First Characters must be in Uppercase");
                password=sc.nextLine();
            } else {
                condition1 =false;
            }
             Password.add(password);
        }
        boolean condition2 = true;
        while (condition2) {
            System.out.print("\nEmail: ");
            email = sc.nextLine();
            if (email.contains("@") && email.endsWith(".com")) {
                condition2 = false;
            } else if (!email.contains("@") && !email.endsWith(".com")){
                condition2 = true;
                System.out.println("Must contains '@' and ends with '.com' ");
            }
            Gmail.add(email);
	   }
        System.out.println("Successfully Registered!");
	}
	
	public static void Login() {
	
	Scanner sg = new Scanner(System.in);
	Scanner sk = new Scanner(System.in);
	
	System.out.println("                      ");
	System.out.println("======================");
	System.out.println("        LOGIN         ");
	System.out.println("======================");
	if(Username.isEmpty() && Password.isEmpty()) {
		System.out.println("!UPSS Kamu belum mendaftar");
		System.out.println("Press Enter to Continue");
		sg.nextLine();
		new finalProject();
	}
	
	boolean condition = true;
	while (condition) {
		System.out.print("Username :");
		String login = sg.nextLine();
		for (int i = 0; i < Username.size(); i++) {
			if (login.equals(Username.get(i))) {
				condition = false;
				
			} else {
				System.out.print("Wrong Username");
				String login1 = sg.nextLine();
				condition = true;
				
			}
		}		
	}
	
	boolean condition1 = true;
	while (condition1) {
		System.out.print("PASSWORD :");
		String password = sg.nextLine();
		for (int i = 0; i < Password.size(); i++) {
			if (password.equals(Password.get(i))) {
				condition1 = false;
				System.out.print("Login Success");
				String sukses = sg.nextLine();
			} else {
				condition1= true;
				System.out.print("Wrong Password");
				String sukses= sg.nextLine();
			}
		}
		
	}
	
	Scanner scan = new Scanner (System.in);
	boolean condition2 = true;
	while (condition2) {
		System.out.println("==============================");
		System.out.println("= 1. Choose Packet & Payment =");
		System.out.println("= 2. Cancel Billing          =");
		System.out.println("= 3. List of Billing         =");
		System.out.println("= 4. Log out                 =");
		System.out.println("==============================");
		System.out.print("Choose >>");
		int choose = scan.nextInt();
		switch (choose) {
		case 1:
			menuSatu();
			break;

		case 2:
			menuDua();
			break;
		case 3:
			menuTiga();
			break;
			
		case 4:
			if(choose==4) {
				System.out.println("Log out successful");
				scan.nextLine();
				
				new finalProject();
			}
		}
	     
	    }
	
	
	}

	public static void menuSatu() {
		
		Scanner sf = new Scanner(System.in);
			
			int paket = 0;
			
			do{
				System.out.println("\n=============================");
				System.out.println("            PACKET            ");
				System.out.println("=============================");
				System.out.println(" 1. Gapulang Packet");
				System.out.println(" 2. Paket Packet");
				System.out.println(" 3. Kere Hore Packet");
				System.out.print("Please choose your packet : ");
				paket = sf.nextInt();
				
				switch(paket) {
				case 1:
					paketSatu();
			      	break;
			      	
				case 2:
					paketDua();
					break;
				
				case 3:
					paketTiga();
					break;
				default:
				}
			}while(paket<0||paket>4);
			
			System.out.println("Press enter to next step");
			String enter = sf.nextLine();
		}
	
	public static void menuDua() {
	
	Scanner so = new Scanner(System.in);
	
	String nun = "";
	if(Waktu.isEmpty()) {
		System.out.println("!UPSS Kamu belum mendaftar");
		System.out.println("Press Enter to Continue");
		nun = so.nextLine();
	}
	screen();
	for (int i = 0 , j = 1; i < Waktu.size(); i++, j++) {
		
		System.out.println("| "+ j + "  | " + Username.get(i) + "            | " + Waktu.get(i) + "          | " + Harga.get(i) + "     |");
	}
	int j = Waktu.size();
		System.out.print("Pilih paket untuk dibatalkan[1-" + j + "]: ");
		int delete = so.nextInt();
		int deleted = delete - 1;
	for (int i = 0; i < Waktu.size(); i++) {
		if (deleted == i ) {
		
		System.out.println("Berhasil delete");
	}
		Waktu.remove(Waktu.get(i));
	}
	
}

	private static void screen() {
		System.out.println("+====+======================+=======================+===============+");
        System.out.println("| NO |        Name          |        Packet         |     Price     |");
        System.out.println("+====+======================+=======================+===============+");
	}
	
	public static void menuTiga() {
		Scanner nw = new Scanner(System.in);
		
		if(Waktu.isEmpty()) {
			System.out.println("There's no packages");
			nw.nextLine();
			
		}
			for(int i=0,j=1;i<Waktu.size();i++,j++) {
				 System.out.println("| " + j + " | " + Username.get(i)+ " | " + Waktu.get(i) + " | "+ Harga.get(i) + " |" );
			}
		}
	
	public static void paketSatu() {
		Scanner xyz = new Scanner(System.in);

		String paket = "GAPULANG";
		String harga ="Rp.100.000";
		String PilihPaket = "";
		
		
		System.out.println("\nGAPULANG PACKET");
		System.out.println("Time : 24 hours");
		System.out.println("Price : " + harga);
		
	
 	boolean condition4=true;
		while(condition4) {
			System.out.print("Buy ? [Y/N] :");
			PilihPaket = xyz.nextLine();
             if(PilihPaket.equals(("Y")) ) {
				condition4 = false;
				Harga.add(harga);
				Waktu.add(paket);
             }else if (PilihPaket.equals("N")) {
				condition4 = false;
			}
			else {
				condition4=true;
				System.out.println("Wrong Character");
				PilihPaket=xyz.nextLine();

			}
		}

	}
	
	public static void paketDua() {
		
		Scanner zz = new Scanner(System.in);

		String isi  = "PAKET PACKET";
		String harga1 = "Rp.50.000";
		String pilihpaket1="";
		
		System.out.println("\nPAKET PACKET");
		System.out.println("Time : 4 hours");
		System.out.println("Price : " + harga1);
		
		
		
 	boolean condition=true;
		while(condition) {
			System.out.print("Buy ? [Y/N] :");
			pilihpaket1 = zz.nextLine();
			if(pilihpaket1.equalsIgnoreCase(("Y"))) {
				condition = false;
				Waktu.add(isi);
				Harga.add(harga1);
			}else if (pilihpaket1.equalsIgnoreCase("N")) {
				condition = false;
			}	
			else {
				condition=true;
				System.out.println("Wrong Character");
				pilihpaket1=zz.nextLine();
				
			}
						
			
			
		}
		
	}
	
	public static void paketTiga() {
		
		Scanner sb = new Scanner(System.in);
		String harga2 = "Rp.10.000";
		String kereHore = "KERE HORE ";
		String pilipaket2;
		System.out.println("\nKERE HORE PACKET");
		System.out.println("Time : 2 hours");
		System.out.println("Price : " + harga2);
		
		boolean condition=true;
		while(condition) {
			System.out.print("Buy ? [Y/N] :");
			pilipaket2 = sb.nextLine();
			if(pilipaket2.equalsIgnoreCase(("Y")) ) { 
				condition = false;
				Harga.add(harga2);
				Waktu.add(kereHore);
			}else if (pilipaket2.equalsIgnoreCase("N")) {
				condition = false;
			}
			else {
				condition=true;
				System.out.println("Wrong Character");
				pilipaket2=sb.nextLine();
					
			}
		}
		
	}
	
	public static void main(String[] args) {
		new finalProject();

	}

}
