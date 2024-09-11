package spring.HelloSpring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {
    Sort sort;

    @BeforeEach
    void beforeEachTest() {
        sort = new Sort();
    }

    @Test
    void sort() {
        // 실행
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        // 작업
        Assertions.assertThat(list).isEqualTo(Arrays.asList("b", "aa"));
    }

    @Test
    void sort3Items() {
        // 실행
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        // 작업
        Assertions.assertThat(list).isEqualTo(Arrays.asList("b", "aa", "ccc"));
    }

    @Test
    void sortAlreadySorted() {
        // 준비
        Sort sort = new Sort();

        // 실행
        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

        // 작업
        Assertions.assertThat(list).isEqualTo(Arrays.asList("b", "aa", "ccc"));
    }
}
