package me.sunny.demo.rp.reactor.simple;

/*
 * Reactor 实例解析 (https://www.infoq.cn/article/reactor-by-example)
 */

import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class ReactorSnippets {
    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    /*
     * Observable 的创建跟在 RxJava 里有点类似，在 Reactor 里可以使用 just(T...)
     * 和 fromIterator(Iterable<T>) 工厂方法来创建。
     * just 方法会把 List 作为一个整体触发，而 fromIterable 会逐个触发 List 里的每个元素
     */
    @Test
    public void simpleCreation() {
        Flux<String> fewWords = Flux.just("Hello", "World");
        Flux<String> manyWords = Flux.fromIterable(words);

        fewWords.subscribe(System.out::println);
        System.out.println();
        manyWords.subscribe(System.out::println);
    }


    @Test
    public void findingMissingLetter() {
        Flux<String> manyLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        manyLetters.subscribe(System.out::println);
    }

    // 可以使用 concat/concatWith 和一个 Mono 来手动往字母 Flux 里添加“s”
    @Test
    public void restoringMissingLetter() {
        Mono<String> missing = Mono.just("s");
        Flux<String> allLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .concatWith(missing)
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        allLetters.subscribe(System.out::println);
    }

    /**
      * 这个单元测试会打印出“Hello”，但无法打印出“world”，因为程序会过早地退出。
      */
    @Test
    public void shortCircuit() {
        Flux<String> helloPauseWorld =
                Mono.just("Hello")
                        .concatWith(Mono.just("world")
                                .delaySubscriptionMillis(500));

        helloPauseWorld.subscribe(System.out::println);
    }
}
