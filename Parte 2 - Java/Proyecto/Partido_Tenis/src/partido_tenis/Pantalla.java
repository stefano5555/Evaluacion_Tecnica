/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partido_tenis;

import java.awt.Font;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Pantalla extends javax.swing.JFrame {
    
    private Jugador jugador1 =  new Jugador();
    private Jugador jugador2 =  new Jugador();
    private Set setActual;
    private Set setAnterior;
    private StringBuilder SB= new StringBuilder("");
    private boolean mejorDe3 =  false;
    private boolean mejorDe5 = false;
    private String[][] tableroResultados = new String[2][5];
    private int cantidadPartidos = 0;
    private int victoriasJugador1 = 0;
    private int victoriasJugador2 = 0;
    private int reglamentoSeleccionado = 0;
    Font defecto = new Font("Tahoma",Font.PLAIN,11);
    Font victoria = new Font("Tahoma",Font.BOLD,12);
    
    
    
    
    
    private void cargarJugadores()
    {
 
        jugador1.setNombre(txtNombreJugador1.getText());
        jugador1.setProbabilidad(Double.valueOf(txtProbGanarJugador1.getText()));
        jugador2.setNombre(txtNombreJugador2.getText());
        jugador2.setProbabilidad(Double.valueOf(txtProbGanarJugador2.getText()));


    }
    
    private void inicializarMatrizResultados() {
        
        
        for (int i = 0; i < 2; i++) 
        {
            for(int j = 0; j<cantidadPartidos; j++)
            {
                tableroResultados[i][j] = " ";
            }

        }
    }
    
    
    private void cargarMatrizResultados()
    {
        
        if(mejorDe5 == true)
        {
            txtResultado11.setText(tableroResultados[0][0]);
            txtResultado12.setText(tableroResultados[0][1]);
            txtResultado13.setText(tableroResultados[0][2]);
            txtResultado14.setText(tableroResultados[0][3]);
            txtResultado15.setText(tableroResultados[0][4]);
            txtResultado21.setText(tableroResultados[1][0]);
            txtResultado22.setText(tableroResultados[1][1]);
            txtResultado23.setText(tableroResultados[1][2]);
            txtResultado24.setText(tableroResultados[1][3]);
            txtResultado25.setText(tableroResultados[1][4]);
        }
        if(mejorDe3 == true)
        {
            txtResultado11.setText(tableroResultados[0][0]);
            txtResultado12.setText(tableroResultados[0][1]);
            txtResultado13.setText(tableroResultados[0][2]);
            txtResultado14.setText("-");
            txtResultado15.setText("-");
            txtResultado21.setText(tableroResultados[1][0]);
            txtResultado22.setText(tableroResultados[1][1]);
            txtResultado23.setText(tableroResultados[1][2]);
            txtResultado24.setText("-");
            txtResultado25.setText("-");
        }
    }
    
    private boolean verificarVictoria()
    {
       boolean aux = true;
       if((cantidadPartidos == 3 && victoriasJugador1 < 2 && victoriasJugador2 <2) || (cantidadPartidos == 5 && victoriasJugador1 < 3 && victoriasJugador2 < 3))
       {
           aux = false;
       }
       return aux;
       
    }

    
    private void cargarTextBoxs(int i, boolean tie)
    {
        if(i == 1 && tie == false)
        {
            txtResultado11.setText(String.valueOf(setActual.getPuntaje1()));
            txtResultado21.setText(String.valueOf(setActual.getPuntaje2()));
        }
        if(i == 2 && tie == false)
        {
            txtResultado12.setText(String.valueOf(setActual.getPuntaje1()));
            txtResultado22.setText(String.valueOf(setActual.getPuntaje2()));
        }
        if(i == 3 && tie == false)
        {
            txtResultado13.setText(String.valueOf(setActual.getPuntaje1()));
            txtResultado23.setText(String.valueOf(setActual.getPuntaje2()));
        }
        if(i == 4 && tie == false)
        {
            txtResultado14.setText(String.valueOf(setActual.getPuntaje1()));
            txtResultado24.setText(String.valueOf(setActual.getPuntaje2()));
        }
        if(i == 5 && tie == false)
        {
            txtResultado15.setText(String.valueOf(setActual.getPuntaje1()));
            txtResultado25.setText(String.valueOf(setActual.getPuntaje2()));
        }
        if(i == 1 && tie == true)
        {
            txtResultado11.setText(String.valueOf(setActual.getPuntaje1()) + "(" + String.valueOf(setActual.getPuntajeTie1()) + ")");
            txtResultado21.setText(String.valueOf(setActual.getPuntaje2()) + "(" + String.valueOf(setActual.getPuntajeTie2()) + ")");
        }
        if(i == 2 && tie == true)
        {
            txtResultado12.setText(String.valueOf(setActual.getPuntaje1()) + "(" + String.valueOf(setActual.getPuntajeTie1()) + ")");
            txtResultado22.setText(String.valueOf(setActual.getPuntaje2()) + "(" + String.valueOf(setActual.getPuntajeTie2()) + ")");
        }
        if(i == 3 && tie == true)
        {
            txtResultado13.setText(String.valueOf(setActual.getPuntaje1()) + "(" + String.valueOf(setActual.getPuntajeTie1()) + ")");
            txtResultado23.setText(String.valueOf(setActual.getPuntaje2()) + "(" + String.valueOf(setActual.getPuntajeTie2()) + ")");
        }
        if(i == 4 && tie == true)
        {
            txtResultado14.setText(String.valueOf(setActual.getPuntaje1()) + "(" + String.valueOf(setActual.getPuntajeTie1()) + ")");
            txtResultado24.setText(String.valueOf(setActual.getPuntaje2()) + "(" + String.valueOf(setActual.getPuntajeTie2()) + ")");
        }
        if(i == 5 && tie == true)
        {
            txtResultado15.setText(String.valueOf(setActual.getPuntaje1()) + "(" + String.valueOf(setActual.getPuntajeTie1()) + ")");
            txtResultado25.setText(String.valueOf(setActual.getPuntaje2()) + "(" + String.valueOf(setActual.getPuntajeTie2()) + ")");
        }
        
    }
    
    private void checkearUltimoSet()
    {
        if(mejorDe3 && (victoriasJugador1 + victoriasJugador2) == 2)
        {
            setActual.setUltimoSet(true);
        }
        if(mejorDe5 && (victoriasJugador1 + victoriasJugador2) == 4)
        {
            setActual.setUltimoSet(true);
        }
    }
    
    private void reiniciarParametros()
    {
        mejorDe3 = false;
        mejorDe5 = false;
        jugador1 =  new Jugador();
        jugador2 =  new Jugador();
        cargarMatrizResultados();
        cantidadPartidos = 0;
        victoriasJugador1 = 0;
        victoriasJugador2 = 0;
        SB= new StringBuilder("");
        lblResultadosActualJugador1.setText(" ");
        lblResultadosActualJugador2.setText(" ");
        lblQuienSaca.setText(" ");
        
        
        
        
        txtResultado11.setFont(defecto);
        txtResultado12.setFont(defecto);
        txtResultado13.setFont(defecto);
        txtResultado14.setFont(defecto);
        txtResultado15.setFont(defecto);
        txtResultado21.setFont(defecto);
        txtResultado22.setFont(defecto);
        txtResultado23.setFont(defecto);
        txtResultado24.setFont(defecto);
        txtResultado25.setFont(defecto);
        
        
    }
    private void inicializarLabels()
    {
        lblResultadosJugador1.setText(jugador1.getNombre());
        lblResultadosJugador2.setText(jugador2.getNombre());
        lblNombreTorneoResultados.setText(txtNombreTorneo.getText());
        lblEstadoPartidoResultados.setText("En juego");
        lblResultadosActualJugador1.setText(jugador1.getNombre());
        lblResultadosActualJugador2.setText(jugador2.getNombre());
        
        
    }
    
    private void empezarNuevoSet()
    {
        setAnterior = setActual;
        setActual = new Set(jugador1, jugador2, setAnterior.getNumeroDeSet() + 1, setAnterior.getQuienSaca(), reglamentoSeleccionado);
        setActual.empezarNuevoGame();
        txtResultadoActual1.setText("");
        txtResultadoActual2.setText("");
            SB.append("\n");
            SB.append("COMIENZO SET " + setActual.getNumeroDeSet() + " game : " + setActual.getNumeroDeGameActual());
            SB.append("\n");
            SB.append("----------------------");
            SB.append("\n");
        SB.append("saca " + setActual.getGameActual().getQuienSaca().getNombre());
        lblQuienSaca.setText(setActual.getGameActual().getQuienSaca().getNombre());
        SB.append("\n");
        SB.append("------------");
        SB.append("\n");
        
    }
    
    private void empezarNuevoGame()
    {
        setActual.empezarNuevoGame();
        if (setActual.isEstaEnTie() == false)
        {
            txtResultadoActual1.setText("");
            txtResultadoActual2.setText("");
            SB.append("\n");
            SB.append("COMIENZO SET " + setActual.getNumeroDeSet() + " game : " + setActual.getNumeroDeGameActual());
            SB.append("\n");
            SB.append("----------------------");
            SB.append("\n");

        }
        SB.append("saca " + setActual.getGameActual().getQuienSaca().getNombre());
        lblQuienSaca.setText(setActual.getGameActual().getQuienSaca().getNombre());
        SB.append("\n");
        SB.append("------------");
        SB.append("\n");
        
    }
    
    private void generarStringVictoria()
    {
        double random = Math.random();
        double randomSecundario = Math.random();
        String stringVictoria = "";
        
        if (random <= 0.30)
        {
            if (setActual.getGameActual().getQuienGanaPunto() == setActual.getGameActual().getQuienSaca())
            {
                stringVictoria = setActual.getGameActual().getQuienGanaPunto().getNombre() + " hace un Ace";

            }
            if (setActual.getGameActual().getQuienGanaPunto() != setActual.getGameActual().getQuienSaca())
            {
                stringVictoria = setActual.getGameActual().getQuienSaca().getNombre() + " saca mal 2 veces";
            }

        }
        if(random > 0.30 && random <= 0.70)
        {
            if(randomSecundario <= 0.30)
            {
                stringVictoria = ("Excelente reves del jugador " + setActual.getGameActual().getQuienGanaPunto().getNombre());
            }
            if(randomSecundario <= 0.60 && randomSecundario > 0.30)
            {
                stringVictoria = ("Espectacular punto del jugador " + setActual.getGameActual().getQuienGanaPunto().getNombre());
            }
            if(randomSecundario > 0.60 && randomSecundario <= 0.80)
            {
                stringVictoria = ("Jugador " + setActual.getGameActual().getQuienGanaPunto().getNombre() + " realiza un fenomenal Drop");
            }
            if(randomSecundario > 0.80)
            {
                stringVictoria = ("Jugador " + setActual.getGameActual().getQuienGanaPunto().getNombre() + " realiza un increible Winner");
            }
        }
        if(random > 0.70)
        {
            if(randomSecundario <= 0.30)
            {
                stringVictoria = ("Jugador " + setActual.getGameActual().getQuienPierdePunto().getNombre() + " Comete una infraccion");
            }
            if(randomSecundario <= 0.60 && randomSecundario > 0.30)
            {
                stringVictoria = ("Jugador " + setActual.getGameActual().getQuienPierdePunto().getNombre() + " Comete un error ");
            }
            if(randomSecundario > 0.60 && randomSecundario <= 0.80)
            {
                stringVictoria = ("Jugador " + setActual.getGameActual().getQuienPierdePunto().getNombre() + " la tira afuera");
            }
            if(randomSecundario > 0.80 && randomSecundario <= 0.99)
            {
                stringVictoria = ("Jugador " + setActual.getGameActual().getQuienPierdePunto().getNombre() + " se tropieza");
            }
            if(randomSecundario > 0.99)
            {
                stringVictoria = ("Jugador " + setActual.getGameActual().getQuienPierdePunto().getNombre() + " insulta al Juez");
            }
            
        }
        SB.append("\n");
        SB.append(stringVictoria);
        SB.append("\n");
        
    }
    
    private boolean validarDatos()
    {
        double prob1;
        double prob2;
        boolean valido = true;
        try
        {
            prob1 = Double.valueOf(txtProbGanarJugador1.getText());
            prob2 = Double.valueOf(txtProbGanarJugador2.getText());
            
            if(prob1 >= 0 && prob1 <= 100 && prob2 >= 0 && prob2 <= 100 && prob1 + prob2 == 100)
            {
                valido = true;
            }
            else
            {
                valido = false;
                JOptionPane.showMessageDialog(null, "Las probabilidades deben ser valores positivos y sumar 100", "ERROR", JOptionPane.ERROR_MESSAGE);
            }   
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Valores de Probabilidad Invalidos", "ERROR", JOptionPane.ERROR_MESSAGE);
            valido = false;
        }
        
        if(txtNombreJugador1.getText().isEmpty() || txtNombreJugador2.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Debe ingresar ambos nombres", "ERROR", JOptionPane.ERROR_MESSAGE);
            valido = false;
            
        }
        else
        {
            if(txtNombreTorneo.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de torneo", "ERROR", JOptionPane.ERROR_MESSAGE);
                valido = false;
            }
        }
        

        return valido;
    }
    
    private void resaltarMarcador(int jugador, int nroSet)
    {
        if(nroSet == 1)
        {
            if(jugador == 1)
            {
                txtResultado11.setFont(victoria);
            }
            if(jugador == 2)
            {
                txtResultado21.setFont(victoria);
            }
        }
        if(nroSet == 2)
        {
            if(jugador == 1)
            {
                txtResultado12.setFont(victoria);
            }
            if(jugador == 2)
            {
                txtResultado22.setFont(victoria);
            }
        }
        if(nroSet == 3)
        {
            if(jugador == 1)
            {
                txtResultado13.setFont(victoria);
            }
            if(jugador == 2)
            {
                txtResultado23.setFont(victoria);
            }
        }
        if(nroSet == 4)
        {
            if(jugador == 1)
            {
                txtResultado14.setFont(victoria);
            }
            if(jugador == 2)
            {
                txtResultado24.setFont(victoria);
            }
        }
        if(nroSet == 5)
        {
            if(jugador == 1)
            {
                txtResultado15.setFont(victoria);
            }
            if(jugador == 2)
            {
                txtResultado25.setFont(victoria);
            }
        }
    }
    
    private void inicializarDatos()
    {
        if (validarDatos())
        {
            reiniciarParametros();
            txtAreaDesarrollo.setText("");
            cargarJugadores();
            reglamentoSeleccionado = comboBoxReglamento.getSelectedIndex();
            double randomQuienSaca = Math.random();
            if (randomQuienSaca <= 0.5)
            {
                setActual = new Set(jugador1, jugador2, 1, 1, reglamentoSeleccionado);
                System.out.println("saca jugador 1 random: " + randomQuienSaca);
                lblQuienSaca.setText(jugador1.getNombre());
            } else
            {
                setActual = new Set(jugador1, jugador2, 1, 2, reglamentoSeleccionado);
                System.out.println("saca jugador 2 random: " + randomQuienSaca);
                lblQuienSaca.setText(jugador2.getNombre());
            }

            if (radioMejorDe3.isSelected())
            {
                mejorDe3 = true;
                cantidadPartidos = 3;
                tableroResultados = new String[2][3];
            }
            if (radioMejorDe5.isSelected())
            {
                mejorDe5 = true;
                cantidadPartidos = 5;
                tableroResultados = new String[2][5];
            }
            inicializarMatrizResultados();
            inicializarLabels();

            setActual.empezarNuevoGame();

            cargarMatrizResultados();
            SB.append("\n");
            SB.append("COMIENZO SET " + setActual.getNumeroDeSet() + " game : " + setActual.getNumeroDeGameActual());
            SB.append("\n");
            SB.append("----------------------");
            SB.append("\n");
            SB.append("saca " + setActual.getGameActual().getQuienSaca().getNombre());
            SB.append("\n");
            SB.append("------------");
            SB.append("\n");

            btnSimularPartido.setEnabled(true);
            btnSimularPuntoAPunto.setEnabled(true);
            btnConfirmar.setEnabled(false);
            btnPorDefecto.setEnabled(false);
            
            txtNombreTorneo.setEnabled(false);
            radioMejorDe3.setEnabled(false);
            radioMejorDe5.setEnabled(false);
            txtNombreJugador1.setEnabled(false);
            txtProbGanarJugador1.setEnabled(false);
            txtNombreJugador2.setEnabled(false);
            txtProbGanarJugador2.setEnabled(false);
            comboBoxReglamento.setEnabled(false);

        }

    }

    /**
     * Creates new form Pantalla
     */
    public Pantalla() {
        initComponents();
        setLocationRelativeTo(null);
        
        
    }
    
    public void validacionCaracteres(java.awt.event.KeyEvent evento)
    {
        if(evento.getKeyChar() >= 33 && evento.getKeyChar() <= 64 
                || evento.getKeyChar() >= 91 && evento.getKeyChar() <= 96
                || evento.getKeyChar() >= 123 && (evento.getKeyChar() != 241 && evento.getKeyChar() != 208))
        {
            evento.consume();
            //JOptionPane.showMessageDialog(null, "caracter invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreJugador1 = new javax.swing.JTextField();
        txtProbGanarJugador1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNombreJugador2 = new javax.swing.JTextField();
        txtProbGanarJugador2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreTorneo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        radioMejorDe3 = new javax.swing.JRadioButton();
        radioMejorDe5 = new javax.swing.JRadioButton();
        comboBoxReglamento = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDesarrollo = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lblResultadosJugador1 = new javax.swing.JLabel();
        lblResultadosJugador2 = new javax.swing.JLabel();
        txtResultado11 = new javax.swing.JTextField();
        lblNombreTorneoResultados = new javax.swing.JLabel();
        lblEstadoPartidoResultados = new javax.swing.JLabel();
        txtResultado12 = new javax.swing.JTextField();
        txtResultado13 = new javax.swing.JTextField();
        txtResultado14 = new javax.swing.JTextField();
        txtResultado15 = new javax.swing.JTextField();
        txtResultado21 = new javax.swing.JTextField();
        txtResultado22 = new javax.swing.JTextField();
        txtResultado23 = new javax.swing.JTextField();
        txtResultado24 = new javax.swing.JTextField();
        txtResultado25 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblResultadosActualJugador1 = new javax.swing.JLabel();
        lblResultadosActualJugador2 = new javax.swing.JLabel();
        txtResultadoActual1 = new javax.swing.JTextField();
        txtResultadoActual2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblJugadorQueSaca = new javax.swing.JLabel();
        lblQuienSaca = new javax.swing.JLabel();
        btnSimularPartido = new javax.swing.JButton();
        btnSimularPuntoAPunto = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        btnRevancha = new javax.swing.JButton();
        btnPorDefecto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Jugador 1");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Prob. de ganar:");

        txtNombreJugador1.setText("N.Djokovic");
        txtNombreJugador1.setMaximumSize(new java.awt.Dimension(34, 20));
        txtNombreJugador1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreJugador1KeyTyped(evt);
            }
        });

        txtProbGanarJugador1.setText("50");

        jLabel5.setText("%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreJugador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProbGanarJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)))
                        .addGap(0, 105, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreJugador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtProbGanarJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setText("Jugador 2");

        jLabel7.setText("Nombre:");

        jLabel8.setText("Prob. de ganar:");

        txtNombreJugador2.setText("A.Zverev");
        txtNombreJugador2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreJugador2KeyTyped(evt);
            }
        });

        txtProbGanarJugador2.setText("50");
        txtProbGanarJugador2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProbGanarJugador2ActionPerformed(evt);
            }
        });

        jLabel9.setText("%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreJugador2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProbGanarJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)))
                        .addGap(0, 105, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNombreJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtProbGanarJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Nombre del torneo:");

        txtNombreTorneo.setText("Tennis World Cup 2022");
        txtNombreTorneo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreTorneoKeyTyped(evt);
            }
        });

        jLabel10.setText("Sets");

        buttonGroup1.add(radioMejorDe3);
        radioMejorDe3.setText("Mejor de 3");

        buttonGroup1.add(radioMejorDe5);
        radioMejorDe5.setSelected(true);
        radioMejorDe5.setText("Mejor de 5");

        comboBoxReglamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open de Australia: Supertiebreak (a 10 puntos) cuando se llega al 6-6", "Roland Garros: Se mantiene sin tiebreak; diferencia de dos juegos.", "Wimbledon: Tiebreak cuando se llega al 12-12", "US Open: Tiebreak en el 6-6." }));

        jLabel13.setText("Reglamento");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxReglamento, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombreTorneo)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(radioMejorDe3)
                            .addComponent(radioMejorDe5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreTorneo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxReglamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioMejorDe3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioMejorDe5)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        txtAreaDesarrollo.setEditable(false);
        txtAreaDesarrollo.setColumns(20);
        txtAreaDesarrollo.setRows(5);
        jScrollPane1.setViewportView(txtAreaDesarrollo);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel14.setText("RESULTADOS");

        lblResultadosJugador1.setText(" ");

        lblResultadosJugador2.setText(" ");

        txtResultado11.setBackground(new java.awt.Color(240, 240, 240));
        txtResultado11.setEnabled(false);

        lblNombreTorneoResultados.setText(" ");

        lblEstadoPartidoResultados.setText(" ");

        txtResultado12.setBackground(new java.awt.Color(240, 240, 240));
        txtResultado12.setEnabled(false);

        txtResultado13.setBackground(new java.awt.Color(240, 240, 240));
        txtResultado13.setEnabled(false);

        txtResultado14.setBackground(new java.awt.Color(240, 240, 240));
        txtResultado14.setEnabled(false);

        txtResultado15.setBackground(new java.awt.Color(240, 240, 240));
        txtResultado15.setEnabled(false);

        txtResultado21.setBackground(new java.awt.Color(240, 240, 240));
        txtResultado21.setEnabled(false);

        txtResultado22.setBackground(new java.awt.Color(240, 240, 240));
        txtResultado22.setEnabled(false);

        txtResultado23.setBackground(new java.awt.Color(240, 240, 240));
        txtResultado23.setEnabled(false);

        txtResultado24.setBackground(new java.awt.Color(240, 240, 240));
        txtResultado24.setEnabled(false);

        txtResultado25.setBackground(new java.awt.Color(240, 240, 240));
        txtResultado25.setEnabled(false);

        jLabel11.setText("Actual");

        lblResultadosActualJugador1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblResultadosActualJugador1.setText(" ");

        lblResultadosActualJugador2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblResultadosActualJugador2.setText(" ");

        txtResultadoActual1.setBackground(new java.awt.Color(240, 240, 240));
        txtResultadoActual1.setEnabled(false);

        txtResultadoActual2.setBackground(new java.awt.Color(240, 240, 240));
        txtResultadoActual2.setEnabled(false);

        jLabel12.setText("Jugador que saca: ");

        lblQuienSaca.setText(" ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblResultadosJugador2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblResultadosJugador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblResultadosActualJugador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblResultadosActualJugador2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblJugadorQueSaca)
                                    .addComponent(jLabel11))
                                .addGap(0, 130, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtResultado11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtResultado12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtResultado13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtResultado14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtResultado15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtResultadoActual1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtResultadoActual2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lblQuienSaca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(txtResultado21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtResultado22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtResultado23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtResultado24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtResultado25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblNombreTorneoResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEstadoPartidoResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstadoPartidoResultados, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombreTorneoResultados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblResultadosJugador1)
                    .addComponent(txtResultado11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtResultado12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtResultado13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtResultado14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtResultado15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblResultadosJugador2)
                    .addComponent(txtResultado21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtResultado22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtResultado23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtResultado24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtResultado25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtResultadoActual1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResultadosActualJugador2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtResultadoActual2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResultadosActualJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblJugadorQueSaca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblQuienSaca))
                .addContainerGap())
        );

        txtResultado11.getAccessibleContext().setAccessibleName("");
        txtResultado12.getAccessibleContext().setAccessibleName("");
        txtResultado13.getAccessibleContext().setAccessibleName("");
        txtResultado14.getAccessibleContext().setAccessibleName("");
        txtResultado15.getAccessibleContext().setAccessibleName("");
        txtResultado21.getAccessibleContext().setAccessibleName("");
        txtResultado22.getAccessibleContext().setAccessibleName("");
        txtResultado23.getAccessibleContext().setAccessibleName("");
        txtResultado24.getAccessibleContext().setAccessibleName("");
        txtResultado25.getAccessibleContext().setAccessibleName("");

        btnSimularPartido.setText("Simular partido");
        btnSimularPartido.setEnabled(false);
        btnSimularPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularPartidoActionPerformed(evt);
            }
        });

        btnSimularPuntoAPunto.setText("Simular punto a punto");
        btnSimularPuntoAPunto.setEnabled(false);
        btnSimularPuntoAPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularPuntoAPuntoActionPerformed(evt);
            }
        });

        btnConfirmar.setText("Confirmar datos");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnReiniciar.setText("Reiniciar");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        btnRevancha.setText("Revancha");
        btnRevancha.setEnabled(false);
        btnRevancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevanchaActionPerformed(evt);
            }
        });

        btnPorDefecto.setText("Valores por defecto");
        btnPorDefecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPorDefectoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPorDefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSimularPuntoAPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSimularPartido, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(btnRevancha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(btnPorDefecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimularPuntoAPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimularPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReiniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnRevancha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimularPuntoAPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularPuntoAPuntoActionPerformed
        // TODO add your handling code here:
        if (verificarVictoria() == false)
        {
            if (setActual.isEstaEnTie() == false)
            {
                if (setActual.getGameActual().isFinalizado() == false)
                {
                    setActual.jugarPunto();

                    generarStringVictoria();
                    SB.append("\n");
                    SB.append(setActual.getJugador1().getNombre() + " puntos: " + setActual.getGameActual().getPuntosJugador1());
                    txtResultadoActual1.setText(setActual.getGameActual().getPuntosJugador1());
                    SB.append("\n");
                    SB.append(setActual.getJugador2().getNombre() + " puntos: " + setActual.getGameActual().getPuntosJugador2());
                    txtResultadoActual2.setText(setActual.getGameActual().getPuntosJugador2());
                    SB.append("\n");
                    
                    SB.append("------------");
                    SB.append("\n");

                    if (setActual.getGameActual().isFinalizado())
                    {
                        cargarTextBoxs(setActual.getNumeroDeSet(), setActual.isEstaEnTie());
                    }

                } else
                {
                    if (setActual.getGameActual().isFinalizado())
                    {
                        if (setActual.obtenerGanador() == 1)
                        {
                            victoriasJugador1++;
                            
                            SB.append("----> Gana set " + setActual.getNumeroDeSet() + " Jugador " + jugador1.getNombre() + " <----");
                            SB.append("\n");
                            SB.append("------------");
                            SB.append("\n");
                            resaltarMarcador(1, setActual.getNumeroDeSet());

                            if (verificarVictoria() == false)
                            {
                                empezarNuevoSet();
                                checkearUltimoSet();
                            }

                        } else
                        {
                            if (setActual.obtenerGanador() == 2)
                            {
                                victoriasJugador2++;
                                SB.append("----> Gana set " + setActual.getNumeroDeSet() + " Jugador " + jugador2.getNombre() + " <----");
                                SB.append("\n");
                                SB.append("------------");
                                SB.append("\n");
                                resaltarMarcador(2, setActual.getNumeroDeSet());
                                if (verificarVictoria() == false)
                                {
                                    empezarNuevoSet();
                                    checkearUltimoSet();
                                }

                            } else
                            {
                                if (setActual.obtenerGanador() == 0)
                                {
                                    
                                    if (setActual.isEstaEnTie())
                                    {
                                        SB.append("COMIENZA TIE PARA DESEMPATE");
                                        SB.append("\n");
                                        SB.append("------------");
                                        SB.append("\n");
                                        
                                    }
                                    empezarNuevoGame();
                                }
                            }

                        }

                    }
                }

            } else
            {
                if (setActual.getGameActual().isFinalizado() == false)
                {
                    setActual.jugarTie();
                     generarStringVictoria();
                       SB.append("\n");
                    SB.append(setActual.getJugador1().getNombre() + " puntos: " + setActual.getPuntajeTie1());
                    txtResultadoActual1.setText(String.valueOf(setActual.getPuntajeTie1()));
                    SB.append("\n");
                    SB.append(setActual.getJugador2().getNombre() + " puntos: " + setActual.getPuntajeTie2());
                    txtResultadoActual2.setText(String.valueOf(setActual.getPuntajeTie2()));
                    SB.append("\n");
                    SB.append("------------");
                    SB.append("\n");

                    if (setActual.getGameActual().isFinalizado())
                    {
                        cargarTextBoxs(setActual.getNumeroDeSet(), setActual.isEstaEnTie());
                    }

                } else
                {
                    if (setActual.getGameActual().isFinalizado())
                    {
                        if (setActual.obtenerGanadorTie() == 1)
                        {
                            victoriasJugador1++;
                            setActual.finalizarTie(1);
                            cargarTextBoxs(setActual.getNumeroDeSet(), setActual.isEstaEnTie());
                            SB.append("----> Gana set de Tie " + setActual.getNumeroDeSet() + " Jugador " + jugador1.getNombre() + " <----");
                            SB.append("\n");
                            SB.append("------------");
                            SB.append("\n");
                            resaltarMarcador(1, setActual.getNumeroDeSet());
                            if (verificarVictoria() == false)
                            {

                                empezarNuevoSet();
                            }

                        } else
                        {
                            if (setActual.obtenerGanadorTie() == 2)
                            {
                                victoriasJugador2++;
                                setActual.finalizarTie(2);
                                cargarTextBoxs(setActual.getNumeroDeSet(), setActual.isEstaEnTie());
                                SB.append("----> Gana set de Tie " + setActual.getNumeroDeSet() + " Jugador " + jugador2.getNombre() + "<----");
                                SB.append("\n");
                                SB.append("------------");
                                SB.append("\n");
                                resaltarMarcador(2, setActual.getNumeroDeSet());
                                if (verificarVictoria() == false)
                                {

                                    empezarNuevoSet();
                                }

                            } else
                            {
                                if (setActual.obtenerGanadorTie() == 0)
                                {
                                    empezarNuevoGame();
                                }
                            }

                        }

                    }
                }

            }
        } else
        {

            if ((cantidadPartidos == 3 && victoriasJugador1 == 2) || (cantidadPartidos == 5 && victoriasJugador1 == 3))
            {
                SB.append("Jugador: " + jugador1.getNombre() + " GANA EL PARTIDO");
                SB.append("\n");
                SB.append("------------");
                SB.append("\n");
                 btnRevancha.setEnabled(true);
                    btnSimularPuntoAPunto.setEnabled(false);
                    btnSimularPartido.setEnabled(false);
            } else
            {
                if ((cantidadPartidos == 3 && victoriasJugador2 == 2) || (cantidadPartidos == 5 && victoriasJugador2 == 3))
                {
                    SB.append("Jugador: " + jugador2.getNombre() + " GANA EL PARTIDO");
                    SB.append("\n");
                    SB.append("------------");
                    SB.append("\n");
                     btnRevancha.setEnabled(true);
                    btnSimularPuntoAPunto.setEnabled(false);
                    btnSimularPartido.setEnabled(false);
                }
            }

        }

        txtAreaDesarrollo.setText(SB.toString());

    }//GEN-LAST:event_btnSimularPuntoAPuntoActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed

        inicializarDatos();

        
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void txtProbGanarJugador2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProbGanarJugador2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProbGanarJugador2ActionPerformed

    private void btnSimularPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularPartidoActionPerformed
        // TODO add your handling code here:
        while (true)
        {
            if (verificarVictoria() == false)
            {
                if (setActual.isEstaEnTie() == false)
                {
                    if (setActual.getGameActual().isFinalizado() == false)
                    {
                        setActual.jugarPunto();
                        generarStringVictoria();
                        SB.append("\n");

                        SB.append(setActual.getJugador1().getNombre() + " puntos: " + setActual.getGameActual().getPuntosJugador1());
                        txtResultadoActual1.setText(setActual.getGameActual().getPuntosJugador1());
                        SB.append("\n");
                        SB.append(setActual.getJugador2().getNombre() + " puntos: " + setActual.getGameActual().getPuntosJugador2());
                        txtResultadoActual2.setText(setActual.getGameActual().getPuntosJugador2());
                        SB.append("\n");
                        SB.append("------------");
                        SB.append("\n");

                        if (setActual.getGameActual().isFinalizado())
                        {
                            cargarTextBoxs(setActual.getNumeroDeSet(), setActual.isEstaEnTie());
                        }

                    } else
                    {
                        if (setActual.getGameActual().isFinalizado())
                        {
                            if (setActual.obtenerGanador() == 1)
                            {
                                victoriasJugador1++;
                                SB.append("----> Gana set " + setActual.getNumeroDeSet() + " Jugador " + jugador1.getNombre() + " <----");
                                SB.append("\n");
                                SB.append("------------");
                                SB.append("\n");
                                resaltarMarcador(1, setActual.getNumeroDeSet());

                                if (verificarVictoria() == false)
                                {
                                    empezarNuevoSet();
                                    checkearUltimoSet();
                                }

                            } else
                            {
                                if (setActual.obtenerGanador() == 2)
                                {
                                    victoriasJugador2++;
                                    SB.append("----> Gana set " + setActual.getNumeroDeSet() + " Jugador " + jugador2.getNombre() + " <----");
                                    SB.append("\n");
                                    SB.append("------------");
                                    SB.append("\n");
                                    resaltarMarcador(2, setActual.getNumeroDeSet());
                                    if (verificarVictoria() == false)
                                    {
                                        empezarNuevoSet();
                                        checkearUltimoSet();
                                    }

                                } else
                                {
                                    if (setActual.obtenerGanador() == 0)
                                    {
                                       
                                        if (setActual.isEstaEnTie())
                                        {
                                            SB.append("COMIENZA TIE PARA DESEMPATE");
                                            SB.append("\n");
                                            SB.append("------------");
                                            SB.append("\n");
                                             
                                        }
                                        empezarNuevoGame();
                                    }
                                }

                            }

                        }
                    }

                } else
                {
                    if (setActual.getGameActual().isFinalizado() == false)
                    {
                        setActual.jugarTie();
                        generarStringVictoria();
                        SB.append("\n");
                        SB.append(setActual.getJugador1().getNombre() + " puntos: " + setActual.getPuntajeTie1());
                        txtResultadoActual1.setText(String.valueOf(setActual.getPuntajeTie1()));
                        SB.append("\n");
                        SB.append(setActual.getJugador2().getNombre() + " puntos: " + setActual.getPuntajeTie2());
                        txtResultadoActual2.setText(String.valueOf(setActual.getPuntajeTie2()));
                        SB.append("\n");
                        SB.append("------------");
                        SB.append("\n");

                        if (setActual.getGameActual().isFinalizado())
                        {
                            cargarTextBoxs(setActual.getNumeroDeSet(), setActual.isEstaEnTie());
                        }

                    } else
                    {
                        if (setActual.getGameActual().isFinalizado())
                        {
                            if (setActual.obtenerGanadorTie() == 1)
                            {
                                victoriasJugador1++;
                                setActual.finalizarTie(1);
                                cargarTextBoxs(setActual.getNumeroDeSet(), setActual.isEstaEnTie());
                                SB.append("----> Gana set de Tie " + setActual.getNumeroDeSet() + " Jugador " + jugador1.getNombre() + " <----");
                                SB.append("\n");
                                SB.append("------------");
                                SB.append("\n");
                                resaltarMarcador(1, setActual.getNumeroDeSet());
                                if (verificarVictoria() == false)
                                {

                                    empezarNuevoSet();
                                }

                            } else
                            {
                                if (setActual.obtenerGanadorTie() == 2)
                                {
                                    victoriasJugador2++;
                                    setActual.finalizarTie(2);
                                    cargarTextBoxs(setActual.getNumeroDeSet(), setActual.isEstaEnTie());
                                    SB.append("----> Gana set de Tie " + setActual.getNumeroDeSet() + " Jugador " + jugador2.getNombre() + "<----");
                                    SB.append("\n");
                                    SB.append("------------");
                                    SB.append("\n");
                                    resaltarMarcador(2, setActual.getNumeroDeSet());
                                    if (verificarVictoria() == false)
                                    {

                                        empezarNuevoSet();
                                    }

                                } else
                                {
                                    if (setActual.obtenerGanadorTie() == 0)
                                    {
                                        empezarNuevoGame();
                                    }
                                }

                            }

                        }
                    }

                }
            } else
            {
                if ((cantidadPartidos == 3 && victoriasJugador1 == 2) || (cantidadPartidos == 5 && victoriasJugador1 == 3))
                {
                    SB.append("Jugador: " + jugador1.getNombre() + " GANA EL PARTIDO");
                    SB.append("\n");
                    SB.append("------------");
                    SB.append("\n");
                    txtAreaDesarrollo.setText(SB.toString());
                    btnRevancha.setEnabled(true);
                    btnSimularPuntoAPunto.setEnabled(false);
                    btnSimularPartido.setEnabled(false);
                    break;
                } else
                {
                    if ((cantidadPartidos == 3 && victoriasJugador2 == 2) || (cantidadPartidos == 5 && victoriasJugador2 == 3))
                    {
                        SB.append("Jugador: " + jugador2.getNombre() + " GANA EL PARTIDO");
                        SB.append("\n");
                        SB.append("------------");
                        SB.append("\n");
                        txtAreaDesarrollo.setText(SB.toString());
                        btnRevancha.setEnabled(true);
                        btnSimularPuntoAPunto.setEnabled(false);
                        btnSimularPartido.setEnabled(false);
                        break;
                    }
                }
            }

        }


    }//GEN-LAST:event_btnSimularPartidoActionPerformed

    private void txtNombreJugador1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreJugador1KeyTyped
        // TODO add your handling code here:
        if(txtNombreJugador1.getText().length() > 20)
        {
            evt.consume();
        }
        validacionCaracteres(evt);
        
    }//GEN-LAST:event_txtNombreJugador1KeyTyped

    private void txtNombreJugador2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreJugador2KeyTyped
        // TODO add your handling code here:
        if(txtNombreJugador2.getText().length() > 20)
        {
            evt.consume();
        }
        validacionCaracteres(evt);
    }//GEN-LAST:event_txtNombreJugador2KeyTyped

    private void txtNombreTorneoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreTorneoKeyTyped
        // TODO add your handling code here:
        if(txtNombreTorneo.getText().length() > 40)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreTorneoKeyTyped

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        // TODO add your handling code here:
        reiniciarParametros();
        
        btnRevancha.setEnabled(false);
        txtAreaDesarrollo.setText("");
        btnConfirmar.setEnabled(true);
        btnSimularPartido.setEnabled(false);
        btnSimularPuntoAPunto.setEnabled(false);
        btnPorDefecto.setEnabled(true);
        txtResultado11.setText("");
        txtResultado12.setText("");
        txtResultado13.setText("");
        txtResultado14.setText("");
        txtResultado15.setText("");
        txtResultado21.setText("");
        txtResultado22.setText("");
        txtResultado23.setText("");
        txtResultado24.setText("");
        txtResultado25.setText("");
        lblResultadosActualJugador1.setText(" ");
        lblResultadosActualJugador2.setText(" ");
        lblQuienSaca.setText(" ");
        lblEstadoPartidoResultados.setText(" ");
        lblNombreTorneoResultados.setText(" ");
        lblResultadosJugador1.setText("");
        lblResultadosJugador2.setText("");
        txtResultadoActual1.setText("");
        txtResultadoActual2.setText("");
        txtNombreTorneo.setText("");
        txtNombreJugador1.setText("");
        txtProbGanarJugador1.setText("");
        txtNombreJugador2.setText("");
        txtProbGanarJugador2.setText("");
        
        txtNombreTorneo.setEnabled(true);
            radioMejorDe3.setEnabled(true);
            radioMejorDe5.setEnabled(true);
            txtNombreJugador1.setEnabled(true);
            txtProbGanarJugador1.setEnabled(true);
            txtNombreJugador2.setEnabled(true);
            txtProbGanarJugador2.setEnabled(true);
            comboBoxReglamento.setEnabled(true);
        
        
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnPorDefectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorDefectoActionPerformed
        // TODO add your handling code here:
        txtNombreTorneo.setText("Tennis World Cup 2022");
        txtNombreJugador1.setText("N.Djokovic");
        txtProbGanarJugador1.setText("50");
        txtNombreJugador2.setText("A.Zverev");
        txtProbGanarJugador2.setText("50");
    }//GEN-LAST:event_btnPorDefectoActionPerformed

    private void btnRevanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevanchaActionPerformed
        // TODO add your handling code here:
        inicializarDatos();

    }//GEN-LAST:event_btnRevanchaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnPorDefecto;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnRevancha;
    private javax.swing.JButton btnSimularPartido;
    private javax.swing.JButton btnSimularPuntoAPunto;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> comboBoxReglamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstadoPartidoResultados;
    private javax.swing.JLabel lblJugadorQueSaca;
    private javax.swing.JLabel lblNombreTorneoResultados;
    private javax.swing.JLabel lblQuienSaca;
    private javax.swing.JLabel lblResultadosActualJugador1;
    private javax.swing.JLabel lblResultadosActualJugador2;
    private javax.swing.JLabel lblResultadosJugador1;
    private javax.swing.JLabel lblResultadosJugador2;
    private javax.swing.JRadioButton radioMejorDe3;
    private javax.swing.JRadioButton radioMejorDe5;
    private javax.swing.JTextArea txtAreaDesarrollo;
    private javax.swing.JTextField txtNombreJugador1;
    private javax.swing.JTextField txtNombreJugador2;
    private javax.swing.JTextField txtNombreTorneo;
    private javax.swing.JTextField txtProbGanarJugador1;
    private javax.swing.JTextField txtProbGanarJugador2;
    private javax.swing.JTextField txtResultado11;
    private javax.swing.JTextField txtResultado12;
    private javax.swing.JTextField txtResultado13;
    private javax.swing.JTextField txtResultado14;
    private javax.swing.JTextField txtResultado15;
    private javax.swing.JTextField txtResultado21;
    private javax.swing.JTextField txtResultado22;
    private javax.swing.JTextField txtResultado23;
    private javax.swing.JTextField txtResultado24;
    private javax.swing.JTextField txtResultado25;
    private javax.swing.JTextField txtResultadoActual1;
    private javax.swing.JTextField txtResultadoActual2;
    // End of variables declaration//GEN-END:variables
}
