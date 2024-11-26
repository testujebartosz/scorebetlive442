package com.bart.scorebetlive442.service;


import com.bart.scorebetlive442.entity.LeagueEntity;
import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.repository.LeagueRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LeagueServiceTest {

    @Mock
    private LeagueRepository leagueRepository;
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
        LeagueEntity mockLeagueEntity = new LeagueEntity();
        mockLeagueEntity.setId(1L);
        mockLeagueEntity.setName("Premier League");

        League mockLeague = new League();
        mockLeague.setId(1L);
        mockLeague.setName("Premier League");

        BDDMockito.given(leagueRepository.getByIdEager(1L)).willReturn(Optional.of(mockLeagueEntity));
        BDDMockito.given(leagueMapper.toLeagueModel(mockLeagueEntity)).willReturn(mockLeague);

        League result = leagueService.getLeagueById(1L);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(1L);
        Assertions.assertThat(result.getName()).isEqualTo("Premier League");
        BDDMockito.verify(leagueRepository).getByIdEager(1L);
        BDDMockito.verify(leagueMapper).toLeagueModel(mockLeagueEntity);
        BDDMockito.verifyNoMoreInteractions(leagueRepository, leagueMapper);
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
