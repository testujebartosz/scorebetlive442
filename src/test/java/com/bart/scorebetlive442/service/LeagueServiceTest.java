package com.bart.scorebetlive442.service;


import com.bart.scorebetlive442.repository.LeagueRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LeagueServiceTest {

    @Mock
    private LeagueRepository leagueRepository;

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
}