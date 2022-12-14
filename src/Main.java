import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends JFrame {
    //Declaracion de las variables
    JPanel panelIzquierdo, panelInferior;
    JPanel panelTarjetas;
    CardLayout tarjetas;
    JPanel[] panel = new JPanel[5];
    JEditorPane p1 = new JEditorPane();
    JTextField correoo, nombre;
    JPasswordField contraseña;
    JLabel labelNombre, labelCorreo, labelContraseña;
    JComboBox combo1;

    JComboBox combo2;
    JEditorPane editor = new JEditorPane();
    File JFC;
    JFileChooser ruta;
    JLabel label2;
    GridBagConstraints[] gridPanelCinco = new GridBagConstraints[3];

    public Main() throws IOException {
        setLayout(new BorderLayout());
        //Inicializamos los metodos

        initPanelTarjetas();
        panelesColores();
        initPanelIzq();
        initpanelInferior();
        initPaneluno();
        initPaneldos();
        initPaneltres();
        initPanelCuatro();
        initPanelCinco();
        initBoton();
        fill();
        initPantalla();
    }

    //Los botones con sus getComponent respectivo para saber en que tarjeta estas y hacer diferentes acciones
    private void initBoton() {
        JButton siguiente, anterior;
        anterior = new JButton("anterior");
        anterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Para que no puedas ir para atras si estas en la primera
                if (!panelTarjetas.getComponent(0).isShowing()) {
                    tarjetas.previous(panelTarjetas);
                }
            }
        });
        panelInferior.add(anterior);
        siguiente = new JButton("siguiente");
        siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelTarjetas.getComponent(1).isShowing()) {
                    if (!comprobarCorreo(correoo.getText())) {
                        return;
                    }
                    if (!comprobarContraseña(contraseña.getText())) {
                        return;
                    }
                }
                //no puedas ir a siguiente si estas en la ultima
                if (!panelTarjetas.getComponent(4).isShowing()) {
                    tarjetas.next(panelTarjetas);
                }
                if (panelTarjetas.getComponent(3).isShowing()) {
                    resultado();
                }
                if(panelTarjetas.getComponent(4).isShowing()){
                    labels();
                }

            }
        });
        panelInferior.add(siguiente);

    }

    private void initPaneluno() {
        p1.setContentType("text/html");
        p1.setText(
                "<h1><b>Formulario</h1></b>" +
                        "<font size=8>" +
                        "<b>Bienvenido al formulario oficial de Alvaro Quiroga<b>" + "<br>" +
                        "<br>" +
                        "<b>En este formulario se le pedira el datos personales y pais <b>" +
                        "<b>Despues se le mostara el<br> resultado de este mismo formulario y se le descargara en una ruta especificada<b>" +
                        "<br>" + "<br>" +
                        "<b>Todos sus datos estan protegidos y puede confiar en nosotros <b>" +
                        "</font>"
        );
        p1.setBackground(Color.decode("#edede9"));
        panel[0].add(p1);
    }

    private void initPaneldos() {


        panel[1].setLayout(new GridBagLayout());

        GridBagConstraints[] grid = new GridBagConstraints[6];
        int y = 0;
        int x = 0;
        for (int i = 0; i < grid.length; i++) {
            grid[i] = new GridBagConstraints();
            grid[i].anchor = GridBagConstraints.WEST;
            if (x < 2) {

                grid[i].gridx = x;
                grid[i].gridy = y;

            }
            if (x == 2) {
                y++;
                x = 0;
                grid[i].gridx = x;
                grid[i].gridy = y;
            }
            x++;
        }

        labelNombre = new JLabel("Nombre ");
        labelCorreo = new JLabel("Correo ");
        labelContraseña = new JLabel("Contraseña ");
        labelNombre.setFont(new Font("MONOSPACED", Font.PLAIN, 50));
        labelCorreo.setFont(new Font("MONOSPACED", Font.PLAIN, 50));
        labelContraseña.setFont(new Font("MONOSPACED", Font.PLAIN, 50));


        nombre = new JTextField();
        correoo = new JTextField();
        contraseña = new JPasswordField();
        nombre.setPreferredSize(new Dimension(300, 45));
        correoo.setPreferredSize(new Dimension(600, 45));
        contraseña.setPreferredSize(new Dimension(300, 40));
        nombre.setFont(new Font("MONOSPACED", Font.PLAIN, 40));
        correoo.setFont(new Font("MONOSPACED", Font.PLAIN, 40));
        contraseña.setFont(new Font("MONOSPACED", Font.PLAIN, 40));


        panel[1].add(labelNombre, grid[0]);
        panel[1].add(nombre, grid[1]);
        panel[1].add(labelCorreo, grid[2]);
        panel[1].add(correoo, grid[3]);
        panel[1].add(labelContraseña, grid[4]);
        panel[1].add(contraseña, grid[5]);
    }

    public void initPaneltres() throws IOException {
        panel[2].setLayout(new GridBagLayout());
        JLabel texto = new JLabel("Selecciona tu pais y provincia");
        texto.setFont(new Font("MONOSPACED", Font.PLAIN, 40));
        GridBagConstraints label = new GridBagConstraints();
        GridBagConstraints combo = new GridBagConstraints();
        GridBagConstraints comb2 = new GridBagConstraints();

        label.gridx = 0;
        label.gridy = 0;
        label.anchor = GridBagConstraints.WEST;
        combo.gridx = 0;
        combo.gridy = 1;
        combo.anchor = GridBagConstraints.WEST;
        comb2.gridx = 0;
        comb2.gridy = 2;
        comb2.anchor = GridBagConstraints.WEST;

        combo1 = new JComboBox();
        combo1.setFont(new Font("MONOSPACED", Font.PLAIN, 30));
        rellenaCombo1();
        combo1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                try {
                    rellenaCombo2((String) combo1.getSelectedItem());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        panel[2].add(texto, label);
        panel[2].add(combo1, combo);


        combo2 = new JComboBox();
        combo2.setFont(new Font("MONOSPACED", Font.PLAIN, 30));
        rellenaCombo2((String) combo1.getSelectedItem());

        panel[2].add(combo2, comb2);


    }

    //un rellenacombo para rellenar los combobox
    private void rellenaCombo1() {
        combo1.addItem("España");
        combo1.addItem("Estados Unidos");
    }

    private void rellenaCombo2(String seleccionEnCombo1) throws IOException {
        combo2.removeAllItems();
        File f = new File("src/TXT/España.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        if (seleccionEnCombo1.equals("España")) {
            String cadena;
            while ((cadena = br.readLine()) != null) {
                combo2.addItem(cadena);
                cadena = br.readLine();

            }
            br.close();
        } else if (seleccionEnCombo1.equals("Estados Unidos")) {
            String cadena;
            File f2 = new File("src/TXT/EstadosUnidos.txt");
            FileReader fr2 = new FileReader(f2);
            BufferedReader br2 = new BufferedReader(fr2);
            while ((cadena = br2.readLine()) != null) {
                combo2.addItem(cadena);
                cadena = br2.readLine();

            }

        }

    }

    private void initPanelCuatro() {
        panel[3].setLayout(new BorderLayout());
        JCheckBox checkBox = new JCheckBox("Descargar resultado...");
        JLabel label = new JLabel("Se ha guardado el archivo");
        label.setFont(new Font("MONOSPACED", Font.PLAIN, 50));
        checkBox.setRolloverEnabled(false);
        ruta = new JFileChooser();
        ruta.setApproveButtonText("Guardar");

        ruta.setFont(new Font("MONOSPACED", Font.PLAIN, 50));

        checkBox.setSize(50, 50);
        checkBox.setFont(new Font("MONOSPACED", Font.PLAIN, 50));
        checkBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ruta.setVisible(true);


            }
        });
        editor.setEditable(false);
        editor.setContentType("text/html");
        ruta.setVisible(false);
        panel[3].add(editor, BorderLayout.NORTH);
        panel[3].add(checkBox, BorderLayout.SOUTH);
        panel[3].add(ruta, BorderLayout.WEST);

        ruta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFC = ruta.getSelectedFile();
                    String PATH = JFC.getAbsolutePath();
                    PrintWriter printwriter = new PrintWriter(JFC);
                    printwriter.println("Nombre: " + nombre.getText());
                    printwriter.println("Correo: " + correoo.getText());
                    printwriter.println("Contraseña: " + contraseña.getText());
                    printwriter.println("Pais: " + combo1.getSelectedItem().toString());
                    printwriter.println("Provincia: " + combo2.getSelectedItem().toString());
                    printwriter.close();
                    ruta.setVisible(false);
                    panel[3].add(label, BorderLayout.WEST);

                    if (!(PATH.endsWith(".txt"))) {
                        File temp = new File(PATH + ".txt");
                        JFC.renameTo(temp);//renombramos el archivo
                    }


                } catch (Exception ex) {
                }
            }
        });


    }

    private void initPanelCinco(){
        panel[4].setLayout(new GridBagLayout());

        int y = 0;
        int x = 0;
        for (int i = 0; i < gridPanelCinco.length; i++) {
            gridPanelCinco[i] = new GridBagConstraints();
            gridPanelCinco[i].anchor = GridBagConstraints.WEST;

                gridPanelCinco[i].gridx = x;
                gridPanelCinco[i].gridy = y;


            y++;
        }
        JButton boton = new JButton("Salir");
        boton.setSize(50,20);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JLabel label = new JLabel("El registro se ha guardado en: ");
        label.setFont(new Font("MONOSPACED", Font.PLAIN, 50));
        panel[4].add(label, gridPanelCinco[0]);
        panel[4].add(boton, gridPanelCinco[2]);


    }
    private void panelesColores(){
        for (int i = 0; i < panel.length; i++) {
            panel[i].setBackground(Color.decode("#edede9"));
        }
    }
    private void labels(){
        label2 = new JLabel();
        JFC = ruta.getSelectedFile();
        String PATH = JFC.getAbsolutePath();
        label2.setText(PATH);
        label2.setFont(new Font("MONOSPACED", Font.PLAIN, 25));
        panel[4].add(label2, gridPanelCinco[1]);

    }

    //Resultado para que el JEditor muestre ya todos los resultado actualizados
    private void resultado() {
        editor.setText(
                "<h1><b>Resultado del formulario</h1></b>" +
                        "<font size=6>" +
                        "<b>Nombre: </b>" + nombre.getText() + "<br>" +
                        "<b>Correo: </b>" + correoo.getText() + "<br>" +
                        "<b>Contraseña: </b>" + contraseña.getText() + "<br>" +
                        "<b>Pais: </b>" + combo1.getSelectedItem().toString() + "<br>" +
                        "<b>Provincia: </b>" + combo2.getSelectedItem().toString() + "<br>" +
                        "</font>"


        );
        editor.setBackground(Color.decode("#edede9"));
    }

    //Fill para poner los datos automaticamente
    private void fill() {
        correoo.setText("pepe@gmail.com");
        contraseña.setText("asasA12*sa");
        nombre.setText("pepe");
    }

    //comprobacion del correo para todos los posibles correos existentes
    private boolean comprobarCorreo(String x) {
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
    //comprobacion de la contraseña con todos lo posibles simbolos existentes
    private boolean comprobarContraseña(String x) {
        String com = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^*)(%$·!/&+=]).{8,16}$";
        Pattern patron = Pattern.compile(com);
        if (patron.matcher(x).matches()) {
            labelContraseña.setForeground(Color.black);
            return true;

        } else {
            labelContraseña.setForeground(Color.red);
            return false;
        }


    }

    private void initPanelTarjetas() {
        tarjetas = new CardLayout();
        panelTarjetas = new JPanel();
        panelTarjetas.setLayout(tarjetas);

        for (int i = 0; i < panel.length; i++) {
            panel[i] = new JPanel();
            panelTarjetas.add(panel[i]);
        }
        add(panelTarjetas, BorderLayout.CENTER);

    }

    private void initPanelIzq() {
        JLabel imagen;
        panelIzquierdo = new JPanel();
        panelIzquierdo.setPreferredSize(new Dimension(270, 800));
        panelIzquierdo.setBackground(Color.decode("#d6ccc2"));

        imagen = new JLabel();
        imagen.setIcon(new ImageIcon("src/IMG/logo.png"));
        imagen.setPreferredSize(new Dimension(250, 200));
        panelIzquierdo.add(imagen);
        add(panelIzquierdo, BorderLayout.WEST);
    }

    //El panel para los botones
    private void initpanelInferior() {


        panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        panelInferior.setPreferredSize(new Dimension(1500, 30));
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void initPantalla() {

        setTitle("Formulario"); //Título del JFrame
        setBounds(250, 100, 1600, 800); //Dimensiones del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar proceso al cerrar ventana
        setVisible(true); //Mostrar JFrame
        getContentPane().setBackground(Color.decode("#d5bdaf"));
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}