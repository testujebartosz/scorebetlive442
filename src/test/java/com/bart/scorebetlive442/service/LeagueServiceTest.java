package com.bart.scorebetlive442.service;


import com.bart.scorebetlive442.entity.LeagueEntity;
import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.repository.LeagueRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LeagueServiceTest {

    @Mock
    private LeagueRepository leagueRepository;

    @Mock
    private LeagueMapper leagueMapper;

    @InjectMocks
    private LeagueService leagueService;

    @Test
    void givenLeagueExists_whenDeleteLeagueById_thenLeagueDeleted() {
        BDDMockito.given(leagueRepository.existsById(ArgumentMatchers.any())).willReturn(true);

        boolean result = leagueService.deleteLeagueById(1L);

        Assertions.assertThat(result).isTrue();
        BDDMockito.verify(leagueRepository).deleteById(1L);
        BDDMockito.verify(leagueRepository).existsById(1L);
        BDDMockito.verifyNoMoreInteractions(leagueRepository);
    }

    @Test
    void giveLeagueDoesNotExist_whenDeleteLeagueById_thenReturnFalse() {
        BDDMockito.given(leagueRepository.existsById(ArgumentMatchers.any())).willReturn(false);

        boolean result = leagueService.deleteLeagueById(2L);

        Assertions.assertThat(result).isFalse();
        BDDMockito.verify(leagueRepository).existsById(2L);
        BDDMockito.verifyNoMoreInteractions(leagueRepository);
    }

    @Test
    void givenLeagueExists_whenGetLeagueById_thenReturnLeague() {
        LeagueEntity givenLeagueEntity = new LeagueEntity();
        givenLeagueEntity.setId(1L);
        givenLeagueEntity.setName("Premier League");

        League givenLeague = new League();
        givenLeague.setId(1L);
        givenLeague.setName("Premier League");

        BDDMockito.given(leagueRepository.getByIdEager(1L)).willReturn(Optional.of(givenLeagueEntity));
        BDDMockito.given(leagueMapper.toLeagueModel(givenLeagueEntity)).willReturn(givenLeague);


        League result = leagueService.getLeagueById(1L);


        Assertions.assertThat(result)
            .isNotNull()
            .satisfies(league -> {
                Assertions.assertThat(league.getId()).isEqualTo(1L);
                Assertions.assertThat(league.getName()).isEqualTo("Premier League");
            });
//        BDDMockito.then(leagueRepository).should(BDDMockito.times(2)).getByIdEager(1L);
//        Mockito.verify(leagueRepository, Mockito.times(2)).getByIdEager(1L);
        BDDMockito.verify(leagueMapper).toLeagueModel(givenLeagueEntity);
        Mockito.verify(leagueRepository).getByIdEager(1L);
        Mockito.verifyNoMoreInteractions(leagueRepository);
    }

    @Test
    void givenLeagueDoesNotExist_whenGetLeagueById_thenThrowException() {
        BDDMockito.given(leagueRepository.getByIdEager(2L)).willReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> leagueService.getLeagueById(2L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("No such league");

        BDDMockito.verify(leagueRepository).getByIdEager(2L);
        BDDMockito.verifyNoMoreInteractions(leagueRepository);
        BDDMockito.verifyNoInteractions(leagueMapper);
    }
}
