package app.gokada.qulinr.app_core.store;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import app.gokada.qulinr.app_core.api.models.FullTimeTableResponse;
import app.gokada.qulinr.app_core.api.models.TimeTableResponse;
import app.gokada.qulinr.app_core.store.localdatabase.RealmManager;
import app.gokada.qulinr.app_core.store.realmmodel.RealmFoodType;
import app.gokada.qulinr.app_core.store.realmmodel.RealmFullTimeTable;
import app.gokada.qulinr.app_core.store.realmmodel.RealmTimeTable;
import app.gokada.qulinr.app_core.store.realmmodel.RealmToken;
import app.gokada.qulinr.app_core.store.realmmodel.RealmWorkId;
import app.gokada.qulinr.global.ModelMapper;
import io.realm.RealmObject;

public class OfflineStore {

    private Context context;
    RealmManager realmManager;
    ModelMapper modelMapper;

    @Inject
    public OfflineStore(RealmManager realmManager, Context context, ModelMapper modelMapper){
        this.realmManager = realmManager;
        this.modelMapper = modelMapper;
        this.context = context;
    }

    public<T extends RealmObject> void cache(T object) {
        realmManager.save(object);
    }

    public<T extends RealmObject> void cacheFromAnotherthread(T object) {
        realmManager.saveAsCopy(object);
    }

    public void deleteTypeOfClass(Class<RealmToken> token) {
        //Log.i("Delete cached user", "Deleted");
        realmManager.deleteAllOf(token);
    }

    public void deleteAll(){
        realmManager.deleteAll();
    }

    public void deleteWorkId(){
        RealmWorkId workId = modelMapper.mapIdToRealmWorkId(getCachedWorkId());
        realmManager.delete(workId);
    }

    public void deleteCountedValue(){
        realmManager.deleteAllOf(RealmFoodType.class);
    }

    public void cacheToken(String token){
        realmManager.deleteAllOf(RealmToken.class);
        RealmToken realmToken = modelMapper.map(token);
        cache(realmToken);
    }

    public String getToken() {
        List<RealmToken> realmToken = realmManager.findByClass(RealmToken.class);

        System.out.println("======= " + realmToken.size());
        if (realmToken.size() > 0) {
            return  modelMapper.map(realmToken.get(0));
        } else
            return null;
    }

    public String getCachedWorkId(){
        List<RealmWorkId> realmWorkIds = realmManager.findByClass(RealmWorkId.class);

        if (realmWorkIds.size() > 0){
            return modelMapper.mapRealmWorkIdToString(realmWorkIds.get(0));
        } else {
            return null;
        }
    }

    public String getCachedFoodType(){
        List<RealmFoodType> realmCounters = realmManager.findByClass(RealmFoodType.class);

        if (realmCounters.size() > 0){
            return modelMapper.mapRealmFoodTypeToString(realmCounters.get(0));
        } else {
            return null;
        }
    }

    public TimeTableResponse getCachedTimetable(){
        List<RealmTimeTable> realmTimeTables = realmManager.findByClass(RealmTimeTable.class);

        if (realmTimeTables.size() > 0){
            return modelMapper.mapRealmTimetableToTimetableResponse(realmTimeTables.get(0));
        }
        return null;
    }

    public FullTimeTableResponse getCachedFullTimetable(){
        List<RealmFullTimeTable> realmFullTimeTables = realmManager.findByClass(RealmFullTimeTable.class);

        if (realmFullTimeTables.size() > 0){
            return modelMapper.mapRealmFullTimeTableToFullTimeTableResponse(realmFullTimeTables.get(0));
        }
        return null;
    }

    public void cacheFoodType(String foodType){
        realmManager.deleteAllOf(RealmFoodType.class);
        RealmFoodType counter = modelMapper.mapStringToRealmFoodType(foodType);
        cache(counter);
    }

    public void cacheWorkId(String workId){
        RealmWorkId realmWorkId = modelMapper.mapIdToRealmWorkId(workId);
        cache(realmWorkId);
    }

    public void cacheTimetable(TimeTableResponse response){
        realmManager.deleteAllOf(RealmTimeTable.class);
        RealmTimeTable table = modelMapper.mapTimeTableResponseToRealmTimeTable(response);
        cache(table);
    }

    public void cacheFullTimetable(FullTimeTableResponse response){
        realmManager.deleteAllOf(RealmFullTimeTable.class);
        RealmFullTimeTable table = modelMapper.mapFullTimeTableResponseToRealmFullTimeTable(response);
        cache(table);
    }

}
