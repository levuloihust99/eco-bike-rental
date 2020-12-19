package vn.hust.kstn.tkxdpm;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Class chính, chứa hàm main khởi tạo chương trình .
 */
@SpringBootApplication
@RequiredArgsConstructor
public class AppStarter {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppStarter.class);
        ApplicationContext context =  app.run(args);
    }
}
