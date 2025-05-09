package project.apis.datastorage;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import datastorage.Datastorage.*;
import datastorage.DataStorageAPIGrpc;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class DataStorageApiGrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090)
            .addService(new DataStorageApiServiceImpl())
            .build();
        System.out.println("DataStorageAPI gRPC server started on port 9090");
        server.start();
        server.awaitTermination();
    }

    static class DataStorageApiServiceImpl extends DataStorageAPIGrpc.DataStorageAPIImplBase {
        private final Map<String, byte[]> store = new ConcurrentHashMap<>();

        @Override
        public void readData(ReadDataRequest request, StreamObserver<ReadDataResponse> responseObserver) {
            byte[] data = store.getOrDefault(request.getKey(), new byte[0]);
            ReadDataResponse response = ReadDataResponse.newBuilder().setData(com.google.protobuf.ByteString.copyFrom(data)).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void writeData(WriteDataRequest request, StreamObserver<WriteDataResponse> responseObserver) {
            store.put(request.getKey(), request.getData().toByteArray());
            WriteDataResponse response = WriteDataResponse.newBuilder().setSuccess(true).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
