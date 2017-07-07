package com.livelearn.ignorance.test.ninegridlayout;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.utils.ResourceUtils;

import java.util.ArrayList;

/**
 * Created on 2017/7/6.
 */

public class JournalModel {

    public static ArrayList<Journal> getJournal(int page) {
        ArrayList<Journal> journals = new ArrayList<>();
        if (page > 4) return journals;
        String[] friendNameArray = ResourceUtils.getStringArray(R.array.Journal_Name);
        String[] friendAvatarUrlArray = ResourceUtils.getStringArray(R.array.Journal_AvatarUrl);
        String[] journalDescriptionArray = ResourceUtils.getStringArray(R.array.Journal_Description);
        String[] pictureArray = ResourceUtils.getStringArray(R.array.Journal_Pictures);
        String[] releaseLocationArray = ResourceUtils.getStringArray(R.array.Journal_Location);
        String[] releaseTimeArray = ResourceUtils.getStringArray(R.array.Journal_Time);
        for (int i = 0; i < friendNameArray.length; i++) {
            Journal journal = new Journal();
            journal.setFriendName(friendNameArray[i]);
            journal.setFriendAvatarUrl(friendAvatarUrlArray[i]);
            journal.setJournalDescription(journalDescriptionArray[i]);
            journal.setPictureList(pictureArray[i]);
            journal.setReleaseLocation(releaseLocationArray[i]);
            journal.setReleaseTime(releaseTimeArray[i]);
            journals.add(journal);
        }
        return journals;
    }
}
