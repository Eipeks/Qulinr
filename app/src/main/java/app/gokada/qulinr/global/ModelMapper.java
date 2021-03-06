package app.gokada.qulinr.global;

import app.gokada.qulinr.app_core.api.models.FullTimeTableResponse;
import app.gokada.qulinr.app_core.api.models.TimeTableResponse;
import app.gokada.qulinr.app_core.store.realmmodel.RealmFoodType;
import app.gokada.qulinr.app_core.store.realmmodel.RealmFullTimeTable;
import app.gokada.qulinr.app_core.store.realmmodel.RealmTimeTable;
import app.gokada.qulinr.app_core.store.realmmodel.RealmToken;
import app.gokada.qulinr.app_core.store.realmmodel.RealmWorkId;

/**
 * Created by knightbenax on 3/8/18.
 */

public class ModelMapper {

    public RealmToken map(String rideId){
        RealmToken ride = new RealmToken();
        ride.setToken(rideId);

        return ride;
    }

    public String map(RealmToken ride){
        return ride.getToken();
    }

    public RealmWorkId mapIdToRealmWorkId(String realmId){
        RealmWorkId workId = new RealmWorkId();
        workId.setWorkId(realmId);

        return workId;
    }

    public String mapRealmWorkIdToString(RealmWorkId workId){
        return workId.getWorkId();
    }

    public RealmFoodType mapStringToRealmFoodType(String value){
        RealmFoodType foodType = new RealmFoodType();
        foodType.setValue(value);

        return foodType;
    }

    public String mapRealmFoodTypeToString(RealmFoodType foodType){
        return foodType.getValue();
    }

    public TimeTableResponse mapRealmTimetableToTimetableResponse(RealmTimeTable timeTable){
        TimeTableResponse response = new TimeTableResponse();
        response.setBreakfast(timeTable.getBreakfast());
        response.setLunch(timeTable.getLunch());
        response.setDinner(timeTable.getDinner());

        return response;
    }

    public RealmTimeTable mapTimeTableResponseToRealmTimeTable(TimeTableResponse response){
        RealmTimeTable timeTable = new RealmTimeTable();
        timeTable.setBreakfast(response.getBreakfast());
        timeTable.setLunch(response.getLunch());
        timeTable.setDinner(response.getDinner());

        return timeTable;
    }

    public FullTimeTableResponse mapRealmFullTimeTableToFullTimeTableResponse(RealmFullTimeTable fullTimeTable){
        FullTimeTableResponse timeTable = new FullTimeTableResponse();
        timeTable.setSunday(mapRealmTimetableToTimetableResponse(fullTimeTable.getSunday()));
        timeTable.setMonday(mapRealmTimetableToTimetableResponse(fullTimeTable.getMonday()));
        timeTable.setTuesday(mapRealmTimetableToTimetableResponse(fullTimeTable.getTuesday()));
        timeTable.setWednesday(mapRealmTimetableToTimetableResponse(fullTimeTable.getWednesday()));
        timeTable.setThursday(mapRealmTimetableToTimetableResponse(fullTimeTable.getThursday()));
        timeTable.setFriday(mapRealmTimetableToTimetableResponse(fullTimeTable.getFriday()));
        timeTable.setSaturday(mapRealmTimetableToTimetableResponse(fullTimeTable.getSaturday()));

        return timeTable;
    }

    public RealmFullTimeTable mapFullTimeTableResponseToRealmFullTimeTable(FullTimeTableResponse fullTimeTableResponse){
        RealmFullTimeTable timeTable = new RealmFullTimeTable();
        timeTable.setSunday(mapTimeTableResponseToRealmTimeTable(fullTimeTableResponse.getSunday()));
        timeTable.setMonday(mapTimeTableResponseToRealmTimeTable(fullTimeTableResponse.getMonday()));
        timeTable.setTuesday(mapTimeTableResponseToRealmTimeTable(fullTimeTableResponse.getTuesday()));
        timeTable.setWednesday(mapTimeTableResponseToRealmTimeTable(fullTimeTableResponse.getWednesday()));
        timeTable.setThursday(mapTimeTableResponseToRealmTimeTable(fullTimeTableResponse.getThursday()));
        timeTable.setFriday(mapTimeTableResponseToRealmTimeTable(fullTimeTableResponse.getFriday()));
        timeTable.setSaturday(mapTimeTableResponseToRealmTimeTable(fullTimeTableResponse.getSaturday()));

        return timeTable;
    }

}
