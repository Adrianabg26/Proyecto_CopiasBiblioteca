public class CentroCopias {
    // Representa las 2 máquinas disponibles
    private boolean[] maquinasLibres = {true, true};

    public synchronized void solicitarMaquina(int idEstudiante) throws InterruptedException {
        // Solicita maquina
        System.out.println("Estudiante " + idEstudiante + " solicita máquina.");

        // Mientras no haya máquinas, el hilo espera
        while (getIndiceLibre() == -1) {
            wait();//
        }
        // Al salir del wait, el estudiante usa una máquina específica
        int indice = getIndiceLibre();
        maquinasLibres[indice] = false;// Se ocupa la máquina
        System.out.println("Estudiante " + idEstudiante + " usa la máquina " + (indice + 1));
    }

    public synchronized void liberarMaquina(int idEstudiante) {
        // Buscamos cuál máquina está ocupada (false) para liberarla (true)
        int liberada = -1;
        for (int i = 0; i < maquinasLibres.length; i++) {
            if (!maquinasLibres[i]) { //Si encontramos una ocupada
                maquinasLibres[i] = true;
                liberada = i;
                break; //Salimos del bucle tras liberar una
            }
        }
        System.out.println("Estudiante " + idEstudiante + " termina y libera máquina " + (liberada + 1));
        notifyAll(); //Notificamos a los estudiantes en espera
    }

    // Método auxiliar para buscar el índice de una máquina libre
    private int getIndiceLibre() {
        for (int i = 0; i < maquinasLibres.length; i++) {
            if (maquinasLibres[i]) // Si la posición i es 'true', significa que la máquina está libre
                return i;
        }
        // Si el bucle termina y no ha entrado en el 'if' anterior, significa que TODAS están en 'false' (ocupadas).
        // Devolvemos -1 como código de "lleno".
        return -1;
    }
}

