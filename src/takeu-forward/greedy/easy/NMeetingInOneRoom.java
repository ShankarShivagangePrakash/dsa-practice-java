package greedy.easy;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

/*
Problem:
    https://takeuforward.org/data-structure/n-meetings-in-one-room/
    https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1
 */
public class NMeetingInOneRoom {

    public static int nMeetingsInOneRoomOptimal(int[] start, int[] end){
        int n = start.length;
        Meeting[] meeting = new Meeting[n];

        for(int i = 0; i < meeting.length; i++){
            // we will follow 1 based numbering for meeting id's so (i+1)
            meeting[i] = new Meeting(start[i], end[i], i+1);
        }

        Arrays.sort(meeting, new MeetingComparator());

        int count = 0;
        int freeTime = 0;
        ArrayList<Integer> meetingOrganisedOrder = new ArrayList<>();

        for(int i = 0; i < meeting.length; i++){
            // if the current meeting start time is greater than `freeTime` which represents when the ongoing meeting will end
            // then we can organise this meeting.
            if(meeting[i].start > freeTime){
                count++;
                freeTime = meeting[i].end;
                meetingOrganisedOrder.add(meeting[i].meetingNo);
            }
        }

        // return count of `meetingOrganisedOrder` list based on problem statement.
        return count;
    }
}

class Meeting {
    int start;
    int end;
    int meetingNo;

    Meeting(int start, int end, int meetingNo){
        this.start = start;
        this.end = end;
        this.meetingNo = meetingNo;
    }
}

/* The comparator we create for Meeting should order based on end time in ascending order
 but if two meetings has same end time, then they should be orderd using meeting Number*/
class MeetingComparator implements Comparator<Meeting> {

    @Override
    public int compare(Meeting meeting1, Meeting meeting2) {
        /* If meeting1 end time is less than meeting2 end time, there is no need to reorder/shuffle two objects
         as they are already sorted, so return -1*/
        if (meeting1.end < meeting2.end)
            return -1;
        // if meeting1 end time is greater than meeting2 end time, we have to shuffle two objects, so return 1 so that they will be shuffled.
        else if (meeting1.end > meeting2.end)
            return 1;
        // if both meeting1 and meeting2 end time are same, then we have to sort based on meeting number.
        else if(meeting1.meetingNo < meeting2.meetingNo)
            return -1;
        // below statement is just to avoid syntax error.
        return 1;
    }
}


