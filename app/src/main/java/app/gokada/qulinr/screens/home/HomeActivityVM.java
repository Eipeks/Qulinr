package app.gokada.qulinr.screens.home;

import java.util.Calendar;

import javax.inject.Inject;

import app.gokada.qulinr.app_core.api.QulinrResponse;
import app.gokada.qulinr.app_core.api.models.CreateMenuRequest;
import app.gokada.qulinr.app_core.api.models.NotifySlackRequest;
import app.gokada.qulinr.app_core.api.models.TimeTableResponse;
import app.gokada.qulinr.app_core.store.DataStore;
import app.gokada.qulinr.app_core.view.CoreVM;
import rx.Subscriber;

public class HomeActivityVM extends CoreVM {

    private DataStore dataStore;

    @Inject
    public HomeActivityVM(DataStore dataStore){
        this.dataStore = dataStore;
    }

    public void createMenu(CreateMenuRequest menuRequest, final OnMenuCreatedCallback onMenuCreatedCallback){
        dataStore.createMenu(menuRequest).subscribe(new Subscriber<QulinrResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                onMenuCreatedCallback.onMenuCreated(null, e);
            }

            @Override
            public void onNext(QulinrResponse qulinrResponse) {
                onMenuCreatedCallback.onMenuCreated(qulinrResponse, null);
            }
        });
    }

    public void notifySlack(final OnSlackNotifiedCallback onSlackNotifiedCallback){
        dataStore.notifySlack(buildRequestFromToken()).subscribe(new Subscriber<QulinrResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                onSlackNotifiedCallback.onSlackNotified(null, e);
            }

            @Override
            public void onNext(QulinrResponse response) {
                onSlackNotifiedCallback.onSlackNotified(response, null);
            }
        });
    }

    public void deleteWorkId(){
        dataStore.deleteWorkId();
    }

    public void deleteAll(){
        dataStore.deleteAll();
    }

    public void deleteToken(){
        dataStore.deleteToken();
    }

    public void cacheToken(String token){
        dataStore.cacheToken(token);
    }

    public void cacheWorkId(String workId){
        dataStore.cacheWorkId(workId);
    }

    private NotifySlackRequest buildRequestFromToken(){
        NotifySlackRequest request = new NotifySlackRequest();
        request.setToken(getCachedToken());

        return request;
    }

    public String getCachedToken(){
        return dataStore.getCachedToken();
    }

    public void cacheFoodType(String foodType){
        dataStore.cacheFoodType(foodType);
    }

    public String getCachedFoodType(){
        return dataStore.getCachedFoodType();
    }

    public TimeTableResponse getCachedTimetable(){
        return dataStore.getCachedTimetable();
    }

    public interface OnMenuCreatedCallback {
        void onMenuCreated(QulinrResponse response, Throwable throwable);
    }

    public interface OnSlackNotifiedCallback {
        void onSlackNotified(QulinrResponse response, Throwable throwable);
    }
}
