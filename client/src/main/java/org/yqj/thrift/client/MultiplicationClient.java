package org.yqj.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.yqj.thrift.api.MultiplicationService;

/**
 * Created by yaoqijun on 2018/8/16.
 * email: yaoqijunmail@foxmail.com
 */
public class MultiplicationClient {
    public static void main(String[] args) throws Exception {
        for (int i=0; i<10; i++){
            new Thread(()->singleThread()).start();
//            new Thread(()->nonFrameBlockSingleTest()).start();
        }
    }

    private static void nonFrameBlockSingleTest(){
        try {

            TTransport transport = new TFramedTransport(new TSocket("localhost", 9090));
            TProtocol protocol = new TBinaryProtocol(transport);
            transport.open();
            MultiplicationService.Client client = new MultiplicationService.Client(protocol);

            perform(client);

            transport.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void singleThread() {
        try {
            TTransport transport;

            transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            MultiplicationService.Client client = new MultiplicationService.Client(protocol);

            perform(client);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(MultiplicationService.Client client) throws TException {
        for (int i=0; i<100; i++) {
            int product = client.multiply(3, 5);
            System.out.println("3*5=" + product);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
