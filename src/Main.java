//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Instanciamos el recurso compartido
        CentroCopias centro = new CentroCopias();

        //Creamos los arrays para los 5 estudiantes
        Estudiante[] tareas = new Estudiante[5];
        Thread[] hilos = new Thread[5];

        // Mensaje inicio actividad
        System.out.println("==========================================");
        System.out.println("   INICIANDO SIMULACIÓN CENTRO COPIAS 🖨️  ");
        System.out.println("   (Duración: 20 segundos)              ");
        System.out.println("==========================================");

        // Lanzamos los hilos
        for (int i = 0; i < 5; i++) {
            tareas[i] = new Estudiante(i, centro);
            hilos[i] = new Thread(tareas[i]);
            hilos[i].start();
        }

        // Ejecución durante 20 segundos
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Finalización
        System.out.println("\nFinalizando tiempo de simulación... ");
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].interrupt();
        }

        // Cierre
        for (Thread h : hilos) {
            try {
                h.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Informe final
        System.out.println("\n--- Registro Estadísticas Finales ---");
        for (Estudiante est : tareas) {
            System.out.println("Estudiante " + est.getId() + " hizo copias " + est.getContadorCopias() + " veces");
        }
    }
}
