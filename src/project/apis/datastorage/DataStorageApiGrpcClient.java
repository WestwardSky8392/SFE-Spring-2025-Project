package project.apis.datastorage;

import datastorage.Datastorage.*;
import datastorage.DataStorageAPIGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class DataStorageApiGrpcClient implements DataStorageAPI {
    private final DataStorageAPIGrpc.DataStorageAPIBlockingStub stub;

    public DataStorageApiGrpcClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        stub = DataStorageAPIGrpc.newBlockingStub(channel);
    }

    @Override
    public int[] readData(String key) throws Exception {
        ReadDataRequest req = ReadDataRequest.newBuilder().setKey(key).build();
        ReadDataResponse resp = stub.readData(req);
        byte[] bytes = resp.getData().toByteArray();
        int[] result = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = bytes[i];
        }
        return result;
    }

    @Override
    public void writeData(String key, int[] data) throws Exception {
        byte[] bytes = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            bytes[i] = (byte) data[i];
        }
        WriteDataRequest req = WriteDataRequest.newBuilder()
            .setKey(key)
            .setData(com.google.protobuf.ByteString.copyFrom(bytes))
            .build();
        stub.writeData(req);
    }
}
