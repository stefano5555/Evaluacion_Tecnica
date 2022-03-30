/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partido_tenis;

/**
 *
 * @author Usuario
 */
public class Game {
    
    private String puntosJugador1;
    private int puntosJugador1Int;
    private int puntosJugador2Int;
    private String puntosJugador2;
    private Jugador quienSaca;
    private Jugador quienGana = null;
    private Jugador jugador1;
    private Jugador jugador2;
    private boolean finalizado = false;
    private Jugador quienGanaPunto;

    public Game(Jugador quienSaca, Jugador jugador1, Jugador jugador2) {
        
        if(quienSaca == jugador1)
        {
            this.quienSaca = jugador1;
        }
        else
        {
            this.quienSaca = jugador2;
        }
        quienGana = null;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    
    public Game() {
    }

    public void jugarPunto()
    {
                       
        // sacamos un numero random para determinar quien gana el punto.
        // lo multiplicamos por 100 para tener un numero entre 0 y 100.
        double rndVictoria = Math.random() * 100;
        if(rndVictoria >= 0 && rndVictoria <= jugador1.getProbabilidad())
        {
            puntosJugador1Int++;
            quienGanaPunto = jugador1;
        }
        else
        {
            puntosJugador2Int++;
            quienGanaPunto = jugador2;
        }
        calcularPuntos();

               
        
    }
    
    public void jugarPuntoTie()
    {
        double rndVictoria = Math.random() * 100;
        if(rndVictoria >= 0 && rndVictoria <= jugador1.getProbabilidad())
        {
            puntosJugador1Int++;
            quienGana = jugador1;
            quienGanaPunto = jugador1;
        }
        else
        {
            puntosJugador2Int++;
            quienGana = jugador2;
            quienGanaPunto = jugador2;
        }
        finalizado = true;
        
    }
    
        
    private void calcularPuntos()
    {
        if(puntosJugador1Int == 0){puntosJugador1 = "0";}
        if(puntosJugador1Int == 1){puntosJugador1 = "15";}
        if(puntosJugador1Int == 2){puntosJugador1 = "30";}
        if(puntosJugador1Int == 3){puntosJugador1 = "40";}
        if(puntosJugador1Int >= 4)
        {
             puntosJugador1 = "D" + Integer.toString(puntosJugador1Int - 4);
        }
        
        if(puntosJugador2Int == 0){puntosJugador2 = "0";}
        if(puntosJugador2Int == 1){puntosJugador2 = "15";}
        if(puntosJugador2Int == 2){puntosJugador2 = "30";}
        if(puntosJugador2Int == 3){puntosJugador2 = "40";}
         if(puntosJugador2Int >= 4)
        {
             puntosJugador2 = "D" + Integer.toString(puntosJugador2Int - 4);
        }
        
        if(puntosJugador1Int - puntosJugador2Int >= 2 && puntosJugador1Int >= 4)
        {
            puntosJugador1 = "Game";
            quienGana = jugador1;
            finalizado = true;
        }
        else
        {
            if(puntosJugador2Int - puntosJugador1Int >= 2 && puntosJugador2Int >= 4)
            {
                puntosJugador2 = "Game";
                quienGana = jugador2;
                finalizado = true;
            
            }
        }
  
    }
    
        
    public boolean ganaJugador1()
    {
        if (quienGana == jugador1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean ganaJugador2()
    {
        if (quienGana == jugador2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
public Jugador getQuienPierdePunto()
{
    if (quienGanaPunto == jugador1)
    {
        return jugador2;
    }
    else
    {
        return jugador1;
    }
}
     
    
    public String getPuntosJugador1() {
        return puntosJugador1;
    }

    public void setPuntosJugador1(String puntosJugador1) {
        this.puntosJugador1 = puntosJugador1;
    }

    public String getPuntosJugador2() {
        return puntosJugador2;
    }

    public void setPuntosJugador2(String puntosJugador2) {
        this.puntosJugador2 = puntosJugador2;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public Jugador getQuienSaca() {
        return quienSaca;
    }

    public void setQuienSaca(Jugador quienSaca) {
        this.quienSaca = quienSaca;
    }

    public Jugador getQuienGana() {
        return quienGana;
    }

    public void setQuienGana(Jugador quienGana) {
        this.quienGana = quienGana;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getPuntosJugador1Int() {
        return puntosJugador1Int;
    }

    public void setPuntosJugador1Int(int puntosJugador1Int) {
        this.puntosJugador1Int = puntosJugador1Int;
    }

    public int getPuntosJugador2Int() {
        return puntosJugador2Int;
    }

    public void setPuntosJugador2Int(int puntosJugador2Int) {
        this.puntosJugador2Int = puntosJugador2Int;
    }

    public Jugador getQuienGanaPunto() {
        return quienGanaPunto;
    }

    public void setQuienGanaPunto(Jugador quienGanaPunto) {
        this.quienGanaPunto = quienGanaPunto;
    }
    
    
    
    
    
    
    
}
