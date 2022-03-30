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
public class Set {
    
    private int puntaje1 = 0;
    private int puntaje2 = 0;
    private int puntajeTie1 = 0;
    private int puntajeTie2 = 0;
    private int quienSaca;
    private Game gameActual;
    private Game gameAnterior = null;
    private Jugador jugador1;
    private Jugador jugador2;
    private int numeroDeSet;
    private int numeroDeGameActual = 0;
    private boolean estaEnTie = false;
    private int reglamento = 0;
    private boolean ultimoSet = false;
    // 0 Open de Australia: Supertiebreak (a 10 puntos) cuando se llega al 6-6
    // 1 Roland Garros: Se mantiene sin tiebreak; diferencia de dos juegos.
    // 2 Wimbledon: Tiebreak cuando se llega al 12-12
    // 3 US Open: Tiebreak en el 6-6.
    

    
    
    public void empezarNuevoGame()
    {
        
        numeroDeGameActual++;

            if(quienSaca == 2)
            {
                gameActual = new Game(jugador2, jugador1, jugador2);
                quienSaca = 1;

            }
            else
            {
                gameActual = new Game(jugador1, jugador1, jugador2);
                quienSaca = 2;

            }

    }
    
    
    
    
    public void jugarPunto() {
            
            gameActual.jugarPunto();
            
            
            if(gameActual.ganaJugador1())
            {
                puntaje1++;
            }
            if(gameActual.ganaJugador2())
            {
                puntaje2++;
            }
            
            /*if (gameActual.ganaJugador1() && estaEnTie == false) {
                puntaje1++;
            } else {
                if (gameActual.ganaJugador1() && estaEnTie == true) {
                    puntajeTie1++;
                } else {
                    if (gameActual.ganaJugador2() && estaEnTie == false) {
                        puntaje2++;
                    } else {
                        if (gameActual.ganaJugador2() && estaEnTie == true) {
                            puntajeTie2++;
                        }
                    }
                }
            }*/
        
    }
    
    public void jugarTie()
    {
        gameActual.jugarPuntoTie();
        if(gameActual.ganaJugador1())
        {
            puntajeTie1++;
        }
        if(gameActual.ganaJugador2())
        {
            puntajeTie2++;
        }
    }
    
    public int obtenerGanadorTie()
    {
        
        int aux = 0;
        // 0 Open de Australia: Supertiebreak (a 10 puntos) cuando se llega al 6-6
        if (ultimoSet == true && reglamento == 0) {
            if (puntajeTie1 >= 10 && puntajeTie1 - puntajeTie2 >= 2) {
                aux = 1;
            } else {
                if (puntajeTie2 >= 10 && puntajeTie2 - puntajeTie1 >= 2) {
                    aux = 2;
                }
            }
        }
        // 1 Roland Garros: Se mantiene sin tiebreak; diferencia de dos juegos.
        // 2 Wimbledon: Tiebreak cuando se llega al 12-12
        // 3 US Open: Tiebreak en el 6-6.
        else {
            if (puntajeTie1 >= 7 && puntajeTie1 - puntajeTie2 >= 2) {
                aux = 1;
            } else {
                if (puntajeTie2 >= 7 && puntajeTie2 - puntajeTie1 >= 2) {
                    aux = 2;
                }
            }

        }

        
        return aux;
    }
    
    public void finalizarTie(int j)
    {
        if(j == 1)
        {puntaje1++;}
        if(j == 2)
        {puntaje2++;}
    }
    
