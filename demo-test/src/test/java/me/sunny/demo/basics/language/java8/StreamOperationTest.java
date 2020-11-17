package me.sunny.demo.basics.language.java8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import static com.google.common.primitives.Ints.asList;

public class StreamOperationTest {
  @Test
  public void forEach() {
    // 你不鸟我,我也不鸟你
    List<String> list = Arrays.asList("you", "don't", "bird", "me", ",",
        "I", "don't", "bird", "you");
    // 方式一：JDK1.8之前的循环方式
    for (String item : list) {
      System.out.println(item);
    }
    // 方式二：使用Stream的forEach方法
    // void forEach(Consumer<? super T> action)
    list.stream().forEach(item -> System.out.println(item));
    // 方式三：方式二的简化方式
    // 由于方法引用也属于函数式接口，所以方式二Lambda表达式也可以使用方法引用来代替
    // 此种方式就是方式一、方式二的简写形式
    list.stream().forEach(System.out::println);
  }

  @Test
  public void filter() {
    List<User> users = Arrays.asList(
        new User(1L, "mengday", 28),
        new User(2L, "guoguo", 18),
        new User(3L, "liangliang", 17)
    );

    // Stream<T> filter(Predicate<? super T> predicate);
    users.stream().filter(user -> user.getAge() > 18).forEach(System.out::println);
  }

  @Test
  public void map() {
    List<String> list = Arrays.asList("how", "are", "you", "how", "old", "are", "you", "?");
    // <R> Stream<R> map(Function<? super T, ? extends R> mapper);
    list.stream().map(item -> item.toUpperCase()).forEach(System.out::println);
  }

  @Test
  public void flatMap() {
    List<Integer> a = Arrays.asList(1, 2, 3);
    List<Integer> b = Arrays.asList(4, 5, 6);

    // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
    List<List<Integer>> collect = Stream.of(a, b).collect(Collectors.toList());
    // [[1, 2, 3], [4, 5, 6]]
    System.out.println(collect);

    // 将多个集合中的元素合并成一个集合
    List<Integer> mergeList = Stream.of(a, b).flatMap(list -> list.stream()).collect(Collectors.toList());
    // [1, 2, 3, 4, 5, 6]
    System.out.println(mergeList);

    // 通过Builder模式来构建
    Stream<Object> stream = Stream.builder().add("hello").add("hi").add("byebye").build();
    stream.forEach(System.out::println);

    List<Integer> together = Stream.of(asList(1, 2, 3), asList(4, 5, 6))
        .flatMap(List::stream)
        .collect(Collectors.toList());
    System.out.println(together);
  }

  @Test
  public void sort() {
    List<String> list = Arrays.asList("c", "e", "a", "d", "b");
    // Stream<T> sorted(Comparator<? super T> comparator);
    // int compare(T o1, T o2);
    list.stream().sorted((s1, s2) -> s1.compareTo(s2)).forEach(System.out::println);
  }

  @Test
  public void distinct() {
    // 知之为知之,不知为不知
    Stream<String> stream = Stream.of("know", "is", "know", "noknow", "is", "noknow");
    stream.distinct().forEach(System.out::println); // know is noknow
  }

  @Test
  public void count() {
    Stream<String> stream = Stream.of("know", "is", "know", "noknow", "is", "noknow");
    long count = stream.count();
    System.out.println(count);
  }

  @Test
  public void min() {
    List<String> list = Arrays.asList("1", "2", "3", "4", "5");
    // Optional<T> min(Comparator<? super T> comparator);
    Optional<String> optional = list.stream().min((a, b) -> a.compareTo(b));
    String value = optional.get();
    System.out.println(value);
  }

  @Test
  public void skip() {
    List<String> list = Arrays.asList("a", "b", "c", "d", "e");
    // Stream<T> skip(long n)
    list.stream().skip(2).forEach(System.out::println);  // c、d、e
  }

  @Test
  public void limit() {
    List<String> list = Arrays.asList("a", "b", "c", "d", "e");
    list.stream().skip(2).limit(2).forEach(System.out::println);    // c、d
  }

  @Test
  public void collect() {
    List<String> list = Arrays.asList("a", "b", "c", "d", "e");
    // Stream -> Collection
    List<String> collect = list.stream().collect(Collectors.toList());
    // Stream -> Object[]
    Object[] objects = list.stream().toArray();
  }

  @Test
  public void concat() {
    List<String> list = Arrays.asList("a", "b");
    List<String> list2 = Arrays.asList("c", "d");
    Stream<String> concatStream = Stream.concat(list.stream(), list2.stream());
    concatStream.forEach(System.out::println);
  }

  @Test
  public void match() {
    // 你给我站住
    List<String> list = Arrays.asList("you", "give", "me", "stop");
    // boolean anyMatch(Predicate<? super T> predicate);
    // parallelStream可以并行计算，速度比stream更快
    boolean result = list.parallelStream().anyMatch(item -> item.equals("me"));
    System.out.println(result);

    result = list.parallelStream().noneMatch(item -> item.equals("me"));
    System.out.println(result);

    list = Arrays.asList("he", "give", "me", "some thing");
    result = list.parallelStream().allMatch(item -> item.contains("e"));
    System.out.println(result);
  }

  @Test
  public void reduce() {
    Stream<String> stream = Stream.of("you", "give", "me", "stop");
    // Optional<T> reduce(BinaryOperator<T> accumulator);
    Optional<String> optional = stream.reduce((before, after) -> before + "," + after);
    optional.ifPresent(System.out::println);    // you,give,me,stop

    // BigDecimal求和
    List<BigDecimal> list = Arrays.asList(
        new BigDecimal("11.11"),
        new BigDecimal("22.22"),
        new BigDecimal("33.33")
    );
    // 66.66
    BigDecimal sum = list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    System.out.println(sum);
  }

  @Test
  public void findFirst() {
    Stream<String> stream = Stream.of("you", "give", "me", "stop");
    String value = stream.filter(r -> r.startsWith("m")).findFirst().get();
    System.out.println(value);
  }

  @Test
  public void findAny() {
    // findAny()操作，返回的元素是不确定的，对于同一个列表多次调用findAny()有可能会返回不同的值。
    // 使用findAny()是为了更高效的性能。
    // 如果是数据较少，串行地情况下，一般会返回第一个结果，如果是并行的情况，那就不能确保是第一个。
    System.out.println(IntStream.range(0, 100).parallel().findAny());
  }

  public static class User {
    private Long id;
    private String username;
    private Integer age;

    public User() {}

    public User(Long id, String username, Integer age) {
      this.id = id;
      this.username = username;
      this.age = age;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public Integer getAge() {
      return age;
    }

    public void setAge(Integer age) {
      this.age = age;
    }
  }
}
