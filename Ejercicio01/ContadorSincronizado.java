

public class ContadorSincronizado {
    private int contador = 0;

    // 'synchronized' actúa como un candado (lock)
    // Solo un hilo puede ejecutar este método a la vez
    public synchronized void incrementar() {
        contador++;
    }

    public static void main(String[] args) throws InterruptedException {
        ContadorSincronizado recurso = new ContadorSincronizado();
        Thread[] hilos = new Thread[5];

        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    recurso.incrementar();
                }
            });
            hilos[i].start();
        }

        for (Thread h : hilos) h.join();

        System.out.println("Resultado corregido: " + recurso.contador);
    }
}
