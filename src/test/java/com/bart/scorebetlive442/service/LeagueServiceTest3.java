package com.bart.scorebetlive442.service;


import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.model.json.Dog;
import com.bart.scorebetlive442.repository.LeagueRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
class LeagueServiceTest3 {

    @Mock()
    Dog d1 = new Dog("Waclaw");


    @Test
    @MockitoSettings(strictness = Strictness.WARN)
    public void a3() {
        Mockito.when(d1.getName()).thenReturn("Pluto");
    }

    @Test
    public void a5() {
        Mockito.when(d1.getName()).thenReturn("Pluto");
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
