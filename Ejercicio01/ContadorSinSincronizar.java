public class ContadorSinSincronizar {
    private int contador = 0;

    public void incrementar() {
        // Esta operación NO es atómica
        contador++;
    }

    public static void main(String[] args) throws InterruptedException {
        ContadorSinSincronizar recurso = new ContadorSinSincronizar();
        int numHilos = 5;
        int incrementosPorHilo = 1000;
        Thread[] hilos = new Thread[numHilos];

        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < incrementosPorHilo; j++) {
                    recurso.incrementar();
                }
            });
            hilos[i].start();
        }

        // Esperar a que todos terminen
        for (Thread h : hilos) h.join();

        System.out.println("Resultado final esperado: 5000");
        System.out.println("Resultado final obtenido: " + recurso.contador);
    }
}
