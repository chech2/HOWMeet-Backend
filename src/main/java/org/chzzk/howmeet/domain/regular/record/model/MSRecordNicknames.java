package org.chzzk.howmeet.domain.regular.record.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NoArgsConstructor;
import org.chzzk.howmeet.domain.common.model.Nickname;
import org.chzzk.howmeet.domain.common.model.Nicknames;
import org.chzzk.howmeet.domain.regular.record.entity.MemberScheduleRecord;

@NoArgsConstructor
public class MSRecordNicknames extends Nicknames {

    public static MSRecordNicknames create() {
        return new MSRecordNicknames();
    }

    public static Nicknames distinctNicknames(final List<MemberScheduleRecord> msRecords,
            final Map<Long, Nickname> nicknamesById) {
        Nicknames nicknames = create();
        Set<Nickname> nickNameSet = new HashSet<>();

        Nickname nickname;
        for (MemberScheduleRecord msRecord : msRecords) {
            nickname = nicknamesById.get(msRecord.getMemberId());
            nickNameSet.add(nickname);
        }
        nicknames.addFromNicknameSet(nickNameSet);

        return nicknames;
    }
}
