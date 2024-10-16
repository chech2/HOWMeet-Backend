package org.chzzk.howmeet.domain.temporary.guest.repository;

import org.chzzk.howmeet.domain.common.model.Nickname;
import org.springframework.data.repository.query.Param;

public interface GuestQueryDSL {
    boolean existsByGuestId(@Param("guestId") final Long guestId);
    boolean existsByGuestScheduleIdAndNickname(@Param("guestScheduleId") final Long guestScheduleId, @Param("nickname") final Nickname nickname);
}
