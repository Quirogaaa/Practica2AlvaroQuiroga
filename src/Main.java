import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    JPanel panelIzquierdo;
    JLabel imagen;
    JPanel panelTarjetas;
    CardLayout tarjetas;
    JButton siguiente;
    JPanel[] panel = new JPanel[4];
    JEditorPane p1 = new JEditorPane();


    public Main(){
        setLayout(new BorderLayout());


        initPanelTarjetas();
        initPanelIzq();
        initBoton();
        initPantalla();
    }

    private void initBoton(){
        siguiente = new JButton("Siguiente");
        siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarjetas.next(panelTarjetas);
            }
        });
        panelIzquierdo.add(siguiente);

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
        p1.setText(
                "<h1><b>Formulario</h1></b>"





        );
        p1.setBackground(Color.decode("#edede9"));
        panel[0].add(p1);




    }
    private void initPanelIzq(){
        panelIzquierdo = new JPanel();
        panelIzquierdo.setPreferredSize(new Dimension(270, 800));
        panelIzquierdo.setBackground(Color.decode("#d6ccc2"));

        imagen = new JLabel();
        imagen.setIcon(new ImageIcon("src/IMG/logo.png"));
        imagen.setPreferredSize(new Dimension(250,200));
        panelIzquierdo.add(imagen);
        add(panelIzquierdo,BorderLayout.WEST);
    }
    private void initPantalla() {

        setTitle("Formulario"); //Título del JFrame
        setBounds(500,100,900,800); //Dimensiones del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar proceso al cerrar ventana
        setVisible(true); //Mostrar JFrame
        getContentPane().setBackground(Color.decode("#d5bdaf"));
    }

    public static void main(String[] args) {
        new Main();
    }
}