    public int obtenerGanador()
    {
        
        // 0 Open de Australia: Supertiebreak (a 10 puntos) cuando se llega al 6-6
        // 1 Roland Garros: Se mantiene sin tiebreak; diferencia de dos juegos.
        // 2 Wimbledon: Tiebreak cuando se llega al 12-12
        // 3 US Open: Tiebreak en el 6-6.
        
        int aux = 0;
        
        if (ultimoSet == true) 
        {

                if (puntaje1 >= 6 && puntaje1 - puntaje2 >= 2) {
                    aux = 1;
                } else {
                    if (puntaje2 >= 6 && puntaje2 - puntaje1 >= 2) {
                        aux = 2;
                    }
                    // 1 Roland Garros: Se mantiene sin tiebreak; diferencia de dos juegos.
                    else
                    {
                        // 2 Wimbledon: Tiebreak cuando se llega al 12-12
                        if(reglamento == 2)
                        {
                            if (puntaje1 == puntaje2 && puntaje1 == 12) {
                                estaEnTie = true;
                            }
                        }
                        // 0 Open de Australia: Supertiebreak (a 10 puntos) cuando se llega al 6-6
                        //3 US OPEN TIEBREAK EN EL 6-6
                        if(reglamento == 0 || reglamento == 3)
                        {
                            if (puntaje1 == puntaje2 && puntaje1 == 6) {
                                estaEnTie = true;
                            }
                        }
                    }
                }
            }

        else {
            if (puntaje1 >= 6 && puntaje1 - puntaje2 >= 2) {
                aux = 1;
            } else {
                if (puntaje2 >= 6 && puntaje2 - puntaje1 >= 2) {
                    aux = 2;
                } else {
                    if (puntaje1 == puntaje2 && puntaje1 == 6) {
                        estaEnTie = true;
                    }
                }
            }
        }

        
        
        
        
        
        return aux;
    }
    
    

    public Set(Jugador jugador1, Jugador jugador2, int numeroDeSet, int quienSaca, int reglamento) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.numeroDeSet = numeroDeSet;
        this.quienSaca = quienSaca;
        this.reglamento = reglamento;
    }

   
    public int getNumeroDeSet() {
        return numeroDeSet;
    }

    public void setNumeroDeSet(int numeroDeSet) {
        this.numeroDeSet = numeroDeSet;
    }
    
    public int getPuntaje1() {
        return puntaje1;
    }

    public void setPuntaje1(int puntaje1) {
        this.puntaje1 = puntaje1;
    }

    public int getPuntaje2() {
        return puntaje2;
    }

    public void setPuntaje2(int puntaje2) {
        this.puntaje2 = puntaje2;
    }
    
    public int getPuntajeJugador(int j)
    {
        int puntaje = 0;
        if(j == 1)
        {
            puntaje = puntaje1;
        }
        else
        {
            if(j==2)
            {
                puntaje = puntaje2;
            }
        }
        return puntaje;
    }
    


    public int getPuntajeTie1() {
        return puntajeTie1;
    }

    public void setPuntajeTie1(int puntajeTie1) {
        this.puntajeTie1 = puntajeTie1;
    }

    public int getPuntajeTie2() {
        return puntajeTie2;
    }

    public void setPuntajeTie2(int puntajeTie2) {
        this.puntajeTie2 = puntajeTie2;
    }

    public Game getGameActual() {
        return gameActual;
    }

    public void setGameActual(Game gameActual) {
        this.gameActual = gameActual;
    }

    public Game getGameAnterior() {
        return gameAnterior;
    }

    public void setGameAnterior(Game gameAnterior) {
        this.gameAnterior = gameAnterior;
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

    public int getNumeroDeGameActual() {
        return numeroDeGameActual;
    }

    public void setNumeroDeGameActual(int numeroDeGameActual) {
        this.numeroDeGameActual = numeroDeGameActual;
    }

    public int getQuienSaca() {
        return quienSaca;
    }

    public void setQuienSaca(int quienSaca) {
        this.quienSaca = quienSaca;
    }

    public boolean isEstaEnTie() {
        return estaEnTie;
    }

    public void setEstaEnTie(boolean estaEnTie) {
        this.estaEnTie = estaEnTie;
    }

    public int getReglamento() {
        return reglamento;
    }

    public void setReglamento(int reglamento) {
        this.reglamento = reglamento;
    }

    public boolean isUltimoSet() {
        return ultimoSet;
    }

    public void setUltimoSet(boolean utimoSet) {
        this.ultimoSet = utimoSet;
    }
    
    
    
    
    
    
    
    
    
    
    
}
