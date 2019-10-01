package Project;
import java.awt.*;
import java.awt.event.*;
public class HomePage extends Frame implements MouseListener,ActionListener
{
    public String origin,destination,mobile;
    Choice c2=new Choice();
    Choice c1=new Choice();
    HomePage()
    {
        setVisible(true);
        setSize(500,500);
        setTitle("Bus Reservation System");
        setLayout(null);
        Label l1=new Label("Origin");
        add(l1);
        l1.setBounds(10, 50, 100, 30);
        c1.addItem("Somnath");
        c1.addItem("Keshod");
        c1.addItem("Junagadh");
        c1.addItem("Gondal");
        c1.addItem("Rajkot");
        c1.addItem("Ahmedabad");
        c1.addItem("Gandhinagar");
        c1.addItem("Vadnagar");
        c1.addItem("Nadiad");
        c1.addItem("Vadodara");
        c1.addItem("Surat");
        c1.addItem("Valsad");
        add(c1);
        c1.setBounds(120, 50, 100, 30);
        Label l2=new Label("Destination");
        add(l2);
        l2.setBounds(10, 100, 100, 30);
        c2.addItem("Somnath");
        c2.addItem("Keshod");
        c2.addItem("Junagadh");
        c2.addItem("Gondal");
        c2.addItem("Rajkot");
        c2.addItem("Ahmedabad");
        c2.addItem("Gandhinagar");
        c2.addItem("Vadnagar");
        c2.addItem("Nadiad");
        c2.addItem("Vadodara");
        c2.addItem("Surat");
        c2.addItem("Valsad");
        add(c2);
        c2.setBounds(120, 100, 100, 30);
        Button b= new Button("Search");
        add(b);
        b.setBounds(60, 150, 100, 30);
        b.addActionListener(this);
        Label l4=new Label("Mobile no.");
        add(l4);
        l4.setBounds(10, 250, 100, 30);
        TextField tf4=new TextField(10);
        add(tf4);
        tf4.setBounds(120,250,100,30);
        Button b1= new Button("Get your Ticket(s)");
        add(b1);
        b1.setBounds(60, 300, 100, 30);
        b1.addActionListener((ActionEvent ae) -> {
            mobile=(String)tf4.getText();
            TicketInfo s=new TicketInfo(mobile);
            setVisible(false);
            s.setVisible(true);
        });
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) 
    {
        HomePage h=new HomePage();
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
            destination=c2.getSelectedItem();
            origin=c1.getSelectedItem();
            setVisible(false); // Hide current frame
            BusInfo t=new BusInfo(origin,destination);           
            t.setVisible(true);
    }
}