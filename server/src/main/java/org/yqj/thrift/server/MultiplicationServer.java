package org.yqj.thrift.server;

import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.yqj.thrift.api.MultiplicationService;

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
            new Thread(()-> noblockServerTransport(processor)).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    /**
     * nio 多线程执行方式
     * @param processor
     */
    public static void nonblockThreadTransport(MultiplicationService.Processor processor){
        try {
            TNonblockingServerSocket transport = new TNonblockingServerSocket(9090);
            TThreadedSelectorServer tNonblockingServer = new TThreadedSelectorServer(new TThreadedSelectorServer.Args(transport).processor(processor));
            System.out.println("Starting the simple server...");
            tNonblockingServer.serve();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * nio 非阻塞方式, 单线程执行方式
     * @param processor
     */
    public static void noblockServerTransport(MultiplicationService.Processor processor){
        try {
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(9090);
            TNonblockingServer tNonblockingServer = new TNonblockingServer(new TNonblockingServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            tNonblockingServer.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TServer 多线程执行方式
     * @param processor
     */
    public static void multipleThreadPoolServer(MultiplicationService.Processor processor){
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        }catch (Exception e){
            e.printStackTrace();
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
