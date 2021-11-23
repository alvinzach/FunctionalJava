import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class RunnableExample {
    public static void main(String[] args) {
        //Block Lambda
        new Thread(()->{
            System.out.println("Hello world" + Thread.currentThread().getName());
        }).start();
        // Expression Lambda
        new Thread(()->System.out.println("Hello world" + Thread.currentThread().getName())).start();
        // Runnable
        Runnable r = ()->{
            System.out.println("Hello world" + Thread.currentThread().getName());
        };
        new Thread(r).start();
        // Cast
        new Thread((Runnable)()->System.out.println("Hello world" + Thread.currentThread().getName())).start();
    }

}
