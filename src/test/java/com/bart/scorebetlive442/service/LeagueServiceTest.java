package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.entity.LeagueEntity;
import com.bart.scorebetlive442.mapper.LeagueMapper;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.repository.LeagueRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class LeagueServiceTest {

    @Mock
    private LeagueRepository leagueRepository;

    @Mock
    private LeagueMapper leagueMapper;

    @Mock
    private Validator validator;

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

    @Test
    void giveValidLeague_whenCreateLeague_thenReturnCreatedLeague() {
        League givenLeague = new League();
        givenLeague.setName("Premier League");
        givenLeague.setCountry("England");

        LeagueEntity givenLeagueEntity = new LeagueEntity();
        givenLeagueEntity.setName("Premier League");
        givenLeagueEntity.setCountry("England");

        LeagueEntity savedLeagueEntity = new LeagueEntity();
        savedLeagueEntity.setId(1L);
        savedLeagueEntity.setName("Premier League");
        savedLeagueEntity.setCountry("England");

        League createdLeague = new League();
        createdLeague.setId(1L);
        createdLeague.setName("Premier League");
        createdLeague.setCountry("England");

        BDDMockito.given(leagueMapper.toLeagueEntity(givenLeague)).willReturn(givenLeagueEntity);
        BDDMockito.given(leagueRepository.save(givenLeagueEntity)).willReturn(savedLeagueEntity);
        BDDMockito.given(leagueMapper.toLeagueModel(savedLeagueEntity)).willReturn(createdLeague);

        League result = leagueService.createLeague(givenLeague);

        Assertions.assertThat(result)
                .isNotNull()
                .satisfies(league -> {
                    Assertions.assertThat(league.getId()).isEqualTo(1L);
                    Assertions.assertThat(league.getName()).isEqualTo("Premier League");
                    Assertions.assertThat(league.getCountry()).isEqualTo("England");
                });

        BDDMockito.verify(leagueMapper).toLeagueEntity(givenLeague);
        BDDMockito.verify(leagueRepository).save(givenLeagueEntity);
        BDDMockito.verify(leagueMapper).toLeagueModel(savedLeagueEntity);
        BDDMockito.verifyNoMoreInteractions(leagueRepository, leagueMapper);

    }

    @Test
    void givenInvalidLeague_whenCreateLeague_thenThrowRuntimeException() {
        League givenLeague = new League();
        givenLeague.setName(" ");
        givenLeague.setCountry(" ");

        Set<ConstraintViolation<League>> violations = new HashSet<>();
        violations.add(mock(ConstraintViolation.class));
        BDDMockito.given(validator.validate(givenLeague, League.Group.Create.class, Default.class)).willReturn(violations);

        Assertions.assertThatThrownBy(() -> leagueService.createLeague(givenLeague))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Validation failed");

        BDDMockito.verify(validator).validate(givenLeague, League.Group.Create.class, Default.class);
        BDDMockito.verifyNoMoreInteractions(validator);
    }
}
