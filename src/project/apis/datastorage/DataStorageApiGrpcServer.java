package project.apis.datastorage;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import datastorage.Datastorage.*;
import datastorage.DataStorageAPIGrpc;

import java.io.IOException;

public class DataStorageApiGrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090)
            .addService(new DataStorageApiServiceImpl())
            .build();
        System.out.println("DataStorageAPI gRPC server started on port 9090");
        server.start();
        server.awaitTermination();
    }

    // Service implementation that delegates to the file-based implementation
    static class DataStorageApiServiceImpl extends DataStorageAPIGrpc.DataStorageAPIImplBase {
        // Delegate to the file-based implementation
        private final ImplementDataStorage fileDataStorage = new ImplementDataStorage();

        @Override
        public void readData(ReadDataRequest request, StreamObserver<ReadDataResponse> responseObserver) {
            try {
                // Use file-based storage for reading
                String data = fileDataStorage.readFile(request.getKey());
                byte[] byteData = data.getBytes();
                ReadDataResponse response = ReadDataResponse.newBuilder()
                    .setData(com.google.protobuf.ByteString.copyFrom(byteData))
                    .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } catch (Exception e) {
                System.err.println("Error in readData: " + e.getMessage());
                ReadDataResponse response = ReadDataResponse.newBuilder()
                    .setData(com.google.protobuf.ByteString.EMPTY)
                    .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        }

        @Override
        public void writeData(WriteDataRequest request, StreamObserver<WriteDataResponse> responseObserver) {
            try {
                // Use file-based storage for writing
                String data = new String(request.getData().toByteArray());
                fileDataStorage.writeFile(request.getKey(), data);
                WriteDataResponse response = WriteDataResponse.newBuilder()
                    .setSuccess(true)
                    .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } catch (Exception e) {
                System.err.println("Error in writeData: " + e.getMessage());
                WriteDataResponse response = WriteDataResponse.newBuilder()
                    .setSuccess(false)
                    .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        }
    }
}
