package Project;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TicketInfoCurrent extends Frame implements MouseListener,ActionListener
{
    String busid;
    int seatno;
    int c1,c2,c4;
    String c3,c5,c6;
    int j=10,r=100;
    TicketInfoCurrent(String busId, int seatNo)
    {
        busid=busId;
        seatno=seatNo;
        setVisible(true);
        setSize(650,650);
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
            String truncateticket="truncate table ticket";
            st.executeUpdate(truncateticket);
            String displayticketview="select * from bus"+busid+" where busid="+busid+" and seatno="+seatno+"";
            setLayout(null);
            int i=1;
            Label l1=new Label("Bus ID ");
            add(l1);
            l1.setBounds(10, 50, 100, 30);
            Label l2=new Label("Origin ");
            add(l2);
            l2.setBounds(110, 50, 100, 30);
            Label l3=new Label("Destination ");
            add(l3);
            l3.setBounds(210, 50, 100, 30);
            Label l4=new Label("Seat No ");
            add(l4);
            l4.setBounds(310, 50, 100, 30);
            Label l5=new Label("Name ");
            add(l5);
            l5.setBounds(410, 50, 100, 30);
            Label l6=new Label("Age ");
            add(l6);
            l6.setBounds(510, 50, 100, 30);
            Label l7=new Label("Mobile ");
            add(l7);
            l7.setBounds(610,50,100,30);
            Label l8=new Label("Email ID ");
            add(l8);
            l8.setBounds(710,50,100,30);
            ResultSet rs=st.executeQuery(displayticketview);
            rs.next();
            while(i<9)
            {
                System.out.println("before result");
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
    public void mouseReleased(MouseEvent e) {       }
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