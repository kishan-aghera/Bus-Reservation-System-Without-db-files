package Project;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
public class CustomerInfo extends Frame implements MouseListener,ActionListener
{
    String busid;
    public String name,email_id,age,mobile_no,gender,origin,destination;
    TextField tf1=new TextField(20);
    TextField tf2=new TextField(20);
    TextField tf3=new TextField(20);
    TextField tf4=new TextField(20);
    Choice c1=new Choice();
    CustomerInfo(String x,String ori,String dest)
    {
        origin=ori;
        destination=dest;
        busid=x;
        System.out.println(busid);
        setVisible(true);
        setSize(1000,1000);
        setTitle("Customer Information");
        setLayout(null);
        Label l1=new Label("Name");
        add(l1);
        l1.setBounds(10, 50, 100, 30);
        add(tf1);
        tf1.setBounds(200,50,100,30);
        Label l2=new Label("Email-ID");
        add(l2);
        l2.setBounds(10, 100, 100, 30);
        add(tf2);
        tf2.setBounds(200,100,100,30);
        Label l3=new Label("Mobile no");
        add(l3);
        l3.setBounds(10, 150, 100, 30);
        add(tf3);
        tf3.setBounds(200,150,100,30);
        Label l4=new Label("Age");
        add(l4);
        l4.setBounds(10, 200, 100, 30);
        add(tf4);
        tf4.setBounds(200,200,100,30);
        Label l5=new Label("Gender");
        add(l5);
        l5.setBounds(10, 250, 100, 30);
        c1.add("Male");
        c1.add("Female");
        c1.add("Others");
        add(c1);
        c1.setBounds(200, 250, 100, 30);
        Button b1=new Button("Submit");
        add(b1);
        b1.setBounds(10, 300, 100, 30);
        name = (String)tf1.getText();
        email_id = (String)tf2.getText();
        mobile_no = (String)tf3.getText();
        gender = c1.getSelectedItem();
        age = (String)tf4.getText();
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
        int seatno=0;
        name = (String)tf1.getText();
        System.out.println("name");
        System.out.println(name.isEmpty());
        email_id = (String)tf2.getText();
        System.out.println("email id : "+ email_id);
        mobile_no = (String)tf3.getText();
        System.out.println("mobile no : "+ mobile_no);
        gender = c1.getSelectedItem();
        System.out.println("Gender:" + gender);
        age = (String)tf4.getText();
        System.out.println("age : " + age);
        String url="jdbc:mysql://localhost:3306/busdata?serverTimezone=UTC";//path
        String uname="root";//username
        String pass="";//password                
        try
        {
            Class.forName("com.mysql.jdbc.Driver");//load driver
            try (Connection con = DriverManager.getConnection(url,uname,pass); //establish connection
            Statement st = con.createStatement() //create statement
            ) {
                String fetchseatno="select seatno from bus"+busid+"";
                //System.out.println(fetchseatno);
                ResultSet rs = st.executeQuery(fetchseatno);
                while(rs.next())
                { 
                    seatno=rs.getInt(1);
                    System.out.println(seatno);
                }
                seatno++;
                String query="insert into bus"+busid+" values("+busid+",'"+origin+"','"+destination+"',"+seatno+",'"+name+"','"+age+"','"+mobile_no+"','"+email_id+"')";
                System.out.println(query);
                st.executeUpdate(query);
                //close statement
                //close connection
                //execution of query
            }//create statement
        }
        catch(  ClassNotFoundException | SQLException e1)
        {
            e1.printStackTrace();
        }
        setVisible(false); // Hide current frame
        TicketInfoCurrent t=new TicketInfoCurrent(busid,seatno);           
        t.setVisible(true);
    }  
}