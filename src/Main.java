import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends JFrame{
    JPanel panelIzquierdo,panelInferior;
    JPanel panelTarjetas;
    CardLayout tarjetas;
    JPanel[] panel = new JPanel[4];
    JEditorPane p1 = new JEditorPane();
    JTextField correoo,nombre;
    JPasswordField contraseña;
    JLabel labelNombre,labelCorreo,labelContraseña;

    public Main(){
        setLayout(new BorderLayout());


        initPanelTarjetas();
        initPanelIzq();
        initpanelInferior();
        initPaneluno();
        initPaneldos();
        initBoton();
        fill();
        initPantalla();
    }

    private void initBoton(){
        JButton siguiente,anterior;
        anterior = new JButton("anterior");
        anterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!panelTarjetas.getComponent(0).isShowing()){
                    tarjetas.previous(panelTarjetas);
                }
            }
        });
        panelInferior.add(anterior);
        siguiente = new JButton("siguiente");
        siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panelTarjetas.getComponent(1).isShowing()){
                    if(!comprobarCorreo(correoo.getText())){
                        return;
                    }
                    if(!comprobarContraseña(contraseña.getText())){
                        return;
                    }
                }
                if(!panelTarjetas.getComponent(3).isShowing()){
                    tarjetas.next(panelTarjetas);
                }

            }
        });
        panelInferior.add(siguiente);

    }

    private void initPaneluno(){
        p1.setContentType("text/html");
        p1.setText(
                "<h1><b>Formulario</h1></b>"+
                        "<font size=8>"+
                        "<b>Bienvenido al formulario oficial de Alvaro Quiroga<b>"+"<br>"+
                        "<br>"+
                        "<b>En este formulario se le pedira el datos personales y pais <b>"+
                        "<b>Despues se le mostara el<br> resultado de este mismo formulario y se le descargara en una ruta especificada<b>"+
                        "<br>"+"<br>"+
                        "<b>Todos sus datos estan protegidos y puede confiar en nosotros <b>"+
                        "</font>"
        );
        p1.setBackground(Color.decode("#edede9"));
        panel[0].add(p1);
    }
    private void initPaneldos(){


        panel[1].setLayout(new GridBagLayout());

        GridBagConstraints[] grid = new GridBagConstraints[6];
        int y = 0;
        int x = 0;
        for (int i = 0; i < grid.length; i++) {
            grid[i] = new GridBagConstraints();
            grid[i].anchor = GridBagConstraints.WEST;
            if(x<2) {

                grid[i].gridx = x;
                grid[i].gridy = y;

            }
            if(x==2) {
                y++; x=0;
                grid[i].gridx = x;
                grid[i].gridy = y;
            }
            x++;
        }

        labelNombre = new JLabel("Nombre ");
        labelCorreo = new JLabel("Correo ");
        labelContraseña = new JLabel("Contraseña ");
        labelNombre.setFont(new Font("MONOSPACED",Font.PLAIN,50));
        labelCorreo.setFont(new Font("MONOSPACED",Font.PLAIN,50));
        labelContraseña.setFont(new Font("MONOSPACED",Font.PLAIN,50));


        nombre = new JTextField();
        correoo = new JTextField();
        contraseña = new JPasswordField();
        nombre.setPreferredSize(new Dimension(300,45));
        correoo.setPreferredSize(new Dimension(600,45));
        contraseña.setPreferredSize(new Dimension(300,40));
        nombre.setFont(new Font("MONOSPACED",Font.PLAIN,40));
        correoo.setFont(new Font("MONOSPACED",Font.PLAIN,40));
        contraseña.setFont(new Font("MONOSPACED",Font.PLAIN,40));


        panel[1].add(labelNombre,grid[0]);
        panel[1].add(nombre,grid[1]);
        panel[1].add(labelCorreo,grid[2]);
        panel[1].add(correoo,grid[3]);
        panel[1].add(labelContraseña,grid[4]);
        panel[1].add(contraseña,grid[5]);
    }

    private void fill(){
        correoo.setText("pepe@gmail.com");
        contraseña.setText("asasA12/sas");
        nombre.setText("pepe");
    }

    private boolean comprobarCorreo(String x){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(x);

        if (mather.find()) {
            labelCorreo.setForeground(Color.black);
            return true;
        } else {
            labelCorreo.setForeground(Color.RED);
            return false;
        }


    }

    private boolean comprobarContraseña(String x){
        String com = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$";
        Pattern patron = Pattern.compile(com);
        if (patron.matcher(x).matches()) {
            labelContraseña.setForeground(Color.black);
            return true;

        }
        else {
            labelContraseña.setForeground(Color.red);
            return false;
        }


    }

    private void initPanelTarjetas(){
        tarjetas = new CardLayout();
        panelTarjetas = new JPanel();
        panelTarjetas.setLayout(tarjetas);

        for (int i = 0; i < panel.length; i++) {
            panel[i] = new JPanel();
            panelTarjetas.add(panel[i]);
        }
        add(panelTarjetas,BorderLayout.CENTER);

    }
    private void initPanelIzq(){
        JLabel imagen;
        panelIzquierdo = new JPanel();
        panelIzquierdo.setPreferredSize(new Dimension(270, 800));
        panelIzquierdo.setBackground(Color.decode("#d6ccc2"));

        imagen = new JLabel();
        imagen.setIcon(new ImageIcon("src/IMG/logo.png"));
        imagen.setPreferredSize(new Dimension(250,200));
        panelIzquierdo.add(imagen);
        add(panelIzquierdo,BorderLayout.WEST);
    }
    private void initpanelInferior(){


        panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT,5,0));
        panelInferior.setPreferredSize(new Dimension(1500, 30));
        add(panelInferior,BorderLayout.SOUTH);
    }
    private void initPantalla() {

        setTitle("Formulario"); //Título del JFrame
        setBounds(250,100,1600,800); //Dimensiones del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar proceso al cerrar ventana
        setVisible(true); //Mostrar JFrame
        getContentPane().setBackground(Color.decode("#d5bdaf"));
    }

    public static void main(String[] args) {
        new Main();
    }
}