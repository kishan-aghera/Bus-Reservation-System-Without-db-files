package Project;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TicketInfo extends Frame implements MouseListener,ActionListener
{
    String mobile;
    int c1[]=new int[10];
    String c2[]=new String[10];
    String c3[]=new String[10];
    String c4[]=new String[10];
    String c5[]=new String[10];
    String c6[]=new String[10];
    String c7[]=new String[10];
    String c8[]=new String[10];
    int k=0,j=10,r=100;
    TicketInfo(String mobile0)
    {
        mobile=mobile0;
        setVisible(true);
        setSize(1000,1000);
        setTitle("Ticket Information");
        setLayout(new FlowLayout());
        String url="jdbc:mysql://localhost:3306/busdata?serverTimezone=UTC";//path
        String uname="root";//username
        String pass="";//password
        try
        {
            Class.forName("com.mysql.jdbc.Driver");//load driver
            Connection con=DriverManager.getConnection(url,uname,pass);//establish connection
            Statement st=con.createStatement();//create statement
            int viewid=101;
            String truncateticket="truncate table ticket";
            st.executeUpdate(truncateticket);
            while(viewid<106)
            {
                String displayticketview="select * from bus"+viewid+" where Mobile='"+mobile+"'";
                ResultSet rs=st.executeQuery(displayticketview);
                setLayout(null);
                while(rs.next())
                {
                    c1[k]=rs.getInt(1);
                    c2[k]=rs.getString(2);
                    c3[k]=rs.getString(3);
                    c4[k]=rs.getString(4);
                    c5[k]=rs.getString(5);
                    c6[k]=rs.getString(6);
                    c7[k]=rs.getString(7);
                    c8[k]=rs.getString(8);
                    k++;
                    int i=1;
                    Label l1=new Label("Bus ID ");
                    add(l1);
                    l1.setBounds(10, 50, 100, 30);
                    Label l7=new Label("Origin ");
                    add(l7);
                    l7.setBounds(110, 50, 100, 30);
                    Label l8=new Label("Destination ");
                    add(l8);
                    l8.setBounds(210, 50, 100, 30);
                    Label l2=new Label("Seat no ");
                    add(l2);
                    l2.setBounds(310, 50, 100, 30);
                    Label l3=new Label("Name ");
                    add(l3);
                    l3.setBounds(410, 50, 100, 30);
                    Label l4=new Label("Age ");
                    add(l4);
                    l4.setBounds(510, 50, 100, 30);
                    Label l5=new Label("Mobile ");
                    add(l5);
                    l5.setBounds(610,50,100,30);
                    Label l6=new Label("Email ID ");
                    add(l6);
                    l6.setBounds(710,50,100,30);
                    while(i<9)
                    {
                        String c=(String)rs.getString(i);
                        System.out.println("tickets"+c);
                        Label l=new Label(c);
                        add(l);
                        l.setBounds(j,r,100, 30);
                        j+=100;
                        i++;
                    }
                    j = 10;
                    r+=50;
                }
                viewid++;
            }
            int g=k;
            for(k=0;k<g;k++)
            {
                String insertview="insert into ticket values("+c1[k]+",'"+c2[k]+"','"+c3[k]+"',"+c4[k]+",'"+c5[k]+"','"+c6[k]+"','"+c7[k]+"','"+c8[k]+"')";
                System.out.println(insertview);
                st.executeUpdate(insertview);
            }
        }
        catch(HeadlessException | ClassNotFoundException | SQLException z)
        {
            z.printStackTrace();
        }
        Button b1=new Button("Go to home page");
        add(b1);
        b1.setBounds(260, r, 100, 30);
        b1.addActionListener(this);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
    }
    @Override
    public void mouseClicked(MouseEvent e) {    }
    @Override
    public void mousePressed(MouseEvent e) {    }
    @Override
    public void mouseReleased(MouseEvent e) {   }
    @Override
    public void mouseEntered(MouseEvent e) {    }
    @Override
    public void mouseExited(MouseEvent e) {     }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        setVisible(false); // Hide current frame
        HomePage t=new HomePage();           
        t.setVisible(true);        
    }    
}