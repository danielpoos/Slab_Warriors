package slab_warriors.api;

import android.os.AsyncTask;
import android.util.Log;
import slab_warriors.data.User;
import com.google.gson.Gson;
import java.io.IOException;

public class RequestTask extends AsyncTask<Void, Void, Response> {
    private String requestType;
    private String requestParams;
    private String url;
    public Response response;
    public Runnable finalTask;
    public RequestTask(String url, String requestType) {
        this.requestType = requestType;
        this.url = url;
    }
    public RequestTask(String url, String requestType, String requestParams) {
        this.requestType = requestType;
        this.requestParams = requestParams;
        this.url = url;
    }
    public Runnable getFinalTask() {
        return finalTask;
    }
    public void setFinalTask(Runnable finalTask) {
        this.finalTask = finalTask;
    }
    public RequestTask(){
        this.finalTask = null;
    }
    @Override protected Response doInBackground(Void... voids) {
        Response response = null;
        try {
            switch(requestType){
                case "get":
                    response = RequestHandler.get(url); break;
                case "post":
                    response = RequestHandler.post(url,requestParams); break;
                case "put":
                    response = RequestHandler.put(url,requestParams); break;
                case "delete":
                    response = RequestHandler.delete(url+"/"+requestParams); break;
            }
            //TODO database address
        } catch (IOException e) {
            Log.d("AAAAA", e.toString());
        }
        this.response = response;
        return response;
    }
    @Override protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        finalTask.run();
        Gson converter = new Gson();
        switch(requestType){
            case "get":
                User[] users = converter.fromJson(response.getContent(),User[].class);
                /*userList.clear(); userList.addAll(Arrays.asList(users));*/break;
            case "post":
                User newUser = converter.fromJson(response.getContent(),User.class);
                /*userList.add(newUser);*/break;
            case "put":
                User changeUser = converter.fromJson(response.getContent(),User.class);
                /*userList.add(changeUser);*/break;
            case "delete":
                int id = Integer.parseInt(requestParams);
                /*userList.removeIf(u -> u.getId() == id);*/break;
        }
    }
}
