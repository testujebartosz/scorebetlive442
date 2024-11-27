package com.bart.scorebetlive442.service;


import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.model.json.Dog;
import com.bart.scorebetlive442.repository.LeagueRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

import java.util.concurrent.atomic.AtomicInteger;

//@ExtendWith(MockitoExtension.class)
class LeagueServiceTest2 {

//    @Mock
//    League l1;

    @Test
    public void a() throws InterruptedException {
        League l1 = Mockito.mock(League.class);
//        Mockito.when(l1.hi()).thenReturn("a1", "a2", "a3");
//        Mockito.when(l1.hi()).thenReturn("b1");
//        Mockito.when(l1.hi()).thenCallRealMethod();
//        Mockito.when(l1.hi()).thenThrow(RuntimeException.class);
//        Mockito.when(l1.hi()).thenReturn(String.valueOf(System.currentTimeMillis()));
        AtomicInteger cnt = new AtomicInteger();


        Mockito.when(l1.hi()).thenAnswer(invocation -> {
                if (cnt.getAndIncrement() == 0) {
                    return "a";
                }

                throw new RuntimeException("aa");
            });
//        l.setId(1L);
//        l.setId(1L);

        System.out.println(l1.hi());
        Thread.sleep(500);
        System.out.println(l1.hi());
        Thread.sleep(500);
        System.out.println(l1.hi());
        Thread.sleep(2500);
        System.out.println(l1.hi());

//        Mockito.when(l1.getId()).thenReturn(1L);
//
//        Assertions.assertThat(l1.getId()).isEqualTo(1L);


    }

    @Test
    public void a2() {
        League l1 = Mockito.mock(League.class);

        Mockito.when(l1.upperCase(Mockito.any(), Mockito.any())).thenAnswer(AdditionalAnswers.returnsFirstArg());
//        Mockito.when(l1.upperCase(Mockito.any(), Mockito.any())).thenAnswer(invocation -> {
//            var argument = invocation.getArgument(0);
//            var argument2 = invocation.getArgument(1);
//
//
//            System.out.println("Podałeś " + argument + argument2);
//            return "ABC";
//        });

        System.out.println(l1.upperCase("aaaa", "bbbb"));
    }

    @Test
    public void a3() {

        Dog orig = new Dog("Waclaw");
        System.out.println(orig.getName());

//        Dog d1 = Mockito.spy(Dog.class);
        Dog d1 = Mockito.spy(orig);
        Mockito.when(d1.getName()).thenReturn("Pluto");
        System.out.println(d1.getName());


    }

    @Test
    public void a4() {
        LeagueRepository lr = Mockito.mock(LeagueRepository.class);
        LeagueMapper lm = Mockito.mock(LeagueMapper.class);
        LeagueService orig = new LeagueService(lr, lm, null, null, null);

//        Dog d1 = Mockito.spy(Dog.class);
        LeagueService d1 = Mockito.spy(orig);
        Mockito.when(lr.existsById(Mockito.any())).thenReturn(false);
//        System.out.println(d1.hi());

        Assertions.assertThat(d1.deleteLeagueById(1L)).isFalse();
    }
}
