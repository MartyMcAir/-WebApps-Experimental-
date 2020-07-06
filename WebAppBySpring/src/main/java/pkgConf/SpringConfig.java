package pkgConf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// https://www.youtube.com/watch?v=stuAmyyootQ&list=PLAma_mKffTOR5o0WNHnY0mTjKxnCgSXrZ&index=13
// Alishev
@Configuration   // объявлем класс - классом Spring конфигурации
@ComponentScan("pkgConf")   // указываем пакет который следует сканить на наличие аннотаций
@PropertySource("classpath:appleJuice.properties")   // импортим фаил с ключ-значениями
public class SpringConfig {
    @Bean   // указываем на создание бина из класса OrangeJuice
    public OrangeJuice orangeJuice() {
        return new OrangeJuice();
    }

    // ручное внедрение DI зависимости без @Autowired
    @Bean   // smoothie - id бина в который производится внедрений объекта-бина OrangeJuice
    public Smoothie smoothie() {
        Smoothie smoothie = new Smoothie();
        // сеттим orangeJuice() метод, что возвращает бин orangeJuice
        smoothie.setOrangeJuice(orangeJuice());
        return smoothie;
    }

    @Bean
    public AppleJuice appleJuice() {
        return new AppleJuice();
    }
}