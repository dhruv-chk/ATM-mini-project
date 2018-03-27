import java.util.*;
import java.io.*;
interface atmMachine
{
	public void Withdrawal(int amt);
	public void BalanceEnq();
	public void MiniStatemnet();
	public int user(long ac);
	public void compress();
}
class Machine implements atmMachine
{
	static int t=-1,pos;//used as index for the arrays
	static int[] a=new int[5];//stores the ministatements
	static long[] acc=new long[5];
	static int[] pin=new int[5];
	static int[] bl=new int[5];
	static String[] s=new String[5];
	static String s1="";
	public void Withdrawal(int amt)
	{
		if(amt<=bl[t])
		{
			System.out.println("\n"+amt+"is Withdrawn");
			bl[pos]=bl[pos]-amt;
			System.out.println("\nLeft Balance: "+bl[pos]);
		}
		else
			System.out.println("\nInsufficient Balance");
		a[t]=amt;
	}
	public void BalanceEnq()
	{
		System.out.println("\nAvailable Balance"+bl[pos]);
	}
	public void MiniStatemnet()
	{
		
		System.out.println("\nYour MiniStatemnet");
		for(int i=0;i<=t;i++)
		{
			System.out.print("\nWithdrawal: "+a[i]);
			System.out.println();
		}
	}
	public int user(long ac)
	{
		for(int i=0;i<5;i++)
		{
			if(acc[i]==ac)
				return i;
			
		}
		return -1;
	}
	public void compress()
	{
		for(int l1=0;l1<5;l1++)
		{
			s1=s1+acc[l1]+" "+pin[l1]+" "+bl[l1];
			s1=s1+"\n";
		}
	}
	public static void main(String[] args)throws IOException
	{
		int i,p,max=0,t1=0,i1=0,i2=0,i3=0,k=0;
		Scanner x=new Scanner(System.in);
		Machine ob=new Machine();
		BufferedReader is=new BufferedReader(new FileReader("User.txt"));
		String m=null;
		while((m=is.readLine())!=null)
		{
			s[k]=m;
			k=k+1;
		}
		/*for(int k3=0;k3<5;k3++)
		{
			System.out.println(s[k3]);
		}*/
		System.out.println("Wait till the process is complete..");
		for(int k2=0;k2<5;k2++)
		{
		StringTokenizer st=new StringTokenizer(s[k2]," ");
			while(st.hasMoreTokens())
			{
				if(t1==0)
				{
				acc[i1]=Long.parseLong(st.nextToken());
				i1=i1+1;
				}
				else if(t1==1)
				{
					pin[i2]=Integer.parseInt(st.nextToken());
					i2=i2+1;
				}
				else if(t1==2)
				{
					bl[i3]=Integer.parseInt(st.nextToken());
					i3=i3+1;
				}
				t1=t1+1;
			}
		}
		FileOutputStream file=new FileOutputStream("User.txt");
		while(true)
		{
			System.out.println("Enter the account number");
			long ac=x.nextLong();
			pos=ob.user(ac);
			if(pos==-1)
			System.out.println("Invalid user");
		else{
		
		System.out.println("\nEnter the pin");
		p=x.nextInt();
		t=-1;
		if(p==pin[pos])
		{
		for(i=0;i<5;i++)
		{
		System.out.println("\n1.Withdrawal\n2.Balance Enquiry\n3.Mini Statement\n4.Change Pin\n");
		int ch=x.nextInt();
		switch(ch)
		{
			case 1:	if(max>=20000)
						System.out.println("\nYou have exceeded the limit of withdrawal for the day");
					else
					{
						System.out.println("\nEnter amount to withdraw");
						int amt=x.nextInt();
						t=t+1;
						max=max+amt;
						ob.Withdrawal(amt);
						ob.compress();
						byte b[]=s1.getBytes();
						file.write(b);
						file.close();
						
					}
					
					break;
			case 2: ob.BalanceEnq();
					break;
			case 3:	ob.MiniStatemnet();
					break;
			case 4:	System.out.println("\nEnter old pin");
					p=x.nextInt();
					if(p==pin[pos])
					{
					System.out.println("\nEnter new pin");
					int p2=x.nextInt();
					System.out.println("\nConfirm your pin");
					int p1=x.nextInt();
					if(p1==p2)
						pin[pos]=p1;
					else
					System.out.println("\nPin did not match");
					}
					else
						System.out.println("\nWrong pin enterred");
					ob.compress();
					byte b[]=s1.getBytes();
					file.write(b);
					file.close();
					break;
		}
		System.out.println("\nPress n to cancel transaction or y to continue");
		char cr=(x.next()).charAt(0);
		if(cr=='n')
			break;
		}
		if(i==5)
			System.out.println("\nYou have exceeded the transaction limit for the day");
		}
		else
			System.out.println("\nWrong Pin enterred");
		System.out.println("\nPress 0 to exit or 1 to continue");
		int ce=x.nextInt();
		if(ce==0)
			break;
		for(int l2=0;l2<5;l2++)
		{
			a[l2]=0;
		}
		}}
	}
}
