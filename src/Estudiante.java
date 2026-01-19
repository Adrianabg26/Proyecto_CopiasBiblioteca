import java.util.Random;

public class Estudiante implements Runnable {
    private final int id;
    private final CentroCopias centro;
    private int contadorCopias = 0;
    private final Random random = new Random();

    public Estudiante(int id, CentroCopias centro) {
        this.id = id;
        this.centro = centro;
    }

    @Override
    public void run() {
        try {
            // Cada estudiante repite el ciclo
            while (true) {
                // 1. Estudia
                System.out.println("Estudiante " + id + " está estudiando");
                Thread.sleep(random.nextInt(2000) + 1000);

                // 2. Va al centro de copias y solicita una máquina
                centro.solicitarMaquina(id);

                // 3. Hace las copias
                Thread.sleep(random.nextInt(1000) + 500);

                // 4. Libera la máquina
                centro.liberarMaquina(id);
                contadorCopias++;
            }
        } catch (InterruptedException e) {
            System.out.println("Estudiante " + id + " ha sido avisado para finalizar.");
        }
    }

    public int getContadorCopias() {
        return contadorCopias;
    }

    public int getId() {
        return id;
    }
}
