package org.yqj.thrift.demo;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Created by yaoqijun on 2018/8/16.
 * email: yaoqijunmail@foxmail.com
 */
public class MultiplicationServer {

    public static MultiplicationHandler handler;

    public static MultiplicationService.Processor processor;

    public static void main(String[] args) {
        try {
            handler = new MultiplicationHandler();
            processor = new MultiplicationService.Processor(handler);
            new Thread(()-> simple(processor)).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void simple(MultiplicationService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void secure(MultiplicationService.Processor processor) {
        try {
            TSSLTransportParameters params = new TSSLTransportParameters();
            params.setKeyStore("../../lib/java/test/.keystore", "thrift", null, null);

            TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(9091, 0, null, params);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

            System.out.println("Starting the secure server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
