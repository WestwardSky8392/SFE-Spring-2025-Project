package project.apis.networkapi;

import io.grpc.stub.StreamObserver;

import networkapi.Networkapi.AskUserRequest;
import networkapi.Networkapi.WindowResponse;
import networkapi.Networkapi.ApiTaskRequest;
import networkapi.Networkapi.ApiTaskResponse;
import networkapi.NetworkAPIGrpc;

public class NetworkApiServiceImpl extends NetworkAPIGrpc.NetworkAPIImplBase {
    @Override
    public void showWindow(AskUserRequest request, StreamObserver<WindowResponse> responseObserver) {
        String message = "Showing window for inputPath: " + request.getInputPath();
        WindowResponse response = WindowResponse.newBuilder().setMessage(message).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void makeApiCall(ApiTaskRequest request, StreamObserver<ApiTaskResponse> responseObserver) {
        String result = "Task completed: " + request.getTask();
        ApiTaskResponse response = ApiTaskResponse.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}