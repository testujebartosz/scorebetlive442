package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.entity.BetEntity;
import com.bart.scorebetlive442.model.Bet;
import com.bart.scorebetlive442.model.json.BetCreateJson;
import com.bart.scorebetlive442.model.json.BetResponseJson;
import org.mapstruct.Mapper;

@Mapper
public interface BetMapper {

    Bet convertJsonToBet(BetCreateJson betCreateJson);

    BetResponseJson convertBetToJson(Bet bet);

    Bet toBetModel(BetEntity betEntity);

    BetEntity toBetEntity(Bet bet);
}
