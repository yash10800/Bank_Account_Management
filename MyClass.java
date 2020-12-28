import java.io.*;
import java.lang.*;
import java.util.Scanner;
import java.util.Random;
class Transaction
{
    int userid,bal,tr1,amount;
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    File f = new File("Balance.txt");
    Transaction(int userid)throws IOException
    {
        this.userid = userid;
        bal = searchId(userid);
    }
    int getBal()
    {
        return bal;
    }
    int searchId(int userid)throws IOException
    {
        String s="";
        Scanner scan= new Scanner(f);
        while(scan.hasNextLine())
        {
            s=scan.nextLine();
            if(s.startsWith(userid+"#"))
            {
                return Integer.parseInt(s.split("[#]")[1]);
            }
        }
        scan.close();
        return -1;
    }
    void updatefile(int userid,int bal)throws IOException
    {
        Scanner scan = new Scanner(f);
        String s="";
        String temp="";
        while(scan.hasNextLine())
        {
            s= scan.nextLine();
            if(s.startsWith(userid+"#"))
                continue;
            temp= temp+s+"\n";
        }
        scan.close();
        FileWriter f1= new FileWriter(f);
        f1.write(temp);
        f1.append(userid+"#"+bal+"\n");
        f1.close();
    }
    void loan()throws IOException
    {
        int ch;
        Show.clear();
        do
        {
            System.out.println("\n\t\t___________");
            System.out.println("\n\t\t1. Car Loan");
            System.out.println("\t\t2. Housing Loan");
            System.out.println("\t\t3. Education Loan");
            System.out.println("\t\t4. Personal Loan");
            System.out.println("\t\t5. Exit");
            System.out.print("\n\t\tENTER YOUR CHOICE : ");
            ch = Integer.parseInt(scan.readLine());
            switch(ch)
            {
                case 1:
                    carloan();
                    break;
                case 2:
                    houseloan();
                    break;
                case 3:
                    eduloan();
                    break;
                case 4:
                    personalloan();
                    break;
                case 5:
                    Show.clear();
                    System.out.println("\n\n\t\t\t\t***** EXITING From Loan Eligibility....... *****");
                    break;
                default:
                    Show.clear();
                    System.out.println("\n\n\t\t\t\t***** Wrong Choice ...Enter Again *****");

            }
        }while(ch!=5);
    }
    void carloan()throws IOException
    {
        Show.clear();
        System.out.println("\n\n\n\t\t\t\t***** Car Loan *****");
        System.out.print("\n\t\tEnter the Total Amount of Car Loan You Required : ");
        try
        {
        	amount = Integer.parseInt(scan.readLine());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        if(getBal()*24 >= amount*2)
        {
            Show.clear();
            System.out.print("\n\n\t\t***** You are Eligible For Rs. " + amount + " Car Loan *****");
        }
        else
        {
            Show.clear();
            System.out.print("\n\n\t\t***** You are not Eligible For Rs. " + amount + " Car Loan *****");
        }
    }
    void houseloan()throws IOException
    {
        Show.clear();
        System.out.println("\n\n\n\t\t\t\t***** Housing Loan *****");
        System.out.print("\n\t\tEnter the Total Amount of Housing Loan You Required : ");
        try
        {
        	amount = Integer.parseInt(scan.readLine());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        if(getBal()*120 >= amount*2)
        {
            Show.clear();
            System.out.print("\n\n\t\t***** You are Eligible For Rs. " + amount + " Housing Loan *****");
        }
        else
        {
            Show.clear();
            System.out.print("\n\n\t\t***** You are not Eligible For Rs. " + amount + " Housing Loan *****");
        }
    }
    void eduloan()throws IOException
    {
        Show.clear();
        System.out.println("\n\n\n\t\t\t\t***** Education Loan *****");
        System.out.print("\n\t\tEnter the Total Amount of Education Loan You Required : ");
        try
        {
        	amount = Integer.parseInt(scan.readLine());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        if(getBal()*60 >= amount)
        {
            Show.clear();
            System.out.print("\n\n\t\t***** You are Eligible For Rs. " + amount + " Education Loan *****");
        }
        else
        {
            Show.clear();
            System.out.print("\n\n\t\t***** You are not Eligible For Rs. " + amount + " Education Loan *****");
        }
    }
    void personalloan()throws IOException
    {
        Show.clear();
        System.out.println("\n\n\n\t\t\t\t***** Personal Loan *****");
        System.out.print("\n\t\tEnter the Total Amount of Personal Loan You Required : ");
        try
        {
        	amount = Integer.parseInt(scan.readLine());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        if(getBal()*12 >= amount*2)
        {
            Show.clear();
            System.out.print("\n\n\t\t***** You are Eligible For Rs. " + amount + " Personal Loan *****");
        }
        else
        {
            Show.clear();
            System.out.print("\n\n\t\t***** You are not Eligible For Rs. " + amount + " Personal Loan *****");
        }
    }
    void deposit()throws IOException
    {
        Show.clear();
        System.out.println("\n\n\n\t\t\t\t***** Max. 50000 Ruppees at a Time *****");
        System.out.print("\n\n\n\t\tEnter Amount to be Deposited : ");
        tr1 = Integer.parseInt(scan.readLine());
        if(tr1>50000)
        {
            Show.clear();
            System.out.println("\n\n\t\t\t\t***** MAXIMUN LIMIT EXCEEDED......TRANSACTION FAILED *****");
        }
        else if(tr1<1)
        {
            Show.clear();
            System.out.println("\n\n\t\t\t\t***** InValid Amount....Transaction Failed *****");
        }
        else
        {
            bal +=tr1;
            updatefile(userid,bal);
            Show.clear();
            System.out.println("\n\n\t\t\t\t***** TRANSACTION SUCCESSFUL *****");
        }
    }
    void withdraw()throws IOException
    {
        Show.clear();
        System.out.println("\n\n\n\t\t\t\t***** Max. 20000 Ruppees at a Time *****");
        System.out.print("\n\n\t\tEnter Amount to be WithDrawn : ");
        tr1 = Integer.parseInt(scan.readLine());
        if(tr1>20000)
        {
            Show.clear();
            System.out.println("\n\n\t\t\t\t***** MAXIMUN LIMIT EXCEEDED......TRANSACTION FAILED *****\n\n\t\tCURRENT BALANCE:"+bal);
        }
        else if(tr1<1)
        {
            Show.clear();
            System.out.println("\n\n\t\t\t\t***** InValid Amount....Transaction Failed *****");
        }
        else if(bal<tr1)
        {
            Show.clear();
            System.out.println("\n\n\t\t\t\t***** NOT ENOUGH FUNDS....TRANSACTION FAILED *****\n\n\t\tCURRENT BALANCE:"+bal);
        }
        else
        {
            bal -=tr1;
            updatefile(userid,bal);
            Show.clear();
            System.out.println("\n\n\t\t\t\t***** TRANSACTION SUCCESSFUL *****");
        }
    }
    void transfer()throws IOException
    {
        int temp = 0;
        Show.clear();
        System.out.print("\n\n\n\t\tENTER USERID OF BENEFICIARY : ");
        int userid1=0;
        try
        {
            userid1 = Integer.parseInt(scan.readLine());
            Show.clear();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        if(userid1 == userid)
            System.out.println("\n\n\t\t\t\t***** CANNOT TRANSFER TO SELF *****");
        else if((temp=searchId(userid1))==-1)
            System.out.println("\n\n\t\t\t\t***** USERID NOT FOUND....TRANSACTION FAILED *****");
        else
        {
            System.out.println("\n\n\n\t\t\t\t***** Max. 10000 Ruppees Allowed *****");
            System.out.print("\n\n\t\tEnter the Amount to be Transfered : ");
            tr1 = Integer.parseInt(scan.readLine());
            Show.clear();
            if(tr1>10000)
                System.out.println("\n\n\t\t\t\t***** MAXIMUM LIMIT EXCEEDED......TRANSACTION FAILED *****\n\n\t\tCURRENT BALANCE:"+this.bal);
            else if(tr1<1)
                System.out.println("\n\n\t\t\t\t***** InValid Amount....Transaction Failed *****");
            else if(bal<tr1)
                System.out.println("\n\n\t\t\t\t***** NOT ENOUGH FUNDS....TRANSACTION FAILED *****\n\n\t\tCURRENT BALANCE:"+this.bal);
            else
            {
            	int bal1 = searchId(userid1);
            	//System.out.println(bal1);
                bal -=tr1;
                bal1 += tr1;
                updatefile(userid,bal);
                updatefile(userid1,bal1);
                System.out.println("\n\n\t\t\t\t***** TRANSACTION SUCCESSFUL *****");
            }
        }
    }
}
class User
{
    private String name, password;
    private int userid;
    private long ph_no;
    public final BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    File f = new File("Accounts.txt");
    File f1 = new File("Balance.txt");

    String getName()
    {
        return name;
    }
    void setName(String name)
    {
        this.name = name;
    }
    String getPassword()
    {
        return password;
    }
    void setPassword(String password)
    {
        this.password = password;
    }
    int getUserid()
    {
        return userid;
    }
    void setUserid(int userid)
    {
        this.userid = userid;
    }
    long getPh_no()
    {
        return ph_no;
    }
    void setPh_no(long ph_no)
    {
        this.ph_no = ph_no;
    }

    public boolean auth(int userid, String password) throws IOException
    {

        Scanner in= new Scanner(f);
        String[] data;
        do
        {
            if(!in.hasNextLine())
                break;
            data = in.nextLine().trim().split("[#]");
            setUserid(Integer.parseInt(data[0]));
            setPassword(data[1]);
            setName(data[2]);
            setPh_no(Long.parseLong(data[3]));
        }while(getUserid()!=userid || !getPassword().equals(password));
        in.close();
        if(getUserid()==userid && getPassword().equals(password))
            return true;
        return false;
    }

    public boolean login() throws IOException
    {
        Show.clear();
        int error=0,userid=0;
        System.out.println("\n\n\n\t\t\t\t***** Login *****");
        do
        {
            System.out.print("\n\n\t\tEnter your userid: ");
            error = 0;
            try
            {
                userid = Integer.parseInt(scan.readLine());
            }
            catch(NumberFormatException e)
            {
                error = 1;
                System.out.println("\n\t\t\t\t***** InValid UserId *****");
            }
        }while(error == 1);

        Scanner alag = new Scanner(System.in);
        System.out.print("\t\tEnter your Password : ");
        String password = scan.readLine();
        if(auth(userid, password))
        {
            Show.clear();
            System.out.println("\n\t\t\t\t***** Login Successfull *****");
            return true;
        }
        else
        {
            Show.clear();
            System.out.print("\n\n\t\t\t\t***** Login Failed! *****");
            System.out.print("\n\n\t\t\t\tLogin Again?[y/n]");
            char choice = alag.nextLine().charAt(0);
            if(choice == 'y')
                return login();
            else
            {
                Show.clear();
                System.out.print("\n\n\t\t\t\t***** SignUp Instead?[y/n] *****");
                choice = alag.nextLine().charAt(0);
                if(choice == 'y')
                    return signUp();
                else
                {
                    Show.clear();
                    return false;
                }
            }
        }
    }

    public boolean signUp() throws IOException
    {
        String s = "",str[] = {"",""};
        Show.clear();
        Random r = new Random();
        if(f.length() != 0)
        {
            Scanner in = new Scanner(f);
             s = "";
            while (in.hasNextLine())
                s += in.nextLine() + "\n";
            in.close();
            str = s.trim().split("\n");
        }
        int userid,error;
        System.out.print("\n\n\n\t\t\t\t***** SignUp *****");
        System.out.print("\n\n\n\t\tEnter your Name : ");
        setName(scan.readLine());
        do
        {
            System.out.print("\t\tEnter your PhoneNo : ");
            error = 1;
            try
            {
                long temp = Long.parseLong(scan.readLine());
                setPh_no(temp);
            }
            catch(NumberFormatException e)
            {
                System.out.print("\n\t\t\t\t***** InValid number *****");
                error = 0;
            }
            if(!(getPh_no()>=6351000000L && getPh_no()<=9999999999L))
            {
                System.out.print("\n\t\t\t\t***** InValid number *****");
                error = 0;
            }
        }while(error == 0);
        do
        {
        	int temp = 0;
            userid = 100001 + r.nextInt(9999);
            for(int i=0; i<str.length; i++) {
                try {
                    if (Integer.parseInt((str[i].split("[#]"))[0]) == userid) {
                        userid = 0;
                        break;
                    }
                } catch (NumberFormatException nfe) {
                    continue;
                }
                setUserid(userid);
            }
        }while(userid == 0);
        System.out.println("\n\t\t***** Generated UserId is : " + userid + " *****");
        System.out.print("\n\t\tEnter your Password : ");
        setPassword(scan.readLine());
        FileWriter writer = new FileWriter(f);
        writer.write(s);
        writer.append(userid+"#"+getPassword()+"#"+getName()+"#"+getPh_no()+"\n");
        writer.close();
        Show.clear();
        System.out.print("\n\n\t\t\t\t***** SIGNUP SUCCESSFUL *****");
        Scanner ins= new Scanner(f1);
        s = "";
        while(ins.hasNextLine())
            s+=ins.nextLine()+"\n";
        ins.close();
        FileWriter writer1 = new FileWriter(f1);
        writer1.write(s);
        writer1.append(userid+"#"+0+"\n");
        writer1.close();
        return false;
    }
    boolean change()throws IOException
    {
        String oldpas="";
        Show.clear();
        System.out.print("\n\n\t\tEnter your old password : ");
        oldpas=scan.readLine();
        if(!oldpas.equals(getPassword()))
        {
            Show.clear();
            System.out.println("\n\n\t\t***** You have Entered a Wrong Password.....Task Failed!! *****");
            return false;
        }
        System.out.print("\t\tEnter New Password : ");
        setPassword(scan.readLine());
        Scanner s= new Scanner(f);
        String ch2="";
        String temp="";
        while(s.hasNextLine())
        {
            temp = s.nextLine();
            if(temp.startsWith(getUserid()+"#"))
                continue;
            ch2 = ch2 + temp + "\n";
        }
        s.close();
        FileWriter name = new FileWriter(f);
        name.write(ch2);
        name.append(userid+"#"+getPassword()+"#"+getName()+"#"+getPh_no()+"\n");
        name.close();
        Show.clear();
        System.out.println("\n\n\t\t***** PASSWORD CHANGED SUCCESSFULLY *****");
        return true;
    }
}
class banking extends User
{
    public final BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

    void display(int bal) throws IOException
    {
        Show.clear();
        System.out.println("\n\n\n\t\tUSER ID: "+getUserid());
        System.out.println("\t\tACCOUNT HOLDER NAME: "+getName());
        System.out.println("\t\tPHONE NUMBER: "+ getPh_no());
        System.out.println("\t\tAVAILABLE BALANCE: "+ bal);
        System.out.print("\n\tPress Enter to Continue");
        scan.readLine();
        Show.clear();
    }
    void mainmenu1()throws IOException
    {
        int ch;
        Transaction tr = new Transaction(getUserid());
        do
        {
            System.out.println("\n\t\t___________");
            System.out.println("\n\t\t1. DEPOSIT");
            System.out.println("\t\t2. WITHDRAW");
            System.out.println("\t\t3. TRANSFER");
            System.out.println("\t\t4. Loan Eligibility");
            System.out.println("\t\t5. CHANGE YOUR PASSWORD");
            System.out.println("\t\t6. DISPLAY DETAILS AND BALANCE");
            System.out.println("\t\t7. LOGOUT");
            System.out.print("\n\t\tENTER YOUR CHOICE : ");
            ch = Integer.parseInt(scan.readLine());
            switch(ch)
            {
                case 1:
                    tr.deposit();
                    break;
                case 2:
                    tr.withdraw();
                    break;
                case 3:
                    tr.transfer();
                    break;
            	case 4:
            		tr.loan();
            		break;
                case 5:
                    change();
                    break;
                case 6:
                    display(tr.getBal());
                    break;
                case 7:
                    Show.clear();
                    System.out.println("\n\n\t\tLOGGING OUT.......");
                    break;
                default:
                    Show.clear();
                    System.out.println("\n\n\t\t\t\t***** Wrong Choice ...Enter Again *****");

            }
        }while(ch!=7);
        tr.updatefile(getUserid(),tr.getBal());
    }
}
class Show
{
    public static void clear()
    {
        try
        {
            new ProcessBuilder("Terminal","/c","clear").inheritIO().start().waitFor();
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
        System.out.println("");
      System.out.println("\t\t\t\t\t\t********************");
      System.out.println("\t\t\t\t\t\t*                  *");
      System.out.println("\t\t\t\t\t\t*      BANKING     *");
      System.out.println("\t\t\t\t\t\t*                  *");
      System.out.println("\t\t\t\t\t\t********************");
    }
}
class MyClass
{
    public static void main(String args[])throws IOException
    {
        //Scanner scan1 = new Scanner(System.in);
        BufferedReader scan1 = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        banking ob1 = new banking();
        Show.clear();
        if(!(new File("Accounts.txt")).exists())
            (new File("Accounts.txt")).createNewFile();

        if(!(new File("Balance.txt")).exists())
            (new File("Balance.txt")).createNewFile();
        do
        {
            System.out.println("\n\n\n\t\t1: NEW USER REGISTRATION");
            System.out.println("\t\t2: LOGIN");
            System.out.println("\t\t3: CHANGE PASSWORD");
            System.out.println("\t\t4: EXIT");
            System.out.print("\t\tEnter your choice : ");
            choice = Integer.parseInt(scan1.readLine());

            switch(choice)
            {
                case 1:
                    ob1.signUp();
                    break;
                case 2:
                    if(ob1.login())
                        ob1.mainmenu1();
                    break;
                case 3:
                    if(ob1.login())
                        ob1.change();
                    break;
                case 4:
                    System.out.println("\n\n\t\tEXITING...");
                    break;
                default :
                    System.out.println("\n\n\t\t\t\t***** Wrong Choice ...Enter Again *****");
            }
        }while(choice != 4);
    }
}