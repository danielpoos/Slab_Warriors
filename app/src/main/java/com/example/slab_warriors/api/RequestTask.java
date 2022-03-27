package com.example.slab_warriors.api;

import android.os.AsyncTask;
import java.io.IOException;

public class RequestTask extends AsyncTask<Void, Void, Response> {
    private String requestType;
    private String requestParams;
    private String url;
    public Response response;
    private Runnable finalTask;
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
                    response = RequestHandler.get(url);
                    break;
                case "post":
                    response = RequestHandler.post(url,requestParams);
                    break;
                case "put":
                    response = RequestHandler.put(url,requestParams);
                    break;
                case "delete":
                    response = RequestHandler.delete(url+"/"+requestParams);
                    break;
            }
        } catch (IOException e) {e.printStackTrace();   }
        this.response = response;
        return response;
    }
    @Override protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        finalTask.run();
    }
}
