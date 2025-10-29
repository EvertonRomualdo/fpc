import java.lang.Thread;
import java.lang.Runnable;
import java.util.Random; // Importação necessária para tempo aleatório

public class SimpleConcurrentSolution {

    // 1.2. Tarefa de Verificação de Recursos (Classe Interna Não Anônima com TEMPO ALEATÓRIO)
    private static class ResourceCheckTask implements Runnable {
        
        private static final Random random = new Random();
        private  int i;

        public ResourceCheckTask(int number) {
            i = number;
            
        }
        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();

            System.out.println("[" + currentThread.getName() + "] INÍCIO: Verificação de Recursos.");

                try {
                    // Gera um valor aleatório entre 1000ms (1s) e 3000ms (3s)
                    int sleepTime = 1000 + random.nextInt(2000); 
                    System.out.println("[" + currentThread.getName() + "] Verificando Recurso " + i + " (Duração: " + sleepTime + "ms)...");
                    Thread.sleep(sleepTime); 
                } catch (InterruptedException e) {
                    System.err.println("[" + currentThread.getName() + "] Verificação interrompida.");
                    Thread.currentThread().interrupt();
                    return;
                }
            
            System.out.println("[" + currentThread.getName() + "] FIM: Verificação concluída.");
        }
    }
    // Fim da Classe Interna não anônima

    // 1.1. Tarefa de Inicialização de Logs (Tempo Fixo)
    private static Runnable logSetupTask = new Runnable() {
        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();

            System.out.println("[" + currentThread.getName() + "] INÍCIO: Configuração de Logs...");

            try {
                Thread.sleep(4000); // TEMPO FIXO: 4.0 segundos
            } catch (InterruptedException e) {
                System.err.println("[" + currentThread.getName() + "] Configuração de Logs interrompida.");
                Thread.currentThread().interrupt();
                return;
            }

            System.out.println("[" + currentThread.getName() + "] FIM: Configuração de Logs concluída.");
        }
    };

    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.println("[" + currentThread.getName() + "] --- INÍCIO DO PROGRAMA JAVA ---");

        // Criação das instâncias
        ResourceCheckTask checkInstance1 = new ResourceCheckTask(1);
        ResourceCheckTask checkInstance2 = new ResourceCheckTask(2);
        ResourceCheckTask checkInstance3 = new ResourceCheckTask(3);
        ResourceCheckTask checkInstance4 = new ResourceCheckTask(4);
        ResourceCheckTask checkInstance5 = new ResourceCheckTask(5);

        // Criação e Início das Threads
        Thread tLogs = new Thread(logSetupTask); //, "Setup-Logs");
        Thread tCheck1 = new Thread(checkInstance1); //, "Check-Recursos");
        Thread tCheck2 = new Thread(checkInstance2);
        Thread tCheck3 = new Thread(checkInstance3);
        Thread tCheck4 = new Thread(checkInstance4);
        Thread tCheck5 = new Thread(checkInstance5);

        // Início da execução concorrente
        tLogs.start();
        tCheck1.start();
        tCheck2.start();
        tCheck3.start();
        tCheck4.start();
        tCheck5.start();

        System.out.println("[" + currentThread.getName() + "] Inicialização de tarefas concorrentes solicitada.");

        System.out.println("[" + currentThread.getName() + "] --- FIM DO PROGRAMA JAVA (Main terminou) ---");
    }
}
