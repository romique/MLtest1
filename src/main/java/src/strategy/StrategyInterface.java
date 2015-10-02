package src.strategy;

/**
 * Created by Romique on 14.09.2015.
 */
public interface StrategyInterface {
    //Инициализируем стратегию, наполняя её тестами
    void init();

    //Стратегия должна уметь принимать решения
    public String decide();

    public String process(ValuesBean valuesBean);

}
