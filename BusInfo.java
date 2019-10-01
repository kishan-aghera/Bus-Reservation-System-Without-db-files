package Project;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class BusInfo extends Frame implements MouseListener,ActionListener
{
    int j = 100;
    TextField tf=new TextField(6);
    String busNo;
    String or,des;
    BusInfo(String origin,String destination)//Here we have to run query
    {
        or=origin;
        des=destination;
        setVisible(true);
        setSize(500,500);
        setLayout(null);
        String url="jdbc:mysql://localhost:3306/busdata?serverTimezone=UTC";//path
        String uname="root";//username
        String pass="";//password
        try
        {
            Class.forName("com.mysql.jdbc.Driver");//load driver
            try (Connection con = DriverManager.getConnection(url,uname,pass); //establish connection
                Statement st = con.createStatement() //create statement
            ){
                String querytofind="create view availablebus as select * from businfo where busid in(select busid from busstops where '"+origin+"' in(stop1,stop2,stop3,stop4,stop5) and '"+destination+"' in(stop1,stop2,stop3,stop4,stop5))";
                //query to create view
                st.executeUpdate(querytofind);//execution of query
                String querytodisplay="select * from availablebus";//query to display
                ResultSet rs=st.executeQuery(querytodisplay);//execution of query
                Label trainid = new Label("Bus ID");
                add(trainid);
                trainid.setBounds(10,50,100,30);
                Label trainname = new Label("Bus Name");
                add(trainname);
                trainname.setBounds(160,50,100,30);
                while(rs.next())//rs.next() return false if next row is not available and true o/w
                {
                    String Data1=(String)(rs.getInt(1)+" ");
                    String Data2=(String)(rs.getString(2));
                    //System.out.println(Data);//print data
                    Label l1=new Label(Data1);
                    add(l1);
                    l1.setBounds(10,j,100,30);
                    Label l2=new Label(Data2);
                    add(l2);
                    l2.setBounds(160,j,100,30);
                    j+=50;
                }           
                String querytoreset="drop view availablebus";
                st.executeUpdate(querytoreset);
                //close statement
                //close connection
            } //create statement
            //create statement
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e1)
        {
            e1.printStackTrace();
        }
        Label l1=new Label("Enter Bus Number:");
        add(l1);
        l1.setBounds(10, j, 150, 30);
        //tf.minimumSize(50);
        add(tf);
        tf.setBounds(160,j,100,30);
        setTitle("Bus Info");
        Button btnNext =new Button("Next");
        add(btnNext);
        btnNext.setBounds(60,j+50,100,30);
        btnNext.addActionListener(this);
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
    public void mouseReleased(MouseEvent e) {    }
    @Override
    public void mouseEntered(MouseEvent e) {    }
    @Override
    public void mouseExited(MouseEvent e) {     }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        busNo=(String)tf.getText();
        setVisible(false); // Hide current frame
        CustomerInfo t=new CustomerInfo(busNo,or,des);           
        t.setVisible(true);        
    }   
}